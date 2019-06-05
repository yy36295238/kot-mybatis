package com.kot.kotmybatis.biz.mapper;

import com.kot.kotmybatis.biz.entity.User;
import kot.bootstarter.kotmybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author yangyu
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from t_user u inner join user_info ui where u.id=ui.user_id")
    List<Map<String, Object>> listForMap();

    @Select("select * from t_user u inner join user_info ui where u.id=ui.user_id limit 1")
    Map<String, Object> findOneForMap();

}
