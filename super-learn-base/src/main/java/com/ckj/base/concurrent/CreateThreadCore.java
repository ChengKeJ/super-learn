package com.ckj.base.concurrent;

import java.util.concurrent.*;

/**
 * @author c.kj
 * @Description
 * @Date 2020-11-26
 * @Time 17:34
 * @Copyright @2019 Zhongan.com All right reserved
 **/
public class CreateThreadCore {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        Object object = Executors.newFixedThreadPool(1).submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("=== this is runable");
                return;
            }
        }).get();

        System.out.println(object);

        Future<String> submit = Executors.newSingleThreadExecutor().submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "this is a callable return object !";
            }
        });
        System.out.println(submit.get());
    }
}
