package com.ckj.base.spring;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class TestFactoryBeanPostProcessorCore {

    @Autowired
    private DateCoreFactoryBean dateCoreFactoryBean;

    private String name;

}
