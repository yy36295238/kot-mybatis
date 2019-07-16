package com.kot.kotmybatis;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.kot.kotmybatis.biz.pg.biz.entity.Account;
import com.kot.kotmybatis.biz.pg.biz.service.IAccountService;
import com.kot.kotmybatis.utils.JsonFormatUtil;
import com.kot.kotmybatis.utils.RandomValueUtil;
import kot.bootstarter.kotmybatis.common.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KotPgTests {


    @Autowired
    private IAccountService accountService;


    /**
     * 插入数据
     */
    @Test
    public void insert() {
        final Account account = Account.builder().accountName(RandomValueUtil.name()).accountNo(RandomValueUtil.getNum(18)).acctountBankNo(RandomValueUtil.getNum(19)).createTime(new Date()).updateTime(new Date()).isDelete(0).build();
        final int insert = accountService.newUpdate().insert(account);
        println("insert count:" + insert + ",id=" + account.getId());
    }

    /**
     * 批量插入
     */
    @Test
    public void batchInsert() {
        List<Account> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(Account.builder().accountName(RandomValueUtil.name()).accountNo(RandomValueUtil.getNum(18)).acctountBankNo(RandomValueUtil.getNum(19)).createTime(new Date()).updateTime(new Date()).isDelete(0).build());
        }
        System.out.println("size:" + list.size());
        final int count = accountService.newUpdate().batchInsert(list);
        println("batchInsert", count);
    }

    /**
     * 插入数据
     */
    @Test
    public void list() {
        final List<Account> list = accountService.newQuery().list(new Account());
        println("list:", list);
    }

    /**
     * 插入数据
     */
    @Test
    public void page() {
        final PageInfo<Account> page = accountService.newQuery().selectPage(new Page<>(1, 10), new Account());
        println("page:", page);
    }

    public static void println(Object obj) {
        println("", obj);
    }

    public static void println(String prefix, Object obj) {
        System.err.println(prefix + ": " + JsonFormatUtil.formatJson(JSON.toJSONString(obj)));
    }


}
