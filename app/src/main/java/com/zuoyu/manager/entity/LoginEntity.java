package com.zuoyu.manager.entity;

import com.zuoyu.manager.base.BaseEntity;

/**
 * <pre>
 * Function：登录实体类
 *
 * Created by JoannChen on 2017/3/7 15:35
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class LoginEntity extends BaseEntity {

    private UserInfo data;

    public UserInfo getData() {
        return data;
    }

    public void setData(UserInfo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LoginEntity{" +
                "data=" + data +
                '}';
    }
}
