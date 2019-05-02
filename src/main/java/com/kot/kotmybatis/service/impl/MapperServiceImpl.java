package com.kot.kotmybatis.service.impl;

import com.kot.kotmybatis.common.CT;
import com.kot.kotmybatis.common.Page;
import com.kot.kotmybatis.enums.ConditionEnum;
import com.kot.kotmybatis.mapper.BaseMapper;
import com.kot.kotmybatis.service.MapperService;
import com.kot.kotmybatis.utils.KotBeanUtils;
import com.kot.kotmybatis.utils.KotStringUtils;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author YangYu
 * 通用实现
 */
public class MapperServiceImpl<T> implements MapperService<T> {

    private BaseMapper<T> baseMapper;

    MapperServiceImpl(BaseMapper<T> baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 各种条件集合
     */
    private Map<String, Object> eqMap = null;
    private Map<String, Object> neqMap = null;
    private Map<String, Object> inMap = null;
    private Map<String, Object> ninMap = null;
    private Map<String, Object> ltMap = null;
    private Map<String, Object> gtMap = null;
    private Map<String, Object> lteMap = null;
    private Map<String, Object> gteMap = null;
    private Map<String, Object> orMap = null;
    private Map<String, Object> likeMap = null;
    private Map<String, Object> conditonMap = new HashMap<>();

    @Override
    public MapperService<T> fields(String field) {
        return this;
    }

    @Override
    public MapperService<T> fields(List<String> fields) {
        return this;
    }

    @Override
    public MapperService<T> skip(Integer skip) {
        return this;
    }

    @Override
    public MapperService<T> limit(Integer limit) {
        return this;
    }

    @Override
    public MapperService<T> page(Integer skip, Integer limit) {
        return this;
    }

    @Override
    public MapperService<T> orderBy(String... sortKey) {
        return this;
    }

    @Override
    public MapperService<T> eq(String key, Object value) {
        (eqMap = map(eqMap)).put(key, value);
        return this;
    }

    @Override
    public MapperService<T> neq(String key, Object value) {
        (neqMap = map(neqMap)).put(key, value);
        return this;
    }

    @Override
    public MapperService<T> in(String key, Object[] values) {
        (inMap = map(inMap)).put(key, Arrays.asList(values));
        return this;
    }

    @Override
    public MapperService<T> in(String key, Collection<?> values) {
        (inMap = map(inMap)).put(key, values);
        return this;
    }

    @Override
    public MapperService<T> nin(String key, Object[] values) {
        (ninMap = map(ninMap)).put(key, Arrays.asList(values));
        return this;
    }

    @Override
    public MapperService<T> nin(String key, Collection<?> values) {
        (ninMap = map(ninMap)).put(key, values);
        return this;
    }

    @Override
    public MapperService<T> lt(String key, Object value) {
        (ltMap = map(ltMap)).put(key, value);
        return this;
    }

    @Override
    public MapperService<T> gt(String key, Object value) {
        (gtMap = map(gtMap)).put(key, value);
        return this;
    }


    @Override
    public MapperService<T> lte(String key, Object value) {
        (lteMap = map(lteMap)).put(key, value);
        return this;
    }

    @Override
    public MapperService<T> gte(String key, Object value) {
        (gteMap = map(gteMap)).put(key, value);
        return this;
    }

    @Override
    public MapperService<T> or(String key, Object value) {
        (orMap = map(orMap)).put(key, value);
        return this;
    }

    @Override
    public MapperService<T> like(String key, Object value) {
        (likeMap = map(likeMap)).put(key, value);
        return this;
    }

    @Override
    public MapperService<T> between(String key, Object left, Object right) {
        map(gteMap).put(key, left);
        map(lteMap).put(key, right);
        return this;
    }

    @Override
    public int insert(T entity) {
        return baseMapper.insert(entity);
    }

    @Override
    public int save(T entity) {
        final Object id = KotBeanUtils.fieldVal("id", entity);
        if (id == null) {
            return insert(entity);
        }
        return updateById(entity);
    }

    @Override
    public T findById(Class<T> clazz, Serializable id) {
        return baseMapper.findById(clazz, id);
    }

    @Override
    public T findOne(T entity) {
        return baseMapper.findOne(conditionSql(), conditonMap, entity);
    }

    @Override
    public List<T> list(T entity) {
        return baseMapper.list(conditionSql(), conditonMap, entity);
    }

    @Override
    public List<T> selectBatchIds(Collection<? extends Serializable> ids) {
        return baseMapper.selectBatchIds(ids);
    }

    @Override
    public List<T> selectByMap(Map<String, Object> columnMap) {
        return baseMapper.selectByMap(columnMap);
    }

    @Override
    public Integer selectCount(T entity) {
        return baseMapper.selectCount(conditionSql(), entity);
    }

    @Override
    public Page<T> selectPage(Page<T> page, T entity) {
        final int count = baseMapper.selectCount(conditionSql(), entity);
        if (count > 0) {
            final List<T> list = baseMapper.selectPage(conditionSql(), page, entity);
            page.setData(list);
            page.setTotal(count);
        }
        return page;
    }


    @Override
    public int deleteById(Serializable id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public int deleteByMap(Map<String, Object> columnMap) {
        return baseMapper.deleteByMap(columnMap);
    }

    @Override
    public int deleteByEntity(T entity) {
        return baseMapper.deleteByEntity(entity);
    }

    @Override
    public int deleteBatchIds(Collection<? extends Serializable> ids) {
        return baseMapper.deleteBatchIds(ids);
    }

    @Override
    public int updateById(T entity) {
        return baseMapper.updateById(entity);
    }

    @Override
    public int update(T setEntity, T whereEntity) {
        return baseMapper.update(setEntity, whereEntity);
    }

    private Map<String, Object> map(Map<String, Object> conditionMap) {
        return (conditionMap == null ? new HashMap<>() : conditionMap);
    }

    /**
     * 实际查询条件
     */
    private String conditionSql() {
        StringBuilder sqlBuilder = new StringBuilder();
        if (eqMap != null) {
            eqMap.forEach((k, v) -> sqlBuilder(sqlBuilder, ConditionEnum.EQ, k, v));
            conditonMap.putAll(eqMap);
        }
        if (neqMap != null) {
            neqMap.forEach((k, v) -> sqlBuilder(sqlBuilder, ConditionEnum.EQ, k, v));
            conditonMap.putAll(neqMap);
        }
        if (inMap != null) {
            inMap.forEach((k, v) -> sqlBuilder(sqlBuilder, ConditionEnum.IN, k, v));
            conditonMap.putAll(inMap);
        }
        return sqlBuilder.toString();
    }

    private void sqlBuilder(StringBuilder sqlBuilder, ConditionEnum conditionEnum, String k, Object v) {
        final StringBuilder appender = sqlBuilder.append(CT.SPACE).append(k).append(conditionEnum.oper);
        // id in ('','');
        final String collect;
        if (conditionEnum == ConditionEnum.IN) {
            Collection collection = (Collection) v;
            if (isStringForCollection(collection)) {
                Collection<String> strings = (Collection<String>) v;
                collect = strings.stream().peek(c -> c = "'" + c + "'").collect(Collectors.joining(","));
            } else {
                collect = KotStringUtils.joinSplit(collection);
            }
            appender.append(CT.ALIAS_CONDITION).append(CT.DOT).append(collect);
        } else {
            appender.append("#{").append(CT.ALIAS_CONDITION).append(CT.DOT).append(k).append("}");
        }
        appender.append(CT.AND);


    }

    private boolean isStringForCollection(Collection<?> collection) {
        for (Object o : collection) {
            return o instanceof String;
        }
        return false;
    }


}
