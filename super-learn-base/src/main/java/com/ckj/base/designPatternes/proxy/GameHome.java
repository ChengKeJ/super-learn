package com.ckj.base.designPatternes.proxy;

import com.ckj.base.designPatternes.proxy.base.GamePayer;
import com.ckj.base.designPatternes.proxy.base.GameProcess;
import com.ckj.base.designPatternes.proxy.extend.BigChecker;
import com.ckj.base.designPatternes.proxy.extend.BigGamePayer;
import com.ckj.base.designPatternes.proxy.extend.Dieter;
import com.ckj.base.designPatternes.proxy.proxy.ProxyProcess;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author c.kj
 * @Description 游戏屋
 * @Date 2020-10-30
 * @Time 15:16

 **/
@Slf4j
public class GameHome {

    public static void main(String[] args) {
        proxyCheck();
        proxyPlay();
        proxyDiete();

        // dynamic proxy
        GameProcess proxy = (GameProcess) Proxy.newProxyInstance(GameProcess.class.getClassLoader(), new Class[]{GameProcess.class},
                new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                log.info("start dynamic process...");
                return method.invoke(new BigGamePayer(), args);
            }
        });
        proxy.play();

    }





    public static void proxyPlay() {
        // 代理玩游戏
        BigGamePayer bigGamePayer = new BigGamePayer();
        ProxyProcess proxyPayer = new ProxyProcess(bigGamePayer);
        proxyPayer.play();
    }


    public static void proxyCheck() {
        // 代理检票
        BigChecker bigChecker = new BigChecker();
        ProxyProcess proxyProcess = new ProxyProcess(bigChecker);
        proxyProcess.check();
    }


    public static void proxyDiete() {
        // 代理美食
        Dieter dieter = new Dieter();
        ProxyProcess proxyProcess = new ProxyProcess(dieter);
        proxyProcess.diet();
    }

    public static void selfPlay(){

        BigGamePayer bigGamePayer = new BigGamePayer();

        bigGamePayer.play();

    }

}
