package com.kot.kotmybatis;

import com.kot.kotmybatis.entity.User;
import com.kot.kotmybatis.service.MapperManagerService;
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
    private MapperManagerService<User> mapperManagerService;


    @Test
    public void mt() {
//        mapperManagerService.newQuery().findById(2);
        mapperManagerService.newQuery().findOne(new User("admin","123"));
    }

}
