package com.ckj.base.designPatternes.proxy.DynamicProxy;

import org.openjdk.jmh.annotations.*;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author c.kj
 * @Description
 * @Date 2021-03-04
 * @Time 21:55
 **/
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Fork(1)
public class CglibProxy {

    @Benchmark
    @Warmup(iterations = 5, time = 100, timeUnit = TimeUnit.MICROSECONDS)
    @Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MICROSECONDS)
    public int measureName() throws InterruptedException {
       Thread.sleep(1);
        getProxyInstance();
        return 0;
    }

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
