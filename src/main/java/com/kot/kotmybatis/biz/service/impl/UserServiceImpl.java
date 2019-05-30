package com.kot.kotmybatis.biz.service.impl;

import com.kot.kotmybatis.biz.entity.User;
import com.kot.kotmybatis.biz.mapper.UserMapper;
import com.kot.kotmybatis.biz.service.UserService;
import kot.bootstarter.kotmybatis.service.impl.MapperManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author yangyu
 */
@Service
public class UserServiceImpl extends MapperManagerServiceImpl<User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Map<String, Object>> listForMap() {
        return userMapper.listForMap();
    }

    @Override
    public Map<String, Object> findOneForMap() {
        return userMapper.findOneForMap();
    }
}
