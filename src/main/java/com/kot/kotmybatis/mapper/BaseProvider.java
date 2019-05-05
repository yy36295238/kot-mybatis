package com.kot.kotmybatis.mapper;

import com.kot.kotmybatis.annotation.TableName;
import com.kot.kotmybatis.common.CT;
import com.kot.kotmybatis.common.Page;
import com.kot.kotmybatis.utils.KotBeanUtils;
import com.kot.kotmybatis.utils.KotStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
public class BaseProvider<T> implements ProviderMethodResolver {

    private static final Map<Class, String> TABLE_CACHE = new HashMap<>();

    public String insert(T entity) {
        return insertSqlBuilder(entity);
    }

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

    public String delete(Map<String, Object> map) {
        final T entity = (T) map.get(CT.ALIAS_ENTITY);
        final String conditionSql = (String) map.get(CT.SQL_CONDITION);
        final String whereBuilder = whereBuilder(entity, conditionSql);
        Assert.hasLength(whereBuilder, "delete must be contain where condition");
        return new SQL().DELETE_FROM(tableByClazz(entity)).WHERE(whereBuilder).toString();
    }

    public String updateById(T entity) {
        final Object id = KotBeanUtils.fieldVal("id", entity);
        Assert.notNull(id, "id is null");
        return new SQL().UPDATE(tableByClazz(entity)).SET(updateSqlBuilder(entity)).WHERE("id=#{id}").toString();
    }

    public String update(Map<String, Object> map) {
        final T whereEntity = (T) map.get(CT.ALIAS_ENTITY);
        final T setEntity = (T) map.get(CT.SET_ENTITY);
        final String conditionSql = (String) map.get(CT.SQL_CONDITION);
        final String whereBuilder = whereBuilder(whereEntity, conditionSql);
        Assert.hasLength(whereBuilder, "update must be contain where condition");
        return new SQL().UPDATE(tableByClazz(whereEntity)).SET(updateSqlBuilder(setEntity, CT.SET_ENTITY)).WHERE(whereBuilder).toString();
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
     * 插入SQL
     */
    private static String insertSqlBuilder(Object entity) {
        StringBuilder columnsBuilder = new StringBuilder();
        StringBuilder valuesBuilder = new StringBuilder();
        final Field[] fields = entity.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object val = field.get(entity);
                if (val != null) {
                    String column = KotStringUtils.camel2Underline(field.getName());
                    columnsBuilder.append("`").append(column).append("`").append(CT.SPILT);
                    valuesBuilder.append("#{").append(field.getName()).append("}").append(CT.SPILT);
                }
            }
            final String columns = columnsBuilder.deleteCharAt(columnsBuilder.lastIndexOf(",")).toString();
            final String values = valuesBuilder.deleteCharAt(valuesBuilder.lastIndexOf(",")).toString();
            return new SQL().INSERT_INTO(tableByClazz(entity)).INTO_COLUMNS(columns).INTO_VALUES(values).toString();
        } catch (Exception e) {
            throw new RuntimeException("", e);
        }
    }

    /**
     * 更新SQL
     */
    private static String updateSqlBuilder(Object entity) {
        return updateSqlBuilder(entity, "");
    }

    private static String updateSqlBuilder(Object entity, String alias) {
        StringBuilder columnsBuilder = new StringBuilder();
        final Field[] fields = entity.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object val = field.get(entity);
                if (val != null && !"id".equals(field.getName())) {
                    String column = KotStringUtils.camel2Underline(field.getName());
                    columnsBuilder.append("`").append(column).append("`").append("=");
                    String aliasField = StringUtils.isBlank(alias) ? field.getName() : alias + CT.DOT + field.getName();
                    columnsBuilder.append("#{").append(aliasField).append("}").append(CT.SPILT);
                }
            }
            return columnsBuilder.deleteCharAt(columnsBuilder.lastIndexOf(",")).toString();
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
        if (TABLE_CACHE.containsKey(entityClass)) {
            return TABLE_CACHE.get(entityClass);
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
        TABLE_CACHE.put(entityClass, tableName);
        return tableName;
    }

    public static void main(String[] args) {
        Assert.hasLength("", "1");
    }

}
