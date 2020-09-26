package com.ckj.base.concurrent;

import java.util.concurrent.ConcurrentHashMap;

import lombok.extern.slf4j.Slf4j;

/**
 * @author c.kj
 * @Description
 * @Date 2020-09-26
 * @Time 11:11
 * *
 **/
@Slf4j
public class UserSearchContext implements Runnable {

    public static ConcurrentHashMap<Integer, Object> userMap   = new ConcurrentHashMap<>();

    public static ThreadLocal<UserContext>           userLocal = new ThreadLocal<>();

    private Integer                                  userId;

    @Override
    public void run() {
        // find user biz data by concurrent thread`s userId
        UserContext user = search(userId);
        userMap.put(userId, user != null ? user : "is null");
        userLocal.set(user);
        log.info("thread context for given userId: " + userId + " is: " + userLocal.get());
    }

    UserSearchContext(Integer userId) {
        this.userId = userId;
    }

    private UserContext search(Integer userId) {
        switch (userId) {
            case 1:
                return new UserContext("chris", 23);
            case 2:
                return new UserContext("doug leg", 63);
            default:
                return null;
        }
    }
}
