package com.ckj.base.designPatternes.proxy.StaticProxy;

import com.ckj.base.designPatternes.proxy.DynamicProxy.Rent;

/**
 * @author c.kj
 * @Description 房东
 * @Date 2021-02-17
 * @Time 20:02
 * @Copyright @2019 Zhongan.com All right reserved
 **/
public class Landlord implements Rent {

    @Override
    public void rent() {
        System.out.println("landlord rent house !");
    }
}
