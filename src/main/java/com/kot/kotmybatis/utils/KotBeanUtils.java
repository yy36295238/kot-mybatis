package com.kot.kotmybatis.utils;

import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author YangYu
 */
public class KotBeanUtils {

    /**
     * 类包含注解
     */
    public static boolean classContainsAnno(Class<?> clazz, Annotation annotation) {
        return clazz.getAnnotation(annotation.getClass()) != null;
    }

    /**
     * 属性包含注解
     */
    public static boolean fieldContainsAnno(Field field, Class annotationClass) {
        return field.getAnnotation(annotationClass) != null;
    }

    /**
     * 类中包含注解的属性
     */
    public static Field getContainsAnnoField(Class<?> clazz, Class annotationClass) {
        final Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (fieldContainsAnno(field, annotationClass)) {
                return field;
            }
        }
        return null;
    }

    /**
     * 类中包含注解的属性
     */
    public static Object getContainsAnnoFieldVal(Object bean, Class annotationClass) throws IllegalAccessException {
        final Field field = getContainsAnnoField(bean.getClass(), annotationClass);
        if (field == null) {
            return null;
        }
        return field.get(bean);
    }

    /**
     * 根据属性名称获取属性值
     */
    public static <T> Object fieldVal(String fieldName, T bean) {
        Assert.notNull(bean, "bean is null");
        final Field field = ReflectionUtils.findField(bean.getClass(), fieldName);
        if (field == null) {
            return null;
        }
        return ReflectionUtils.getField(field, bean);
    }
}
