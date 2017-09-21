package com.cpp.web.bean.response;

import com.cpp.web.util.BeanUtil;
import com.cpp.web.constant.ErrorCode;

import java.util.*;

/**
 * Created by cpp59 on 2017/9/7.
 */
public class BaseResponse {

    private static String SUCCESS_STR = "OK";
    private int code;
    private String msg;


    public boolean successExecute() {
        return code == ErrorCode.SUCCESS;
    }

    public boolean failExecute() {
        return code != ErrorCode.SUCCESS;
    }

    public BaseResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public BaseResponse() {
    }


    public static BaseResponse emptySuccessResult() {
        return new BaseResponse(ErrorCode.SUCCESS, SUCCESS_STR);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
