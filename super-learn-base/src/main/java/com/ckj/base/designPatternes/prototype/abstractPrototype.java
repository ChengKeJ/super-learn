package com.ckj.base.designPatternes.prototype;

/**
 * @author c.kj
 * @Description abstractFactory
 * @Date 2020-11-03
 * @Time 11:45
 * @Copyright @2019 Zhongan.com All right reserved
 **/
public class abstractPrototype implements Cloneable {

    @Override
    protected abstractPrototype clone() throws CloneNotSupportedException {
        abstractPrototype abstractPrototype;
        abstractPrototype = (abstractPrototype) super.clone();
        return abstractPrototype;
    }
}
