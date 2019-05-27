package com.zuoyu.manager.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.zuoyu.manager.application.MyApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * <pre>
 * Function：SharedPreferences保存数据工具类
 *
 * Created by JoannChen on 2017/3/10 15:48
 * QQ:411083907
 * E-mail:q8622268@163.com
 * Version Information：V 1.0
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class SharedUtil {

    /**
     * 文件名
     */
    private static final String FILE_NAME = "51Manager";


    public static final String IS_LOGIN = "isLogin";// Boolean 登录状态？登录：未登录；
    public static final String TOKEN = "token";   // String token
    public static final String PARK_ID = "parkId";    // String 用户选择的停车场id
    public static final String USER_ID = "userId";  // String 用户id
    public static final String MERCHANT_ID = "merchantId";  // String 商户id
    public static final String MERCHANT_NAME = "merchantName";  // String 商户id
    public static final String PARK_NAME = "parkName";   // String 用户选择的停车场名称（结账统计接口，后台未返回停车场名称）


    // string 筛选
    public static final String FILTER_SEARCH = "filter_search";
    public static final String FILTER_DATE = "filter_date";
    public static final String FILTER_PAY_TYPE = "filter_pay_type";
    public static final String FILTER_CAR_TYPE = "filter_car_type";


    // Boolean 不提示更新吗？
    // String 点击忽略按钮时的时间，用来计算下次提示更新的时间
    public static final String IS_UPDATE = "noUpdate";
    public static final String UPDATE_TIME = "updateTime";


    /**
     * 将所有本地数据置空
     */
    public static void setEmptyAllData() {
        setString(TOKEN, "");
        setString(PARK_ID, "");
        setString(USER_ID, "");
        setString(MERCHANT_ID, "");
        setString(MERCHANT_NAME, "");
        setString(PARK_NAME, "");
        setString(FILTER_SEARCH, "");
        setString(FILTER_DATE, "");
        setString(FILTER_PAY_TYPE, "");
        setString(FILTER_CAR_TYPE, "");
        setString(UPDATE_TIME, "");

        setBoolean(IS_LOGIN, false);
        setBoolean(IS_UPDATE, false);
    }

    /**
     * 将筛选条件置空
     */
    public static void setEmptyFilter() {
        setString(FILTER_DATE, "");
        setString(FILTER_PAY_TYPE, "");
        setString(FILTER_CAR_TYPE, "");
    }


    /**
     * 获取SharedPreferences对象
     *
     * @return SharedPreferences
     */
    private static SharedPreferences getSharedPreferences() {

        return MyApplication.getContext()
                .getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }


    /**
     * 保存String信息
     *
     * @param key     键名
     * @param content 默认值
     */
    public static void setString(String key, String content) {
        getSharedPreferences().edit().putString(key, content).apply();
    }

    public static String getString(String key) {
        return getSharedPreferences().getString(key, "");
    }


    public static void setInt(String key, int content) {
        getSharedPreferences().edit().putInt(key, content).apply();
    }

    public static int getInt(String key) {
        return getSharedPreferences().getInt(key, -1);
    }

    /**
     * 保存Boolean类型的信息
     *
     * @param key  键名
     * @param flag 默认值
     */
    public static void setBoolean(String key, boolean flag) {
        getSharedPreferences().edit().putBoolean(key, flag).apply();
    }

    public static boolean getBoolean(String key) {
        return getSharedPreferences().getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean flag) {
        return getSharedPreferences().getBoolean(key, flag);
    }


    /**
     * 使用SharedPreference保存对象
     *
     * @param key 储存对象的key
     * @param obj 储存的对象
     */
    public static void setObject(String key, Object obj) {
        getSharedPreferences().edit().putString(key, Object2String(obj)).apply();
    }

    /**
     * 获取SharedPreference保存的对象
     *
     * @param key 储存对象的key
     * @return object 返回根据key得到的对象
     */
    public static Object getObject(String key) {
        String string = getSharedPreferences().getString(key, null);
        return string != null ? String2Object(string) : null;
    }





    /* ========================================================================== */

    /**
     * writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
     * 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
     *
     * @param object 待加密的转换为String的对象
     * @return String   加密后的String
     */
    private static String Object2String(Object object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            String string = new String(Base64.encode(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
            objectOutputStream.close();
            return string;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用Base64解密String，返回Object对象
     *
     * @param objectString 待解密的String
     * @return object      解密后的object
     */
    private static Object String2Object(String objectString) {
        byte[] mobileBytes = Base64.decode(objectString.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mobileBytes);
        ObjectInputStream objectInputStream;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /* ========================================================================== */


}

