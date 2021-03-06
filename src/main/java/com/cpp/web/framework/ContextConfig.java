package com.cpp.web.framework;

import com.squareup.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by cpp59 on 2017/9/11.
 */
@Configuration
@ImportResource({"classpath:config/spring/dubbo-demo-provider.xml"})
//@ImportResource({"classpath:config/spring/dubbo-demo-consumer.xml"})
public class ContextConfig {

    @Bean
    public OkHttpClient getOkHttpClient() {
        return new OkHttpClient();
    }
}
