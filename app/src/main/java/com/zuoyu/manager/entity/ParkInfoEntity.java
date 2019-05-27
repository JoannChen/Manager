package com.zuoyu.manager.entity;

import com.zuoyu.manager.base.BaseEntity;

import java.util.List;

/**
 * <pre>
 * Function：停车场详情实体类
 *
 * Created by JoannChen on 2017/3/10 15:37
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class ParkInfoEntity extends BaseEntity {

    private ParkInfo data;


    public ParkInfo getData() {
        return data;
    }

    public void setData(ParkInfo data) {
        this.data = data;
    }

    //    1.
    public class ParkInfo {

        // 停车场图片url地址
        // 车位总数目
        // 停车场地址
        // 停车场名称
        // 停车场电话
        // 停车场编号
        // 停车场类型
        // 营业时间

        private String image;
        private String park_count;
        private String address;
        private String park_name;
        private String park_tel;
        private String park_num;
        private String park_type;
        private String service_time;

        // 白天时间
        // 白天价格信息list

        // 晚上时间
        // 晚上价格信息list

        private String dpricedaytime;
        private List<PriceInfo> dpricedaylist;

        private String dpricenighttime;
        private List<PriceInfo> dpricenightlist;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPark_count() {
            return park_count;
        }

        public void setPark_count(String park_count) {
            this.park_count = park_count;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPark_name() {
            return park_name;
        }

        public void setPark_name(String park_name) {
            this.park_name = park_name;
        }

        public String getPark_tel() {
            return park_tel;
        }

        public void setPark_tel(String park_tel) {
            this.park_tel = park_tel;
        }

        public String getPark_num() {
            return park_num;
        }

        public void setPark_num(String park_num) {
            this.park_num = park_num;
        }

        public String getPark_type() {
            return park_type;
        }

        public void setPark_type(String park_type) {
            this.park_type = park_type;
        }

        public String getService_time() {
            return service_time;
        }

        public void setService_time(String service_time) {
            this.service_time = service_time;
        }

        public String getDpricedaytime() {
            return dpricedaytime;
        }

        public void setDpricedaytime(String dpricedaytime) {
            this.dpricedaytime = dpricedaytime;
        }

        public List<PriceInfo> getDpricedaylist() {
            return dpricedaylist;
        }

        public void setDpricedaylist(List<PriceInfo> dpricedaylist) {
            this.dpricedaylist = dpricedaylist;
        }

        public String getDpricenighttime() {
            return dpricenighttime;
        }

        public void setDpricenighttime(String dpricenighttime) {
            this.dpricenighttime = dpricenighttime;
        }

        public List<PriceInfo> getDpricenightlist() {
            return dpricenightlist;
        }

        public void setDpricenightlist(List<PriceInfo> dpricenightlist) {
            this.dpricenightlist = dpricenightlist;
        }


        @Override
        public String toString() {
            return "ParkInfoEntity{" +
                    "image='" + image + '\'' +
                    ", park_count='" + park_count + '\'' +
                    ", address='" + address + '\'' +
                    ", park_name='" + park_name + '\'' +
                    ", park_tel='" + park_tel + '\'' +
                    ", park_num='" + park_num + '\'' +
                    ", park_type='" + park_type + '\'' +
                    ", service_time='" + service_time + '\'' +
                    ", dpricedaytime='" + dpricedaytime + '\'' +
                    ", dpricedaylist=" + dpricedaylist +
                    ", dpricenighttime='" + dpricenighttime + '\'' +
                    ", dpricenightlist=" + dpricenightlist +
                    '}';
        }


        //    2.
        public class PriceInfo {

            // 描述 （xx分钟）
            // 价格（xxx元）

            private String info;
            private String price;


            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            @Override
            public String toString() {
                return "PriceInfo{" +
                        "info='" + info + '\'' +
                        ", price='" + price + '\'' +
                        '}';
            }
        }
    }


}
