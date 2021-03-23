package com.ckj.base.designPatternes.proxy.repeate.jdkProxy;

import com.ckj.base.designPatternes.proxy.repeate.GameActionInterface;
import com.ckj.base.designPatternes.proxy.repeate.Player;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author c.kj
 * @Description 代理 --- 对真实对象的增强
 * @Date 2021-03-16
 * @Time 16:58
 * @Copyright @2019 Zhongan.com All right reserved
 **/
public class JdkPorxyCore {

    public static void main(String[] args) {

        // jdk proxy
        GameActionInterface o = (GameActionInterface) Proxy.newProxyInstance(JdkPorxyCore.class.getClassLoader(),
                Player.class.getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("jdkProxy start play..");
                        method.invoke(new Player(), args);
                        return null;
                    }
                });
        o.gamePlay();

    }

}
