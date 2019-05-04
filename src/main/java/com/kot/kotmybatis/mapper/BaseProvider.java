package com.kot.kotmybatis.mapper;

import com.kot.kotmybatis.annotation.TableName;
import com.kot.kotmybatis.common.CT;
import com.kot.kotmybatis.common.Page;
import com.kot.kotmybatis.utils.KotStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
public class BaseProvider<T> implements ProviderMethodResolver {

    private static final Map<Class, String> tableCache = new HashMap<>();

    public String findOne(Map<String, Object> map) {
        return list(map) + CT.LIMIT_ONE;
    }

    public String list(Map<String, Object> map) {
        return selectGeneralSql(map, new SQL(), "*").toString();
    }

    public String count(Map<String, Object> map) {
        return selectGeneralSql(map, new SQL(), "COUNT(*)").toString();
    }

    public String selectPage(Map<String, Object> map) {
        final Page page = (Page) map.get("page");
        int pageIndex = (page.getPageIndex() - 1) * page.getPageSize();
        final SQL sql = selectGeneralSql(map, new SQL(), "*");
        if (StringUtils.isNotBlank(page.getOrderBy())) {
            sql.ORDER_BY(page.getOrderBy() + CT.SPACE + page.getSort());
        }
        return sql.toString() + CT.LIMIT + pageIndex + CT.SPILT + page.getPageSize();
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
            condition = KotStringUtils.removeFirstAndOr(whereBuilder.toString());
        }
        return condition;
    }

    private static void entitySqlBuilder(StringBuilder whereBuilder, Object entity) {
        final Field[] fields = entity.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object val = field.get(entity);
                if (val != null) {
                    String col = field.getName();
                    whereBuilder.append(String.format("%s%s=#{%s%s}", CT.AND, KotStringUtils.camel2Underline(col), CT.ALIAS_ENTITY + CT.DOT, col));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("", e);
        }
    }

    /**
     * 通用查询构建
     */
    private SQL selectGeneralSql(Map<String, Object> map, SQL sql, String column) {
        final T entity = (T) map.get(CT.ALIAS_ENTITY);
        final String conditionSql = (String) map.get(CT.SQL_CONDITION);
        final Set<String> columns = map.containsKey(CT.COLUMNS) ? (Set<String>) map.get(CT.COLUMNS) : null;
        sql.SELECT(CollectionUtils.isEmpty(columns) ? column : String.join(CT.SPILT, columns));
        return sql.FROM(tableByClazz(entity)).WHERE(whereBuilder(entity, conditionSql));
    }

    private static String tableByClazz(Object obj) {
        Class<?> entityClass = obj.getClass();
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
