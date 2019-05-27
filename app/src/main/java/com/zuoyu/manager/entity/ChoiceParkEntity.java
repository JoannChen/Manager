package com.zuoyu.manager.entity;

import com.zuoyu.manager.base.BaseEntity;
import com.zuoyu.manager.base.BaseModel;

import java.util.List;

/**
 * <pre>
 * Function：选择停车场 实体类
 *
 * Created by JoannChen on 2017/3/9 17:12
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class ChoiceParkEntity extends BaseEntity {

    private List<ParkInfo> data;

    public List<ParkInfo> getData() {
        return data;
    }

    public void setData(List<ParkInfo> data) {
        this.data = data;
    }

    //    1.
    public class ParkInfo extends BaseModel {

        // 车场id
        // 停车场名称

        private String parkid;
        private String park_name;

        public String getParkid() {
            return parkid;
        }

        public String getPark_name() {
            return park_name;
        }

        public void setParkid(String parkid) {
            this.parkid = parkid;
        }

        public void setPark_name(String park_name) {
            this.park_name = park_name;
        }

        @Override
        public String toString() {
            return "ParkInfo{" +
                    "parkid='" + parkid + '\'' +
                    ", park_name='" + park_name + '\'' +
                    '}';
        }
    }


}

