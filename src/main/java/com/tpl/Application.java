package com.tpl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: sso-service
 * @description:
 * @author: tianp
 * @create: 2018-08-21 17:37
 **/
@MapperScan(value = "com.tpl.dao")
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
