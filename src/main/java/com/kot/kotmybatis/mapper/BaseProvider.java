package com.kot.kotmybatis.mapper;

import com.kot.kotmybatis.annotation.TableName;
import com.kot.kotmybatis.common.Page;
import com.kot.kotmybatis.utils.KotStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BaseProvider<T> implements ProviderMethodResolver {

    private static final Map<Class, String> tableCache = new HashMap<>();

    private static final String WHERE = " WHERE ";
    private static final String AND = " AND ";
    private static final String LIMIT = " LIMIT ";
    private static final String LIMIT_ONE = " LIMIT 1 ";
    private static final String ORDER_BY = " ORDER BY ";
    private static final String SPACE = " ";
    private static final String SPILT = ",";
    private static final String EMPTY = "";
    private static final String DOT = ".";

    public String findById(@Param("table") Class<T> clazz, @Param("id") Serializable id) {
        return "select * from " + tableByClazz(clazz) + " where id = #{id}";
    }

    public String findOne(T entity) throws IllegalAccessException {
        return list(entity) + LIMIT_ONE;
    }

    public String list(T entity) throws IllegalAccessException {
        return "select * from " + tableByClazz(entity.getClass()) + whereBuilder(entity);
    }

    public String selectCount(T entity) throws IllegalAccessException {
        return "select count(*) from " + tableByClazz(entity.getClass()) + whereBuilder(entity);
    }

    public String selectPage(Map<String, Object> map) throws IllegalAccessException {
        final Page page = (Page) map.get("page");
        final T entity = (T) map.get("entity");
        int pageIndex = (page.getPageIndex() - 1) * page.getPageSize();
        return "select * from " + tableByClazz(entity.getClass()) + whereBuilder(entity, "entity") + ORDER_BY + page.getOrderBy() + SPACE + page.getSort() + LIMIT + pageIndex + SPILT + page.getPageSize();
    }

    private String whereBuilder(T entity) throws IllegalAccessException {
        return whereBuilder(entity, "");
    }

    private String whereBuilder(T entity, String alias) throws IllegalAccessException {
        alias = StringUtils.isBlank(alias) ? EMPTY : alias + DOT;
        StringBuilder whereBuilder = new StringBuilder();
        final Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object val = field.get(entity);
            if (val != null) {
                String col = field.getName();
                whereBuilder.append(String.format("%s=#{%s%s}%s", KotStringUtils.camel2Underline(col), alias, col, AND));
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

    public static String tableByClazz(Class<?> entityClass) {
        if (tableCache.containsKey(entityClass)) {
            return tableCache.get(entityClass);
        }
        String tableName;
        // 实体带注解，直接使用做表名
        TableName tableNameAnnotation = entityClass.getAnnotation(TableName.class);
        if (tableNameAnnotation != null && StringUtils.isNoneBlank(tableNameAnnotation.value())) {
            tableName = tableNameAnnotation.value();
        } else {
            // 实体名直接当作表名
            tableName = KotStringUtils.camel2Underline(entityClass.getSimpleName());
        }
        tableCache.put(entityClass, tableName);
        return tableName;
    }

}
