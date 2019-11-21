package com.kot.kotmybatis;

import com.kot.kotmybatis.biz.mysql.biz.entity.User;
import com.kot.kotmybatis.biz.mysql.biz.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.kot.kotmybatis.KotMysqlTests.println;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KotMybatisConditionTests {


    @Autowired
    private UserService userService;

    @Test
    public void eq() {
        final List<User> users = userService.newQuery().eq("user_name", "N8V3R78nD9").eq("create_user", 793).neq("user_status", -9).list(User.builder().userStatus(1).build());
        println(users);
    }

    @Test
    public void eqLambda() {
        final User user = User.builder().userStatus(1).build();
        final List<User> users = userService.newQuery().eq(user::getUserName, "N8V3R78nD9").eq("create_user", 793).list(user);
        println(users);
    }

    @Test
    public void neq() {
        final List<User> users = userService.newQuery().neq("user_name", "N8V3R78nD9").list(new User());
        println(users);
    }

    @Test
    public void neqLambda() {
        final User user = new User();
        final List<User> users = userService.newQuery().neq(user::getUserName, "N8V3R78nD9").list(user);
        println(users);
    }

    @Test
    public void in() {
        final List<User> users = userService.newQuery().in("user_name", Arrays.asList("kakrot", "zhangsan")).list(new User());
        println(users);
    }

    @Test
    public void inLambda() {
        final User user = User.builder().userStatus(1).build();
        final List<User> users = userService.newQuery().in(user::getUserName, Arrays.asList("ql9h477JD1", "TPgX813h55")).list(user);
        println(users);
    }

    @Test
    public void nin() {
        final List<User> users = userService.newQuery().nin("user_name", Arrays.asList("kakrot", "zhangsan")).list(new User());
        println(users);
    }

    @Test
    public void ninLambda() {
        final User user = User.builder().userStatus(1).build();
        final List<User> users = userService.newQuery().nin(user::getUserName, Arrays.asList("kakrot", "zhangsan")).list(user);
        println(users);
    }

    @Test
    public void ltAndGt() {
        final List<User> users = userService.newQuery().lt("id", 43110).gt("id", 43093).list(new User());
        println(users);
    }

    @Test
    public void ltAndGtLambda() {
        final User user = User.builder().userStatus(1).build();
        final List<User> users = userService.newQuery().lt(user::getId, 43110L).gt(user::getId, 43093L).list(user);
        println(users);
    }

    @Test
    public void lteAndGte() {
        final List<User> users = userService.newQuery().lte("id", 43110L).gte("id", 43093L).list(new User());
        println(users);
    }

    @Test
    public void lteAndGtLambda() {
        final User user = User.builder().userStatus(1).build();
        final List<User> users = userService.newQuery().lte(user::getId, 43110L).gte(user::getId, 43093L).list(user);
        println(users);
    }

    @Test
    public void between() {
        final List<User> users = userService.newQuery().between("id", 43093L, 43110L).list(new User());
        println(users);
    }

    @Test
    public void or() {
        final List<User> users = userService.newQuery().eq("user_name", "N8V3R78nD9").or("user_name", "PR27c66Wzi").or("email", "jpy60iek@163.net").list(new User());
        println(users);
    }

    @Test
    public void orLambda() {
        final User user = User.builder().userStatus(1).build();
        final List<User> users = userService.newQuery().eq(user::getId, 43093L).or(user::getUserName, "TPgX813h55").or(user::getEmail, "nd3pbiqu@263.net").list(user);
        println(users);
    }

    @Test
    public void like() {
        final List<User> users = userService.newQuery().fields(Arrays.asList("user_name", "id")).eq("user_status", 1).like("user_name", "D1").list(new User());
        println(users);
    }


    @Test
    public void likeLambda() {
        final User user = User.builder().userStatus(1).build();
        final List<User> users = userService.newQuery().fields(user::getId, user::getUserName).eq(user::getUserStatus, 1).like(user::getUserName, "D1").list(user);
        println(users);
    }

    @Test
    public void isNull() {
        final List<User> users = userService.newQuery().eq("id", 43095).isNull("real_name").list(new User());
        println(users);
    }

    @Test
    public void isNullLambda() {
        final User user = User.builder().userStatus(1).build();
        final List<User> users = userService.newQuery().isNull(user::getRealName).list(user);
        println(users);
    }

    @Test
    public void isNotNull() {
        final List<User> users = userService.newQuery().eq("id", 43095).notNull("real_name").list(new User());
        println(users);
    }

    @Test
    public void isNotNullLambda() {
        final User user = User.builder().userStatus(1).build();
        final List<User> users = userService.newQuery().notNull(user::getRealName).list(user);
        println(users);
    }


}
