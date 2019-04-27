package com.kot.kotmybatis.mapper;

import com.kot.kotmybatis.utils.KotStringUtils;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.lang.reflect.Field;

public class BaseProvider<T> {

    private static final String WHERE = " WHERE ";
    private static final String AND = " AND ";
    private static final String LIMIT_ONE = " LIMIT 1";

    public String findById(@Param("tableName") String tableName, @Param("id") Serializable id) {
        return "select * from ${tableName} where id = #{id}";
    }

    public String findOne(T bean) throws IllegalAccessException {
        return list(bean) + LIMIT_ONE;
    }

    public String list(T bean) throws IllegalAccessException {
        return "select * from " + KotStringUtils.table(bean.getClass()) + whereBuilder(bean);
    }

    private String whereBuilder(T bean) throws IllegalAccessException {
        StringBuilder whereBuilder = new StringBuilder();
        final Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object val = field.get(bean);
            if (val != null) {
                String col = field.getName();
                whereBuilder.append(String.format("%s=#{%s}%s", KotStringUtils.camel2Underline(col), col, AND));
            }
        }
        String condition = "";
        final int len = whereBuilder.length();
        if (len > 0) {
            // 删除最后一个`AND`
            whereBuilder.delete(len - 4, len);
            condition = WHERE + whereBuilder.toString();
        }
        return condition;
    }
}
