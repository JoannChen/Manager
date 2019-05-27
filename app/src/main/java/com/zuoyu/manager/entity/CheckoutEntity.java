package com.zuoyu.manager.entity;

import com.zuoyu.manager.base.BaseEntity;

import java.util.List;

/**
 * <pre>
 * Function：结账统计实体类，两层集合
 *
 * Created by JoannChen on 2017/3/13 11:09
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class CheckoutEntity extends BaseEntity {


    private Checkout data;

    public Checkout getData() {
        return data;
    }

    public void setData(Checkout data) {
        this.data = data;
    }

    //    1.
    public class Checkout {


        // 今日收入
        // 今日应收
        // 今日优惠
        // 收费方式集合

        private String today_earn;
        private String receivable;
        private String discount;
        private List<CheckoutList> typelist;

        public String getToday_earn() {
            return today_earn;
        }

        public void setToday_earn(String today_earn) {
            this.today_earn = today_earn;
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

        public List<CheckoutList> getTypelist() {
            return typelist;
        }

        public void setTypelist(List<CheckoutList> typelist) {
            this.typelist = typelist;
        }

        //    2.
        public class CheckoutList {

            // 收费方式（岗亭、自助缴费、中央缴费）
            // 现金总和
            // 每笔收费详情

            private String name;
            private String cashcount;
            private List<CheckoutInfo> chargeinfolist;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCashcount() {
                return cashcount;
            }

            public void setCashcount(String cashcount) {
                this.cashcount = cashcount;
            }

            public List<CheckoutInfo> getChargeinfolist() {
                return chargeinfolist;
            }

            public void setChargeinfolist(List<CheckoutInfo> chargeinfolist) {
                this.chargeinfolist = chargeinfolist;
            }

            //    3.
            public class CheckoutInfo {

                // 收费员
                // 现金
                // 电子收费金额
                // 出口

                private String name;
                private String cash;
                private String nocash;
                private String exit;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getCash() {
                    return cash;
                }

                public void setCash(String cash) {
                    this.cash = cash;
                }

                public String getNocash() {
                    return nocash;
                }

                public void setNocash(String nocash) {
                    this.nocash = nocash;
                }

                public String getExit() {
                    return exit;
                }

                public void setExit(String exit) {
                    this.exit = exit;
                }
            }

        }
    }

}
