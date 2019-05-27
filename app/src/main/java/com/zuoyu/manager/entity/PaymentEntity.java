package com.zuoyu.manager.entity;

import com.zuoyu.manager.base.BaseEntity;
import com.zuoyu.manager.base.BaseModel;

import java.util.List;


/**
 * <pre>
 * Function：财务分析——支付方式实体类
 *
 * Created by JoannChen on 2017/3/24 17:38
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class PaymentEntity extends BaseEntity {

    private PaymentList data;

    public PaymentList getData() {
        return data;
    }

    public void setData(PaymentList data) {
        this.data = data;
    }

    public class PaymentList extends BaseModel {

        // 总金额
        // 支付方式集合

        private float total;
        private List<Payment> paylist;

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


        public float getTotal() {
            return total;
        }

        public void setTotal(float total) {
            this.total = total;
        }

        public List<Payment> getPaylist() {
            return paylist;
        }

        public void setPaylist(List<Payment> paylist) {
            this.paylist = paylist;
        }

        public class Payment extends BaseModel {

            // 支付方式
            // 支付占比
            // 单支付方式合计
            private String payway;
            private float count;
            private float paycount;


            public String getPayway() {
                return payway;
            }

            public void setPayway(String payway) {
                this.payway = payway;
            }

            public float getCount() {
                return count;
            }

            public void setCount(float count) {
                this.count = count;
            }

            public float getPaycount() {
                return paycount;
            }

            public void setPaycount(float paycount) {
                this.paycount = paycount;
            }

            @Override
            public String toString() {
                return "Payment{" +
                        "payway='" + payway + '\'' +
                        ", count=" + count +
                        ", paycount=" + paycount +
                        '}';
            }
        }
    }


}
