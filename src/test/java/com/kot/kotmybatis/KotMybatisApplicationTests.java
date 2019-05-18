package com.kot.kotmybatis;

import com.kot.kotmybatis.biz.entity.User;
import com.kot.kotmybatis.biz.service.UserService;
import kot.bootstarter.kotmybatis.common.CT;
import kot.bootstarter.kotmybatis.common.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KotMybatisApplicationTests {


    @Autowired
    private UserService userService;

    @Test
    public void insert() {
        final User user = User.builder().realName("张三").phone("13800138000").email("13800138000@139.com").userName("zhangsan").password("123").userStatus(1).createUser(1L).build();
        final int insert = userService.newQuery().insert(user);
        System.err.println("insert count:" + insert + ",id=" + user.getId());
    }

    @Test
    public void save() {
        final User user = User.builder().id(19L).realName("张三1").phone("13800138000").email("13800138000@139.com").userName("zhangsan").password("123").userStatus(1).createUser(1L).build();
        final int save = userService.newQuery().save(user);
        System.err.println(save);
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
                .eq("user_name", "zhangsan")
                .eq("id", 2)
                .eq("password", "123")
                .list(User.builder().userStatus(1).build());
        System.err.println(list);
    }

    @Test
    public void page() {
        final Page<User> page = userService.newQuery()
                .fields(Arrays.asList("id", "user_name", "password"))
                .between("id", 1, 12)
                .selectPage(new Page<>(2, 10, "id", CT.DESC), User.builder().userStatus(1).build());
        System.err.println(page);
    }

    @Test
    public void delete() {
        final int delete = userService.newUpdate().eq("user_status", 1).gte("id", 17).delete(User.builder().userName("zhangsan").build());
        System.err.println(delete);
    }

    @Test
    public void updateById() {
        final int update = userService.newUpdate().updateById(User.builder().id(15L).phone("13800138000").build());
        System.err.println(update);
    }

    @Test
    public void update() {
        final int update = userService.newUpdate().between("id", 13, 15).update(User.builder().password("123").build(), User.builder().userStatus(1).build());
        System.err.println(update);
    }

    @Test
    public void batchInsert() {
        List<User> list = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 3000; i++) {
            list.add(new User("wukong" + i, "123" + i, 1, 999L, new Date()));
        }
        System.out.println("size:" + list.size());
        final int count = userService.newUpdate().batchInsert(list);
        System.err.println(count + " cost time:" + (System.currentTimeMillis() - start));
    }


    /**
     * 自定义mapper方法
     */
//    @Test
//    public void findByUserName() {
//        final List<User> lisis = userService.findByUserName("lisi");
//        System.err.println(lisis);
//    }
//
//    @Test
//    public void findListByUserNameForMap() {
//        final List<Map<String, Object>> lisis = userService.findListByUserNameForMap("lisi");
//        System.err.println(lisis);
//    }
//
//    @Test
//    public void findByUserNameForMap() {
//        final Map<String, Object> lisi = userService.findByUserNameForMap("lisi");
//        System.err.println(lisi);
//    }


}
