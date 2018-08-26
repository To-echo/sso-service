package com.tpl.common.utils;

import com.tpl.service.AuthService;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @program: sso-service
 * @description:
 * @author: tianp
 * @create: 2018-08-26 14:01
 **/
@Component
@Log
public class CookieUtils {
    @Autowired
    AuthService authService;
    private static final String DEFAULT_DECODE = "utf-8";

    /**
     * 不编码的方式获得cookie的值
     *
     * @param request
     * @param name
     * @return
     */
    public String getCookieValue(HttpServletRequest request, String name) {
        return getName(request.getCookies(), name, false);
    }

    /**
     * 选择是否编码获得cookie的值
     *
     * @param request
     * @param name
     * @param isDecode
     * @return
     */
    public String getCookieValue(HttpServletRequest request, String name, boolean isDecode) {
        return getName(request.getCookies(), name, isDecode);
    }

    public String getName(Cookie[] cookies, String name, boolean isDecode) {
        if (null == cookies || StringUtils.isBlank(name)) {
            return null;
        }
        String retValue = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                if (isDecode) {
                    try {
                        retValue = URLDecoder.decode(cookie.getValue(), DEFAULT_DECODE);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else {
                    retValue = cookie.getValue();
                }
                break;
            }
        }
        return retValue;
    }

    /**
     * @param response
     * @param name
     * @param value
     */
    public void setCookieValue(HttpServletResponse response, String name, String value) {
        setCookieValue(response, name, value, false);
    }

    public void setCookieValue(HttpServletResponse response, String name, String value, boolean isEncode) {
        if (StringUtils.isBlank(name) || StringUtils.isBlank(value)) {
            return;
        }
        Cookie cookie = null;
        try {
            cookie = new Cookie(name, URLEncoder.encode(value, DEFAULT_DECODE));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        assert cookie != null;
        cookie.setMaxAge(60 * 60 * 24);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public void removeCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        removeCookie(cookies, name, response);
    }

    public void removeCookie(Cookie[] cookies, String name, HttpServletResponse response) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                //删除redis中数据
                authService.delete(cookie.getValue());
                cookie.setValue(null);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                log.info("删除cookie的名字为：" + cookie.getName());
                response.addCookie(cookie);
                break;
            }
        }
    }
}