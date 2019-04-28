package com.kot.kotmybatis;

import com.kot.kotmybatis.common.CT;
import com.kot.kotmybatis.common.Page;
import com.kot.kotmybatis.entity.User;
import com.kot.kotmybatis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KotMybatisApplicationTests {


    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() throws Exception {
        final User user = userService.findById(1);
        System.err.println(user);
    }

    @Test
    public void findOne() {
        final User user = userService.findOne(new User("test", null));
        System.err.println(user);
    }

    @Test
    public void list() {
        final List<User> admin = userService.list(new User("admin", "123"));
        final List<User> list = userService.list(new User(1));
        System.err.println(admin);
        System.err.println(list);
    }

    @Test
    public void page() {
        final Page<User> page2 = userService.selectPage(new Page<>(1, 10, "id,name", CT.DESC), new User("admin", "123"));
        final Page<User> page3 = userService.selectPage(new Page<>(1, 10, "id,name", CT.DESC), new User(1));
        System.err.println(page2);
        System.err.println(page3);
    }

}
