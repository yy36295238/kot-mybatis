package com.kot.kotmybatis.biz.service;

import com.kot.kotmybatis.biz.entity.User;
import kot.bootstarter.kotmybatis.service.MapperManagerService;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author yangyu
 */
public interface UserService extends MapperManagerService<User> {
    List<Map<String, Object>> listForMap();

    Map<String, Object> findOneForMap();
}
