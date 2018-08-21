package com.tpl.dao;

import com.tpl.model.UserInfo;
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
}
