package com.zuoyu.manager.entity;

import com.zuoyu.manager.base.BaseEntity;

import java.io.Serializable;

/**
 * <pre>
 * Function：在场车辆详情／订单记录详情 实体类
 *
 * Created by JoannChen on 2017/3/8 15:18
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class DetailsEntity extends BaseEntity {

    private Details data;

    public Details getData() {
        return data;
    }

    public void setData(Details data) {
        this.data = data;
    }

    //  1.
    public class Details implements Serializable {

        // 车场名称
        // 停车费用
        // 车牌号
        // 停车时长（如02:25:12）

        // 车辆类型 1：临停，2：月卡
        // 订单状态 1：未支付，2：已支付，3：已超时，4：VIP
        // 入场时间
        // 出场时间

        // 超时时长
        // 补缴费用

        private String park_name;
        private String price;
        private String plate;
        private String park_long;

        private int type;
        private int status;
        private String enter_time;
        private String exit_time;

        private String over_time;
        private String supplement;


        // 入场图片
        // 出场图片
        private String enter_image;
        private String exit_image;


        /**
         * 以下为结果(status =2时有效)
         */

        //  订单号编号
        //  支付时间（如2017-02-21 12:20:05）(多次支付)
        //  订单金额
        //  优惠金额
        //  余额支付
        //  实际支付
        //  支付方式 1:支付宝，2:微信，3:现金，4:多次支付
        // 纸票ID编号 (16进制的字符串)

        private String orderid;
        private String pay_time;
        private String money;
        private String discount;
        private String balance_pay;
        private String actual_pay;
        private int pay_type;
        private String ticket_id;

        public String getEnter_image() {
            return enter_image;
        }

        public void setEnter_image(String enter_image) {
            this.enter_image = enter_image;
        }

        public String getExit_image() {
            return exit_image;
        }

        public void setExit_image(String exit_image) {
            this.exit_image = exit_image;
        }

        public String getTicket_id() {
            return ticket_id;
        }

        public void setTicket_id(String ticket_id) {
            this.ticket_id = ticket_id;
        }

        // 支付时停车时长（如02:25:12）
        private String pay_park_long;

        public String getPay_park_long() {
            return pay_park_long;
        }

        public void setPay_park_long(String pay_park_long) {
            this.pay_park_long = pay_park_long;
        }

        public String getPark_name() {
            return park_name;
        }

        public String getPlate() {
            return plate;
        }

        public String getPrice() {
            return price;
        }

        public String getPark_long() {
            return park_long;
        }

        public int getType() {
            return type;
        }

        public int getStatus() {
            return status;
        }

        public String getEnter_time() {
            return enter_time;
        }

        public String getExit_time() {
            return exit_time;
        }

        public String getOver_time() {
            return over_time;
        }

        public String getSupplement() {
            return supplement;
        }

        public String getOrderid() {
            return orderid;
        }

        public String getPay_time() {
            return pay_time;
        }

        public String getMoney() {
            return money;
        }

        public String getDiscount() {
            return discount;
        }

        public String getBalance_pay() {
            return balance_pay;
        }

        public String getActual_pay() {
            return actual_pay;
        }

        public int getPay_type() {
            return pay_type;
        }


        public void setPark_name(String park_name) {
            this.park_name = park_name;
        }

        public void setPlate(String plate) {
            this.plate = plate;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setPark_long(String park_long) {
            this.park_long = park_long;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setEnter_time(String enter_time) {
            this.enter_time = enter_time;
        }

        public void setExit_time(String exit_time) {
            this.exit_time = exit_time;
        }

        public void setOver_time(String over_time) {
            this.over_time = over_time;
        }

        public void setSupplement(String supplement) {
            this.supplement = supplement;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public void setBalance_pay(String balance_pay) {
            this.balance_pay = balance_pay;
        }

        public void setActual_pay(String actual_pay) {
            this.actual_pay = actual_pay;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }


        @Override
        public String toString() {
            return "Details{" +
                    "park_name='" + park_name + '\'' +
                    ", price='" + price + '\'' +
                    ", plate='" + plate + '\'' +
                    ", park_long='" + park_long + '\'' +
                    ", type=" + type +
                    ", status=" + status +
                    ", enter_time='" + enter_time + '\'' +
                    ", exit_time='" + exit_time + '\'' +
                    ", over_time='" + over_time + '\'' +
                    ", supplement='" + supplement + '\'' +
                    ", enter_image='" + enter_image + '\'' +
                    ", exit_image='" + exit_image + '\'' +
                    ", orderid='" + orderid + '\'' +
                    ", pay_time='" + pay_time + '\'' +
                    ", money='" + money + '\'' +
                    ", discount='" + discount + '\'' +
                    ", balance_pay='" + balance_pay + '\'' +
                    ", actual_pay='" + actual_pay + '\'' +
                    ", pay_type=" + pay_type +
                    ", ticket_id='" + ticket_id + '\'' +
                    ", pay_park_long='" + pay_park_long + '\'' +
                    '}';
        }
    }


}
