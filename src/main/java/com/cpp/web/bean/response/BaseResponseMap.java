package com.cpp.web.bean.response;

import com.cpp.web.constant.ErrorCode;
import com.cpp.web.util.BeanUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by cpp59 on 2017/9/7.
 */
public class BaseResponseMap extends BaseResponse {

    public BaseResponseMap(int code, String msg, List<Map<String, Object>> resultList) {
        super(code, msg);
        this.resultList = resultList;
    }

    public BaseResponseMap() {
    }

    public List<Map<String, Object>> getResultList() {
        return resultList;
    }

    public void setResultList(List<Map<String, Object>> resultList) {
        this.resultList = resultList;
    }

    private List<Map<String, Object>> resultList;


}
