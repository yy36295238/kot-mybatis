package com.kot.kotmybatis.entity;


import com.kot.kotmybatis.annotation.PrimaryKey;
import com.kot.kotmybatis.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {

    @PrimaryKey
    private Long id;
    private String userName;
    private String password;
    private Integer userStatus;

    public User(int userStatus) {
        this.userStatus = userStatus;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
