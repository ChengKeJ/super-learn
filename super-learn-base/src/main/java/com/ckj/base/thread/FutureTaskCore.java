package com.ckj.base.thread;

import java.util.concurrent.*;

import lombok.extern.slf4j.Slf4j;

/**
 * @author c.kj
 * @Description Future simple use
 * @Date 2020-09-14
 * @Time 19:46
 **/
@Slf4j
public class FutureTaskCore {

    private static final FutureTaskCore INSTANCE = new FutureTaskCore();

    public static void main(String[] args) {
        try {
            FutureTaskCore.INSTANCE.futureTask();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void futureTask() throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        // no future returned
        FutureTask<String> future = new FutureTask<>(() -> {
            log.info("start task ....");
            Thread.sleep(1000);
            log.info("end task ...");
            return "ok";
        });
        executor.execute(future);

        // have returned future
        Future<String> submit = executor.submit(() -> {
            log.info("start task2 ....");
            Thread.sleep(1000);
            log.info("end task2...");
            return "ok";
        });
        log.info("start another task ...");

        if ("ok".equals(submit.get())) {
            log.info("over start another task2 ...");
        }
    }

}
