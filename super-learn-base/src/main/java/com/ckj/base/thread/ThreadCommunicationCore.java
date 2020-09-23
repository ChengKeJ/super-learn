package com.ckj.base.thread;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import jodd.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author c.kj
 * @Description 线程之间通信
 * @Date 2020-09-23
 * @Time 11:54
 * @Copyright @2019 Zhongan.com All right reserved
 **/
@Slf4j
public class ThreadCommunicationCore {

    private static final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3, 6, 20, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(13), ThreadFactoryBuilder.create().setNameFormat("pipedThread").get());

    private volatile boolean                block      = true;

    /**
     * 利用管道进行线程之间的通信
     *
     * @throws IOException
     */
    private void pipedThreadCommunication() throws IOException {

        PipedWriter pipedWriter = new PipedWriter();

        PipedReader pipedReader = new PipedReader();

        pipedReader.connect(pipedWriter);

        threadPool.execute(() -> {
            log.info("thread start write ...");
            for (int i = 0; i < 10; i++) {
                try {
                    log.info("start write {}", i);
                    pipedWriter.write(i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            log.info("thread end write ...");
        });

        threadPool.execute(() -> {
            try {
                log.info("thread start read ...");
                int msg = 0;
                while ((msg = pipedReader.read()) != -1) {
                    log.info("====> " + msg);
                }
            } catch (IOException ignored) {
            }
            log.info("thread end read ...");
        });

        threadPool.shutdown();
    }

    /**
     * 利用同一个对象进行线程通信
     *
     * @param obj
     */
    private void notifyOrWait(Object obj) {
        AtomicBoolean flag = new AtomicBoolean(true);
        threadPool.execute(() -> {
            synchronized (obj) {
                for (int i = 0; i < 10; i++) {
                    if (flag.get()) {
                        log.info("thread1 == {}", i);
                        flag.set(false);
                        obj.notify();
                    } else {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        threadPool.execute(() -> {
            synchronized (obj) {
                for (int i = 1; i < 10; i++) {
                    if (!flag.get()) {
                        log.info("thread2 == {}", i);
                        flag.set(true);
                        obj.notify();
                    } else {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        threadPool.shutdown();
    }

    /**
     * 利用共享内存来进行线程之间的通信
     */
    private void volatileCore() {

        threadPool.execute(() -> {
            while (block) {
                log.info("start task ...");
            }
            log.info("end task ...");
        });
        threadPool.shutdown();
    }

    public static void main(String[] args) throws IOException {

        ThreadCommunicationCore threadCommunicationCore = new ThreadCommunicationCore();
        // 管道通信
        threadCommunicationCore.pipedThreadCommunication();
        // 利用同一个对象进行线程通信
        threadCommunicationCore.notifyOrWait(new Object());
        // 利用共享内存进行线程通信
        threadCommunicationCore.volatileCore();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadCommunicationCore.block = false;

    }

}
