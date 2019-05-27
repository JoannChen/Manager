package com.zuoyu.manager.entity;

import com.zuoyu.manager.base.BaseEntity;
import com.zuoyu.manager.base.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * Function：车位利用率 实体类
 *
 * Created by JoannChen on 2017/3/24 10:05
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class UtilizationEntity extends BaseEntity {


    public UtilizationList data;

    public UtilizationList getData() {
        return data;
    }

    public void setData(UtilizationList data) {
        this.data = data;
    }

    //    1.
    public class UtilizationList extends BaseModel {


        // 日平均占用率
        // 总车位
        // 车位占用率集合(24小时)
        // 车位利用率集合(7日／30日)

        private String use;
        private int park_count;
        private List<UtilizationInfo> occupyList;
        private List<UtilizationInfo> unilizationList;

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

        public String getUse() {
            return use;
        }

        public int getPark_count() {
            return park_count;
        }

        public List<UtilizationInfo> getOccupyList() {
            return occupyList;
        }

        public List<UtilizationInfo> getUnilizationList() {
            return unilizationList;
        }

        public void setUse(String use) {
            this.use = use;
        }

        public void setPark_count(int park_count) {
            this.park_count = park_count;
        }

        public void setOccupyList(List<UtilizationInfo> occupyList) {
            this.occupyList = occupyList;
        }

        public void setUnilizationList(List<UtilizationInfo> unilizationList) {
            this.unilizationList = unilizationList;
        }

        //        2.
        public class UtilizationInfo extends BaseModel implements Serializable {

            // 时间（5/17）
            // 日期（2017-5-17）

            // 占用率(24小时)
            // 使用率(7日／30日)

            private String time;
            private String date;
            private float occupy;
            private float unilization;


            public String getTime() {
                return time;
            }

            public String getDate() {
                return date;
            }

            public float getOccupy() {
                return occupy;
            }

            public float getUnilization() {
                return unilization;
            }


            public void setTime(String time) {
                this.time = time;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public void setOccupy(float occupy) {
                this.occupy = occupy;
            }

            public void setUnilization(float unilization) {
                this.unilization = unilization;
            }

            @Override
            public String toString() {
                return "UtilizationInfo{" +
                        "time='" + time + '\'' +
                        ", date='" + date + '\'' +
                        ", occupy=" + occupy +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "UtilizationList{" +
                    "use='" + use + '\'' +
                    ", park_count=" + park_count +
                    ", occupyList=" + occupyList +
                    '}';
        }
    }


}
