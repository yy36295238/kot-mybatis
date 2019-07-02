package com.kot.kotmybatis.biz.mysql.biz.service.impl;

import com.kot.kotmybatis.biz.mysql.biz.entity.Order;
import com.kot.kotmybatis.biz.mysql.biz.service.IOrderService;
import kot.bootstarter.kotmybatis.service.impl.MapperManagerServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author yangyu
 */
@Service
public class OrderServiceImpl extends MapperManagerServiceImpl<Order> implements IOrderService {
}
