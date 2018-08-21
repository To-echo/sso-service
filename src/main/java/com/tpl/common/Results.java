package com.tpl.common;

/**
 * @program: sso-service
 * @description: 通用返回结果集
 * @author: tianp
 * @create: 2018-08-21 18:05
 **/
public class Results {
    private int code = 200;
    private String msg;
    private Object data;

    public static Results success() {
        return new Results();
    }

    public static Results success(Object data) {
        return new Results().setData(data);
    }

    public static Results error() {
        return new Results().setCode(500);
    }

    public static Results error(int code, String msg) {
        return new Results().setCode(code).setMsg(msg);
    }

    private Results setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    private Results setData(Object data) {
        this.data = data;
        return this;
    }

    private Results setCode(int code) {
        this.code = code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }
}
