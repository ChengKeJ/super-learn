package com.ckj.base.designPatternes.proxy.demo;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author c.kj
 * @Description
 * @Date 2020-11-03
 * @Time 11:18
 **/
@Slf4j
public class proxy {

    private abstractFactory abstractFactory;

    proxy(abstractFactory abstractFactory) {
        this.abstractFactory = abstractFactory;
    }

    void invoke() {
        log.info("..start proxy");
        this.abstractFactory.invoke();
    }

    public static void main(String[] args) {
        new proxy(new BFactory()).invoke();
        new proxy(new CFactory()).invoke();
        abstractFactory o =(abstractFactory)
                Proxy.newProxyInstance(com.ckj.base.designPatternes.proxy.demo.abstractFactory.class.getClassLoader(),
                new Class[]{com.ckj.base.designPatternes.proxy.demo.abstractFactory.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return method.invoke(new AFactory(), args);
            }
        });
        o.invoke();
    }

}
