package com.kot.kotmybatis.biz.mysql.biz.service.impl;

import com.kot.kotmybatis.biz.mysql.biz.entity.Goods;
import com.kot.kotmybatis.biz.mysql.biz.mapper.GoodsMapper;
import com.kot.kotmybatis.biz.mysql.biz.service.IGoodsService;
import kot.bootstarter.kotmybatis.service.impl.MapperManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author yangyu
 */
@Service
public class GoodsServiceImpl extends MapperManagerServiceImpl<Goods> implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Map<String, Object>> all() {
        return goodsMapper.all();
    }
}
