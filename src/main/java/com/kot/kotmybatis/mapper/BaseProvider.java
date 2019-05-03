package com.kot.kotmybatis.mapper;

import com.kot.kotmybatis.annotation.TableName;
import com.kot.kotmybatis.common.CT;
import com.kot.kotmybatis.common.Page;
import com.kot.kotmybatis.utils.KotStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class BaseProvider<T> implements ProviderMethodResolver {

    private static final Map<Class, String> tableCache = new HashMap<>();


    public String findById(@Param("table") Class<T> clazz, @Param("id") Serializable id) {
        return "select * from " + tableByClazz(clazz) + " where id = #{id}";
    }

    public String findOne(Map<String, Object> map) throws IllegalAccessException {
        return list(map) + CT.LIMIT_ONE;
    }

    public String list(Map<String, Object> map) {
        final String conditionSql = (String) map.get(CT.SQL_CONDITION);
        final T entity = (T) map.get(CT.ALIAS_ENTITY);
        return "select * from " + tableByClazz(entity) + whereBuilder(entity, conditionSql);
    }

    public String selectCount(@Param(CT.SQL_CONDITION) String conditionSql, T entity) {
        return "select count(*) from " + tableByClazz(entity.getClass()) + whereBuilder(entity, conditionSql);
    }

    public String selectPage(Map<String, Object> map) throws IllegalAccessException {
        final Page page = (Page) map.get("page");
        final T entity = (T) map.get(CT.ALIAS_ENTITY);
        int pageIndex = (page.getPageIndex() - 1) * page.getPageSize();
        return "select * from " + tableByClazz(entity.getClass()) + whereBuilder(entity, CT.ALIAS_ENTITY) + CT.ORDER_BY + page.getOrderBy() + CT.SPACE + page.getSort() + CT.LIMIT + pageIndex + CT.SPILT + page.getPageSize();
    }

    private String whereBuilder(T entity, String conditionSql) {
        StringBuilder whereBuilder = new StringBuilder();
        // 实体条件
        entitySqlBuilder(whereBuilder, entity);
        // 链式条件
        whereBuilder.append(conditionSql);

        String condition = "";
        final int len = whereBuilder.length();
        if (len > 0) {
            // 删除第一个一个`AND`、`OR`
            condition = CT.WHERE + KotStringUtils.removeFirstAndOr(whereBuilder.toString());
        }
        return condition;
    }

    private static void entitySqlBuilder(StringBuilder whereBuilder, Object entity) {
        if (!isClass(entity.getClass())) {
            final Field[] fields = entity.getClass().getDeclaredFields();
            try {
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object val = field.get(entity);
                    if (val != null) {
                        String col = field.getName();
                        whereBuilder.append(String.format("%s%s=#{%s%s}", KotStringUtils.camel2Underline(col), CT.AND, CT.ALIAS_ENTITY + CT.DOT, col));
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("", e);
            }
        }
    }

    private static String tableByClazz(Object obj) {
        Class<?> entityClass = isClass(obj.getClass()) ? (Class<?>) obj : obj.getClass();
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

    private static boolean isClass(Class clazz) {
        return clazz.getSimpleName().equals("Class");
    }


}
