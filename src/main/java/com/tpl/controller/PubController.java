package com.tpl.controller;

import com.tpl.common.Results;
import com.tpl.common.utils.CookieUtils;
import com.tpl.model.UserInfo;
import com.tpl.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: sso-service
 * @description:
 * @author: tianp
 * @create: 2018-08-26 16:25
 **/
@RestController
@RequestMapping("/pub")
public class PubController {
    @Autowired
    UserService userService;
    @Autowired
    CookieUtils cookieUtils;

    @GetMapping("login")
    public Results login(UserInfo userInfo,HttpServletResponse response) {
        String token = userService.login(userInfo);
        if (null !=token){
            cookieUtils.setCookieValue(response,"sid",token);
            return Results.success();
        }
        return Results.error(400,"用户名或密码错误！");
    }

    @GetMapping("register")
    public Results insert(UserInfo userInfo) {
        if (StringUtils.isBlank(userInfo.getUsername()) || StringUtils.isBlank(userInfo.getPassword())) {
            return Results.error(400, "参数不合法！");
        }
        if (userService.insert(userInfo)) {
            return Results.success();
        }
        return Results.error(500, "注册失败，请重试！");
    }

    /**
     * 登出
     * @param request
     * @param response
     * @return
     */
    @GetMapping("out")
    public Results signOut(HttpServletRequest request,HttpServletResponse response) {
        cookieUtils.removeCookie(request,response,"sid");
        return Results.success();
    }

    /**
     * 根据token查询用户是否已登录
     *
     * @param token
     * @return
     */
    @GetMapping("token")
    public Results getToken(@RequestParam(value = "token", required = true) String token) {
        return Results.success(userService.getToken(token));
    }
}
