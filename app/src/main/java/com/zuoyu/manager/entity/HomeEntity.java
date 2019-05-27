package com.zuoyu.manager.entity;

import com.zuoyu.manager.base.BaseEntity;

import java.util.List;

/**
 * <pre>
 * Function：
 *
 * Created by JoannChen on 2017/3/9 17:22
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class HomeEntity extends BaseEntity {


    private HomeInfo data;

    public HomeInfo getData() {
        return data;
    }

    public void setData(HomeInfo data) {
        this.data = data;
    }

    //    1.
    public class HomeInfo {

        // 今日收入
        // 停车场名称
        // 应收金额
        // 优惠金额

        // 支付宝占比
        // 微信占比
        // 现金占比

        // 周转率
        // 利用率(昨日)
        // 利用率(24小时)

        private int today_earn;
        private String park_name;
        private int receivable;
        private int discount;

        private float ali_count;
        private float wx_count;
        private float xj_count;
        private float etc_count;

        private String turnover;
        private String utilization;
        private String utilization_hour;

        public String getUtilization_hour() {
            return utilization_hour;
        }

        public void setUtilization_hour(String utilization_hour) {
            this.utilization_hour = utilization_hour;
        }

        private List<Power> nodelist;

        public int getToday_earn() {
            return today_earn;
        }

        public void setToday_earn(int today_earn) {
            this.today_earn = today_earn;
        }

        public String getPark_name() {
            return park_name;
        }

        public void setPark_name(String park_name) {
            this.park_name = park_name;
        }

        public int getReceivable() {
            return receivable;
        }

        public void setReceivable(int receivable) {
            this.receivable = receivable;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public float getAli_count() {
            return ali_count;
        }

        public void setAli_count(float ali_count) {
            this.ali_count = ali_count;
        }

        public float getWx_count() {
            return wx_count;
        }

        public void setWx_count(float wx_count) {
            this.wx_count = wx_count;
        }

        public float getXj_count() {
            return xj_count;
        }

        public void setXj_count(float xj_count) {
            this.xj_count = xj_count;
        }

        public float getEtc_count() {
            return etc_count;
        }

        public void setEtc_count(float etc_count) {
            this.etc_count = etc_count;
        }

        public String getUtilization() {
            return utilization;
        }

        public void setUtilization(String utilization) {
            this.utilization = utilization;
        }

        public String getTurnover() {
            return turnover;
        }

        public void setTurnover(String turnover) {
            this.turnover = turnover;
        }

        public List<Power> getNodelist() {
            return nodelist;
        }

        public void setNodelist(List<Power> nodelist) {
            this.nodelist = nodelist;
        }

        @Override
        public String toString() {
            return "HomeInfo{" +
                    "today_earn=" + today_earn +
                    ", park_name='" + park_name + '\'' +
                    ", receivable=" + receivable +
                    ", discount=" + discount +
                    ", ali_count=" + ali_count +
                    ", wx_count=" + wx_count +
                    ", xj_count=" + xj_count +
                    ", etc_count=" + etc_count +
                    ", turnover='" + turnover + '\'' +
                    ", utilization='" + utilization + '\'' +
                    ", utilization_hour='" + utilization_hour + '\'' +
                    ", nodelist=" + nodelist +
                    '}';
        }

        //        2.
        public class Power {

            // 权限名称
            // 权限路径

            private String action;
            private String node;

            public String getNode() {
                return node;
            }

            public void setNode(String node) {
                this.node = node;
            }

            public String getAction() {
                return action;
            }

            public void setAction(String action) {
                this.action = action;
            }

            @Override
            public String toString() {
                return "Power{" +
                        "action='" + action + '\'' +
                        ", node='" + node + '\'' +
                        '}';
            }
        }
    }


}
