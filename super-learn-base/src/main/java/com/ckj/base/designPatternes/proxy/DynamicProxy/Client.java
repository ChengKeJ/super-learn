package com.ckj.base.designPatternes.proxy.DynamicProxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Proxy;

/**
 * @author c.kj
 * @Description
 * @Date 2021-02-17
 * @Time 20:33
 * @Copyright @2019 Zhongan.com All right reserved
 **/
@Slf4j
public class Client {

    public static void main(String[] args) {

        Landlord landlord = new Landlord();
        /**
         * 每个代理对象都有一个 invocationHandler (调用处理程序)  中介 intermediary
         */
        ProxyInvocationHandler proxyInvocationHandler = new ProxyInvocationHandler();

        Rent proxy = (Rent) proxyInvocationHandler.getProxy(landlord);

        proxy.rent();




    }
}
