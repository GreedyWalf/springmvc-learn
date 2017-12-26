package com.qs.mvc.base.context;

import com.sun.org.apache.regexp.internal.RE;

import java.util.HashMap;
import java.util.Map;

/**
 * 拦截器中 将登录的用户信息保存在本地线程变量中
 */
public class ExecutionContext {
    //使用本地线程，保证每一个线程存储的都是一份新的数据副本
    private transient static ThreadLocal<Map<String, String>> threadLocal = new ThreadLocal<Map<String, String>>();

    public ExecutionContext() {

    }

    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";

    public static Map<String, String> getContextMap() {
        return threadLocal.get();
    }

    public static void setContextMap(Map<String, String> contextMap) {
        threadLocal.set(contextMap);
    }

    public static String get(String key) {
        Map<String, String> contextMap = getContextMap();
        if (contextMap == null) {
            return null;
        }

        return contextMap.get(key);
    }

    public static void set(String key, String value) {
        Map<String, String> contextMap = getContextMap();
        if (contextMap == null) {
            contextMap = new HashMap<String, String>();
            setContextMap(contextMap);
        }

        contextMap.put(key, value);
    }


    public static String getUserId() {
        return get(USER_ID);
    }

    public static String getUserName() {
        return get(USER_NAME);
    }
}
