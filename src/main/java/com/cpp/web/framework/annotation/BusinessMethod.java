package com.cpp.web.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * Created by cpp59 on 2017/9/18.
 * 自定义注解，用于标记业务方法
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BusinessMethod {
    /**
     * 这里的value就是对应的业务的功能号
     *
     * @return
     */
    String value() default "";
}
