package com.ckj.base.redission;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissionCoreTest {

    private RedissonClient redissonClient;

    @Before
    public void setUp() throws Exception {

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        redissonClient = Redisson.create(config);

        //Concurrent
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void bloomFilter() {
        RedissionCore redissionCore = new RedissionCore();
        boolean b = redissionCore.bloomFilter(redissonClient, "ZAJ020869888");
        assertThat(b).isFalse();
        boolean b2 = redissionCore.bloomFilter(redissonClient, "ZAJ020869888");
        assertThat(b2).isTrue();
    }

}
