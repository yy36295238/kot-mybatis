package com.kot.kotmybatis.biz.mysql.biz.service;

import com.kot.kotmybatis.biz.mysql.biz.entity.Goods;
import kot.bootstarter.kotmybatis.service.MapperManagerService;

import java.util.List;
import java.util.Map;

/**
 * @author yangyu
 */
public interface IGoodsService extends MapperManagerService<Goods> {

    List<Map<String, Object>> all();

    String getCar();
}
