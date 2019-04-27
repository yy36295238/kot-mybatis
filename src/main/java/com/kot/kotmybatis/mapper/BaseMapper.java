package com.kot.kotmybatis.mapper;

import com.kot.kotmybatis.utils.KotStringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.io.Serializable;
import java.util.List;

public interface BaseMapper<T> {

    @SelectProvider(type = BaseProvider.class, method = "findById")
    T findById(@Param("tableName") String tableName, @Param("id") Serializable id);

    default T findById(Serializable id) throws Exception {
        return findById(KotStringUtils.tableName(this), id);
    }

    @SelectProvider(type = BaseProvider.class, method = "findOne")
    List<T> findOne(T entity);

    @SelectProvider(type = BaseProvider.class, method = "list")
    List<T> list(T entity);

}
