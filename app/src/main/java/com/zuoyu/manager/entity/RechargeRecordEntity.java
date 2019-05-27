package com.zuoyu.manager.entity;

import com.zuoyu.manager.base.BaseEntity;
import com.zuoyu.manager.base.BaseModel;

import java.util.List;

/**
 * <pre>
 * Function：充值记录实体类
 *
 * Created by JoannChen on 2017/7/19 15:48
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * </pre>
 */
public class RechargeRecordEntity extends BaseEntity {

    private List<Recharge> data;//详细结果集合

    public List<Recharge> getData() {
        return data;
    }

    public void setData(List<Recharge> data) {
        this.data = data;
    }

    public class Recharge extends BaseModel {

        //商户余额（XX小时XX分钟）
        //充值金额
        //充值时间（2017-07-07 13：23：22）
        private String mttime;
        private String addinfo;
        private String addtime;

        public String getMttime() {
            return mttime;
        }

        public void setMttime(String mttime) {
            this.mttime = mttime;
        }

        public String getAddinfo() {
            return addinfo;
        }

        public void setAddinfo(String addinfo) {
            this.addinfo = addinfo;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        @Override
        public String toString() {
            return "Recharge{" +
                    "mttime='" + mttime + '\'' +
                    ", addinfo=" + addinfo +
                    ", addtime='" + addtime + '\'' +
                    '}';
        }
    }


}
