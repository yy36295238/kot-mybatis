package com.kot.kotmybatis;

import com.kot.kotmybatis.entity.User;
import com.kot.kotmybatis.service.MapperManagerService;
import com.kot.kotmybatis.service.impl.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MtTests {

    @Autowired
    private MapperManagerService<User> mapperManagerService;

    @Test
    public void mt() {
        final Object user = mapperManagerService.newQuery().findById("user", 2);
        System.out.println(user);

        final Object admin = mapperManagerService.newQuery().findOne(new User("admin", "123"));
        System.out.println(admin);
    }

    @Autowired
    private UserService userService;

    @Test
    public void mu() {
        final User user = userService.newQuery().findById("user", 1);
        System.out.println(user);
    }

}
