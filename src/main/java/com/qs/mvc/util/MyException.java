package com.qs.mvc.util;

public class MyException extends RuntimeException {
    private MyErrorCode errorCode;
    private String message;

    public MyException(MyErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public MyErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(MyErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
