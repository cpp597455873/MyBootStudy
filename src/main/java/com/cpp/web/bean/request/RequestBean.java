package com.cpp.web.bean.request;

import java.util.Map;

/**
 * Created by chenpp on 2017/9/18.
 * 请求对象
 */
public class RequestBean {
    /**
     * 请求代码
     */
    private String code;
    /**
     * 请求参数
     */
    private Map<Object, Object> param;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<Object, Object> getParam() {
        return param;
    }

    public void setParam(Map<Object, Object> param) {
        this.param = param;
    }
}
