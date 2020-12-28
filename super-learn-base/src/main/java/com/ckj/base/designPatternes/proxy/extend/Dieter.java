package com.ckj.base.designPatternes.proxy.extend;

import com.ckj.base.designPatternes.proxy.base.DietProcess;
import lombok.extern.slf4j.Slf4j;

/**
 * @author c.kj
 * @Description
 * @Date 2020-10-30
 * @Time 15:49

 **/
@Slf4j
public class Dieter implements DietProcess {

    @Override
    public void diet() {

      log.info("== start make diet : ice ");
    }
}
