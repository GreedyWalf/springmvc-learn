package com.qs.mvc.util;

import java.io.Serializable;

public class JsonResult<T> implements Serializable {

    public static final String CODE_OK = "200";
    public static final String CODE_ERROR = "500";
    public static final String CODE_LOSE = "404";

    private String code;
    private boolean success;
    private String msg;
    private T data;
    private long count;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
