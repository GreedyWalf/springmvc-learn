package com.qs.mvc.util;

public enum MyErrorCode {
    /*
        错误码详解：
        60x:用户信息错误
        70x:登录信息错误
        80x:参数异常
     */

    NOT_FOUNT("404"),
    SERVER_ERROR("500"),
    USER_ERROR("601"),
    LOGIN_ERROR("701"),
    ILLEGAL_PARAM_ERROR("801");

    private String text;

    MyErrorCode(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }
}