package com.ckj.base.concurrent;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
 * @author c.kj
 * @Description
 * @Date 2020-09-26
 * @Time 11:12
 * *
 **/
@Data
public class UserContext {

    private String            userName;

    private Integer           age;

    private Map<Object, Object> objContext = new HashMap<>();

    UserContext(
    String                    userName, Integer age)
    {
        this.userName = userName;
        this.age = age;
    }
}
