package com.kot.kotmybatis.service;

import com.kot.kotmybatis.annotation.ToCamel;
import com.kot.kotmybatis.entity.User;
import com.kot.kotmybatis.mapper.UserMapper;
import kot.bootstarter.kotmybatis.service.impl.MapperManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author yangyu
 */
@Service
public class UserService extends MapperManagerServiceImpl<User> {

    @Autowired
    private UserMapper userMapper;

    public List<User> findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }

    @ToCamel
    public List<Map<String, Object>> findListByUserNameForMap(String userName) {
        return userMapper.findListByUserNameForMap(userName);
    }

    @ToCamel
    public Map<String, Object> findByUserNameForMap(String userName) {
        return userMapper.findByUserNameForMap(userName);
    }


}
