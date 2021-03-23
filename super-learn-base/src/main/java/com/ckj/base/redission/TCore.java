package com.ckj.base.redission;

import com.alibaba.fastjson.JSON;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/**
 * @author c.kj
 * @Description
 * @Date 2020-12-28
 * @Time 20:49
 * @Copyright @2019 Zhongan.com All right reserved
 **/
public class TCore<x> {

    private x x;

    public <T> T print (T t) {

        return t;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Object print = TCore.class.newInstance().print("2");
        System.out.println(JSON.toJSONString(print));
        Object print2 = TCore.class.newInstance().print(3);
        System.out.println(JSON.toJSONString(print2));



    }
}
