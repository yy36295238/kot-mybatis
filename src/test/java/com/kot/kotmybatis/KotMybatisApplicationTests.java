package com.kot.kotmybatis;

import com.kot.kotmybatis.common.CT;
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
    public void contextLoads() throws Exception {
        final User user = userService.newQuery().findById(User.class, 1);
        System.err.println(user);
    }

    @Test
    public void findOne() {
        final User user = userService.newQuery()
                .eq("password", "123")
                .findOne(new User("test", null));
        System.err.println(user);
    }

    @Test
    public void list() {
        final List<User> admin = userService.newQuery().list(new User("admin", "123"));
//        final List<User> list = userService.newQuery().list(new User(1));
        System.err.println(admin);
//        System.err.println(list);
        final List<User> list = userService.newQuery()
                .eq("user_name", "test")
                .eq("id", 2)
                .eq("password", "123")
                .list(new User());
        System.err.println(list);
    }

    @Test
    public void in() {
        final List<User> list = userService.newQuery()
                .in("id", Arrays.asList(1, 2, 3))
                .list(new User());
        System.err.println("in: " + list);
    }

    @Test
    public void page() {
        final Page<User> page2 = userService.newQuery().selectPage(new Page<>(1, 10, "id,name", CT.DESC), new User("admin", "123"));
        final Page<User> page3 = userService.newQuery().selectPage(new Page<>(1, 10, "id,name", CT.DESC), new User(1));
        System.err.println(page2);
        System.err.println(page3);
    }

}
