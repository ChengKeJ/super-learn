package com.ckj.base.concurrent;

import java.util.HashMap;

/**
 * @author c.kj
 * @Description
 * @Date 2020-08-30
 * @Time 23:04
 **/
public class ParentThreadCore {

    public HashMap<String, String> getMap() {
        //        synchronized (new Object()) {
        System.out.println("ParentThreadCore start get map !");
        //        return this.map;
        //        }

        return new HashMap<>();
    }

    public void setMap(HashMap<String, String> map) {

        System.out.println("ParentThreadCore start set map !");
        //        this.map.put("set", "value");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ParentThreadCore set map end !");
    }

}
