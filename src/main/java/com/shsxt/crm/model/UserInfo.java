package com.shsxt.crm.model;

/**
 * @auther: 康晓伟
 * @date: 2018/10/13 15:03
 * @description: shsxt_crm
 */
public class UserInfo {

    private String userIdStr;

    public UserInfo(String userIdStr) {
        this.userIdStr = userIdStr;
    }

    public UserInfo() {
    }

    public String getUserIdStr() {
        return userIdStr;
    }

    public void setUserIdStr(String userIdStr) {
        this.userIdStr = userIdStr;
    }
}
