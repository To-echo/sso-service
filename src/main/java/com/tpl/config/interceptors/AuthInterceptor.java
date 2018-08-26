package com.tpl.config.interceptors;

import com.alibaba.fastjson.JSON;
import com.tpl.common.ContextHolder;
import com.tpl.common.Results;
import com.tpl.common.utils.CookieUtils;
import com.tpl.model.UserInfo;
import com.tpl.service.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

/**
 * @program: sso-service
 * @description: 用户身份信息拦截器
 * @author: tianp
 * @create: 2018-08-26 13:49
 **/
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    CookieUtils cookieUtils;
    @Autowired
    AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         String sid = cookieUtils.getCookieValue(request, "sid");
        if (StringUtils.isBlank(sid)) {
            response(response);
            return false;
        }
        //从redis中查找数据
        UserInfo userInfo = authService.get(sid, UserInfo.class);
        if (null != userInfo) {
            ContextHolder.set(userInfo);
            return true;
        }
        response(response);
        return false;
    }
    public void response(HttpServletResponse res) throws IOException {
        res.setContentType("text/html;charset=utf-8");
        PrintWriter writer = res.getWriter();
        writer.append(JSON.toJSONString(Results.error(400,"登录信息失效，请重新登录！")));
    }
}
