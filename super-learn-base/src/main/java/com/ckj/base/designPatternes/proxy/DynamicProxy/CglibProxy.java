package com.ckj.base.designPatternes.proxy.DynamicProxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * @author c.kj
 * @Description
 * @Date 2021-03-04
 * @Time 21:55
 * @Copyright @2019 Zhongan.com All right reserved
 **/
public class CglibProxy {

    public Object getProxyInstance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CglibTarget.class);
        MethodInterceptor methodInterceptor = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
                    throws Throwable {

                System.out.println("intercept start....");
                Object o1 = methodProxy.invokeSuper(o, objects);
                System.out.println("intercept end....");

                return o1;
            }
        };
        enhancer.setCallback(methodInterceptor);
        Object o = enhancer.create();
        return o;

    }

}
