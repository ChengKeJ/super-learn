package com.ckj.base.designPatternes.proxy.demo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author c.kj
 * @Description
 * @Date 2020-11-03
 * @Time 11:16
 * @Copyright @2019 Zhongan.com All right reserved
 **/
@Slf4j
public class BFactory implements abstractFactory {
    @Override
    public void invoke() {

        log.info("B invoke ..");
    }
}
