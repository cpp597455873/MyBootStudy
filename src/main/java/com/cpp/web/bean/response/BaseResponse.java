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
    private List<Map<String, Object>> resultList;

    public boolean successExecute() {
        return code == ErrorCode.SUCCESS;
    }

    public boolean failExecute() {
        return code != ErrorCode.SUCCESS;
    }

    public BaseResponse(int code, String msg, List<Map<String, Object>> resultList) {
        this.code = code;
        this.msg = msg;
        this.resultList = resultList;
    }

    public BaseResponse(List<Map<String, Object>> resultList) {
        this.code = ErrorCode.SUCCESS;
        this.msg = SUCCESS_STR;
        this.resultList = resultList;
    }

    public BaseResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse() {
    }


    public static BaseResponse emptySuccessResult() {
        return new BaseResponse(ErrorCode.SUCCESS, SUCCESS_STR, Collections.EMPTY_LIST);
    }


    public static BaseResponse successResult(Map<String, Object> data) {
        return new BaseResponse(ErrorCode.SUCCESS, SUCCESS_STR, Arrays.asList(data));

    }

    public static BaseResponse successBeanResult(Object data) {
        return new BaseResponse(ErrorCode.SUCCESS, SUCCESS_STR, Arrays.asList(BeanUtil.bean2Map(data)));
    }

    public static BaseResponse successListBeanResult(List dataList) {
        return new BaseResponse(ErrorCode.SUCCESS, SUCCESS_STR, (List<Map<String, Object>>) BeanUtil.listBean2MapList(dataList));
    }

    public static BaseResponse successListMapResult(List<Map<String, Object>> dataList) {
        return new BaseResponse(ErrorCode.SUCCESS, SUCCESS_STR, dataList);
    }


    public void setCodeAndMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
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

    public List<Map<String, Object>> getResultList() {
        return resultList;
    }

    public void setResultList(List<Map<String, Object>> resultList) {
        this.resultList = resultList;
    }


}
