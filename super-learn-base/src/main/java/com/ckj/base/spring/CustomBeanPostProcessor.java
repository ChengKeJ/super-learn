package com.ckj.base.spring;

import com.alibaba.fastjson.JSON;
import io.reactivex.internal.util.BlockingHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;


/**
 * 自定义beanPostprocessor 处理 TestBeanpostCore bean
 */
@Component
@Slf4j
public class CustomBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof TestBeanPostProcessorCore) {
            ((TestBeanPostProcessorCore) bean).setAge(100);
            ((TestBeanPostProcessorCore) bean).setName("ckj");
            log.info("=== this custom bean :{}", JSON.toJSONString(bean));
            return bean;
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
