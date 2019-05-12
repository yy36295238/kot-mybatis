package com.kot.kotmybatis.mapper;

import com.kot.kotmybatis.entity.User;
import kot.bootstarter.kotmybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author yangyu
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where user_name = #{userName}")
    List<User> findByUserName(@Param("userName") String userName);

    @Select("select * from user where user_name = #{userName}")
    List<Map<String, Object>> findListByUserNameForMap(@Param("userName") String userName);

    @Select("select * from user where user_name = #{userName}")
    Map<String, Object> findByUserNameForMap(@Param("userName") String userName);
}
