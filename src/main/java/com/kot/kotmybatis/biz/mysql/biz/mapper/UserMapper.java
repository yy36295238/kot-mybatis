package com.kot.kotmybatis.biz.mysql.biz.mapper;

import com.kot.kotmybatis.biz.mysql.biz.entity.User;
import kot.bootstarter.kotmybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
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

    @Insert("INSERT INTO t_user (id,open_id,`UNION_ID`,real_name,user_name,password,phone,email,avatar_url,gender,city,province,ip,country,language,type,activation,user_status,version,is_delete,create_user,create_time,update_time) VALUES (#{id},#{openId},#{unionId},#{realName},#{userName},#{password},#{phone},#{email},#{avatarUrl},#{gender},#{city},#{province},#{ip},#{country},#{language},#{type},#{activation},#{userStatus},#{version},#{isDelete},#{createUser},#{createTime},#{updateTime})")
    int myInsert(User user);

}
