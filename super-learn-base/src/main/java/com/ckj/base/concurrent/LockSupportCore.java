package com.ckj.base.concurrent;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * @author c.kj
 * @Description 利用park实现一个锁队列
 * @Date 2021/9/7
 * @Time 9:19 AM
 **/
public class LockSupportCore {
    private final AtomicBoolean locked = new AtomicBoolean(false);
    private final Queue<Thread> waiters
            = new ConcurrentLinkedQueue<Thread>();

    public void lock() {
        boolean wasInterrupted = false;
        Thread current = Thread.currentThread();
        waiters.add(current);
        // Block while not first in queue or cannot acquire lock
        while (waiters.peek() != current ||
                !locked.compareAndSet(false, true)) {
            LockSupport.park(this);
            // ignore interrupts while waiting
            if (Thread.interrupted()) {
                wasInterrupted = true;
            }
        }
        waiters.remove();
        // reassert interrupt status on exit
        if (wasInterrupted) {
            current.interrupt();
        }
    }

    public void unlock() {
        locked.set(false);
        LockSupport.unpark(waiters.peek());
    }


    public static void main(String[] args) {

        LockSupportCore lockSupportCore = new LockSupportCore();



        new Thread(()->{
            lockSupportCore.lock();
            Thread.yield();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("start lock 1");
            lockSupportCore.unlock();
        }).start();

        new Thread(()->{
            lockSupportCore.lock();
            System.out.println("start lock 2");
            lockSupportCore.unlock();

        }).start();
        new Thread(()->{
            lockSupportCore.lock();
            System.out.println("start lock 3");
            lockSupportCore.unlock();

        }).start();
        new Thread(()->{
            lockSupportCore.lock();
            System.out.println("start lock 4");
            lockSupportCore.unlock();

        }).start();


    }
}
