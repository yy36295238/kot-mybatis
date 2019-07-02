package com.kot.kotmybatis.biz.mysql.biz.service.impl;

import com.kot.kotmybatis.biz.mysql.biz.entity.UserInfo;
import com.kot.kotmybatis.biz.mysql.biz.service.UserInfoService;
import kot.bootstarter.kotmybatis.service.impl.MapperManagerServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author yangyu
 */
@Service
public class UserInfoServiceImpl extends MapperManagerServiceImpl<UserInfo> implements UserInfoService {
}
