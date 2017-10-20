package com.cpp.web.dubbo;

/**
 * Created by cpp59 on 2017/10/20.
 */
public class DemoServiceImpl implements DemoService {
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
