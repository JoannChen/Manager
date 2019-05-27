package com.zuoyu.manager.entity;

import com.zuoyu.manager.base.BaseEntity;

/**
 * <pre>
 * Function：验证码信息实体类
 *
 * Created by JoannChen on 2017/3/9 15:01
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class VerifyEntity extends BaseEntity {

    private VerifyInfo data;

    public VerifyInfo getData() {
        return data;
    }

    public void setData(VerifyInfo data) {
        this.data = data;
    }

    //    1.
    public class VerifyInfo {

        //  验证码
        //  验证码创建时间

        private String verify;
        private String createtime;

        public String getVerify() {
            return verify;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setVerify(String verify) {
            this.verify = verify;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        @Override
        public String toString() {
            return "VerifyInfo{" +
                    "verify='" + verify + '\'' +
                    ", createtime='" + createtime + '\'' +
                    '}';
        }
    }

}
