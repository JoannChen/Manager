package com.zuoyu.manager.entity;

import com.zuoyu.manager.base.BaseEntity;
import com.zuoyu.manager.base.BaseModel;

import java.util.List;

/**
 * <pre>
 * Function：在场车辆子条目
 *
 * Created by JoannChen on 2017/3/9 11:43
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class ParkingEntity extends BaseEntity {

    private List<ParkingInfo> data;

    public List<ParkingInfo> getData() {
        return data;
    }

    public void setData(List<ParkingInfo> data) {
        this.data = data;
    }

    //    1.
    public class ParkingInfo extends BaseModel{

        //  订单id
        //  车牌号
        //  订单状态 1：未支付，2：已支付，3：已超时，4：VIP
        //  车辆类型 1：临停，2：月卡
        //  停车费用
        //  入场时间（如2017/02/21 12:20:05）
        //  停车时长（如1时20分）

        private String orderid;
        private String plate;
        private int status;
        private int type;
        private String price;
        private String in_time;
        private String park_long;


        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getPlate() {
            return plate;
        }

        public void setPlate(String plate) {
            this.plate = plate;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getIn_time() {
            return in_time;
        }

        public void setIn_time(String in_time) {
            this.in_time = in_time;
        }

        public String getPark_long() {
            return park_long;
        }

        public void setPark_long(String park_long) {
            this.park_long = park_long;
        }


        @Override
        public String toString() {
            return "ParkingInfo{" +
                    "orderid='" + orderid + '\'' +
                    ", plate='" + plate + '\'' +
                    ", status=" + status +
                    ", type=" + type +
                    ", price='" + price + '\'' +
                    ", in_time='" + in_time + '\'' +
                    ", park_long='" + park_long + '\'' +
                    '}';
        }
    }


}
