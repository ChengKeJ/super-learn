package com.ckj.base.algorithm.loop;

/**
 * @author c.kj
 * @Description 暴力递归 - 汉诺塔
 * @Date 2021/8/11
 * @Time 4:31 PM
 **/
public class ViolentRecursionCore {

    // 推导关系 + 递归 递的终止条件
    public static void move(String from, String target) {
        System.out.println("from" + from + "to" + target);
    }

    public static void opt(int n, String from, String temp, String target) {
        if (n == 1) {
            move(from, target);
        } else {
            opt(n - 1, from, target, temp);
            move(from, target);
            opt(n - 1, temp, from, target);
        }
    }

    public static void main(String[] args) {
        opt(5, "A", "B", "C");
    }
}
