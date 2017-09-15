package com.cpp.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
public class GlassApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(GlassApplication.class);
        springApplication.addListeners((ApplicationListener<ApplicationReadyEvent>) applicationEvent -> System.out.println("应用启动完成")); //添加启动事件
        springApplication.run(args);
    }
}
