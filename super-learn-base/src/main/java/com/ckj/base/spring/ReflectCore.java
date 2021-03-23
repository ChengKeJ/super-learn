package com.ckj.base.spring;

import static org.springframework.boot.SpringApplication.DEFAULT_CONTEXT_CLASS;

import org.springframework.beans.BeanUtils;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author c.kj
 * @Description
 * @Date 2021-01-19
 * @Time 17:36
 * @Copyright @2019 Zhongan.com All right reserved
 **/
public class ReflectCore {

    void initClass() throws ClassNotFoundException {

        Class<?> contextClass = Class.forName(DEFAULT_CONTEXT_CLASS);
        //        Constructor<?> declaredConstructor = contextClass.getDeclaredConstructor();

        ConfigurableApplicationContext context = (ConfigurableApplicationContext) BeanUtils
                .instantiateClass(contextClass);


//        context.getBean();

    }

}
