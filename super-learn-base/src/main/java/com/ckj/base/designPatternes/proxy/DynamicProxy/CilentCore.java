package com.ckj.base.designPatternes.proxy.DynamicProxy;

/**
 * @author c.kj
 * @Description
 * @Date 2021-03-04
 * @Time 22:04
 * @Copyright @2019 Zhongan.com All right reserved
 **/
public class CilentCore {
    public static void main(String[] args) {
//
//        CglibProxy cglibProxy = new CglibProxy();
//
//        CglibTarget proxyInstance = (CglibTarget) cglibProxy.getProxyInstance();
//
//        proxyInstance.getPrint();

        JdkProxy jdkProxy = new JdkProxy();
        Rent proxyInstance = (Rent) jdkProxy.getProxyInstance();

        proxyInstance.rent();


    }
}
