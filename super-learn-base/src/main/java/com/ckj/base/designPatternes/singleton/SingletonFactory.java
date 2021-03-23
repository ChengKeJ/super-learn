package com.ckj.base.designPatternes.singleton;

/**
 * @author c.kj
 * @Description
 * @Date 2021-03-04
 * @Time 21:34
 * @Copyright @2019 Zhongan.com All right reserved
 **/
public class SingletonFactory {

    private static volatile SingletonFactory instance;

    /**
     * 内存屏障 & 可见性
     */
    public static SingletonFactory getInstance() {
        if (instance == null) {
            synchronized (SingletonFactory.class) {
                if (instance == null) {
                    instance = new SingletonFactory();
                }
            }
        }
        return instance;

    }
}
