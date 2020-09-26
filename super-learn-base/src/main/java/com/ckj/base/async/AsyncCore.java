package com.ckj.base.async;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.ckj.base.concurrent.ThreadLocalCore;
import com.ckj.base.concurrent.UserSearchContext;

import lombok.extern.slf4j.Slf4j;

/**
 * @author c.kj
 * @Description
 * @Date 2020-09-17
 * @Time 15:31
 **/
@Slf4j
@Service
@RestController
public class AsyncCore {

    @RequestMapping(value = "/findUser", method = RequestMethod.GET)
    public CompletableFuture<String> findUser(String user) throws InterruptedException {

        ThreadLocalCore threadLocalCore = new ThreadLocalCore();

        threadLocalCore.buildThreadLocalParam(Integer.valueOf(user));

        Object o = UserSearchContext.userLocal.get().getObjContext().get(Integer.valueOf(user));

        log.info(JSON.toJSONString(o));

        return null;

    }

}
