package com.ckj.base.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import jodd.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author c.kj
 * @Description 利用 Semaphore 限流
 * @Date 2020-09-25
 * @Time 10:04
 **/
@Slf4j
public class SemaphoreCore {

    private static final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3, 6, 20, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(13), ThreadFactoryBuilder.create().setNameFormat("QueueThread").get());

    public static void main(String[] args) {

        // set permits 0,1,2... 许可证只有一张 ，只有别人释放出来之后才可以继续 获取执行！
        // 当多个请求过来时 ，只允许处理三个请求 ，第四个请求也只有等三个请求中有人释放 许可证 才可以继续执行
        Semaphore semaphore = new Semaphore(2);

        threadPool.execute(() -> {
            try {
                semaphore.acquire();
                Thread.sleep(10000);
                log.info("task1 start ...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                log.info("task1 end ...");
                semaphore.release();
            }
        });

        threadPool.execute(() -> {
            try {
                semaphore.acquire();
                Thread.sleep(1000);
                log.info("task2 start ...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                log.info("task2 end ...");
                semaphore.release();
            }
        });

        threadPool.execute(() -> {
            try {
                semaphore.acquire();
                Thread.sleep(1000);
                log.info("task3 start ...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                log.info("task3 end ...");
                semaphore.release();
            }
        });

        threadPool.shutdown();

    }
}
