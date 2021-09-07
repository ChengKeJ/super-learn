package com.ckj.base.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author c.kj
 * @Description
 * @Date 2021/9/6
 * @Time 9:49 AM
 **/
public class ReentrantReadwriteLockCore {


    private int ticket = 0;

    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();


    public int refreshTicket() {
        try {
//            readLock.tryLock();
            System.out.println("start refresh..");
            return ticket;
        } catch (Exception e) {
        } finally {
//            readLock.unlock();
        }
        return 0;
    }

    public int optTicket() {
        try {
//            writeLock.tryLock();
            System.out.println("start opt..");
            this.ticket = ticket + 1;
            return this.ticket;
        } catch (Exception e) {
        } finally {
//            writeLock.unlock();
        }
        return -1;
    }

    public static void main(String[] args) {

        ReentrantReadwriteLockCore core = new ReentrantReadwriteLockCore();

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> System.out.println(core.refreshTicket()));


        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

        new Thread(() -> {

            boolean b = writeLock.tryLock();
            if (b) {
                try {
                    core.optTicket();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    writeLock.unlock();

                }
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        new Thread(() -> {
            boolean b = writeLock.tryLock();
            System.out.println(b);
            if (b) {
                try {
                    core.optTicket();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    writeLock.unlock();
                }

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }

        }).start();


    }
}
