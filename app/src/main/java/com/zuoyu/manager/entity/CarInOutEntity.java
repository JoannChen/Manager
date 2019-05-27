package com.zuoyu.manager.entity;

import com.zuoyu.manager.base.BaseEntity;
import com.zuoyu.manager.base.BaseModel;

import java.util.List;

/**
 * <pre>
 * Function：出入场车辆统计实体类
 *
 * Created by JoannChen on 2017/3/24 10:05
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class CarInOutEntity extends BaseEntity {

    public FlowCar data;

    public FlowCar getData() {
        return data;
    }

    public void setData(FlowCar data) {
        this.data = data;
    }

    //    1.
    public class FlowCar extends BaseModel {

        // 总车位
        // 入场车辆
        // 出场车辆

        public int park_count;
        private List<FlowCarInfo> enterlist;
        private List<FlowCarInfo> exitlist;

        // 图表表头描述 ：近24小时统计／近7日统计／近30日统计
        // 图表数据统计展示：日平均收入／利用率／周转率
        private String desc;
        private String dayRate;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getDayRate() {
            return dayRate;
        }

        public void setDayRate(String dayRate) {
            this.dayRate = dayRate;
        }

        public int getPark_count() {
            return park_count;
        }

        public List<FlowCarInfo> getEnterlist() {
            return enterlist;
        }

        public List<FlowCarInfo> getExitlist() {
            return exitlist;
        }

        public void setPark_count(int park_count) {
            this.park_count = park_count;
        }

        public void setEnterlist(List<FlowCarInfo> enterlist) {
            this.enterlist = enterlist;
        }

        public void setExitlist(List<FlowCarInfo> exitlist) {
            this.exitlist = exitlist;
        }

        //        2.
        public class FlowCarInfo extends BaseModel {

            // 日期（格式2017-5-16）
            // 流量
            // 时间（5/16）

            private String date;
            private int count;
            private String time;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            @Override
            public String toString() {
                return "FlowCarInfo{" +
                        "time='" + time + '\'' +
                        ", count='" + count + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "FlowCar{" +
                    "park_count=" + park_count +
                    ", enterlist=" + enterlist +
                    ", exitlist=" + exitlist +
                    ", desc='" + desc + '\'' +
                    ", dayRate='" + dayRate + '\'' +
                    '}';
        }
    }


}
