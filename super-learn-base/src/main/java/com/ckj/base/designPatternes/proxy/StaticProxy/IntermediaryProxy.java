package com.ckj.base.designPatternes.proxy.StaticProxy;

/**
 * @author c.kj
 * @Description
 * @Date 2021-02-17
 * @Time 23:17
 * @Copyright @2019 Zhongan.com All right reserved
 **/
public class IntermediaryProxy implements Rent {

    private Landlord landlord;

    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
    }

    @Override
    public void rent() {
        System.out.println("start..");
        landlord.rent();
    }
}
