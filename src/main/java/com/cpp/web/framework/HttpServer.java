package com.cpp.web.framework;

import com.alibaba.fastjson.JSON;
import com.cpp.web.bean.request.Weather;
import com.squareup.okhttp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by cpp59 on 2017/9/11.
 */
@Component
public class HttpServer {

    @Autowired
    OkHttpClient okHttpClient;


    public static void main(String[] args) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder url = new Request.Builder().get().url("http://api.map.baidu.com/telematics/v3/weather?output=json&ak=040dfb48535ea6859b9f5f78e1e2206d&location=" + URLEncoder.encode("北京", "utf-8"));
            Call call = okHttpClient.newCall(url.build());
            Response execute = call.execute();
            ResponseBody body = execute.body();
            Weather weather = JSON.parseObject(body.string(), Weather.class);
            System.out.println(weather);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
