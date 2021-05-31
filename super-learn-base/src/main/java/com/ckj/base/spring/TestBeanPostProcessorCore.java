package com.ckj.base.spring;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class TestBeanPostProcessorCore {

    private String name;

    private Integer age;
}
