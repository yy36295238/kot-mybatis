package com.kot.kotmybatis.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Map key 转驼峰
 */
@Target(ElementType.METHOD)
@Retention(RUNTIME)
@Documented
public @interface ToCamel {
}
