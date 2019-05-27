package com.zuoyu.manager.application;


import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;
import com.zuoyu.manager.activity.LoginActivity;
import com.zuoyu.manager.entity.UserInfo;
import com.zuoyu.manager.utils.IntentUtil;
import com.zuoyu.manager.utils.LogUtil;
import com.zuoyu.manager.utils.SharedUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * <pre>
 * Function： 全局管理类
 *
 * Created by Joann on 17/1/22 17:15
 * QQ:411083907
 * E-mail:q8622268@163.com
 * Version Information：V 1.0
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class MyApplication extends Application {

    /**
     * 全局Context
     */
    private static Context applicationContext;

    /**
     * 用户基本信息实体类
     */
    private static UserInfo mUserInfo;

    /**
     * 存放所有的Activity
     */
    private static List<Activity> activityList;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationContext = getApplicationContext();

        /*
         * 关闭Log
         */
        LogUtil.closeLog();



        /*
         * 初始化HttpClient
         */
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("网络请求"))
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }


    /**
     * @return 全局上下文
     */
    public static Context getContext() {

        return applicationContext;
    }


    /**
     * 退出应用程序
     * 1.清除Share里的所有数据
     * 2.将用户基本信息置空
     * 3.跳转到登录页面
     * 4.移除所有的Activity
     *
     * @param activity        当前操作的Activity
     * @param isOpenLoginPage 是否跳转到登录界面
     */
    public static void exitApplication(Activity activity, boolean isOpenLoginPage) {

        SharedUtil.setEmptyAllData();
        MyApplication.setUserInfo(null);

        removeActivity();

        if (isOpenLoginPage) {
            IntentUtil.start(activity, LoginActivity.class, true);
        }

    }


    /**
     * 将Activity添加到集合
     *
     * @param activity 当前操作的Activity
     */
    public static void addActivity(Activity activity) {

        if (activityList == null) {
            activityList = new ArrayList<>();
        }

        activityList.add(activity);
    }


    /**
     * 将Activity从集合中移除
     */
    public static void removeActivity() {
        for (Activity activity : activityList) {
            if (null != activity) {
                activity.finish();
//                activityList.remove(activity);
            }
        }

        activityList = null;

    }


    /**
     * 获取用户基本信息 getter／setter方法
     *
     * @return UserInfo
     */
    public static UserInfo getUserInfo() {
        if (mUserInfo == null) {
            mUserInfo = new UserInfo();
            mUserInfo.setUid("");
        }
        return mUserInfo;
    }

    public static void setUserInfo(UserInfo userInfo) {
        mUserInfo = userInfo;
    }


}
