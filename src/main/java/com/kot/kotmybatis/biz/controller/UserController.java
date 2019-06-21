package com.kot.kotmybatis.biz.controller;


import com.kot.kotmybatis.biz.entity.Goods;
import com.kot.kotmybatis.biz.entity.Order;
import com.kot.kotmybatis.biz.entity.User;
import com.kot.kotmybatis.biz.service.IGoodsService;
import com.kot.kotmybatis.biz.service.IOrderService;
import com.kot.kotmybatis.biz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/updateById")
    public String updateById(Long id) {
        final int i = userService.newUpdate().updateById(User.builder().id(id).version(15).build());
        return i > 0 ? "成功" : "失败";
    }

    @RequestMapping("/secKill")
    public String secKill(Long id) {
        final Goods goods = goodsService.newQuery().findOne(Goods.builder().id(id).build());
        if (goods.getNum() <= 0) {
            System.err.println("NOTHING");
            return "NOTHING";
        }
        final int count = goodsService.newUpdate().updateById(Goods.builder().id(id).version(goods.getVersion()).build());
        if (count > 0) {
            goodsService.newUpdate().updateById(Goods.builder().id(goods.getId()).num(goods.getNum() - 1).build());
            orderService.newUpdate().insert(Order.builder().goodsId(goods.getId()).build());
            System.err.println("SUCCESS");
            return "SUCCESS";
        }
        System.err.println("FAILED");
        return "FAILED";
    }
}
