package com.kot.kotmybatis.biz.mysql.biz.entity;

import kot.bootstarter.kotmybatis.annotation.Column;
import kot.bootstarter.kotmybatis.annotation.ID;
import kot.bootstarter.kotmybatis.annotation.Related;
import kot.bootstarter.kotmybatis.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    @Related(clazz = User.class, columns = {"user_name.userName", "real_name.realName"}, pkColumn = "id")
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
}
