package com.shsxt.crm.exceptions;



/**
 * Created by lp on 2018/1/3.
 */
public class NotLoginException extends  RuntimeException {
    private Integer code= 300;
    private String msg="未登录!";

    public NotLoginException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public NotLoginException(Integer code) {
        super("未登录");
        this.code = code;
    }

    public NotLoginException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
