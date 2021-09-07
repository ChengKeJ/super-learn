package com.ckj.base.concurrent;

/**
 * @author c.kj
 * @Description
 * @Date 2021/9/3
 * @Time 9:27 AM
 **/
public class Core {

    public static class Widget {
        public synchronized void doSomething() {
            System.out.println("do ...");
        }
    }

    public static class LoggingWidget extends Widget {
        @Override
        public synchronized void doSomething() {
            System.out.println("do one ...");
            new Thread(() -> super.doSomething()).start();
        }
    }

    public static void main(String[] args) {
//        new Thread(()->{
//            LoggingWidget loggingWidget = new LoggingWidget();
//            loggingWidget.doSomething();
//        }).start();

    }
}
