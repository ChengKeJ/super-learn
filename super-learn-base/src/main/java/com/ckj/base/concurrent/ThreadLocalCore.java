package com.ckj.base.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;

import jodd.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author c.kj @Description@ 线程本地参数 ：保留当前线程的上下文
 * @Date 2020-09-25
 * @Time 14:06
 **/
@Slf4j
public class ThreadLocalCore {

    private static final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3, 6, 20, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(13), ThreadFactoryBuilder.create().setNameFormat("QueueThread").get());

    private void baseUse() {
        ThreadLocal<Object> objectThreadLocal = new ThreadLocal<>();

        threadPool.execute(() -> {
            log.info("start task2 ...");
            objectThreadLocal.set("hello this thread2`s task");
        });

        threadPool.execute(() -> {
            log.info("start task1 ...");
            objectThreadLocal.set("hello this thread1`s task");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object o = objectThreadLocal.get();
            log.info(JSON.toJSONString(o));
        });

        objectThreadLocal.set("this is main thread`s task ...");

        Object o = objectThreadLocal.get();

        log.info(JSON.toJSONString(o));
    }

    public void buildThreadLocalParam(Integer userId) {

        new UserSearchContext(userId).run();

        UserContext userContext = UserSearchContext.userLocal.get();

        userContext.getObjContext().put(userId, "hello ~");

        log.info(JSON.toJSONString(userContext));

    }

    public static void main(String[] args) {

        threadPool.execute(new UserSearchContext(1));

        threadPool.execute(new UserSearchContext(2));

        threadPool.execute(new UserSearchContext(3));

        log.info(JSON.toJSONString(UserSearchContext.userMap));

        new UserSearchContext(1).run();

        UserContext userContext = UserSearchContext.userLocal.get();

        userContext.getObjContext().put("chris", "super Man");

        log.info(JSON.toJSONString(userContext));

    }
}
