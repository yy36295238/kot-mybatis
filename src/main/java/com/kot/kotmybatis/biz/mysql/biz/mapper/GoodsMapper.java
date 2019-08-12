package com.kot.kotmybatis.biz.mysql.biz.mapper;

import com.kot.kotmybatis.biz.mysql.biz.entity.Goods;
import kot.bootstarter.kotmybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * @author yangyu
 */

@CacheNamespace
public interface GoodsMapper extends BaseMapper<Goods> {
}
