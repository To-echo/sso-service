package com.tpl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @program: sso-service
 * @description: redis操作的基础包装类
 * @author: tianp
 * @create: 2018-08-26 12:40
 **/
@Service
public class RedisService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;


    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, String value, long times, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key, value, times, timeUnit);
    }

    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    public void expired(String key, long time, TimeUnit timeUnit) {
        stringRedisTemplate.expire(key, time, timeUnit);
    }
}
