package com.kot.kotmybatis;

import com.alibaba.fastjson.JSON;
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
        final List<User> users = userService.newQuery().eq("user_name", "867KV37tc2").eq("create_user", 876).neq("user_status", -9).list(User.builder().userStatus(1).build());
        println(users);
    }

    @Test
    public void eqLambda() {
        final User user = User.builder().userStatus(1).build();
        final List<User> users = userService.newQuery().eq(user::getUserName, "867KV37tc2").eq("create_user", 876).list(user);
        println(users);
    }

    @Test
    public void neq() {
        final List<User> users = userService.newQuery().neq("user_name", "kakrot").list(new User());
        println(users);
    }

    @Test
    public void in() {
        final List<User> users = userService.newQuery().in("user_name", Arrays.asList("kakrot", "zhangsan")).list(new User());
        println(users);
    }

    @Test
    public void nin() {
        final List<User> users = userService.newQuery().nin("user_name", Arrays.asList("kakrot", "zhangsan")).list(new User());
        println(users);
    }

    @Test
    public void ltAndGt() {
        final List<User> users = userService.newQuery().lt("id", 43110).gt("id", 43093).list(new User());
        println(users);
    }

    @Test
    public void lteAndGte() {
        final List<User> users = userService.newQuery().lte("id", 43110).gte("id", 43093).list(new User());
        println(users);
    }

    @Test
    public void between() {
        final List<User> users = userService.newQuery().between("id", 43093, 43110).list(new User());
        println(users);
    }

    @Test
    public void or() {
        final List<User> users = userService.newQuery().eq("user_name", "N8V3R78nD9").or("user_name", "PR27c66Wzi").or("email", "jpy60iek@163.net").list(new User());
        println(users);
    }

    @Test
    public void like() {
        final List<User> users = userService.newQuery().fields(Arrays.asList("user_name", "id")).eq("user_status", 1).like("user_name", "D1").list(new User());
        println(users);
    }

    @Test
    public void isNull() {
        final List<User> users = userService.newQuery().eq("id", 43095).isNull("real_name").list(new User());
        println(users);
    }

    public static void println(Object obj) {
        System.err.println((JSON.toJSONString(obj)));
    }


}
