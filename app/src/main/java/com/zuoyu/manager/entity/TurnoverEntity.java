package com.zuoyu.manager.entity;

import com.zuoyu.manager.base.BaseEntity;
import com.zuoyu.manager.base.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * Function：车位周转率率统计实体类
 *
 * Created by JoannChen on 2017/3/24 10:05
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class TurnoverEntity extends BaseEntity {


    public TurnoverList data;

    public TurnoverList getData() {
        return data;
    }

    public void setData(TurnoverList data) {
        this.data = data;
    }

    //    1.
    public class TurnoverList extends BaseModel {


        // 日平均周转率
        // 总车位
        // 周转率集合

        private String dayturnover;
        private float maxvelocity;
        private List<TurnoverInfo> turnoverList;

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

        public String getDayturnover() {
            return dayturnover;
        }


        public float getMaxvelocity() {
            return maxvelocity;
        }

        public void setMaxvelocity(float maxvelocity) {
            this.maxvelocity = maxvelocity;
        }

        public List<TurnoverInfo> getTurnoverList() {
            return turnoverList;
        }


        public void setDayturnover(String dayturnover) {
            this.dayturnover = dayturnover;
        }


        public void setTurnoverList(List<TurnoverInfo> turnoverList) {
            this.turnoverList = turnoverList;
        }

        //        2.
        public class TurnoverInfo extends BaseModel implements Serializable {

            // 日期（207-5-16）
            // 时间（5/16）
            // 周转率

            private String date;
            private String time;
            private float turnover;

            public String getDate() {
                return date;
            }

            public float getTurnover() {
                return turnover;
            }

            public String getTime() {
                return time;
            }

            public void setDate(String date) {
                this.date = date;
            }


            public void setTime(String time) {
                this.time = time;
            }

            public void setTurnover(float turnover) {
                this.turnover = turnover;
            }


            @Override
            public String toString() {
                return "TurnoverInfo{" +
                        "date='" + date + '\'' +
                        ", turnover=" + turnover +
                        ", time='" + time + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "TurnoverList{" +
                    "dayturnover='" + dayturnover + '\'' +
                    ", maxvelocity=" + maxvelocity +
                    ", turnoverList=" + turnoverList +
                    ", desc='" + desc + '\'' +
                    ", dayRate='" + dayRate + '\'' +
                    '}';
        }
    }


}
