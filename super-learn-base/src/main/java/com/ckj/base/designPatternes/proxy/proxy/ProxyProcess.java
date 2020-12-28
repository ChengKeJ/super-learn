package com.ckj.base.designPatternes.proxy.proxy;

import com.ckj.base.designPatternes.proxy.base.CheckProcess;
import com.ckj.base.designPatternes.proxy.base.DietProcess;
import com.ckj.base.designPatternes.proxy.base.GameProcess;

import lombok.extern.slf4j.Slf4j;

/**
 * @author c.kj
 * @Description 游戏屋代理商
 * @Date 2020-10-30
 * @Time 15:12

 **/
@Slf4j
public class ProxyProcess implements GameProcess, CheckProcess, DietProcess {

    // 代理检票
    private CheckProcess checkProcess;

    // 代理游戏
    private GameProcess  gameProcess;

    // 代理饮食
    private DietProcess  dietProcess;

    public <T> ProxyProcess(T t) {
        if (t instanceof GameProcess) {
            this.gameProcess = (GameProcess) t;
        } else if (t instanceof CheckProcess) {
            this.checkProcess = (CheckProcess) t;
        } else if (t instanceof DietProcess) {
            this.dietProcess = (DietProcess) t;
        }
    }

    @Override
    public void play() {
        before();
        this.gameProcess.play();
        after();
    }

    @Override
    public void check() {
        this.checkProcess.check();
    }

    @Override
    public void diet() {
        this.dietProcess.diet();

    }
    public void before() {
        log.info("=== proxy: start before play game");

    }

    public void after() {
        log.info("=== proxy:  start after play game");

    }
}
