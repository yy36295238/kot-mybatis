package com.kot.kotmybatis.service.impl;

import com.kot.kotmybatis.common.Page;
import com.kot.kotmybatis.mapper.BaseMapper;
import com.kot.kotmybatis.service.MapperService;
import com.kot.kotmybatis.utils.KotBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author YangYu
 * 通用实现
 */
@Service
public class MapperServiceImpl<T> implements MapperService<T> {

    @Autowired
    private BaseMapper<T> baseMapper;

    @Override
    public MapperService gt(String col, Object val) {
        // TODO TheadLocal 做
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
    public T findById(String tableName, Serializable id) {
        return baseMapper.findById(tableName, id);
    }

    @Override
    public T findById(Serializable id) {
        return baseMapper.findById(id);
    }

    @Override
    public T findOne(T entity) {
        return baseMapper.findOne(entity);
    }

    @Override
    public List<T> list(T entity) {
        return baseMapper.list(entity);
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
        return baseMapper.selectCount(entity);
    }

    @Override
    public Page<T> selectPage(Page<T> page, T entity) {
        final int count = baseMapper.selectCount(entity);
        if (count > 0) {
            final List<T> list = baseMapper.selectPage(page, entity);
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
}
