package com.cpp.web.controler;

import com.alibaba.fastjson.JSON;
import com.cpp.web.bean.request.Weather;
import com.cpp.web.bean.response.BaseResponse;
import com.cpp.web.constant.DefineUrl;
import com.cpp.web.constant.ErrorCode;
import com.cpp.web.framework.HttpServer;
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
@RestController
public class WeatherController {

    @Value("${config.ak}")
    private String ak;

    @Autowired
    OkHttpClient okHttpClient;

    @RequestMapping(value = DefineUrl.WEATHER_GET)
    @ResponseBody
    public BaseResponse get(@RequestBody Map<String, Object> inParam) {
        try {
            Request.Builder url = new Request.Builder().get().url("http://api.map.baidu.com/telematics/v3/weather?output=json&ak="+ak+"&location=" + URLEncoder.encode((String) inParam.get("city"), "utf-8"));
            Call call = okHttpClient.newCall(url.build());
            Response execute = call.execute();
            com.squareup.okhttp.ResponseBody body = execute.body();
            Weather weather = JSON.parseObject(body.string(), Weather.class);
            return new BaseResponse(ErrorCode.SUCCESS, "查询天气成功", BeanUtil.bean2ListMap(weather));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BaseResponse(ErrorCode.SERVER_ERROR_UNKNOWN, "服务器未知异常");
    }
}
