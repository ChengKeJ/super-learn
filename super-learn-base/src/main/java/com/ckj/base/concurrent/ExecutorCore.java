package com.ckj.base.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author c.kj
 * @Description
 * @Date 2021/9/6
 * @Time 4:00 PM
 **/

public class ExecutorCore {

    private int coreThread;

    private ExecutorService executor;

    private Semaphore semaphore;

    ExecutorCore(int coreThread) {
        this.coreThread = coreThread;
        this.semaphore = new Semaphore(coreThread);
        ExecutorService executor = Executors.newFixedThreadPool(4);
        this.executor = executor;
    }


    public void execute(Runnable command) throws InterruptedException {
        semaphore.acquire();
        try {
            executor.submit(command);
        } catch (Exception e) {
            semaphore.release();
        } finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        ExecutorCore executorCore = new ExecutorCore(2);
        try {
            for (int i = 0; i < 4; i++) {
                int finalI = i;
                executorCore.execute(() -> {
                    System.out.println("start task" + finalI);
                });
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorCore.executor.shutdown();
    }
}
