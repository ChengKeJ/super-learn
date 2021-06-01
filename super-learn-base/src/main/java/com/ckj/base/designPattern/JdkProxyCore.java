package com.ckj.base.designPattern;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Data
@Slf4j
public class JdkProxyCore {

    private Object targetObject;

    public Operation getProxyObject() {
        Operation o = (Operation) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{Operation.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                log.info("=== jdkProxy before...");
                return method.invoke(targetObject, args);
            }
        });
        return o;
    }
}
