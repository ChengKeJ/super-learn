package com.ckj.base.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author c.kj
 * @Description
 * @Date 2021-03-23
 * @Time 14:27
 * @Copyright @2019 Zhongan.com All right reserved
 **/
public class ThreadPoolExecutorCore {

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 7, 100, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(7), new ThreadPoolExecutor.AbortPolicy());
        //  > workQueue & < maxPoolSize === new thread
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }
        threadPoolExecutor.shutdown();
    }
}
