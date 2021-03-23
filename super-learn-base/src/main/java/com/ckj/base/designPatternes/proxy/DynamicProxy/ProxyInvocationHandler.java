package com.ckj.base.designPatternes.proxy.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author c.kj
 * @Description
 * @Date 2021-02-17
 * @Time 20:07
 * @Copyright @2019 Zhongan.com All right reserved
 **/
@Slf4j
public class ProxyInvocationHandler implements InvocationHandler {

    /**
     * 真实对象：房东
     */
    private Object target;

    /**
     * 返回代理对象
     *
     * @return
     */
    public Object getProxy(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), this.target.getClass().getInterfaces(), this);
    }

    /**
     * @description 横向拓展某一个方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("start invoke :" + method.getName());
        return method.invoke(this.target, args);
    }
}
