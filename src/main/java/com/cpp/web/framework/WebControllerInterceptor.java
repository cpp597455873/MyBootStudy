package com.cpp.web.framework;

import com.cpp.web.bean.response.BaseResponse;
import com.cpp.web.constant.ErrorCode;
import com.cpp.web.exception.BusinessException;
import com.cpp.web.util.LogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Aspect
@Component
public class WebControllerInterceptor {
    protected static org.slf4j.Logger logger = LoggerFactory.getLogger(WebControllerInterceptor.class);

    @Pointcut("execution(public com.cpp.web.bean.response.BaseResponse com.cpp.web.controler.*.*(..))")
    public void requestInvoke() {
    }

    @Before("requestInvoke()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
//        logger.info("URL : " + request.getRequestURL().toString());
//        logger.info("HTTP_METHOD : " + request.getMethod());
//        logger.info("IP : " + request.getRemoteAddr());
//        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

        Object params;
        String requestMethod = request.getMethod();
        if (requestMethod.equalsIgnoreCase("post")) {
            params = joinPoint.getArgs()[0];
            Map map = (Map) params;
            if (map == null || map.size() == 0) {
                throw new BusinessException(ErrorCode.PARAM_EMPTY, "参数不能为空", new RuntimeException("参数不能为空"));
            }
        } else {
            params = joinPoint.getArgs();
        }

        LogUtil.logInParam("url:" + request.getRequestURL().toString() + "  method:" + requestMethod + "  param:" + params);


    }

    @AfterReturning(returning = "response", pointcut = "requestInvoke()")
    public void doAfterReturning(BaseResponse response) throws Throwable {
        LogUtil.logResponse(response);
    }


}