package com.ckj.base.factoryBean;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

/**
 * @author c.kj
 * @Description
 * @Date 2021-02-26
 * @Time 14:52
 * @Copyright @2019 Zhongan.com All right reserved
 **/
@Slf4j
public class BeanFactorySingleTon {

    private static BeanFactorySingleTon beanFactorySingleTon = null;

    public static BeanFactorySingleTon getBeanFactorySingleTon() {
        if (beanFactorySingleTon == null) {
//            synchronized (BeanFactorySingleTon.class) {
//                if (beanFactorySingleTon == null) {
                    beanFactorySingleTon = new BeanFactorySingleTon();
//                }
//            }
        }
        return beanFactorySingleTon;
    }

    public static void main(String[] args) {

        BeanFactorySingleTon b1 = BeanFactorySingleTon.getBeanFactorySingleTon();

        final AtomicInteger[] count = { new AtomicInteger(0) };

        for (int i = 0; i < 10000; i++) {

            new Thread(() -> {
                BeanFactorySingleTon b2 = BeanFactorySingleTon.getBeanFactorySingleTon();
                if (!b2.equals(b1)) {
                    log.info("thread create b2 is not equal b1");
                } else {
                    count[0].getAndIncrement();
                }
            }).start();

        }

        HashSet<Object> objects = new HashSet<>();
        objects.add("2");

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(16);
        concurrentHashMap.put("1", 1);


        log.info(String.valueOf(count[0].get()));

    }
}
