package com.ckj.base.designPatternes.proxy.StaticProxy;

/**
 * @author c.kj
 * @Description
 * @Date 2021-02-17
 * @Time 23:18
 * @Copyright @2019 Zhongan.com All right reserved
 **/
public class Client {

    public static void main(String[] args) {
        IntermediaryProxy intermediaryProxy = new IntermediaryProxy();
        intermediaryProxy.setLandlord(new Landlord());
        intermediaryProxy.rent();
    }
}
