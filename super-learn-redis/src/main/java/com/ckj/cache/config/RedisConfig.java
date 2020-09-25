package com.ckj.cache.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.*;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {

    public static final int     DEFAULT_MAX_TOTAL = 100;
    public static final int     DEFAULT_MAX_IDLE  = 100;
    public static final int     DEFAULT_MIN_IDLE  = 0;
    private static final String ENABLED_TRUE      = "true";
    @Autowired
    private RedisProperties     redisCacheProperties;
    private CacheManager        cacheManager;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(DEFAULT_MAX_IDLE);
        jedisPoolConfig.setMaxTotal(DEFAULT_MAX_TOTAL);
        jedisPoolConfig.setMinIdle(DEFAULT_MIN_IDLE);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);

        // 哨兵模式
        String enabledSentinel = this.redisCacheProperties.getEnabledSentinel();
        String sentinelMaster = this.redisCacheProperties.getSentinelMaster();
        Set<String> sentinelHostAndPorts = this.redisCacheProperties.getSentinelHostAndPorts();
        if (ENABLED_TRUE.equals(enabledSentinel)) {

            RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration(sentinelMaster,
                    sentinelHostAndPorts);
            jedisConnectionFactory = new JedisConnectionFactory(sentinelConfig, jedisPoolConfig);
        }

        jedisConnectionFactory.setHostName(this.redisCacheProperties.getHostname());
        jedisConnectionFactory.setPort(this.redisCacheProperties.getPort());
        jedisConnectionFactory.setPassword(this.redisCacheProperties.getPassword());
        jedisConnectionFactory.setTimeout(redisCacheProperties.getTimeout());
        //set the ssl switch
        jedisConnectionFactory.setUseSsl(redisCacheProperties.isUseSSL());

        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setExposeConnection(true);
        return redisTemplate;
    }

    /**
     * 对hash类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public HashOperations hashOperations(RedisTemplate redisTemplate) {
        return redisTemplate.opsForHash();
    }

    /**
     * 对redis字符串类型数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public ValueOperations valueOperations(RedisTemplate redisTemplate) {
        return redisTemplate.opsForValue();
    }

    /**
     * 对链表类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public ListOperations listOperations(RedisTemplate redisTemplate) {
        return redisTemplate.opsForList();
    }

    /**
     * 对无序集合类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public SetOperations setOperations(RedisTemplate redisTemplate) {
        return redisTemplate.opsForSet();
    }

    /**
     * 对有序集合类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public ZSetOperations zSetOperations(RedisTemplate redisTemplate) {
        return redisTemplate.opsForZSet();
    }
}
