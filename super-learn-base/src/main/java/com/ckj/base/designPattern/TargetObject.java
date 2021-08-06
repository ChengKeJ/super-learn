package com.ckj.base.designPattern;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TargetObject extends Action implements Operation {

    @Override
    public void fly() {
        log.info("=== target invoke fly===");
    }

    @Override
    public void speak() {
        super.speak();
    }
}
