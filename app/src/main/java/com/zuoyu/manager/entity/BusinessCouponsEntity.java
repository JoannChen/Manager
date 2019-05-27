package com.zuoyu.manager.entity;

import com.zuoyu.manager.base.BaseEntity;
import com.zuoyu.manager.base.BaseModel;

import java.util.List;

/**
 * <pre>
 * Function：商家优惠券实体类
 *
 * Created by JoannChen on 2017/3/24 10:05
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class BusinessCouponsEntity extends BaseEntity {

    public BusinessCoupons data;

    public BusinessCoupons getData() {
        return data;
    }

    public void setData(BusinessCoupons data) {
        this.data = data;
    }

    //    1.
    public class BusinessCoupons extends BaseModel {


        // 商户余额（XX小时XX分钟）
        // 剩余优惠券
        // 集合


        private String mttime;
        private String discount;
        private List<BusinessCouponsInfo> list;

        public String getMttime() {
            return mttime;
        }

        public void setMttime(String mttime) {
            this.mttime = mttime;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public List<BusinessCouponsInfo> getList() {
            return list;
        }

        public void setList(List<BusinessCouponsInfo> list) {
            this.list = list;
        }

        public class BusinessCouponsInfo extends BaseModel {

            //优惠券昵称
            //优惠券总数
            //已发放优惠券
            //有效期（2017-07-07）
            //优惠名称
            //优惠类型(1：全免优惠，4：时长优惠，5：金额优惠，6：折扣优惠)

            private String nickname;
            private String total;
            private String use;
            private String enddate;
            private String coupons_name;
            private int type;

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getUse() {
                return use;
            }

            public void setUse(String use) {
                this.use = use;
            }

            public String getEnddate() {
                return enddate;
            }

            public void setEnddate(String enddate) {
                this.enddate = enddate;
            }

            public String getCoupons_name() {
                return coupons_name;
            }

            public void setCoupons_name(String coupons_name) {
                this.coupons_name = coupons_name;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }


            @Override
            public String toString() {
                return "BusinessCouponsInfo{" +
                        "nickname='" + nickname + '\'' +
                        ", total='" + total + '\'' +
                        ", use='" + use + '\'' +
                        ", enddate='" + enddate + '\'' +
                        ", coupons_name='" + coupons_name + '\'' +
                        ", type=" + type +
                        '}';
            }
        }


    }


}
