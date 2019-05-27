package com.zuoyu.manager.entity;


import com.zuoyu.manager.base.BaseEntity;
import com.zuoyu.manager.base.BaseModel;

import java.util.List;

/**
 * <pre>
 * Function：商家管理实体类
 *
 * Created by JoannChen on 2017/3/24 10:05
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class BusinessEntity extends BaseEntity {

    public Business data;

    public Business getData() {
        return data;
    }

    public void setData(Business data) {
        this.data = data;
    }

    //    1.
    public class Business extends BaseModel {


        //商家总数
        //优惠券总数
        //充值总数（小时）
        //集合

        private String mtcount;
        private String discount;
        private String hour;
        private List<BusinessInfo> list;

        public String getMtcount() {
            return mtcount;
        }

        public void setMtcount(String mtcount) {
            this.mtcount = mtcount;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getHour() {
            return hour;
        }

        public void setHour(String hour) {
            this.hour = hour;
        }

        public List<BusinessInfo> getList() {
            return list;
        }

        public void setList(List<BusinessInfo> list) {
            this.list = list;
        }

        //        2.
        public class BusinessInfo extends BaseModel {

            //商户名称
            //商家余额（XX小时XX分钟）
            //最后充值金额（XX小时）
            //最后充值时间（2017-07-07 16：58：02）
            //商户id

            private String name;
            private String mttime;
            private String lastadd;
            private String addtime;
            private String merchant_id;


            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMttime() {
                return mttime;
            }

            public void setMttime(String mttime) {
                this.mttime = mttime;
            }

            public String getLastadd() {
                return lastadd;
            }

            public void setLastadd(String lastadd) {
                this.lastadd = lastadd;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getMerchant_id() {
                return merchant_id;
            }

            public void setMerchant_id(String merchant_id) {
                this.merchant_id = merchant_id;
            }

            @Override
            public String toString() {
                return "BusinessInfo{" +
                        "name='" + name + '\'' +
                        ", mttime='" + mttime + '\'' +
                        ", lastadd='" + lastadd + '\'' +
                        ", addtime='" + addtime + '\'' +
                        ", merchant_id=" + merchant_id +
                        '}';
            }
        }


    }


}
