package com.ckj.base.spring;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class ApplicationContextCore {

    public static void main(String[] args) {

//
//
//        DefaultListableBeanFactory context = new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(context);
//        xmlBeanDefinitionReader.loadBeanDefinitions("applicationContext.xml");
//
//

        //
//
//        Object dateCoreFactoryBean = context.getBean("dateCoreFactoryBean");
//
//        TestFactoryBeanCore testFactoryBeanCore = (TestFactoryBeanCore) context.getBean("testFactoryBeanCore");
//
//        TestFactoryBeanCore testFactoryBeanCore2 = (TestFactoryBeanCore) context.getBean("testFactoryBeanCore");
//
//        // test bean scope
//        if (testFactoryBeanCore2 == testFactoryBeanCore) {
//            log.info("this bean is same !");
//        }
//
//        DateCoreFactoryBean dateCoreFactoryBean1 = testFactoryBeanCore.getDateCoreFactoryBean();
//
//        log.info("----" + JSON.toJSON(dateCoreFactoryBean1));
//
//        log.info("----" + JSON.toJSON(testFactoryBeanCore));
//
//        log.info("----" + JSON.toJSON(dateCoreFactoryBean));
//
//        CustomBeanPostprocessor customBeanPostprocessor = (CustomBeanPostprocessor) context.getBean("customBeanPostprocessor");
//
//
//        log.info("customBeanPostprocessor---" + JSON.toJSON(customBeanPostprocessor));
//
//        TestBeanpostCore testBeanpostCore = (TestBeanpostCore) context.getBean("testBeanpostCore");
//
//        log.info("testBeanpostCore---" + JSON.toJSON(testBeanpostCore));
//
//        context.addBeanPostProcessor(customBeanPostprocessor);
//
//
//        Object testBeanpostCore2 = context.getBean("testBeanpostCore");
//
//        log.info("testBeanpostCore2---" + JSON.toJSON(testBeanpostCore2));

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        TestBeanPostProcessorCore testBeanPostCore = (TestBeanPostProcessorCore) context.getBean("testBeanPostProcessorCore");

        log.info("=========" + JSON.toJSONString(testBeanPostCore));


    }
}
