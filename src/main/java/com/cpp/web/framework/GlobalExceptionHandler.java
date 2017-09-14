package com.cpp.web.framework;

import com.cpp.web.bean.response.BaseResponse;
import com.cpp.web.constant.ErrorCode;
import com.cpp.web.exception.CustomException;
import com.cpp.web.util.LogUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理
 * Created by luokaiqiongmou on 2016/12/17.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResponse handle(Exception e) {
        LogUtil.logException(e);
        if (e instanceof CustomException) {
            CustomException customException = (CustomException) e;
            return new BaseResponse(customException.code, customException.msg);
        } else {
            return new BaseResponse(ErrorCode.SERVER_ERROR, "服务器发生异常");
        }
    }

}