package com.kot.kotmybatis;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.kot.kotmybatis.biz.mysql.biz.entity.Order;
import com.kot.kotmybatis.biz.mysql.biz.entity.User;
import com.kot.kotmybatis.biz.mysql.biz.service.IOrderService;
import com.kot.kotmybatis.biz.mysql.biz.service.UserService;
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
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KotMysqlTests {


    @Autowired
    private UserService userService;

    @Autowired
    private IOrderService orderService;

    @Test
    public void myInsert() {
        final User user = User.builder().unionId(com.kot.kotmybatis.utils.RandomValueUtil.password()).realName(com.kot.kotmybatis.utils.RandomValueUtil.name()).phone(com.kot.kotmybatis.utils.RandomValueUtil.phone()).email(com.kot.kotmybatis.utils.RandomValueUtil.email(1, 20)).userName(com.kot.kotmybatis.utils.RandomValueUtil.nick())
                .password(com.kot.kotmybatis.utils.RandomValueUtil.password()).userStatus(1).createUser(com.kot.kotmybatis.utils.RandomValueUtil.getLongNum(0, 1000)).isDelete(1).build();
        userService.myInsert(user);
        println("myInsert", user);
    }

    /**
     * 插入数据
     */
    @Test
    public void insert() throws InterruptedException {

        int count = 2;
        CountDownLatch latch = new CountDownLatch(count);
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                final User user = User.builder().unionId(com.kot.kotmybatis.utils.RandomValueUtil.password()).realName(com.kot.kotmybatis.utils.RandomValueUtil.name()).phone(com.kot.kotmybatis.utils.RandomValueUtil.phone()).email(com.kot.kotmybatis.utils.RandomValueUtil.email(1, 20)).userName(com.kot.kotmybatis.utils.RandomValueUtil.nick())
                        .password(com.kot.kotmybatis.utils.RandomValueUtil.password()).userStatus(1).createUser(com.kot.kotmybatis.utils.RandomValueUtil.getLongNum(0, 1000)).isDelete(1).key("mykey").build();
                userService.newQuery().insert(user);
                latch.countDown();
                ids.add(user.getId());
                println("insert", user);
            }).start();
        }
        latch.await();
        ids.stream().sorted().forEach(System.out::println);
        Thread.sleep(5000);
    }

    /**
     * 批量插入
     */
    @Test
    public void batchInsert() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list.add(User.builder().realName(com.kot.kotmybatis.utils.RandomValueUtil.name()).userName(com.kot.kotmybatis.utils.RandomValueUtil.nick()).password(com.kot.kotmybatis.utils.RandomValueUtil.password()).phone(com.kot.kotmybatis.utils.RandomValueUtil.phone())
                    .email(com.kot.kotmybatis.utils.RandomValueUtil.email(1, 10)).userStatus(1).createUser(com.kot.kotmybatis.utils.RandomValueUtil.getLongNum(1, 1000)).isDelete(1).key("mykey").build());
        }
        final int count = userService.newUpdate().batchInsert(list);
        println("batchInsert", count);
        list.forEach(System.out::println);
    }

    /**
     * 有主键id更新数据，无主键id插入数据
     */
    @Test
    public void save() {
        final User user = User.builder().realName("张三1").phone("13800138000").email("13800138000@139.com").userName("zhangsan").password("123").userStatus(1).createUser(1L).isDelete(1).build();
        final int save = userService.newQuery().save(user);
        println("save", save);
    }

    /**
     * 无条件查询，单条信息
     */
    @Test
    public void findOneNoWhere() {
        final User user = userService.newQuery().orderByIdDesc().findOne(new User());
        println("findOneNoWhere", user);
    }

    /**
     * 条件查询，单条信息
     */
    @Test
    public void findOne() {
        final User user = User.builder().userName("A7").realName("林").userStatus(1).build();
        final User result = userService.newQuery()
//                .fields(user::getId, user::getUserName, user::getPassword, user::getUnionId)
                .eq(user::getId, 43175L)
                .eq("UNION_ID", "c4f07e742cae46428e76421e33f24d10")
                .eq("key", "mykey")
                .orderBy("id desc")
                .activeLike()
                .findOne(user);
        println("findOne", result);
    }

    /**
     * 集合查询
     */
    @Test
    public void list() {
        final User user = User.builder().password("123").build();
        final List<User> list = userService.newQuery()
                .fields(user::getId, user::getUserName, user::getCreateUser, user::getRealName)
//                .activeRelated()
                .list(user);
        println("list", list);
    }

    /**
     * 统计
     */
    @Test
    public void count() {
        final int count = userService.newQuery().count(new User());
        println("count", count);
    }

    /**
     * 分页查询
     */
    @Test
    public void page() {
        final PageInfo<User> page = userService.newQuery()
                .fields(Arrays.asList("id", "user_name", "password"))
                .orderByIdDesc()
                .selectPage(new Page<>(1, 10), User.builder().userStatus(1).build());
        println("page", page);
    }

    /**
     * 物理删除
     */
    @Test
    public void delete() {
        final int delete = userService.newUpdate().delete(User.builder().id(100000L).build());
        println("delete", delete);
    }

    /**
     * 根据主键更新，只更新不为null的字段
     */
    @Test
    public void updateById() {
        final int update = userService.newUpdate().updateById(User.builder().id(43115L).phone("13800138000").key("mykey").build());
        println("updateById", update);
    }

    /**
     * 乐观锁更新ById
     */
    @Test
    public void updateByIdForVersionLock() {
        final User user = userService.newQuery().findOne(User.builder().id(43188L).build());
        final int update1 = userService.newUpdate().updateById(User.builder().phone("13900139000").version(user.getVersion()).id(user.getId()).build());
        final int update2 = userService.newUpdate().updateById(User.builder().phone("13900139000").version(user.getVersion()).id(user.getId()).build());
        println("updateByIdForVersionLock", update1 > 0 ? "成功" : "失败");
        println("updateByIdForVersionLock", update2 > 0 ? "成功" : "失败");
    }

    /**
     * 乐观锁更新
     */
    @Test
    public void updateForVersionLock() {
        final User user = userService.newQuery().findOne(User.builder().id(43188L).build());
        final int update1 = userService.newUpdate().update(User.builder().phone("13800138000").build(), User.builder().version(user.getVersion()).id(43188L).build());
        final int update2 = userService.newUpdate().update(User.builder().phone("13800138000").build(), User.builder().version(user.getVersion()).id(43188L).build());
        println("updateForVersionLock", update1 > 0 ? "成功" : "失败");
        println("updateForVersionLock", update2 > 0 ? "成功" : "失败");
    }

    /**
     * 根据主键更新，更新所有字段
     */
    @Test
    public void updateByIdSetNull() {
        final int update = userService.newUpdate().updateById(User.builder().id(43114L).realName("张三").phone("13800138000").userName("kakrot").password("123").createUser(1L).isDelete(1).build(), true);
        println("updateByIdSetNull", update);
    }

    /**
     * 条件更新，只更新不为null的字段
     */
    @Test
    public void update() {
        final User user = User.builder().realName("兴").key("mykey").build();
        final int update = userService.newUpdate().eq(user::getUserName, "S16kKq6A5F").activeLike().update(User.builder().password("123").key("mykey1").build(), user);
        println("update", update);
    }

    /**
     * 条件更新，更新所有字段
     */
    @Test
    public void updateSetNull() {
        final User user = User.builder().realName("兴").build();
        final int update = userService.newUpdate().activeLike().eq(user::getUserName, "kulin").update(User.builder().realName("于兴2").userName("kulin").password("123").createUser(2L).isDelete(1).build(), user, true);
        println("updateSetNull", update);
    }


    /**
     * 逻辑删除
     */
    @Test
    public void logicDelete() {
        final User user = User.builder().realName("衡").build();
        final int count = userService.newUpdate().activeLike()
                .eq(user::getId, 43181L).logicDelete(user);
        println("logicDelete", count);
    }


    /**
     * 数据库字段值是否已存在
     */
    @Test
    public void columnExist() {
        final User user = User.builder().openId("wx123").userName("51Ii00s0s8").realName("福").build();
        final Map<String, Object> columnExist = userService.newQuery().columnExist(user);
        println("columnExist", columnExist);
    }


    /**
     * 自定义mapper方法，返回List<Map>>结果,将Map中的可以由下划线转驼峰
     */
    @Test
    public void listForMap() {
        final List<Map<String, Object>> list = userService.listForMap();
        println("listForMap", list);
    }

    /**
     * 自定义mapper方法，返回Map结果,将Map中的可以由下划线转驼峰
     */
    @Test
    public void findOneForMap() {
        final Map<String, Object> map = userService.findOneForMap();
        println("findOneForMap", map);
    }

    /**
     * 关联查询
     */
    @Test
    public void relatedQuery() {
        final List<Order> list = orderService.newQuery().activeRelated().list(new Order());
        println("relatedQuery", list);
    }

    public static void println(Object obj) {
        println("", obj);
    }

    public static void println(String prefix, Object obj) {
        System.err.println(prefix + ": " + com.kot.kotmybatis.utils.JsonFormatUtil.formatJson(JSON.toJSONString(obj)));
    }


}
