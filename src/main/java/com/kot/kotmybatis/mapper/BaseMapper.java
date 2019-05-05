package com.kot.kotmybatis.mapper;

import com.kot.kotmybatis.common.CT;
import com.kot.kotmybatis.common.Page;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author YangYu
 */
public interface BaseMapper<T> {

    /**
     * 保存操作
     */
    @InsertProvider(type = BaseProvider.class)
    int insert(T entity);

    /**
     * 查询操作
     */
    @SelectProvider(type = BaseProvider.class)
    T findOne(@Param(CT.COLUMNS) Set<String> columns, @Param(CT.SQL_CONDITION) String conditionList, @Param(CT.ALIAS_CONDITION) Map<String, Object> conditionMap, @Param(CT.ALIAS_ENTITY) T entity);

    @SelectProvider(type = BaseProvider.class)
    List<T> list(@Param(CT.COLUMNS) Set<String> columns, @Param(CT.SQL_CONDITION) String conditionList, @Param(CT.ALIAS_CONDITION) Map<String, Object> conditionMap, @Param(CT.ALIAS_ENTITY) T entity);

    @SelectProvider(type = BaseProvider.class)
    int count(@Param(CT.SQL_CONDITION) String conditionList, @Param(CT.ALIAS_CONDITION) Map<String, Object> conditionMap, @Param(CT.ALIAS_ENTITY) T entity);

    @SelectProvider(type = BaseProvider.class)
    List<T> selectPage(@Param(CT.COLUMNS) Set<String> columns, @Param(CT.SQL_CONDITION) String conditionList, @Param("page") Page page, @Param(CT.ALIAS_CONDITION) Map<String, Object> conditionMap, @Param(CT.ALIAS_ENTITY) T entity);

    @DeleteProvider(type = BaseProvider.class)
    int delete(@Param(CT.COLUMNS) Set<String> columns, @Param(CT.SQL_CONDITION) String conditionList, @Param(CT.ALIAS_CONDITION) Map<String, Object> conditionMap, @Param(CT.ALIAS_ENTITY) T entity);

    /**
     * 更新操作
     */
    int updateById(T entity);

    int update(T setEntity, T whereEntity);
}
