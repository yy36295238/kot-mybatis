package com.kot.kotmybatis;

import com.alibaba.fastjson.JSON;
import com.kot.kotmybatis.biz.entity.User;
import com.kot.kotmybatis.biz.service.UserService;
import com.kot.kotmybatis.utils.RandomValueUtil;
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
        final User user = User.builder().realName(RandomValueUtil.name()).phone(RandomValueUtil.phone()).email(RandomValueUtil.email(1, 20)).userName(RandomValueUtil.nick())
                .password(RandomValueUtil.password()).userStatus(1).createUser(Long.valueOf(RandomValueUtil.getNum(0, 1000))).build();
        final int insert = userService.newQuery().insert(user);
        println("insert count:" + insert + ",id=" + user.getId());
    }

    @Test
    public void save() {
        final User user = User.builder().id(43096L).realName("张三1").phone("13800138000").email("13800138000@139.com").userName("zhangsan").password("123").userStatus(1).createUser(1L).build();
        final int save = userService.newQuery().save(user);
        println(save);
    }

    @Test
    public void findOneNoWhere() {
        final User user = userService.newQuery().orderBy("id desc").findOne(new User());
        println(user);
    }

    @Test
    public void findOne() {
        final User user = userService.newQuery()
                .fields(Arrays.asList("id", "user_name", "password", "email"))
                .eq("user_name", "nO2i135Sil")
                .orderBy("id desc")
                .findOne(User.builder().build());
        println(user);
    }

    @Test
    public void list() {
        final List<User> list = userService.newQuery()
                .fields(Arrays.asList("user_name", "password"))
                .list(User.builder().userStatus(1).build());
        println(list);
    }

    @Test
    public void page() {
        final Page<User> page = userService.newQuery()
                .fields(Arrays.asList("id", "user_name", "password"))
                .eq("user_name", "wukong7")
                .orderBy("id desc")
                .selectPage(new Page<>(1, 10), User.builder().userStatus(1).build());
        println(page);
    }

    @Test
    public void delete() {
        final int delete = userService.newUpdate().eq("user_status", 1).gte("id", 37088).delete(User.builder().userName("wukong0").build());
        println(delete);
    }

    @Test
    public void updateById() {
        final int update = userService.newUpdate().updateById(User.builder().id(37092L).phone("13800138000").build());
        println(update);
    }

    @Test
    public void update() {
        final int update = userService.newUpdate().eq("user_name", "4VItQVm1zG").update(User.builder().password("123").build(), new User());
        println(update);
    }

    @Test
    public void batchInsert() {
        List<User> list = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            list.add(User.builder().realName(RandomValueUtil.name()).userName(RandomValueUtil.nick()).password(RandomValueUtil.password()).phone(RandomValueUtil.phone())
                    .email(RandomValueUtil.email(1, 10)).userStatus(1).createUser(RandomValueUtil.getLongNum(1, 1000)).build());
        }
        System.out.println("size:" + list.size());
        final int count = userService.newUpdate().batchInsert(list);
        println(count + " cost time:" + (System.currentTimeMillis() - start));
    }

    @Test
    public void logicDelete() {
        final int count = userService.newUpdate().eq("id", 43093).logicDelete(User.builder().build());
        println("logicDelete count=" + count);
    }


    /**
     * 自定义mapper方法
     */
//    @Test
//    public void findByUserName() {
//        final List<User> lisis = userService.findByUserName("lisi");
//        println(lisis);
//    }
//
//    @Test
//    public void findListByUserNameForMap() {
//        final List<Map<String, Object>> lisis = userService.findListByUserNameForMap("lisi");
//        println(lisis);
//    }
//
//    @Test
//    public void findByUserNameForMap() {
//        final Map<String, Object> lisi = userService.findByUserNameForMap("lisi");
//        println(lisi);
//    }
    public static void println(Object obj) {
        System.err.println(JSON.toJSONString(obj));
    }


}
