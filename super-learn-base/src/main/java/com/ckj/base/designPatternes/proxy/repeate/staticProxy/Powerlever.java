package com.ckj.base.designPatternes.proxy.repeate.staticProxy;

import com.ckj.base.designPatternes.proxy.repeate.Player;

/**
 * @author c.kj
 * @Description 代理对象
 * @Date 2021-03-16
 * @Time 16:49
 * @Copyright @2019 Zhongan.com All right reserved
 **/
public class Powerlever {

    private Player player;

    public void setPlayer(Player player) {
        this.player = player;
    }

    void play() {

        System.out.println("start power lever .. ");
        player.play();

    }
}
