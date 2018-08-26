package com.tpl.dao;

import com.tpl.model.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: sso-service
 * @description:
 * @author: tianp
 * @create: 2018-08-21 17:56
 **/
public interface UserMapper {

    /**
     * 列出所有用户信息
     *
     * @return
     */
    @Select("select * from userinfo")
    List<UserInfo> list();

    /**
     * 通过用户名查找用户
     *
     * @param username
     * @return
     */
    @Select("select * from userinfo where username = #{username}")
    UserInfo get(String username);

    /**
     * 插入
     *
     * @param userInfo
     * @return
     */
    @Insert("insert into userinfo(username,password,email) " +
            "value(#{userInfo.username},#{userInfo.password},#{userInfo.email})")
    int insert(@Param("userInfo") UserInfo userInfo);

}
