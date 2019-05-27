package com.zuoyu.manager.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.zuoyu.manager.R;
import com.zuoyu.manager.application.MyApplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * Function：常用工具类
 *
 * Created by JoannChen on 2017/3/9 15:48
 * QQ:411083907
 * E-mail:q8622268@163.com
 * Version Information：V 1.0
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class ToolUtil {

    /**
     * 密码显示与隐藏
     * @param checkBox 小眼睛
     * @param editText 输入框
     */
    public static void isShowPassword(final CheckBox checkBox, final EditText editText) {

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    //设置EditText文本为可见的
                    editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //设置EditText文本为隐藏的
                    editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

                //切换后将EditText光标置于末尾
                CharSequence charSequence = editText.getText();
                if (charSequence != null) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());

                }
            }
        });

    }


    /**
     * 判断当前是否有网络连接
     * <p>
     * </pre>
     */
    public static boolean isNetConnection() {
        ConnectivityManager connManager = (ConnectivityManager) MyApplication.getContext()
                .getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo info = connManager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    /**
     * 判断是否为null或空字符串
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        //三木运算符，默认 true : false 可以省略
        return (str == null || str.equals(""));
    }


    /**
     * 校验手机号码格式
     *
     * @param phoneNumber 手机号码
     * @return boolean
     */
    public static boolean isMobileNum(String phoneNumber) {

//        Pattern p = Pattern.compile("^((17[0-9])|(13[0-9])|(15[012356789])|(18[0-9])|(14[57]))\\d{8}$");
        Pattern p = Pattern.compile("^((1[358][0-9])|(14[57])|(17[0678]))\\d{8}$");
        Matcher m = p.matcher(phoneNumber);
        return m.matches();

    }

    /**
     * 调用系统电话
     *
     * @param tel 电话号码
     */
    public static void callPhone(Context context, String tel) {
        if (isEmpty(tel)) {
            ToastUtil.show("电话号码不正确");
            return;
        }
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + tel));
            context.startActivity(intent);
        } catch (SecurityException e) {
            ToastUtil.show("请开启拨打电话权限");
        }
    }

    /**
     * 隐藏手机号码中间4位
     *
     * @return String
     */
    public static String hidePhoneFour(String phone) {
        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1 **** $2");
    }


    /**
     * 获取app版本号
     *
     * @return 应用版本号
     */
    public static String getVersionName() {
        try {
            // 获取packageManager的实例
            PackageManager packageManager = MyApplication.getContext().getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(MyApplication.getContext().getPackageName(), 0);
            return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 隐藏软键盘
     *
     * @param context 上下文对象
     * @param view    接受软键盘输入的视图
     */
    public static void hideKeyBoard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }

    /**
     * 显示软键盘
     *
     * @param context 上下文对象
     * @param view    接受软键盘输入的视图
     */
    public static void showKeyBoard(Context context, View view) {

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);

    }


    /**
     * 判断按钮是否可点击
     *
     * @param view    组件view
     * @param isClick true可点击； false不可点击；
     */
    public static void setBtnClick(View view, boolean isClick) {

        if (isClick) {
            view.setBackgroundResource(R.drawable.draw_btn_checked);
            view.setEnabled(true);
        } else {
            view.setBackgroundResource(R.drawable.draw_btn_unchecked);
            view.setEnabled(false);
        }
    }


}
