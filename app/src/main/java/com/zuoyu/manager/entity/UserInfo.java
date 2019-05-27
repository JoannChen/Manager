package com.zuoyu.manager.entity;

import java.io.Serializable;

/**
 * <pre>
 * Function：
 *
 * Created by JoannChen on 2017/3/10 10:09
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class UserInfo implements Serializable {


    // 空构造
    public UserInfo() {
        super();
    }


    // 用户uid
    // 角色
    // 手机号
    // 用户名

    private String uid;
    private String role_name;
    private String phone;
    private String username;

    // 用户的token值
    private String token;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uid='" + uid + '\'' +
                ", role_name='" + role_name + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
