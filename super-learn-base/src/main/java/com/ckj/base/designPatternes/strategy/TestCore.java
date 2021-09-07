package com.ckj.base.designPatternes.strategy;

import java.util.function.UnaryOperator;

/**
 * @author c.kj
 * @Description
 * @Date 2021/8/2
 * @Time 9:11 AM
 * @Copyright @2019 Zhongan.com All right reserved
 **/
public class TestCore {


    public static void main(String[] args) {

        UnaryOperator<String> c1 = (String text) -> {
            text = text.concat("c1");
            System.out.println("test 1 " + text);
            return text;
        };


        UnaryOperator<String> c2 = (String text) -> {
            text = text.concat("c2");
            System.out.println("test 2 " + text);
            return text;
        };
        String a = c1.andThen(c2).apply("A");
        System.out.println("---"+a);


    }
}
