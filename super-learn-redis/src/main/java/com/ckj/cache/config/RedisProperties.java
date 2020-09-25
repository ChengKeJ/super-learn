package com.ckj.cache.config;

import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = RedisProperties.REDIS_PREFIX)
@Getter
@Setter
public class RedisProperties {
    public final static String REDIS_PREFIX    = "spring.cache.redis";

    private String             hostname;
    private String             password;
    private int                port;
    private Long               expireTime      = 3600L;
    private int                timeout         = 5000;

    private String             name;

    //    private List<CustomCache>  customCache;

    private boolean            useSSL          = false;

    // =======================哨兵模式配置==========================

    private String             enabledSentinel = "false";

    private String             sentinelMaster;

    private Set<String>        sentinelHostAndPorts;

}
