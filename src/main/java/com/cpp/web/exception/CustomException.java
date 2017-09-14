package com.cpp.web.exception;

/**
 * Created by cpp59 on 2017/9/8.
 */
public abstract class CustomException extends RuntimeException {
    public int code;
    public String msg;
    public Throwable throwable;

    public CustomException(Throwable throwable) {
        super(throwable);
        this.throwable = throwable;
    }

    public CustomException() {
    }
}
