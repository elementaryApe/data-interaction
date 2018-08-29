package com.herman.di.entity;

import java.io.Serializable;

/**
 * @author hsh
 * @create 2018-08-29 15:50
 **/
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 7853614042359801227L;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
