package com.zuoyu.manager.base;

import java.io.Serializable;

/**
 * <pre>
 * Function：Entity 父类
 *
 * Created by Joann on 2017/3/2 16:05
 * QQ:411083907
 * E-mail:q8622268@163.com
 * Version Information：V 1.0
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class BaseEntity implements Serializable {



    // 服务器返回的参数
    private int code;

    // 服务器返回的信息
    private String msg;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "BaseEntity [msg=" + msg + ", code=" + code + "]";
    }




}

