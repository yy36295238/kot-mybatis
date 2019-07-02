package com.kot.kotmybatis.biz.mysql.biz.service.impl;

import com.kot.kotmybatis.biz.mysql.biz.entity.Goods;
import com.kot.kotmybatis.biz.mysql.biz.service.IGoodsService;
import kot.bootstarter.kotmybatis.service.impl.MapperManagerServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author yangyu
 */
@Service
public class GoodsServiceImpl extends MapperManagerServiceImpl<Goods> implements IGoodsService {
}
