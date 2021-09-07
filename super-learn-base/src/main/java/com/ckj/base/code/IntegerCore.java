package com.ckj.base.code;

import jodd.util.NaturalOrderComparator;

/**
 * @author c.kj
 * @Description int compare integer 注意包装对象的同一性
 * @Date 2021/9/2
 * @Time 9:08 AM
 **/
public class IntegerCore {

    public static void main(String[] args) {


        Integer integer = new Integer(1);
        int q = 1;
        Integer integer2 = new Integer(1);

        NaturalOrderComparator<Object> objectNaturalOrderComparator = new NaturalOrderComparator<>();
        int compare = objectNaturalOrderComparator.compare(integer, integer2);
        System.out.println(compare);

        boolean b = integer == integer2;
        boolean equals = integer.equals(integer2);
        System.out.println(equals);
        System.out.println(b);
    }

}
