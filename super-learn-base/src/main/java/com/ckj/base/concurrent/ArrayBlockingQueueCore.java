package com.ckj.base.concurrent;

import java.util.concurrent.*;

import com.alibaba.fastjson.JSON;

import jodd.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author c.kj
 * @Description
 * @Date 2020-09-24
 * @Time 15:08
 **/
@Slf4j
public class ArrayBlockingQueueCore {

    private static final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3, 6, 20, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(13), ThreadFactoryBuilder.create().setNameFormat("QueueThread").get());

    public static void main(String[] args) {
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(30, true);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            //            while (arrayBlockingQueue.iterator().hasNext()) {
            //                String poll = arrayBlockingQueue.poll();
            //                log.info("from head to tail :{}", poll);
            //            }
            log.info("end produce task ..");
        });

        threadPool.execute(() -> {
            try {
                log.info("produce task1 ...");

                for (int i = 0; i < 10; i++) {
                    arrayBlockingQueue.offer("thread offer queue1 -" + i);
                }
                cyclicBarrier.await();

                log.info("produce end task1 ...");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

        });
        threadPool.execute(() -> {
            try {
                log.info("produce task2 ...");
                for (int i = 0; i < 10; i++) {
                    arrayBlockingQueue.offer("thread offer queue2 -" + i);
                }
                cyclicBarrier.await();
                log.info("produce task2 ...");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        threadPool.shutdown();
        try {
            // 当前线程休眠避免直接先consume 在没有produce的情况下
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("start consumer task ...");
        while (arrayBlockingQueue.iterator().hasNext()) {
            String poll = arrayBlockingQueue.poll();
            log.info("from head to tail :{}", poll);
        }
    }

    private void baseUse() {

        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3, true);

        try {
            arrayBlockingQueue.element();

        } catch (Exception e) {
            log.warn("element get null will throw exception, but offer will not !");
        }

        for (int i = 0; i < 5; i++) {
            arrayBlockingQueue.offer("queue:" + i);
        }

        Object element = arrayBlockingQueue.element();

        log.info(JSON.toJSONString(element));

        // 检索但不删除 head 元素
        Object peek = arrayBlockingQueue.peek();

        log.info(JSON.toJSONString(peek));

        // 检索并删除 head 元素
        String poll = arrayBlockingQueue.poll();
        log.info(poll);

        String peek1 = arrayBlockingQueue.peek();
        log.info(peek1);

        // 检索并删除 head 元素,如果head 不存在会 exception
        String remove = arrayBlockingQueue.remove();
        log.info(remove);
    }
}
