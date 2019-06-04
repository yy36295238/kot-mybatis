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
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KotMybatisApplicationTests {


    @Autowired
    private UserService userService;

    /**
     * 插入数据
     */
    @Test
    public void insert() {
        final User user = User.builder().realName(RandomValueUtil.name()).phone(RandomValueUtil.phone()).email(RandomValueUtil.email(1, 20)).userName(RandomValueUtil.nick())
                .password(RandomValueUtil.password()).userStatus(1).createUser(Long.valueOf(RandomValueUtil.getNum(0, 1000))).build();
        final int insert = userService.newQuery().insert(user);
        println("insert count:" + insert + ",id=" + user.getId());
    }

    /**
     * 有主键id更新数据，无主键id插入数据
     */
    @Test
    public void save() {
        final User user = User.builder().id(43093L).realName("张三1").phone("13800138000").email("13800138000@139.com").userName("zhangsan").password("123").userStatus(1).createUser(1L).build();
        final int save = userService.newQuery().save(user);
        println(save);
    }

    /**
     * 无条件查询，单条信息
     */
    @Test
    public void findOneNoWhere() {
        final User user = userService.newQuery().orderBy("id desc").findOne(new User());
        println(user);
    }

    /**
     * 条件查询，单条信息
     */
    @Test
    public void findOne() {
        final User user = userService.newQuery()
                .fields(Arrays.asList("id", "user_name", "password", "email"))
                .eq("user_name", "v527t8qb70")
                .orderBy("id desc")
                .findOne(User.builder().build());
        println(user);
    }

    /**
     * 集合查询
     */
    @Test
    public void list() {
        final List<User> list = userService.newQuery()
                .fields(Arrays.asList("user_name", "password"))
                .list(User.builder().password("123").build());
        println(list);
    }

    /**
     * 分页查询
     */
    @Test
    public void page() {
        final Page<User> page = userService.newQuery()
                .fields(Arrays.asList("id", "user_name", "password"))
                .eq("user_name", "zhangsan")
                .orderBy("id desc")
                .selectPage(new Page<>(1, 10), User.builder().userStatus(1).build());
        println(page);
    }

    /**
     * 物理删除
     */
    @Test
    public void delete() {
        final int delete = userService.newUpdate().delete(User.builder().id(43140L).build());
        println(delete);
    }

    /**
     * 根据主键更新，只更新不为null的字段
     */
    @Test
    public void updateById() {
        final int update = userService.newUpdate().updateById(User.builder().id(43138L).phone("13800138000").build());
        println(update);
    }

    /**
     * 根据主键更新，更新所有字段
     */
    @Test
    public void updateByIdSetNull() {
        final int update = userService.newUpdate().updateById(User.builder().id(43138L).phone("13800138000").userName("kakrot").password("123").createUser(1L).build(), true);
        println(update);
    }

    /**
     * 条件更新，只更新不为null的字段
     */
    @Test
    public void update() {
        final int update = userService.newUpdate().eq("user_name", "B5ScY3151Q").update(User.builder().password("123").build(), new User());
        println(update);
    }

    /**
     * 条件更新，更新所有字段
     */
    @Test
    public void updateSetNull() {
        final int update = userService.newUpdate().eq("user_name", "B5ScY3151Q").update(User.builder().userName("kulin").password("123").createUser(2L).build(), new User(), true);
        println(update);
    }

    /**
     * 批量插入
     */
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

    /**
     * 逻辑删除
     */
    @Test
    public void logicDelete() {
        final int count = userService.newUpdate().eq("id", 43151L).logicDelete(User.builder().build());
        println("logicDelete count=" + count);
    }


    /**
     * 自定义mapper方法，返回List<Map>>结果,将Map中的可以由下划线转驼峰
     */
    @Test
    public void listForMap() {
        final List<Map<String, Object>> list = userService.listForMap();
        println(list);
    }

    /**
     * 自定义mapper方法，返回Map结果,将Map中的可以由下划线转驼峰
     */
    @Test
    public void findOneForMap() {
        final Map<String, Object> map = userService.findOneForMap();
        println(map);
    }

    public static void println(Object obj) {
        System.err.println(JSON.toJSONString(obj));
    }


}
