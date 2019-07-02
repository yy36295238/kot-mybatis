package com.kot.kotmybatis.biz.pg.biz.service.impl;

import com.kot.kotmybatis.biz.pg.biz.entity.Account;
import com.kot.kotmybatis.biz.pg.biz.service.IAccountService;
import kot.bootstarter.kotmybatis.service.impl.MapperManagerServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author yangyu
 */
@Service
public class AccountServiceImpl extends MapperManagerServiceImpl<Account> implements IAccountService {
}
