package com.kot.kotmybatis.biz.pg.biz.entity;

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
@TableName("t_account")
public class Account {
    /**
     * 主键
     */
    @Column("id")
    @ID("id")
    private Long id;

    /**
     * 姓名
     */
    @Column("account_name")
    private String accountName;

    /**
     * 身份证号
     */
    @Column("account_no")
    private String accountNo;

    /**
     * 银行卡号
     */
    @Column("acctount_bank_no")
    private String acctountBankNo;

    /**
     * 删除标记
     */
    @Column("is_delete")
    private Integer isDelete;

    /**
     * 创建时间
     */
    @Column("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column("update_time")
    private Date updateTime;
}
