package com.cpp.web.framework;

import com.cpp.web.bean.request.RequestBean;
import com.cpp.web.bean.response.BaseResponse;
import com.cpp.web.constant.ErrorCode;
import com.cpp.web.framework.annotation.BusinessModule;
import com.cpp.web.framework.annotation.BusinessMethod;
import com.cpp.web.util.StringUtils;
import org.springframework.context.ApplicationContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by cpp59 on 2017/9/18.
 */
public class AppContext {

    public static ApplicationContext getContext() {
        if (appContext == null) {
            System.err.println("context是空的呢");
        }
        return appContext;
    }

    public static void init(ApplicationContext context) {
        if (context != null) {
            appContext = context;
            initBusinessMethod();
            System.out.println("上下文绑定完成");
        }
    }

    /**
     * 初始化业务业务
     */
    private static void initBusinessMethod() {
        businessBeanMap = appContext.getBeansWithAnnotation(BusinessModule.class);
        if (businessBeanMap != null) {
            Set<Map.Entry<String, Object>> entries = businessBeanMap.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                String key = entry.getKey();
                Object value = entry.getValue();
                Method[] methods = value.getClass().getDeclaredMethods();
                if (methods != null && methods.length > 0) {
                    for (Method method : methods) {
                        Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
                        if (declaredAnnotations != null && declaredAnnotations.length > 0) {
                            for (Annotation declaredAnnotation : declaredAnnotations) {
                                if (declaredAnnotation.annotationType().equals(BusinessMethod.class)) {
                                    BusinessMethod annotation = method.getAnnotation(BusinessMethod.class);
                                    String name = annotation.value();
                                    if (name.equals("")) {
                                        name = method.getName();
                                    }
                                    if (map.containsKey(name)) {
                                        System.err.println("方法重名");
                                        System.exit(-1);
                                    }
                                    map.put(name, new InvokeMethod(value, method));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static BaseResponse invoke(String code, Map inParam) {
        InvokeMethod invokeMethod = map.get(code);
        if (invokeMethod == null) {
            return new BaseResponse(ErrorCode.INVOKE_METHOD_NOT_FOUND, "服务器异常");
        }
        try {
            return (BaseResponse) invokeMethod.method.invoke(invokeMethod.targetObject, inParam);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return new BaseResponse(ErrorCode.INVOKE_EXCEPTION, "服务器异常");
    }

    public static BaseResponse invoke(RequestBean requestBean) {
        if (StringUtils.isEmpty(requestBean.getCode())) {
            return new BaseResponse(ErrorCode.INVOKE_EMPTY_CODE, "服务器异常");
        } else {
            return invoke(requestBean.getCode(), requestBean.getParam());
        }
    }

    private static Map<String, InvokeMethod> map = new HashMap<String, InvokeMethod>();
    private static Map<String, Object> businessBeanMap;
    private static ApplicationContext appContext;
}
