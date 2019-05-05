package com.kot.kotmybatis.entity;


import com.kot.kotmybatis.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * @author YangYu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("user")
public class User {

    private Long id;
    private String name;
    private String cellPhone;
    private String email;
    private String userName;
    private String password;
    private Integer userStatus;
    private Long createUser;
    private Date createTime;
    private Date updateTime;

    public User(Long id) {
        this.id = id;
    }

    public User(String userName) {
        this.userName = userName;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
