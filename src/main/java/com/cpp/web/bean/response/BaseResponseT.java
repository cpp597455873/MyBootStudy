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
public class BaseResponseT<T> extends BaseResponse {

    public BaseResponseT(int code, String msg, List<T> resultList) {
        super(code, msg);
        this.resultList = resultList;
    }

    public BaseResponseT() {
    }

    private List<T> resultList;


    public List<T> getResultList1() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }


}
