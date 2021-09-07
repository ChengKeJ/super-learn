package com.ckj.base.concurrent;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import net.jcip.annotations.NotThreadSafe;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author c.kj
 * @Description
 * @Date 2021/9/2
 * @Time 11:11 AM
 **/
@NotThreadSafe

public class UnsafeSequence {

    private int value;


    /**
     * 返回一个唯一的数值。
     */
    public int getNext() {
        return value++;
    }


    public Object holder;

    public void initialize() {
        holder = new Object();
    }


    public static void main(String[] args) throws InterruptedException {

        UnsafeSequence unsafeSequence = new UnsafeSequence();
        new Thread(() -> {
            unsafeSequence.initialize();
            System.out.println(JSON.toJSONString(unsafeSequence));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            System.out.println(JSON.toJSONString(unsafeSequence));
        }).start();



    }
}

