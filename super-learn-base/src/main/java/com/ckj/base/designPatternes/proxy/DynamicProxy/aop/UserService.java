package com.ckj.base.designPatternes.proxy.DynamicProxy.aop;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author c.kj
 * @Description
 * @Date 2021-02-18
 * @Time 00:05
 * @Copyright @2019 Zhongan.com All right reserved
 **/
@Service
@Slf4j
@RestController
public class UserService {

    @GetMapping("/query")
    public void query() {
        log.info("user service query !");
    }

}
