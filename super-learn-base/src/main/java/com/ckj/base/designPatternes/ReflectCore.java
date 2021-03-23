package com.ckj.base.designPatternes;

import com.ckj.base.designPatternes.singleton.SingletonFactory;

/**
 * @author c.kj
 * @Description
 * @Date 2021-03-04
 * @Time 23:11
 * @Copyright @2019 Zhongan.com All right reserved
 **/
public class ReflectCore {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        Class<SingletonFactory> singletonFactoryClass = SingletonFactory.class;

        SingletonFactory singletonFactory = singletonFactoryClass.newInstance();
    }
}
