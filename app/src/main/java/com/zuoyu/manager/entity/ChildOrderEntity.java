package com.zuoyu.manager.entity;

import com.zuoyu.manager.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * Function：子订单明细实体类
 *
 * Created by JoannChen on 2017/3/8 15:18
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class ChildOrderEntity extends BaseEntity {

    private ChildOrder data;

    public ChildOrder getData() {
        return data;
    }

    public void setData(ChildOrder data) {
        this.data = data;
    }

    //  1.
    public class ChildOrder implements Serializable {

        // 车场名称
        // 停车费用
        // 车牌号
        // 停车时长（如02:25:12）
        // 子订单列表

        private String park_name;
        private String price;
        private String plate;
        private String park_long;
        private List<ChildInfo> orderlist;


        public String getPark_name() {
            return park_name;
        }

        public void setPark_name(String park_name) {
            this.park_name = park_name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPlate() {
            return plate;
        }

        public void setPlate(String plate) {
            this.plate = plate;
        }

        public String getPark_long() {
            return park_long;
        }

        public void setPark_long(String park_long) {
            this.park_long = park_long;
        }

        public List<ChildInfo> getOrderlist() {
            return orderlist;
        }

        public void setOrderlist(List<ChildInfo> orderlist) {
            this.orderlist = orderlist;
        }


        @Override
        public String toString() {
            return "ChildOrder{" +
                    "park_name='" + park_name + '\'' +
                    ", price='" + price + '\'' +
                    ", plate='" + plate + '\'' +
                    ", park_long='" + park_long + '\'' +
                    ", orderlist=" + orderlist +
                    '}';
        }

        //  2.
        public class ChildInfo implements Serializable {

            /**
             * 以下为结果集list(code=200时有效)
             */

            // 子订单号
            // 支付时间（如2017-02-21 12:20:05）
            // 支付时停车时长（如02:25:12）
            // 订单金额
            // 优惠金额
            // 余额支付
            // 实际支付
            // 支付方式1：支付宝，2：微信，3：现金
            // 支付渠道

            private String child_orderid;
            private String pay_time;
            private String pay_park_long;
            private String money;
            private String discount;
            private String balance_pay;
            private String actual_pay;
            private int pay_type;
            private String pay_channel;

            public String getPay_channel() {
                return pay_channel;
            }

            public void setPay_channel(String pay_channel) {
                this.pay_channel = pay_channel;
            }

            public String getChild_orderid() {
                return child_orderid;
            }

            public void setChild_orderid(String child_orderid) {
                this.child_orderid = child_orderid;
            }

            public String getPay_time() {
                return pay_time;
            }

            public void setPay_time(String pay_time) {
                this.pay_time = pay_time;
            }

            public String getPay_park_long() {
                return pay_park_long;
            }

            public void setPay_park_long(String pay_park_long) {
                this.pay_park_long = pay_park_long;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public String getBalance_pay() {
                return balance_pay;
            }

            public void setBalance_pay(String balance_pay) {
                this.balance_pay = balance_pay;
            }

            public String getActual_pay() {
                return actual_pay;
            }

            public void setActual_pay(String actual_pay) {
                this.actual_pay = actual_pay;
            }

            public int getPay_type() {
                return pay_type;
            }

            public void setPay_type(int pay_type) {
                this.pay_type = pay_type;
            }


            @Override
            public String toString() {
                return "ChildInfo{" +
                        "child_orderid='" + child_orderid + '\'' +
                        ", pay_time='" + pay_time + '\'' +
                        ", pay_park_long='" + pay_park_long + '\'' +
                        ", money='" + money + '\'' +
                        ", discount='" + discount + '\'' +
                        ", balance_pay='" + balance_pay + '\'' +
                        ", actual_pay='" + actual_pay + '\'' +
                        ", pay_type=" + pay_type +
                        ", pay_channel='" + pay_channel + '\'' +
                        '}';
            }
        }
    }


}
