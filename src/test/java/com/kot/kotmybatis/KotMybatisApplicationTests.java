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
    public void insert() {
        final User user = User.builder().name("张三").cellPhone("13800138000").email("13800138000@139.com").userName("zhangsan").password("123").userStatus(1).createUser(1L).build();
        final int insert = userService.newQuery().insert(user);
        System.err.println(insert);
    }

    @Test
    public void findOne() {
        final User user = userService.newQuery()
                .fields(Arrays.asList("id", "user_name", "password"))
                .eq("id", 15)
                .isNull("create_user")
                .findOne(User.builder().userStatus(1).build());
        System.err.println(user);
    }

    @Test
    public void list() {
        final List<User> list = userService.newQuery()
                .fields(Arrays.asList("user_name", "password"))
                .eq("user_name", "test")
                .eq("id", 2)
                .eq("password", "123")
                .list(User.builder().userStatus(1).build());
        System.err.println(list);
    }

    @Test
    public void page() {
        final Page<User> page = userService.newQuery()
                .fields(Arrays.asList("user_name", "password"))
                .between("id", 1, 12)
                .selectPage(new Page<>(2, 10), User.builder().userStatus(1).build());
        System.err.println(page);
    }

    @Test
    public void delete() {
        final int delete = userService.newUpdate().eq("user_status", 1).gte("id", 17).delete(new User("zhangsan"));
        System.err.println(delete);
    }

    @Test
    public void updateById() {
        final int update = userService.newUpdate().updateById(User.builder().id(15L).cellPhone("13800138000").build());
        System.err.println(update);
    }

    @Test
    public void update() {
        final int update = userService.newUpdate().between("id", 13, 15).update(User.builder().password("123").build(), User.builder().userStatus(1).build());
        System.err.println(update);
    }

}
