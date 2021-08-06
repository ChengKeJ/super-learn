package com.ckj.base.designPattern;

import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestProxyCore {

    public static void main(String[] args) {
//        // jdk proxy
//        JdkProxyCore jdkProxyCore = new JdkProxyCore();
//        jdkProxyCore.setTargetObject(new TargetObject());
//        Operation proxyObject = jdkProxyCore.getProxyObject();
//        proxyObject.fly();
//
//        // cglib proxy
//        CglibProxyCore cglibProxyCore = new CglibProxyCore();
//        cglibProxyCore.setTargetObject(new TargetObject());
//        Action proxyObject1 = (Action) cglibProxyCore.getProxyObject();
//        proxyObject1.speak();

        ProxyFactory proxyFactory = new ProxyFactory();

        proxyFactory.setTarget(new TargetObject());

        TargetObject proxy = (TargetObject) proxyFactory.getProxy();

        proxy.fly();

    }
}
