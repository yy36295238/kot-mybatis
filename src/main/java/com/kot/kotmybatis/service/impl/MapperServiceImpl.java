package com.kot.kotmybatis.service.impl;

import com.kot.kotmybatis.common.CT;
import com.kot.kotmybatis.common.Page;
import com.kot.kotmybatis.enums.ConditionEnum;
import com.kot.kotmybatis.mapper.BaseMapper;
import com.kot.kotmybatis.service.MapperService;
import com.kot.kotmybatis.utils.KotBeanUtils;

import java.io.Serializable;
import java.util.*;

/**
 * @author YangYu
 * 通用实现
 */
public class MapperServiceImpl<T> implements MapperService<T> {

    private BaseMapper<T> baseMapper;

    public MapperServiceImpl(BaseMapper baseMapper) {
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

    @Override
    public MapperService fields(String field) {
        return this;
    }

    @Override
    public MapperService fields(List<String> fields) {
        return this;
    }

    @Override
    public MapperService skip(Integer skip) {
        return this;
    }

    @Override
    public MapperService limit(Integer limit) {
        return this;
    }

    @Override
    public MapperService page(Integer skip, Integer limit) {
        return this;
    }

    @Override
    public MapperService orderBy(String... sortKey) {
        return this;
    }

    @Override
    public MapperService eq(String key, Object value) {
        (eqMap = map(eqMap)).put(key, value);
        return this;
    }

    @Override
    public MapperService neq(String key, Object value) {
        (neqMap = map(neqMap)).put(key, value);
        return this;
    }

    @Override
    public MapperService in(String key, Object[] values) {
        (inMap = map(inMap)).put(key, Arrays.asList(values));
        return this;
    }

    @Override
    public MapperService in(String key, Collection<?> values) {
        (inMap = map(inMap)).put(key, Arrays.asList(values));
        return this;
    }

    @Override
    public MapperService nin(String key, Object[] values) {
        (ninMap = map(ninMap)).put(key, Arrays.asList(values));
        return this;
    }

    @Override
    public MapperService nin(String key, Collection<?> values) {
        (ninMap = map(ninMap)).put(key, Arrays.asList(values));
        return this;
    }

    @Override
    public MapperService lt(String key, Object value) {
        (ltMap = map(ltMap)).put(key, value);
        return this;
    }

    @Override
    public MapperService gt(String key, Object value) {
        (gtMap = map(gtMap)).put(key, value);
        return this;
    }


    @Override
    public MapperService lte(String key, Object value) {
        (lteMap = map(lteMap)).put(key, value);
        return this;
    }

    @Override
    public MapperService gte(String key, Object value) {
        (gteMap = map(gteMap)).put(key, value);
        return this;
    }

    @Override
    public MapperService or(String key, Object value) {
        (orMap = map(orMap)).put(key, value);
        return this;
    }

    @Override
    public MapperService like(String key, Object value) {
        (likeMap = map(likeMap)).put(key, value);
        return this;
    }

    @Override
    public MapperService between(String key, Object left, Object right) {
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
        return baseMapper.findOne(conditionList(), entity);
    }

    @Override
    public List<T> list(T entity) {
        return baseMapper.list(conditionList(), entity);
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
        return baseMapper.selectCount(conditionList(), entity);
    }

    @Override
    public Page<T> selectPage(Page<T> page, T entity) {
        final int count = baseMapper.selectCount(conditionList(), entity);
        if (count > 0) {
            final List<T> list = baseMapper.selectPage(conditionList(), page, entity);
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
        return conditionMap = (conditionMap == null ? new HashMap<>() : conditionMap);
    }

    /**
     * 实际查询条件
     */
    private String conditionList() {
        final List<Map<String, Object>> maps = Arrays.asList(eqMap, neqMap, inMap, ninMap, ltMap, gtMap, lteMap, gteMap, orMap, likeMap);
        StringBuilder sqlBuilder = new StringBuilder();
        if (eqMap != null) {
            eqMap.forEach((k, v) -> {
                sqlBuilder.append(CT.SPACE).append(k).append(ConditionEnum.EQ.oper).append(v).append(CT.AND);
            });
        }
        if (neqMap != null) {
            eqMap.forEach((k, v) -> {
                sqlBuilder.append(CT.SPACE).append(k).append(ConditionEnum.NEQ.oper).append(v).append(CT.AND);
            });
        }
        return sqlBuilder.toString();
    }

}
