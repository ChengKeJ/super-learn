package com.ckj.base.spring;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateCoreFactoryBean implements FactoryBean {

    @Override
    public Date getObject() throws Exception {
        return new Date();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
