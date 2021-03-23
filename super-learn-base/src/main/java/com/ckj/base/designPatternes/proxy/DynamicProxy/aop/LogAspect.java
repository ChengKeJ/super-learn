package com.ckj.base.designPatternes.proxy.DynamicProxy.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author c.kj
 * @Description
 * @Date 2021-02-18
 * @Time 00:00
 * @Copyright @2019 Zhongan.com All right reserved
 **/
@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * @param joinPoint
     */
    @Before("execution(* com.ckj.base.designPatternes.proxy.DynamicProxy.aop.UserService.*(..))")
    public void advice(JoinPoint joinPoint) {
        /**
         * 获取封装了署名信息的对象,在该对象中可以获取到目标方法名,所属类的Class等信息
         */
        log.info("LogAspect:before-advice joinPoint" + joinPoint.getSignature().getName());
    }

}
