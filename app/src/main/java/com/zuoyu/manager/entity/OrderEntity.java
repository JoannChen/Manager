package com.zuoyu.manager.entity;

import com.zuoyu.manager.base.BaseEntity;
import com.zuoyu.manager.base.BaseModel;

import java.util.List;

/**
 * <pre>
 * Function：订单记录子条目
 *
 * Created by JoannChen on 2017/3/8 10:18
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class OrderEntity extends BaseEntity {

    // 搜索列表

    private List<OrderInfo> data;

    public List<OrderInfo> getData() {
        return data;
    }

    public void setData(List<OrderInfo> data) {
        this.data = data;
    }

    //    1.
    public class OrderInfo extends BaseModel {

        // 订单id
        // 日期（如2017/02/21 12:20:05）
        // 车牌号
        // 停车时长（如1时20分）
        // 停车费用
        // 车辆类型1：临停，2：月卡
        private String orderid;
        private String date;
        private String plate;
        private String park_long;
        private String price;
        private int type;

        public String getOrderid() {
            return orderid;
        }

        public String getDate() {
            return date;
        }

        public String getPlate() {
            return plate;
        }

        public String getPark_long() {
            return park_long;
        }

        public String getPrice() {
            return price;
        }

        public int getType() {
            return type;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setPlate(String plate) {
            this.plate = plate;
        }

        public void setPark_long(String park_long) {
            this.park_long = park_long;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setType(int type) {
            this.type = type;
        }


        @Override
        public String toString() {
            return "OrderList{" +
                    "orderid='" + orderid + '\'' +
                    ", date='" + date + '\'' +
                    ", plate='" + plate + '\'' +
                    ", park_long='" + park_long + '\'' +
                    ", price='" + price + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }
    }

}


