package com.cpp.web.exception;

/**
 * Created by cpp59 on 2017/9/8.
 * 业务异常
 */
public class BusinessException extends CustomException {
    public BusinessException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(int code, String msg, Throwable throwable) {
        super(throwable);
        this.code = code;
        this.msg = msg;
        this.throwable = throwable;
    }
}
