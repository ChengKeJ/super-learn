package com.ckj.base.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

/**
 * @author c.kj
 * @Description
 * @Date 2021-03-03
 * @Time 11:54
 * @Copyright @2019 Zhongan.com All right reserved
 **/
@Slf4j
public class SynchronizedCore {

    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {


        final int[] fff = { 0 };

        AtomicInteger atomicInteger1 = new AtomicInteger(0);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(4000, () -> {
            log.info("end process count is {}", fff[0]);

            log.info("end process count is {}", atomicInteger1.get());

        });


        for (int i = 0; i < 4000; i++) {

            ExecutorService executorService = Executors.newFixedThreadPool(1);

            int finali = i;

            executorService.execute(() -> {

                //

                synchronized (new Object()) {
                    processCore(fff, finali, atomicInteger1);
                }

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    private static void processCore(int[] fff, int finali, AtomicInteger atomicInteger) {
        try {
            Thread.sleep(1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        fff[0] = fff[0] + 1;

        atomicInteger.incrementAndGet();

        log.info("this thread :{} ,count is :{}", finali, fff[0]);
    }
}
