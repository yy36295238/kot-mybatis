package com.kot.kotmybatis.biz.service.impl;

import com.kot.kotmybatis.biz.entity.UserInfo;
import com.kot.kotmybatis.biz.service.UserInfoService;
import kot.bootstarter.kotmybatis.service.impl.MapperManagerServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author yangyu
 */
@Service
public class UserInfoServiceImpl extends MapperManagerServiceImpl<UserInfo> implements UserInfoService {
}
