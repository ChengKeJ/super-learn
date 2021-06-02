package com.ckj.base.concurrent;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.spring.web.json.Json;

@Slf4j
public class ThreadLocalTest {

    public static void main(String[] args) {

        ThreadLocal<Object> objectThreadLocal = new ThreadLocal<>();

        new Thread(() -> {
            objectThreadLocal.set("ckj");
            Object o = objectThreadLocal.get();
            log.info("=== object is {}",JSON.toJSONString(o));
        }).start();

        new Thread(()->{
            Object o = objectThreadLocal.get();
            log.info("=== object is {}",JSON.toJSONString(o));
        }).start();
    }
}
