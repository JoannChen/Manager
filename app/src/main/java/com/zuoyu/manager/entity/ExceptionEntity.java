package com.zuoyu.manager.entity;

import com.zuoyu.manager.base.BaseEntity;
import com.zuoyu.manager.base.BaseModel;

import java.util.List;

/**
 * <pre>
 * Function：异常抬杆实体类
 *
 * Created by JoannChen on 2017/3/13 10:01
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class ExceptionEntity extends BaseEntity {

    private List<ExceptionInfo> data;

    public List<ExceptionInfo> getData() {
        return data;
    }

    public void setData(List<ExceptionInfo> data) {
        this.data = data;
    }

    public class ExceptionInfo extends BaseModel {

        // 操作时间
        // 操作人
        // 操作地址
        // 操作性质
        // 参考车牌号
        // 图片
        // 参考图片

        private String time;
        private String operation;
        private String address;
        private String type;
        private String plate;
        private String image;
        private String plateimage;

        public String getPlateimage() {
            return plateimage;
        }

        public void setPlateimage(String plateimage) {
            this.plateimage = plateimage;
        }

        public String getTime() {

            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getOperation() {
            return operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPlate() {
            return plate;
        }

        public void setPlate(String plate) {
            this.plate = plate;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        @Override
        public String toString() {
            return "ExceptionInfo{" +
                    "time='" + time + '\'' +
                    ", operation='" + operation + '\'' +
                    ", address='" + address + '\'' +
                    ", type='" + type + '\'' +
                    ", plate='" + plate + '\'' +
                    ", image='" + image + '\'' +
                    ", plateimage='" + plateimage + '\'' +
                    '}';
        }
    }


}
