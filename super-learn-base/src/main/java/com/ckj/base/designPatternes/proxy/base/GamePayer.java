package com.ckj.base.designPatternes.proxy.base;

import com.ckj.base.designPatternes.proxy.base.GameProcess;
import lombok.extern.slf4j.Slf4j;

/**
 * @author c.kj
 * @Description 玩家
 * @Date 2020-10-30
 * @Time 15:10

 **/
@Slf4j
public class GamePayer implements GameProcess {
    @Override
    public void play() {

        log.info("=== start play game");

    }
}
