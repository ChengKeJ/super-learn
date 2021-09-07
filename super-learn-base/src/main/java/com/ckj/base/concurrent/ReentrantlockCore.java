package com.ckj.base.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author c.kj
 * @Description 可打断锁
 * @Date 2021/9/5
 * @Time 11:55 PM
 **/
public class ReentrantlockCore {


    public static void reentrantlockCall() {

        ReentrantLock lock = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            try {
                lock.lock();
                Thread.sleep(Integer.MAX_VALUE);
            } catch (Exception e) {
            } finally {
                lock.unlock();
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {

            try {
                lock.lockInterruptibly();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("m2 ---");
        });

        thread2.start();

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();


    }

    public static void main(String[] args) throws InterruptedException {

        reentrantlockCall();


    }
}
