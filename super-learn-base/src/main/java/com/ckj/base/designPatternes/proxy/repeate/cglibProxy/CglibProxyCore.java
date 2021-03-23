package com.ckj.base.designPatternes.proxy.repeate.cglibProxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import com.ckj.base.designPatternes.proxy.repeate.Player;

/**
 * @author c.kj
 * @Description
 * @Date 2021-03-16
 * @Time 18:11
 * @Copyright @2019 Zhongan.com All right reserved
 **/
public class CglibProxyCore {

    public static void main(String[] args) {
        // cglibProxy
        MethodInterceptor methodInterceptor = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
                    throws Throwable {
                System.out.println("cglib start ...");
                method.invoke(new Player(), objects);
                return null;
            }
        };
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(methodInterceptor);
        enhancer.setSuperclass(Player.class);
        // enhancer 用来创建代理对象，以及封装对应的 interceptor 和 继承真实对象
        Player b = (Player) enhancer.create();
        b.play();

    }
}
