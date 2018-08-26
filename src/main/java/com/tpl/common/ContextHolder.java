package com.tpl.common;

import com.tpl.model.UserInfo;

/**
 * @program: sso-service
 * @description:
 * @author: tianp
 * @create: 2018-08-26 14:57
 **/
public class ContextHolder {
    private static ThreadLocal<UserInfo> context = new ThreadLocal<>();

    public static void set(UserInfo userInfo) {
        context.set(userInfo);
    }

    public static UserInfo get() {
        return context.get();
    }

    public static void remove() {
        context.remove();
    }
}
