package com.zuoyu.manager.entity;

import com.zuoyu.manager.base.BaseEntity;

/**
 * <pre>
 * Function：是否更新询问
 *
 * Created by JoannChen on 2017/5/9 11:33
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class UpdateEntity extends BaseEntity {

    public UpdateInfo data;

    public UpdateInfo getData() {
        return data;
    }

    public void setData(UpdateInfo data) {
        this.data = data;
    }

    public class UpdateInfo {

        private String appid;// 应用id
        private int is_online;//本地当前版本是否在线（0 下线 1 在线）
        private String n_version;//最新版本号
        private String url;//最新版本下载地址
        private String illustrate;//最新版本更新说明

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public int getIs_online() {
            return is_online;
        }

        public void setIs_online(int is_online) {
            this.is_online = is_online;
        }

        public String getN_version() {
            return n_version;
        }

        public void setN_version(String n_version) {
            this.n_version = n_version;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIllustrate() {
            return illustrate;
        }

        public void setIllustrate(String illustrate) {
            this.illustrate = illustrate;
        }

        @Override
        public String toString() {
            return "UpdateInfo{" +
                    "appid='" + appid + '\'' +
                    ", is_online=" + is_online +
                    ", n_version='" + n_version + '\'' +
                    ", url='" + url + '\'' +
                    ", illustrate='" + illustrate + '\'' +
                    '}';
        }
    }
}
