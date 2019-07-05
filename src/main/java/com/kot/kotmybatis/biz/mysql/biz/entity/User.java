package com.kot.kotmybatis.biz.mysql.biz.entity;

import kot.bootstarter.kotmybatis.annotation.*;
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
@TableName("t_user")
public class User {
    /**
     * 主键
     */
    @Column("id")
    @ID(value = "id")
    private Long id;

    /**
     * 用户id
     */
    @Column(value = "open_id", unique = true)
    private String openId;

    /**
     *
     */
    @Column(value = "UNION_ID", keyWords = "`")
    private String unionId;

    /**
     * 真实姓名
     */
    @Column(value = "real_name", isLike = true, unique = true)
    private String realName;

    /**
     * 登录账号
     */
    @Column(value = "user_name", isLike = true, unique = true)
    private String userName;

    /**
     *
     */
    @Column("password")
    private String password;

    /**
     * 手机号码
     */
    @Column("phone")
    private String phone;

    /**
     * 邮箱
     */
    @Column("email")
    private String email;

    /**
     * 用户头像地址
     */
    @Column("avatar_url")
    private String avatarUrl;

    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    @Column("gender")
    private Integer gender;

    /**
     * 用户所在城市
     */
    @Column("city")
    private String city;

    /**
     * 用户所在省份
     */
    @Column("province")
    private String province;

    /**
     * ip地址
     */
    @Column("ip")
    private String ip;

    /**
     * 用户所在国家
     */
    @Column("country")
    private String country;

    /**
     * 用户的语言，简体中文为zh_CN
     */
    @Column("language")
    private String language;

    /**
     * 用户类型,1=微信用户，2=后台用户
     */
    @Column("type")
    private Integer type;

    /**
     * 激活状态
     */
    @Column("activation")
    private Integer activation;

    /**
     * 状态：1=正常
     */
    @Column("user_status")
    private Integer userStatus;

    /**
     * 乐观锁标识
     */
    @Column(value = "version", version = true)
    private Integer version;

    /**
     * 逻辑删除字段：-1=删除，1=未删除
     */
    @Column("is_delete")
    @Delete("-1")
    private Integer isDelete;

    /**
     * 创建人
     */
    @Column("create_user")
    @Related(clazz = User.class, columns = {"user_name.createUserName"})
    private Long createUser;

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

    /**
     * 创建人用户名
     */
    private String createUserName;
}
