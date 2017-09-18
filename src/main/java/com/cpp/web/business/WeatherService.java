package com.cpp.web.business;

import com.alibaba.fastjson.JSON;
import com.cpp.web.bean.request.Weather;
import com.cpp.web.bean.response.BaseResponse;
import com.cpp.web.constant.Config;
import com.cpp.web.constant.DefineUrl;
import com.cpp.web.constant.ErrorCode;
import com.cpp.web.framework.HttpServer;
import com.cpp.web.framework.annotation.BusinessMethod;
import com.cpp.web.framework.annotation.BusinessModule;
import com.cpp.web.util.BeanUtil;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by cpp59 on 2017/9/11.
 */
@Component
@BusinessModule
public class WeatherService {

    private final Config config;
    private OkHttpClient okHttpClient;

    @Autowired
    public WeatherService(Config config, OkHttpClient okHttpClient) {
        this.config = config;
        this.okHttpClient = okHttpClient;
    }

    @BusinessMethod
    public BaseResponse getWeather(Map<String, Object> inParam) {
        try {
            Request.Builder url = new Request.Builder().get().url("http://api.map.baidu.com/telematics/v3/weather?output=json&ak=" + config.getAk() + "&location=" + inParam.get("city"));
            Weather weather = JSON.parseObject(okHttpClient.newCall(url.build()).execute().body().string(), Weather.class);
            return new BaseResponse(ErrorCode.SUCCESS, "查询天气成功", BeanUtil.bean2ListMap(weather));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BaseResponse(ErrorCode.SERVER_ERROR_UNKNOWN, "服务器未知异常");
    }
}
