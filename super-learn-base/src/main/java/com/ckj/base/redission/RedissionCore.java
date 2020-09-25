package com.ckj.base.redission;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import lombok.extern.slf4j.Slf4j;

/**
 * @author c.kjf
 * @Description
 * @Date 2020-08-06
 * @Time 16:42
 **/
@Slf4j
public class RedissionCore {

    public boolean bloomFilter(RedissonClient redissonClient, Object target) {
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("bloomFilter");
        bloomFilter.tryInit(55000000L, 0.03);
        if (bloomFilter.contains(target)) {
            return true;
        } else {
            bloomFilter.add(target);
            return false;
        }
    }

    private void Lock(RedissonClient redissonClient) throws InterruptedException {
        RLock myLock = redissonClient.getLock("myLock");
        myLock.lock(100, TimeUnit.SECONDS);
        log.info("start work ..");
        Thread.sleep(90);
        log.info("end work ..");
        myLock.unlock();
        redissonClient.shutdown();
    }

}
