package com.kot.kotmybatis.service.impl;

import com.kot.kotmybatis.mapper.BaseMapper;
import com.kot.kotmybatis.service.MapperManagerService;
import com.kot.kotmybatis.service.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YangYu
 */
@Service
public class MapperManagerServiceImpl<T> implements MapperManagerService<T> {

    @Autowired
    private BaseMapper<T> baseMapper;

    @Override
    public MapperService<T> newQuery() {
        return new MapperServiceImpl<>(baseMapper);
    }

    @Override
    public MapperService<T> newUpdate() {
        return new MapperServiceImpl<>(baseMapper);
    }
}
