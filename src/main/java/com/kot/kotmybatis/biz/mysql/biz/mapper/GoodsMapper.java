package com.kot.kotmybatis.biz.mysql.biz.mapper;

import com.kot.kotmybatis.biz.mysql.biz.entity.Goods;
import kot.bootstarter.kotmybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author yangyu
 */

@CacheNamespace(flushInterval = 5000)
public interface GoodsMapper extends BaseMapper<Goods> {

    @Select("SELECT * FROM t_goods g INNER JOIN t_order o ON g.id=o.goods_id;")
    List<Map<String, Object>> all();
}
