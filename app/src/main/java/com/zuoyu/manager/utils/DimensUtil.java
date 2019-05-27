package com.zuoyu.manager.utils;


import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.zuoyu.manager.application.MyApplication;

/**
 * <pre>
 * Function：屏幕工具类,获取屏幕宽度和高度，以及单位转换
 *
 * Created by Joann on 17/1/22 17:13
 * QQ:411083907
 * E-mail:q8622268@163.com
 * Version Information：V 1.0
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class DimensUtil {

    /**
     * 获取屏幕宽度
     *
     * @return 屏幕宽度
     */
    public static int getScreenWidth() {
        DisplayMetrics metric = new DisplayMetrics();// 获取屏幕的参数
        WindowManager wm = (WindowManager) MyApplication.getContext().getSystemService(Context.WINDOW_SERVICE);//创建系统服务对象
        wm.getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;

    }

    /**
     * 获取屏幕高度
     *
     * @return 屏幕高度
     */
    public static int getScreenHeight() {
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager wm = (WindowManager) MyApplication.getContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metric);
        return metric.heightPixels;

    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param dpValue dp
     * @return px
     */
    public static int dip2px(float dpValue) {
        final float scale = MyApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param pxValue px
     * @return dp
     */
    public static int px2dip(float pxValue) {
        final float scale = MyApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
