package com.kot.kotmybatis.wrapper;

import com.kot.kotmybatis.utils.Tools;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 返回结果为Map类型中的key转成驼峰
 */

@Aspect
@Component
public class ToCamelWrapper {

    @Pointcut("@annotation(com.kot.kotmybatis.annotation.ToCamel)")
    public void toCamelPointcut() {
    }

    @Around("toCamelPointcut()")
    public Object checkParams(ProceedingJoinPoint pdj) throws Throwable {
        if (pdj.proceed() instanceof List) {
            return Tools.toCamel((List<Map<String, Object>>) pdj.proceed());
        }
        if (pdj.proceed() instanceof Map) {
            return Tools.toCamel((Map<String, Object>) pdj.proceed());
        }
        return pdj.proceed();

    }

}
