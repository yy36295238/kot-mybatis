package com.kot.kotmybatis.service;

import java.util.Collection;
import java.util.List;

/**
 * @author yangyu
 */
public interface BaseMapperManager {

    /**
     * 返回指定字段
     */
    MapperService fields(String field);

    MapperService fields(List<String> fields);

    MapperService skip(Integer skip);

    MapperService limit(Integer limit);

    MapperService page(Integer skip, Integer limit);

    MapperService orderBy(String... sortKey);

    MapperService eq(String key, Object value);

    MapperService neq(String key, Object value);

    MapperService in(String key, Object value);

    MapperService in(String key, Object[] value);

    MapperService in(String key, Collection<?> value);

    MapperService nin(String key, Object value);

    MapperService nin(String key, Object[] value);

    MapperService nin(String key, Collection<?> value);

    MapperService lt(String key, Object value);

    MapperService gt(String key, Object value);

    MapperService lte(String key, Object value);

    MapperService gte(String key, Object value);

    MapperService or(String key, Object value);

    MapperService like(String key, Object value);

    MapperService between(String key, Object left, Object right);
}
