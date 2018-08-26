package com.tpl.service.user;

import com.tpl.dao.UserMapper;
import com.tpl.model.UserInfo;
import com.tpl.service.AuthService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: sso-service
 * @description:
 * @author: tianp
 * @create: 2018-08-26 10:15
 **/
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    AuthService authService;

    public String login(UserInfo userInfo) {
        UserInfo realUser = userMapper.get(userInfo.getUsername());
        if (null != realUser) {
            if (realUser.getPassword().equals(DigestUtils.sha1Hex(userInfo.getPassword().getBytes()))) {
                //将用户信息存入redis中
                return authService.set(realUser);
            }
        }
        return null;
    }

    public List<UserInfo> list() {
        return userMapper.list();
    }

    public boolean insert(UserInfo userInfo) {
        userInfo.setPassword(DigestUtils.sha1Hex(userInfo.getPassword().getBytes()));
        return userMapper.insert(userInfo) == 1;
    }

    public UserInfo getToken(String token){
        return authService.get(token,UserInfo.class);
    }
}
