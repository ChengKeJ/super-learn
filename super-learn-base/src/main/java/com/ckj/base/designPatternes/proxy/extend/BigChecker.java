package com.ckj.base.designPatternes.proxy.extend;

import com.ckj.base.designPatternes.proxy.base.CheckProcess;
import lombok.extern.slf4j.Slf4j;

/**
 * @author c.kj
 * @Description
 * @Date 2020-10-30
 * @Time 15:41
 **/
@Slf4j
public class BigChecker implements CheckProcess {

    @Override
    public void check() {
        log.info("=== start check ticket");
    }
}
