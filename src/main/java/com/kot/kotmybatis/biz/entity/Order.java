package com.kot.kotmybatis.biz.entity;

import kot.bootstarter.kotmybatis.annotation.Column;
import kot.bootstarter.kotmybatis.annotation.ID;
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
   *
   */
  @Column("goods_id")
  private Long goodsId;

  /**
   *
   */
  @Column("create_time")
  private Date createTime;
}
