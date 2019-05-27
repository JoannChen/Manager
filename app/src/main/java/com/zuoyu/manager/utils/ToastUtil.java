package com.zuoyu.manager.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zuoyu.manager.R;
import com.zuoyu.manager.application.MyApplication;

/**
 * <pre>
 * Function：Toast工具类
 *
 * Created by Joann on 17/2/6 11:28
 * QQ:411083907
 * E-mail:q8622268@163.com
 * Version Information：V 1.0
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class ToastUtil {

    private static Toast toast;

    private static Toast toastView;


    public ToastUtil() {

    }


    /**
     * 普通的吐司
     *
     * @param str 展示的内容
     */
    public static void show(String str) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getContext(), "", Toast.LENGTH_SHORT);
        }

        toast.setText(str);
        toast.show();
    }



    /**
     * 带图标的吐司
     *
     * @param layout 布局
     * @param icon   图标
     * @param string 显示的文字
     */
    public static void show(int layout, int icon, int string) {

        Context context = MyApplication.getContext();

        View view = LayoutInflater.from(context).inflate(layout, null);
        ImageView iconImg = (ImageView) view.findViewById(R.id.iv_icon);
        TextView contentText = (TextView) view.findViewById(R.id.tv_text);

        iconImg.setImageResource(icon);
        contentText.setText(context.getString(string));

        if (toastView == null) {
            toastView = new Toast(context);
        }

        toastView.setView(view);
        toastView.setGravity(Gravity.CENTER, 0, 0);
        toastView.setDuration(Toast.LENGTH_LONG);
        toastView.show();
    }


    /**
     * 网络异常
     */
    public static void showNetWorkError() {
        show(R.layout.layout_loading, R.mipmap.icon_sys_error, R.string.network_anomaly);
    }

}
