package com.cpp.web.business;

import com.alibaba.fastjson.JSON;
import com.cpp.web.bean.request.Weather;
import com.cpp.web.bean.response.BaseResponse;
import com.cpp.web.bean.response.ResponseMap;
import com.cpp.web.constant.Config;
import com.cpp.web.constant.ErrorCode;
import com.cpp.web.framework.annotation.BizMethod;
import com.cpp.web.framework.annotation.BizModule;
import com.cpp.web.framework.http.HttpRequest;
import com.cpp.web.util.BeanUtil;
import com.squareup.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cpp59 on 2017/9/11.
 */
@Component
@BizModule
public class WeatherService {

    private final Config config;
    private OkHttpClient okHttpClient;

    @Autowired
    public WeatherService(Config config, OkHttpClient okHttpClient) {
        this.config = config;
        this.okHttpClient = okHttpClient;
    }

    @BizMethod
    public BaseResponse getWeather(Map<String, Object> inParam) {
        try {
            HashMap getMap = new HashMap();
            getMap.put("output", "json");
            getMap.put("ak", config.getAk());
            getMap.put("location", inParam.get("city"));
            Weather weather = JSON.parseObject(HttpRequest.newInstance("http://api.map.baidu.com/telematics/v3/weather", HttpRequest.Method.GET).getParams(getMap).send(), Weather.class);
            return new ResponseMap(ErrorCode.SUCCESS, "查询天气成功", BeanUtil.bean2ListMap(weather));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BaseResponse(ErrorCode.SERVER_ERROR_UNKNOWN, "服务器未知异常");
    }
}
