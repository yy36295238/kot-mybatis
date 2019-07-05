package com.kot.kotmybatis.helper;


import kot.bootstarter.kotmybatis.common.id.IdGenerator;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

@Component("idGenerator")
public class MyIdGenerator implements IdGenerator {
    @Override
    public Object gen() {
        final long id = RandomUtils.nextLong();
        System.out.println("myId:" + id);
        return id;
    }
}
