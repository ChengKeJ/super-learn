package com.ckj.base.designPatternes.proxy.repeate.staticProxy;

import com.ckj.base.designPatternes.proxy.repeate.Player;

/**
 * @author c.kj
 * @Description
 * @Date 2021-03-16
 * @Time 16:54
 * @Copyright @2019 Zhongan.com All right reserved
 **/
public class StaicCore {

    public static void main(String[] args) {
        Powerlever powerlever = new Powerlever();
        Player player = new Player();
        powerlever.setPlayer(player);
        powerlever.play();

    }
}
