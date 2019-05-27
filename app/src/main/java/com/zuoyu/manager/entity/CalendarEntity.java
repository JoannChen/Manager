package com.zuoyu.manager.entity;

import com.zuoyu.manager.base.BaseEntity;

import java.util.List;

/**
 * <pre>
 * Function：
 *
 * Created by JoannChen on 2017/3/22 14:15
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class CalendarEntity extends BaseEntity {


    private List<CalendarInfo> data;

    public List<CalendarInfo> getData() {
        return data;
    }

    public void setData(List<CalendarInfo> data) {
        this.data = data;
    }

    //    1.
    public class CalendarInfo {
        private String price;// 金额
        private String date;// 月份（格式 xxxx/x）

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return "CaclendarInfo{" +
                    "price='" + price + '\'' +
                    ", date='" + date + '\'' +
                    '}';
        }
    }

}
