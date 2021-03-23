package com.ckj.base.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import com.alibaba.fastjson.JSON;

/**
 * @author c.kj
 * @Description
 * @Date 2021-03-03
 * @Time 10:58
 * @Copyright @2019 Zhongan.com All right reserved
 **/
public class FutureTaskCore {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable<String> callable = () -> {
            System.out.println("execute call task..");
            return "1";
        };
        FutureTask<String> futureTask = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);
        thread.start();
        Object o = futureTask.get();
        System.out.println(JSON.toJSON(o));

    }

}
