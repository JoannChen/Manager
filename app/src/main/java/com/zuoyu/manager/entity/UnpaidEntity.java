package com.zuoyu.manager.entity;

import com.zuoyu.manager.base.BaseEntity;
import com.zuoyu.manager.base.BaseModel;

import java.util.List;

/**
 * <pre>
 * Function：未支付记录实体类
 *
 * Created by JoannChen on 2017/3/13 10:07
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class UnpaidEntity extends BaseEntity {


    private List<UnpaidInfo> data;

    public List<UnpaidInfo> getData() {
        return data;
    }

    public void setData(List<UnpaidInfo> data) {
        this.data = data;
    }

    public class UnpaidInfo extends BaseModel {

        // 车牌号
        // 票号
        // 有无进场记录 1有 2无
        // 入场时间
        // 出场时间
        // 入口
        // 出口
        // 订单金额
        // 优惠金额
        // 逃单金额
        // 操作人
        // 图片

        private String plate;
        private String tickid;
        private String is_record;
        private String enter_time;
        private String leave_time;
        private String enter_name;
        private String leave_name;
        private String money;
        private String discount;
        private String escape;
        private String operation;
        private String enter_image;
        private String exit_image;

        public String getPlate() {
            return plate;
        }

        public void setPlate(String plate) {
            this.plate = plate;
        }

        public String getTickid() {
            return tickid;
        }

        public void setTickid(String tickid) {
            this.tickid = tickid;
        }

        public String getIs_record() {
            return is_record;
        }

        public void setIs_record(String is_record) {
            this.is_record = is_record;
        }

        public String getEnter_time() {
            return enter_time;
        }

        public void setEnter_time(String enter_time) {
            this.enter_time = enter_time;
        }

        public String getLeave_time() {
            return leave_time;
        }

        public void setLeave_time(String leave_time) {
            this.leave_time = leave_time;
        }

        public String getEnter_name() {
            return enter_name;
        }

        public void setEnter_name(String enter_name) {
            this.enter_name = enter_name;
        }

        public String getLeave_name() {
            return leave_name;
        }

        public void setLeave_name(String leave_name) {
            this.leave_name = leave_name;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getEscape() {
            return escape;
        }

        public void setEscape(String escape) {
            this.escape = escape;
        }

        public String getOperation() {
            return operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }

        public String getEnter_image() {
            return enter_image;
        }

        public void setEnter_image(String enter_image) {
            this.enter_image = enter_image;
        }

        public String getExit_image() {
            return exit_image;
        }

        public void setExit_image(String exit_image) {
            this.exit_image = exit_image;
        }

        @Override
        public String toString() {
            return "UnpaidInfo{" +
                    "plate='" + plate + '\'' +
                    ", tickid='" + tickid + '\'' +
                    ", is_record='" + is_record + '\'' +
                    ", enter_time='" + enter_time + '\'' +
                    ", leave_time='" + leave_time + '\'' +
                    ", enter_name='" + enter_name + '\'' +
                    ", leave_name='" + leave_name + '\'' +
                    ", money='" + money + '\'' +
                    ", discount='" + discount + '\'' +
                    ", escape='" + escape + '\'' +
                    ", operation='" + operation + '\'' +
                    ", enter_image='" + enter_image + '\'' +
                    ", exit_image='" + exit_image + '\'' +
                    '}';
        }
    }


}
