package com.cpp.web.util;

import com.alibaba.fastjson.JSON;
import com.cpp.web.bean.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by cpp59 on 2017/9/8.
 */
public class LogUtil {

    private static Logger logger = LoggerFactory.getLogger(LogUtil.class);

    public static void logInParam(Map map) {
        logger.info("入参: " + map);
    }

    public static void logInParam(Object map) {
        logger.info("入参:" + JSON.toJSONString(map));
    }

    public static void logException(Exception exception) {
        exception.printStackTrace();
        logger.info("异常:" + exception.getMessage());
    }

    public static void logResponse(BaseResponse response) {
        logger.info("出参:" + JSON.toJSONString(response) + "\n");
    }
}
