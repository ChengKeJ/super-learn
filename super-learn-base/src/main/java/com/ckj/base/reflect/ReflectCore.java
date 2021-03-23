package com.ckj.base.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author c.kj
 * @Description
 * @Date 2020-12-28
 * @Time 19:08
 * @Copyright @2019 Zhongan.com All right reserved
 **/
public class ReflectCore {

    public static void main(String[] args)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {




        // 反射本质是 运行试根据类的class信息，进行
        Class<?> personClass = Class.forName("com.ckj.base.reflect.Person");
        Object o = personClass.newInstance();
        Method[] declaredMethods = personClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            declaredMethod.setAccessible(true);
            System.out.println(declaredMethod.getName());
            if (declaredMethod.getName().equals("setAge")) {
                declaredMethod.invoke(o, "10");
            }
            if (declaredMethod.getName().equals("setName")) {
                declaredMethod.invoke(o, "ckj");
            }
            // 处理运行时的数据

            Annotation[] declaredAnnotations = declaredMethod.getDeclaredAnnotations();

            for (Annotation declaredAnnotation : declaredAnnotations) {

                System.out.println(declaredAnnotation);
            }


        }











        // json 格式输出
        System.out.println(o);

    }

}
