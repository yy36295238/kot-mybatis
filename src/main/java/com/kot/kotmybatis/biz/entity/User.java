package com.kot.kotmybatis.biz.entity;

import kot.bootstarter.kotmybatis.annotation.Delete;
import kot.bootstarter.kotmybatis.annotation.Exist;
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
@TableName("user")
public class User {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private String openId;

    /**
     *
     */
    private String unionId;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 登录账号
     */
    private String userName;

    /**
     *
     */
    private String password;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户头像地址
     */
    private String avatarUrl;

    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    private Integer gender;

    /**
     * 用户所在城市
     */
    private String city;

    /**
     * 用户所在省份
     */
    private String province;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 用户所在国家
     */
    private String country;

    /**
     * 用户的语言，简体中文为zh_CN
     */
    private String language;

    /**
     * 用户类型,1=微信用户，2=后台用户
     */
    private Integer type;

    /**
     * 激活状态
     */
    private Integer activation;

    /**
     * 状态：1=正常
     */
    @Delete("-1")
    private Integer userStatus;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private Date updateTime;

    @Exist(false)
    private String test;

    public User(String userName, String password, Integer type, Long createUser, Date createTime) {
        this.userName = userName;
        this.password = password;
        this.type = type;
        this.createUser = createUser;
        this.createTime = createTime;
    }

}
