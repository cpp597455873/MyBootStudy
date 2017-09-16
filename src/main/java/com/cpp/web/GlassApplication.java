package com.cpp.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
public class GlassApplication {
    static long start;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(GlassApplication.class);
        springApplication.addListeners((ApplicationListener<ApplicationStartingEvent>) applicationEvent -> start = System.currentTimeMillis()); //添加启动事件
        springApplication.addListeners((ApplicationListener<ApplicationReadyEvent>) applicationEvent -> System.out.println("应用启动完成,耗时：" + (System.currentTimeMillis() - start) / 1000d + "s")); //添加启动事件
        springApplication.run(args);
    }
}
