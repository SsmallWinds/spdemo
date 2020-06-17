package com.spdemo.ms1.Aspect;

import com.spdemo.common.utils.JsonUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class LogAspect {

    @Before(value = "execution(public * com.spdemo.ms1.Controller..*.*(..))")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        StringBuffer reqUrl = request.getRequestURL();
        String reqMethod = request.getMethod();
        String log = String.format("className %s methodName %s reqMethod %s "
                        + "request for %s resulted in %d  reqArgs %s",
                joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(), reqMethod,
                reqUrl, response.getStatus(), JsonUtils.getJsonFromObject(joinPoint.getArgs()));

        System.out.println(log);
    }

    @AfterReturning(pointcut = "execution(public * com.spdemo.ms1.Controller..*.*(..))", returning = "returnVal")
    public void afterReturn(JoinPoint joinPoint, Object returnVal) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String log = String.format("className %s methodName %s return result %s", className, methodName, JsonUtils.getJsonFromObject(returnVal));
        System.out.println(log);
    }
}
