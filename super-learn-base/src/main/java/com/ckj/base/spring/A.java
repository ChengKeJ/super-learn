package com.ckj.base.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author c.kj
 * @Description
 * @Date 2021-02-02
 * @Time 10:42
 * @Copyright @2019 Zhongan.com All right reserved
 **/
@Component
public class A {

    @Autowired
    private B b;
}
