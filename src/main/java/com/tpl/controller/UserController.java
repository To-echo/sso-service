package com.tpl.controller;

import com.tpl.common.Results;
import com.tpl.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: sso-service
 * @description:
 * @author: tianp
 * @create: 2018-08-21 18:04
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserMapper userMapper;

    @GetMapping("list")
    public Results list() {
        return Results.success(userMapper.list());
    }
}
