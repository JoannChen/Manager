package com.zuoyu.manager.entity;

import com.zuoyu.manager.base.BaseEntity;

import java.util.List;

/**
 * <pre>
 * Function：日／月报表实体类
 *
 * Created by JoannChen on 2017/3/20 10:59
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class ReportEntity extends BaseEntity {

    private Report data;

    public Report getData() {
        return data;
    }

    public void setData(Report data) {
        this.data = data;
    }


    //    1.
    public class Report {


        // 停车场名称
        // 总应收
        // 总实收
        // 总优惠
        private String park_name;
        private String receivable;
        private String charge;
        private String discount;

        // 本地现金
        // 本地现金详情
        private String localcash;
        private List<ReportInfo> localcashdetail;


        // 本地非现金
        // 本地非现金详情
        private String localnocash;
        private List<ReportInfo> localnocashdetail;


        // 无忧平台
        // 无忧平台详情
        private String unispark;
        private List<ReportInfo> unisparkdetail;


        // 月卡
        // 月卡详情
        private String monthcard;
        private List<ReportInfo> monthcarddetail;

        // 第三方平台
        private String thirdcharge;


        public String getPark_name() {
            return park_name;
        }

        public void setPark_name(String park_name) {
            this.park_name = park_name;
        }

        public String getReceivable() {
            return receivable;
        }

        public void setReceivable(String receivable) {
            this.receivable = receivable;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getCharge() {
            return charge;
        }

        public void setCharge(String charge) {
            this.charge = charge;
        }

        public String getLocalcash() {
            return localcash;
        }

        public void setLocalcash(String localcash) {
            this.localcash = localcash;
        }

        public List<ReportInfo> getLocalcashdetail() {
            return localcashdetail;
        }

        public void setLocalcashdetail(List<ReportInfo> localcashdetail) {
            this.localcashdetail = localcashdetail;
        }

        public String getLocalnocash() {
            return localnocash;
        }

        public void setLocalnocash(String localnocash) {
            this.localnocash = localnocash;
        }

        public List<ReportInfo> getLocalnocashdetail() {
            return localnocashdetail;
        }

        public void setLocalnocashdetail(List<ReportInfo> localnocashdetail) {
            this.localnocashdetail = localnocashdetail;
        }

        public String getUnispark() {
            return unispark;
        }

        public void setUnispark(String unispark) {
            this.unispark = unispark;
        }

        public List<ReportInfo> getUnisparkdetail() {
            return unisparkdetail;
        }

        public void setUnisparkdetail(List<ReportInfo> unisparkdetail) {
            this.unisparkdetail = unisparkdetail;
        }

        public String getMonthcard() {
            return monthcard;
        }

        public void setMonthcard(String monthcard) {
            this.monthcard = monthcard;
        }

        public List<ReportInfo> getMonthcarddetail() {
            return monthcarddetail;
        }

        public void setMonthcarddetail(List<ReportInfo> monthcarddetail) {
            this.monthcarddetail = monthcarddetail;
        }

        public String getThirdcharge() {
            return thirdcharge;
        }

        public void setThirdcharge(String thirdcharge) {
            this.thirdcharge = thirdcharge;
        }

        @Override
        public String toString() {
            return "ReportEntity{" +
                    "park_name='" + park_name + '\'' +
                    ", receivable='" + receivable + '\'' +
                    ", discount='" + discount + '\'' +
                    ", charge='" + charge + '\'' +
                    ", localcash='" + localcash + '\'' +
                    ", localcashdetail=" + localcashdetail +
                    ", localnocash='" + localnocash + '\'' +
                    ", localnocashdetail=" + localnocashdetail +
                    ", unispark='" + unispark + '\'' +
                    ", unisparkdetail=" + unisparkdetail +
                    ", monthcard='" + monthcard + '\'' +
                    ", monthcarddetail=" + monthcarddetail +
                    ", thirdcharge='" + thirdcharge + '\'' +
                    '}';
        }

        //    2.
        public class ReportInfo {

            // 说明
            // 价格

            private String info;
            private String price;


            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            @Override
            public String toString() {
                return "ReportInfo{" +
                        "info='" + info + '\'' +
                        ", price='" + price + '\'' +
                        '}';
            }
        }
    }
}
