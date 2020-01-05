package com.kot.kotmybatis.biz.mysql.biz.entity;

import kot.bootstarter.kotmybatis.annotation.Column;
import kot.bootstarter.kotmybatis.annotation.ID;
import kot.bootstarter.kotmybatis.annotation.TableName;
import kot.bootstarter.kotmybatis.annotation.UnionItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangyu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("t_goods")
public class Goods implements Serializable {
    /**
     *
     */
    @Column("id")
    @ID("id")
    private Long id;

    /**
     * 商品名称
     */
    @Column("good_name")
    private String goodName;

    /**
     * 商品总数
     */
    @Column("num")
    private Integer num;

    /**
     * 已售数量
     */
    @Column("sold")
    private Integer sold;

    /**
     * 乐观锁版本
     */
    @Column(value = "version", version = true)
    private Integer version;

    @UnionItem(clazz = Order.class, currColumn = "id", fkColumn = "goodsId")
    private List<Order> orders;

    private ArrayList<Order> orderArrayList;

    private Order order;

}
