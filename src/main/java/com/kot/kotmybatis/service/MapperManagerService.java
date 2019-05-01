package com.kot.kotmybatis.service;

public interface MapperManagerService<T> {
    MapperService<T> newQuery();
    MapperService<T> newUpdate();
}
