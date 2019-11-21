package com.kot.kotmybatis.biz.mysql.biz.entity;

import kot.bootstarter.kotmybatis.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author yangyu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("t_order")
public class Order {
    /**
     *
     */
    @Column("id")
    @ID("id")
    private Long id;

    /**
     * 商品id
     */
    @Column("goods_id")
    @Related(clazz = Goods.class, columns = {"good_name.goodName"})
    private Long goodsId;

    /**
     * 用户id
     */
    @Column("user_id")
    @Related(clazz = User.class, columns = {"user_name.userName", "real_name.realName"}, fkColumn = "id")
    private Long userId;

    /**
     * 创建时间
     */
    @Column("create_time")
    private Date createTime;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 关联表集合
     */
    @UnionItem(clazz = Goods.class, fkColumn = "goodsId")
    private List<Goods> goodsList;

    /**
     * 关联表对象
     */
    @UnionItem(clazz = Goods.class, fkColumn = "goodsId")
    private Goods goods;
}
