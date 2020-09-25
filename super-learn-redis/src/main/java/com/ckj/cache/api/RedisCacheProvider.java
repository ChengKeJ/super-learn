package com.ckj.cache.api;

import java.util.UUID;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisCommands;

@Slf4j
public class RedisCacheProvider {

    /**
     * 将key 的值设为value ，当且仅当key 不存在，等效于 SETNX。
     */
    public static final String NX         = "NX";
    /**
     * seconds — 以秒为单位设置 key 的过期时间，等效于EXPIRE key seconds
     */
    public static final String EX         = "EX";
    /**
     * 调用set后的返回值
     */
    public static final String OK         = "OK";
    /**
     * 默认锁的有效时间(s)
     */
    public static final int    EXPIRE     = 60;
    /**
     * 默认请求锁的超时时间(ms 毫秒)
     */
    private static final long  TIME_OUT   = 100;
    private final String       LOCK_PRE   = "LOCK_";
    private CacheManager       cacheManager;
    /**
     * 锁标志对应的key
     */
    private String             lockKey;
    /**
     * 锁对应的值
     */
    private String             lockValue;
    /**
     * 锁标记
     */
    private volatile boolean   locked     = false;
    /**
     * 记录到日志的锁标志对应的key
     */
    private String             lockKeyLog = "";

    public RedisCacheProvider(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    protected CacheManager getCacheManager() {
        return cacheManager;
    }

    public Object getNativeCache(String regionName) {
        CacheManager cacheManager = getCacheManager();
        if (cacheManager != null) {
            Cache cache = cacheManager.getCache(regionName);
            if (cache != null) {
                return cache.getNativeCache();
            } else {
                return null;
            }
        }
        return null;
    }

    public Long incr(String regionName, String key, Integer expireTime) {
        Long defaultLong = 1L;
        RedisTemplate redisTemplate = (RedisTemplate) this.getNativeCache(regionName);
        if (redisTemplate == null) {
            throw new RuntimeException("get cache failed with regionName " + regionName);
        }
        return (Long) redisTemplate.execute((RedisCallback<Long>) connection -> {
            Object nativeConnection = connection.getNativeConnection();
            if (nativeConnection instanceof JedisCommands) {
                String result = ((JedisCommands) nativeConnection).set(key, String.valueOf(defaultLong), NX, EX,
                        expireTime);
                if (null != result) {
                    return defaultLong;
                } else {
                    Long incrResult = ((JedisCommands) nativeConnection).incr(key);
                    ((JedisCommands) nativeConnection).expire(key, expireTime);
                    return incrResult;
                }
            }
            return null;
        });
    }

    private String redisSet(final String regionName, final String key, String value, final long expireTime) {
        RedisTemplate restTemplate = (RedisTemplate) this.getNativeCache(regionName);
        return (String) restTemplate.execute((RedisCallback<String>) connection -> {
            Object nativeConnection = connection.getNativeConnection();
            String result = null;
            if (nativeConnection instanceof JedisCommands) {
                result = ((JedisCommands) nativeConnection).set(key, value, NX, EX, expireTime);
            }
            if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(result)) {
                log.info("time to acquire lock-{}:{}", key, System.currentTimeMillis());
            }
            return result;
        });
    }

    public boolean tryLock(String regionName, String key, int expireTime) {
        lockValue = UUID.randomUUID().toString();
        String lockKey = LOCK_PRE + key;
        String result = redisSet(regionName, lockKey, lockValue, expireTime);
        log.info("try lock the key:{}", lockKey);
        locked = OK.equalsIgnoreCase(result);
        return locked;
    }

    public boolean releaseLock(String regionName, String key) {
        RedisTemplate redisTemplate = (RedisTemplate) this.getNativeCache(regionName);
        String lockKey = LOCK_PRE + key;
        redisTemplate.execute((RedisCallback<Object>) connection -> {
            connection.del(redisTemplate.getStringSerializer().serialize(lockKey));
            return null;
        }, true);
        log.info("del the lock : {}", lockKey);
        return true;
    }

    public void addCache(String regionName, String key, String value, int expireTime) {
        redisSet(regionName, key, value, expireTime);
    }
}
