package com.kot.kotmybatis.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 表名注解
 */
@Target(ElementType.FIELD)
@Retention(RUNTIME)
@Documented
public @interface PrimaryKey{
}
