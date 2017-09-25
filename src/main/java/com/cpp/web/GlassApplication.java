package com.cpp.web;

import com.cpp.web.framework.AppEngine;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

@SpringBootApplication
public class GlassApplication {
    static long start;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(GlassApplication.class);
        springApplication.addListeners((ApplicationListener<ApplicationStartingEvent>) applicationEvent -> start = System.currentTimeMillis()); //添加启动事件
        springApplication.addListeners((ApplicationListener<ApplicationReadyEvent>) applicationEvent -> System.out.println("应用启动完成,耗时：" + (System.currentTimeMillis() - start) / 1000d + "s")); //添加启动事件
        springApplication.addListeners((ApplicationListener<ContextRefreshedEvent>) applicationEvent -> {
            ApplicationContext applicationContext = applicationEvent.getApplicationContext();
            ((AppEngine) applicationContext.getBean("appEngine")).init(applicationContext);
        }); //添加上下文
        springApplication.run(args);
    }
}
