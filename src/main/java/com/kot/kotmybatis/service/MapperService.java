package com.kot.kotmybatis.service;

import com.kot.kotmybatis.common.Page;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface MapperService<T> extends BaseMapperManager {

//    BaseMapperManager gt(String col, Object val);

    /**
     * 保存操作
     */
    int insert(T entity);

    int save(T entity) throws IllegalAccessException;

    /**
     * 查询操作
     */
    T findById(String tableName, Serializable id);

    T findById(Serializable id);

    T findOne(T entity);

    List<T> list(T entity);

    List<T> selectBatchIds(Collection<? extends Serializable> ids);

    List<T> selectByMap(Map<String, Object> columnMap);

    Integer selectCount(T entity);

    Page<T> selectPage(Page<T> page, T entity);

    /**
     * 删除操作
     */
    int deleteById(Serializable id);

    int deleteByMap(Map<String, Object> columnMap);

    int deleteByEntity(T entity);

    int deleteBatchIds(Collection<? extends Serializable> idList);

    /**
     * 更新操作
     */
    int updateById(T entity);

    int update(T setEntity, T whereEntity);
}
