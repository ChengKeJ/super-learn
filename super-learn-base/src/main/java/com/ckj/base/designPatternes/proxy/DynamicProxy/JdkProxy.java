package com.ckj.base.designPatternes.proxy.DynamicProxy;

import java.lang.reflect.Proxy;

/**
 * @author c.kj
 * @Description
 * @Date 2021-03-04
 * @Time 22:06
 * @Copyright @2019 Zhongan.com All right reserved
 **/
public class JdkProxy {

    public Object getProxyInstance() {
        Object o = Proxy.newProxyInstance(this.getClass().getClassLoader(), Landlord.class.getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("jdkProxy start..");
                    Object invoke = method.invoke(new Landlord(), args);
                    System.out.println("jdkProxy end..");
                    return invoke;
                });

        return o;


    }
}
