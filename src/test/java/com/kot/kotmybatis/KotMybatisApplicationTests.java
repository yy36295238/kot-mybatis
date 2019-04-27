package com.kot.kotmybatis;

import com.kot.kotmybatis.entity.User;
import com.kot.kotmybatis.mapper.BaseMapper;
import com.kot.kotmybatis.mapper.UserMapper;
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
    private UserMapper userMapper;

    @Autowired
    private BaseMapper<User> baseMapper;

    @Test
    public void contextLoads() throws Exception {
        final User user = userMapper.findById(1);
        System.out.println(user);

        final User byId = baseMapper.findById(2);
        System.out.println(byId);
    }

    @Test
    public void findOne() {
        final List<User> list = userMapper.findOne(new User("test", null));
        System.out.println(list);
    }

    @Test
    public void list() {
        final List<User> list = userMapper.list(new User("admin", "123"));
        System.out.println(list);
    }

}
