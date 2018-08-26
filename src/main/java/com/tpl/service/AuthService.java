package com.tpl.service;

import com.alibaba.fastjson.JSON;
import com.tpl.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @program: sso-service
 * @description: 身份验证的redis操作
 * @author: tianp
 * @create: 2018-08-26 13:27
 **/
@Service
public class AuthService {
    @Autowired
    RedisService redisService;
    private static final String SSO_PREFIX = "sso-service:";
    private static final int SSO_EXPIRED = 60 * 60 * 24;

    public String set(UserInfo userInfo) {
        String token = UUID.randomUUID().toString();
        redisService.set(getToken(token), JSON.toJSONString(userInfo));
        return token;
    }

    public String get(String token) {
        return redisService.get(getToken(token));
    }

    public <T> T get(String token, Class<T> clazz) {
        String json = redisService.get(getToken(token));
        return JSON.parseObject(json, clazz);
    }

    public void set(String token, String userInfo) {
        redisService.set(getToken(token), userInfo, SSO_EXPIRED, TimeUnit.SECONDS);
    }

    public void delete(String token) {
        redisService.delete(getToken(token));
    }

    public void expired(String key) {
        redisService.expired(getToken(key), SSO_EXPIRED, TimeUnit.SECONDS);
    }

    private String getToken(String token) {
        return SSO_PREFIX + token;
    }
}
