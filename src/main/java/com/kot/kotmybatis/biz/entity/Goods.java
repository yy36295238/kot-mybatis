package com.kot.kotmybatis.biz.entity;

import kot.bootstarter.kotmybatis.annotation.Column;
import kot.bootstarter.kotmybatis.annotation.ID;
import kot.bootstarter.kotmybatis.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangyu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("t_goods")
public class Goods {
    /**
     *
     */
    @Column("id")
    @ID("id")
    private Long id;

    /**
     *
     */
    @Column("good_name")
    private String goodName;

    /**
     *
     */
    @Column("num")
    private Integer num;

    /**
     *
     */
    @Column("sold")
    private Integer sold;

    /**
     * 乐观锁版本
     */
    @Column(value = "version", version = true)
    private Integer version;
}
