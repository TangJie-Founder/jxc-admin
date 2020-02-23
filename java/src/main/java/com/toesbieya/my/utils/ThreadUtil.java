package com.toesbieya.my.utils;

import com.toesbieya.my.model.entity.RecUserAction;
import com.toesbieya.my.model.entity.SysUser;

import javax.servlet.http.HttpServletRequest;

public class ThreadUtil {
    private static final ThreadLocal<RecUserAction> THREAD_LOCAL_USER_ACTION = new ThreadLocal<>();
    private static final ThreadLocal<SysUser> THREAD_LOCAL_USER = new ThreadLocal<>();

    public static void quicklySetAction(HttpServletRequest request) {
        SysUser user = getUser();
        if (null == user) return;
        RecUserAction userAction = RecUserAction.builder()
                .uid(user.getId())
                .uname(user.getName())
                .ip(IpUtil.getIp(request))
                .url(request.getServletPath())
                .build();
        setAction(userAction);
    }

    public static RecUserAction getAction() {
        return THREAD_LOCAL_USER_ACTION.get();
    }

    public static void setAction(RecUserAction action) {
        THREAD_LOCAL_USER_ACTION.set(action);
    }

    public static SysUser getUser() {
        return THREAD_LOCAL_USER.get();
    }

    public static void setUser(SysUser user) {
        THREAD_LOCAL_USER.set(user);
    }

    public static void clearAll() {
        clearAction();
        clearUser();
    }

    public static void clearAction() {
        THREAD_LOCAL_USER_ACTION.remove();
    }

    public static void clearUser() {
        THREAD_LOCAL_USER.remove();
    }
}
