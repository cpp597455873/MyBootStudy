package com.cpp.web.framework;

import java.lang.reflect.Method;

/**
 * Created by cpp59 on 2017/9/19.
 */
public class InvokeMethod {
    /**
     * 被调用的目标
     */
    public Object targetObject;
    /**
     * 被调用的方法
     */
    public Method method;

    public InvokeMethod(Object targetObject, Method method) {
        this.targetObject = targetObject;
        this.method = method;
    }
}
