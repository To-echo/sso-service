package com.tpl.controller.user;

import com.tpl.common.Results;
import com.tpl.common.utils.CookieUtils;
import com.tpl.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    UserService userService;
    @Autowired
    CookieUtils cookieUtils;
    @GetMapping("list")
    public Results list() {
        return Results.success(userService.list());
    }
}
