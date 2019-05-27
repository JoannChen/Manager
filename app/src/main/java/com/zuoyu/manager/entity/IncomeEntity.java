package com.zuoyu.manager.entity;

import com.zuoyu.manager.base.BaseEntity;
import com.zuoyu.manager.base.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * Function：财务分析——车场收入统计实体类
 *
 * Created by JoannChen on 2017/3/24 17:38
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class IncomeEntity extends BaseEntity implements Serializable {

    private IncomeList data;

    public IncomeList getData() {
        return data;
    }

    public void setData(IncomeList data) {
        this.data = data;
    }


    //    1.
    public class IncomeList extends BaseModel implements Serializable {

        // 日平均收入
        // 一周车场收费集合
        // 最大收费金额
        private String dayave;
        private int maxcharge;
        private List<IncomeInfo> chargeList;

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

        public String getDayave() {
            return dayave;
        }

        public void setDayave(String dayave) {
            this.dayave = dayave;
        }

        public int getMaxcharge() {
            return maxcharge;
        }

        public void setMaxcharge(int maxcharge) {
            this.maxcharge = maxcharge;
        }

        public List<IncomeInfo> getChargeList() {
            return chargeList;
        }

        public void setChargeList(List<IncomeInfo> chargeList) {
            this.chargeList = chargeList;
        }

        //    2.
        public class IncomeInfo extends BaseModel implements Serializable {

            private String date;//日期（2017-5-17）
            private String time;// 时间（5/17）
            private float charge;// 收入
            private double receivable;// 应收金额

            public double getReceivable() {
                return receivable;
            }

            public void setReceivable(double receivable) {
                this.receivable = receivable;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public float getCharge() {
                return charge;
            }

            public void setCharge(float charge) {
                this.charge = charge;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }


            @Override
            public String toString() {
                return "IncomeInfo{" +
                        "date='" + date + '\'' +
                        ", time='" + time + '\'' +
                        ", charge=" + charge +
                        ", receivable=" + receivable +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "IncomeList{" +
                    "dayave='" + dayave + '\'' +
                    ", maxcharge='" + maxcharge + '\'' +
                    ", chargeList=" + chargeList +
                    '}';
        }
    }
}