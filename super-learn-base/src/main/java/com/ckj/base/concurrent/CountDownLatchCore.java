package com.ckj.base.concurrent;

import org.springframework.util.StopWatch;
import other.WatchLog;

import java.util.concurrent.*;

/**
 * @author c.kj
 * @Description 倒计数锁存器 使一些线程等待其余线程，协调他们动作
 * @Date 2021/9/1
 * @Time 11:26 AM
 **/
public class CountDownLatchCore {

    public static ExecutorService executorService = Executors.newFixedThreadPool(3);

    /**
     * 所有线程准备好 -> 开始计时 -> 所有线程处理结束 -> 结束计时
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 15, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        StopWatch stopWatch = new StopWatch();
        CountDownLatch pre = new CountDownLatch(10);
        CountDownLatch timer = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(3);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threadPoolExecutor.execute(() -> {
                System.out.println("thread" + finalI + "is pre !");
                pre.countDown();
                try {
                    timer.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("start opt ...");
                done.countDown();
            });
        }
        pre.await();
        stopWatch.start();
        timer.countDown();
        done.await();
        stopWatch.stop();
        CharSequence startedMessage = WatchLog.INSTANCE.getStartedMessage(stopWatch);
        System.out.println(startedMessage);
        executorService.shutdown();
    }


}
