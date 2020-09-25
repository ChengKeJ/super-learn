package com.ckj.base.concurrent;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

import lombok.extern.slf4j.Slf4j;

/**
 * Thread communication
 *
 * @author c.kj
 * @Description 竞态条件 & 临界区 & 共享资源
 * @Date 2020-08-20
 * @Time 15:46
 **/
@Slf4j
public class ThreadCore extends ParentThreadCore {

    /**
     * 共享资源
     */

    private volatile HashMap<String, String>  map    = new HashMap<>();

    private ConcurrentHashMap<String, String> map2   = new ConcurrentHashMap<>();

    private volatile Integer                  count  = 0;

    private AtomicInteger                     countA = new AtomicInteger(0);

    private LongAdder                         countB = new LongAdder();

    /**
     * 当两个线程竞争同一资源时，如果对资源的访问顺序敏感，就称存在竞态条件。 导致竞态条件发生的代码区称作临界区。
     * 上例中criticalSection()方法就是一个临界区,它会产生竞态条件。 在临界区中使用适当的同步就可以避免竞态条件。
     */
    public static void main(String[] args) {
        ThreadCore threadCore = new ThreadCore();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2000, 4000, 1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(20));
        //        /**
        //         * 竞态条件
        //         */

        long l = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {
            int finalI = i;
            threadPoolExecutor.submit(() -> threadCore.criticalSectioB(Integer.toString(finalI)));
        }
        //        System.out.println(threadCore.count);
        //        System.out.println(threadCore.countA.get());

        System.out.println(System.currentTimeMillis() - l); // --3328 3499

        //
        //        System.out.println(threadCore.map.size());
        //
        //        System.out.println(threadCore.map2.size());
        //
        //        threadPoolExecutor.shutdown();

        //                new Thread(() -> threadCore.setMap(new HashMap<>())).start();
        //
        //                new Thread(threadCore::getMap).start();
    }

    /**
     * 临界区
     */
    public /** synchronized **/
    void criticalSection(String i) {

        //        final Object o = new Object();

        //        synchronized (o) {
        //            this.count = count + 1;
        //        count++;
        //            this.map.put(i, i);
        //            this.map2.put(i, i);
        //        }

        //        CAS
        count++;

        //        System.out.println(i1);

        //        countA.set(i1);

    }

    public synchronized void criticalSectionA(String i) {

        countA.incrementAndGet();

    }

    public /** synchronized **/
    void criticalSectioB(String i) {

        countB.increment();

    }

    @Override
    public synchronized HashMap<String, String> getMap() {
        //        synchronized (new Object()) {
        log.info(String.valueOf(this));
        super.getMap();
        System.out.println("start get map !");
        return this.map;
        //        }
    }

    @Override
    public synchronized void setMap(HashMap<String, String> map) {

        log.info(String.valueOf(this));
        super.setMap(map);

        System.out.println("start set map !");
        this.map.put("set", "value");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("set map end !");
    }

}
