package com.ckj.base.designPatternes.proxy.extend;

import com.ckj.base.designPatternes.proxy.base.GamePayer;
import lombok.extern.slf4j.Slf4j;

/**
 * @author c.kj
 * @Description
 * @Date 2020-10-30
 * @Time 15:19

 **/
@Slf4j
public class BigGamePayer extends GamePayer {

    @Override
    public void play() {

        log.info("=== big player start play ...");
    }

}
