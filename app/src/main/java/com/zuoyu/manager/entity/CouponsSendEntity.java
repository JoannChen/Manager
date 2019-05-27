package com.zuoyu.manager.entity;

import com.zuoyu.manager.base.BaseEntity;
import com.zuoyu.manager.base.BaseModel;

import java.util.List;

/**
 * <pre>
 * Function：优惠券使用记录实体类
 *
 * Created by JoannChen on 2017/7/19 15:48
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * </pre>
 */
public class CouponsSendEntity extends BaseEntity {

    private List<CouponsInfo> data;//详细结果集合

    public List<CouponsInfo> getData() {
        return data;
    }

    public void setData(List<CouponsInfo> data) {
        this.data = data;
    }

    public class CouponsInfo extends BaseModel {

        private String discount_details;//优惠详情(5元、2小时、8折)
        private String start_time;//发放日期(2017.4.10 12:09:34)
        private String license_plate;//车牌号
        private String ticketno;// 小票id
        private String used_time;//使用日期(2017.4.10 12:09:34)
        private String coupons_nickname;//优惠券昵称


        public String getCoupons_nickname() {
            return coupons_nickname;
        }

        public void setCoupons_nickname(String coupons_nickname) {
            this.coupons_nickname = coupons_nickname;
        }

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
            return "DiscountInfo{" +
                    "discount_details='" + discount_details + '\'' +
                    ", start_time='" + start_time + '\'' +
                    ", license_plate='" + license_plate + '\'' +
                    ", ticketno='" + ticketno + '\'' +
                    ", used_time='" + used_time + '\'' +
                    ", coupons_nickname='" + coupons_nickname + '\'' +
                    '}';
        }
    }


}
