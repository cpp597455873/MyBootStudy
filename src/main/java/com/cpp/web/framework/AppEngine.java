package com.cpp.web.framework;

import com.cpp.web.bean.request.RequestBean;
import com.cpp.web.bean.response.BaseResponse;
import com.cpp.web.constant.ErrorCode;
import com.cpp.web.framework.annotation.BusinessModule;
import com.cpp.web.framework.annotation.BusinessMethod;
import com.cpp.web.util.LogUtil;
import com.cpp.web.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by cpp59 on 2017/9/18.
 */
@Component
public class AppEngine {
    private Map<String, InvokeMethod> map = new HashMap<>();
    private ApplicationContext appContext;


    public void init(ApplicationContext context) {
        if (context != null) {
            appContext = context;
            initBusinessMethod();
            LogUtil.info("上下引擎初始化完成");
        }
    }

    /**
     * 初始化业务业务
     */
    private void initBusinessMethod() {
        //扫描出对应的业务代码
        Map<String, Object> businessModuleMap = appContext.getBeansWithAnnotation(BusinessModule.class);
        if (businessModuleMap != null) {
            Set<Map.Entry<String, Object>> entries = businessModuleMap.entrySet();
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
                                        LogUtil.error("方法重名:" + name);
                                        System.exit(-1);
                                    } else if (method.getReturnType().equals(BaseResponse.class) && method.getParameterCount() == 1 && method.getParameterTypes()[0].equals(Map.class)) {
                                        //准确的验证签名
                                        map.put(name, new InvokeMethod(value, method));
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public BaseResponse invoke(String code, Map inParam) {
        InvokeMethod invokeMethod = map.get(code);
        if (appContext == null) {
            return new BaseResponse(ErrorCode.INVOKE_NOT_PREPARED, "服务器异常");
        }
        if (invokeMethod == null) {
            return new BaseResponse(ErrorCode.INVOKE_METHOD_NOT_FOUND, "服务器异常");
        }
        try {
            return (BaseResponse) invokeMethod.method.invoke(invokeMethod.targetObject, inParam);
        } catch (Exception e) {
            LogUtil.logException(e);
        }
        return new BaseResponse(ErrorCode.INVOKE_EXCEPTION, "服务器异常");
    }

    public BaseResponse invoke(RequestBean requestBean) {
        if (StringUtils.isEmpty(requestBean.getCode())) {
            return new BaseResponse(ErrorCode.INVOKE_EMPTY_CODE, "服务器异常");
        } else {
            return invoke(requestBean.getCode(), requestBean.getParam());
        }
    }
}
