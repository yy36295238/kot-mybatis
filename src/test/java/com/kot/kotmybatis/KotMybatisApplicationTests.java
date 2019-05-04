package com.kot.kotmybatis;

import com.kot.kotmybatis.common.Page;
import com.kot.kotmybatis.entity.User;
import com.kot.kotmybatis.service.impl.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KotMybatisApplicationTests {


    @Autowired
    private UserService userService;

    @Test
    public void findOne() {
        final User user = userService.newQuery()
                .fields(Arrays.asList("id", "user_name", "password"))
                .eq("id", 15)
                .isNull("create_user")
                .findOne(new User(1));
        System.err.println(user);
    }

    @Test
    public void list() {
        final List<User> list = userService.newQuery()
                .fields(Arrays.asList("user_name", "password"))
                .eq("user_name", "test")
                .eq("id", 2)
                .eq("password", "123")
                .list(new User(1));
        System.err.println(list);
    }

    @Test
    public void page() {
        final Page<User> page = userService.newQuery()
                .fields(Arrays.asList("user_name", "password"))
                .between("id", 1, 12)
                .selectPage(new Page<>(2, 10), new User(1));
        System.err.println(page);
    }

}
