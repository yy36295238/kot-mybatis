package com.kot.kotmybatis;

import com.kot.kotmybatis.entity.User;
import com.kot.kotmybatis.service.MapperService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MtTests {

    @Autowired
    private MapperService<User> mapperService;


    @Test
    public void mt() {
        final User u = mapperService.findById(1);
        System.err.println(u);
        final List<User> admin = mapperService.list(new User("admin", "123"));
        System.err.println(admin);
    }

}
