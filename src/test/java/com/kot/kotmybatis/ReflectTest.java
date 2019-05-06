package com.kot.kotmybatis;

import com.kot.kotmybatis.entity.User;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ReflectTest {

    static Map<Class<?>, Field[]> fieldsCache = new HashMap<>();

    public static void main(String[] args) {

        /*正常*/
        long normalStart = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            new User().getClass().getDeclaredFields();
        }
        long normalCost = System.currentTimeMillis() - normalStart;
        System.out.println("normalCost: " + normalCost);

        /*缓存优化*/
        long cacheStart = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            fields(new User());
        }
        long cacheCost = System.currentTimeMillis() - cacheStart;
        System.out.println("cacheCost: " + cacheCost);
    }

    public static Field[] fields(Object obj) {
        Class<?> clazz = obj.getClass();
        if (fieldsCache.containsKey(clazz)) {
            return fieldsCache.get(clazz);
        }
        final Field[] declaredFields = clazz.getDeclaredFields();
        fieldsCache.put(clazz, declaredFields);
        return declaredFields;
    }
}
