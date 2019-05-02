package com.kot.kotmybatis.mapper;

import com.kot.kotmybatis.common.CT;
import com.kot.kotmybatis.common.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author YangYu
 */
public interface BaseMapper<T> {

    /**
     * 保存操作
     */
    int insert(T entity);

    /**
     * 查询操作
     */
    @SelectProvider(type = BaseProvider.class)
    T findById(@Param("table") Class<T> clazz, @Param("id") Serializable id);

    @SelectProvider(type = BaseProvider.class)
    T findOne(@Param(CT.SQL_CONDITION) String conditionList, @Param(CT.ALIAS_CONDITION) Map<String, Object> conditionMap, @Param(CT.ALIAS_ENTITY) T entity);

    @SelectProvider(type = BaseProvider.class)
    List<T> list(@Param(CT.SQL_CONDITION) String conditionList, @Param(CT.ALIAS_CONDITION) Map<String, Object> conditionMap, @Param(CT.ALIAS_ENTITY) T entity);

    List<T> selectBatchIds(Collection<? extends Serializable> ids);

    List<T> selectByMap(Map<String, Object> columnMap);

    @SelectProvider(type = BaseProvider.class)
    int selectCount(@Param(CT.SQL_CONDITION) String conditionList, @Param(CT.ALIAS_ENTITY) T entity);

    @SelectProvider(type = BaseProvider.class)
    List<T> selectPage(@Param(CT.SQL_CONDITION) String conditionList, @Param("page") Page page, @Param(CT.ALIAS_ENTITY) T entity);

    /**
     * 删除操作
     */
    int deleteById(Serializable id);

    int deleteByMap(Map<String, Object> columnMap);

    int deleteByEntity(T entity);

    int deleteBatchIds(Collection<? extends Serializable> ids);

    /**
     * 更新操作
     */
    int updateById(T entity);

    int update(T setEntity, T whereEntity);
}
