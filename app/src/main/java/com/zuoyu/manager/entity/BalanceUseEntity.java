package com.zuoyu.manager.entity;

import com.zuoyu.manager.base.BaseEntity;
import com.zuoyu.manager.base.BaseModel;

import java.util.List;

/**
 * <pre>
 * Function：余额使用记录实体类
 *
 * Created by JoannChen on 2017/7/19 15:48
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * </pre>
 */
public class BalanceUseEntity extends BaseEntity {

    private List<Balance> data;//详细结果集合

    public List<Balance> getData() {
        return data;
    }

    public void setData(List<Balance> data) {
        this.data = data;
    }

    public class Balance extends BaseModel {


        //优惠详情(5元、2小时、8折)
        //发放日期(2017.4.10 12:09:34)
        //车牌号
        //小票id
        //使用日期(2017-4-10 12:09:34)

        private String discount_details;
        private String start_time;
        private String license_plate;
        private String ticketno;
        private String used_time;

        public String getDiscount_details() {
            return discount_details;
        }

        public void setDiscount_details(String discount_details) {
            this.discount_details = discount_details;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getLicense_plate() {
            return license_plate;
        }

        public void setLicense_plate(String license_plate) {
            this.license_plate = license_plate;
        }

        public String getTicketno() {
            return ticketno;
        }

        public void setTicketno(String ticketno) {
            this.ticketno = ticketno;
        }

        public String getUsed_time() {
            return used_time;
        }

        public void setUsed_time(String used_time) {
            this.used_time = used_time;
        }


        @Override
        public String toString() {
            return "Balance{" +
                    "discount_details='" + discount_details + '\'' +
                    ", start_time='" + start_time + '\'' +
                    ", license_plate='" + license_plate + '\'' +
                    ", icketno='" + ticketno + '\'' +
                    ", used_time='" + used_time + '\'' +
                    '}';
        }
    }


}
