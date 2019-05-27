package com.zuoyu.manager.entity;

import com.zuoyu.manager.base.BaseEntity;
import com.zuoyu.manager.base.BaseModel;

import java.util.List;

/**
 * <pre>
 * Function：月卡管理实体类
 *
 * Created by JoannChen on 2017/3/10 17:49
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class MonthEntity extends BaseEntity {

    private List<MonthInfo> data;

    public List<MonthInfo> getData() {
        return data;
    }

    public void setData(List<MonthInfo> data) {
        this.data = data;
    }

    //    1.
    public class MonthInfo extends BaseModel {

        // 车牌号
        // 月卡面值
        // 有效期（如2017-02-21至2018-02-21）
        // 月卡类型1：新增，2：续费
        // 持卡人姓名
        // 持卡人电话
        // 停车场名字

        private String plate;
        private String price;
        private String validity;
        private int type;
        private String name;
        private String tel;
        private String park_name;

        public String getPlate() {
            return plate;
        }

        public void setPlate(String plate) {
            this.plate = plate;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getValidity() {
            return validity;
        }

        public void setValidity(String validity) {
            this.validity = validity;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getPark_name() {
            return park_name;
        }

        public void setPark_name(String park_name) {
            this.park_name = park_name;
        }

        @Override
        public String toString() {
            return "MonthInfo{" +
                    "plate='" + plate + '\'' +
                    ", price='" + price + '\'' +
                    ", validity='" + validity + '\'' +
                    ", type=" + type +
                    ", name='" + name + '\'' +
                    ", tel='" + tel + '\'' +
                    ", park_name='" + park_name + '\'' +
                    '}';
        }
    }


}
