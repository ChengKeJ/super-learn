package com.ckj.base.designPatternes.proxy.repeate;

/**
 * @author c.kj
 * @Description 玩家
 * @Date 2021-03-16
 * @Time 16:48
 * @Copyright @2019 Zhongan.com All right reserved
 **/
public class Player implements GameActionInterface {

    public void play() {
        System.out.println("player play...");

    }

    @Override
    public void gamePlay() {
        System.out.println("game play...");
    }
}
