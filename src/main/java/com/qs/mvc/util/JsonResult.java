package com.qs.mvc.util;

import java.io.Serializable;

/**
 * ajax请求返回结果的json对象
 *
 * @author zhaoyue@HF
 * @since 2015-5-11
 */
public class JsonResult {
    /**
     * 状态，默认为SUCCESS
     */
    private JsonStatus status = JsonStatus.SUCCESS;

    /**
     * 消息
     */
    private String message;

    /**
     * 返回的数据
     */
    private Object data;

    public JsonResult() {

    }

    public JsonResult(JsonStatus status) {
        this.status = status;
    }

    public JsonResult(JsonStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public JsonStatus getStatus() {
        return status;
    }

    public void setStatus(JsonStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setStatusAndMessage(JsonStatus jsonStatus, String message) {
        this.status = jsonStatus;
        this.message = message;
    }
}