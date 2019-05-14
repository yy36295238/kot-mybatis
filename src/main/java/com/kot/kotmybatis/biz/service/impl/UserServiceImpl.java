package com.kot.kotmybatis.biz.service.impl;

import com.kot.kotmybatis.biz.entity.User;
import com.kot.kotmybatis.biz.service.UserService;
import kot.bootstarter.kotmybatis.service.impl.MapperManagerServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author yangyu
 */
@Service
public class UserServiceImpl extends MapperManagerServiceImpl<User> implements UserService {
}
