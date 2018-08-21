package com.tpl.model;

import lombok.Data;

import java.util.Date;

/**
 * @program: sso-service
 * @description: 用户信息
 * @author: tianp
 * @create: 2018-08-21 17:52
 **/
@Data
public class UserInfo {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Date cts;
    private Date uts;
}
