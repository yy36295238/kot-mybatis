package com.kot.kotmybatis;

import com.kot.kotmybatis.biz.entity.User;
import com.kot.kotmybatis.biz.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KotMybatisConditionTests {


    @Autowired
    private UserService userService;

    @Test
    public void eq() {
        final List<User> users = userService.newQuery().eq("user_name", "admin").eq("create_user", null).neq("user_status", -9).list(new User());
        System.err.println(users);
    }

    @Test
    public void neq() {
        final List<User> users = userService.newQuery().neq("user_name", "admin").list(new User());
        System.err.println(users);
    }

    @Test
    public void in() {
        final List<User> users = userService.newQuery().eq("id", 2).in("user_name", Arrays.asList("admin", "test")).list(new User());
        System.err.println(users);
    }

    @Test
    public void nin() {
        final List<User> users = userService.newQuery().nin("user_name", Arrays.asList("admin", "test")).list(new User());
        System.err.println(users);
    }

    @Test
    public void ltAndGt() {
        final List<User> users = userService.newQuery().lt("id", 6).gt("id", 3).list(new User());
        System.err.println(users);
    }

    @Test
    public void lteAndGte() {
        final List<User> users = userService.newQuery().lte("id", 3).gte("id", 2).list(new User());
        System.err.println(users);
    }

    @Test
    public void between() {
        final List<User> users = userService.newQuery().between("id", 12, 13).list(new User());
        System.err.println(users);
    }

    @Test
    public void or() {
        final List<User> users = userService.newQuery().or("user_name", "test").like("email", "5q8kdo5s2kr5vgewqg@msn.com").list(new User());
        System.err.println(users);
    }

    @Test
    public void like() {
        final List<User> users = userService.newQuery().eq("user_status", 1).like("user_name", "adm").list(new User());
        System.err.println(users);
    }

    @Test
    public void isNull() {
        final List<User> users = userService.newQuery().isNull("create_user").list(new User());
        System.err.println(users);
    }


}
