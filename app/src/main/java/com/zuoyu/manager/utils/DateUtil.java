package com.zuoyu.manager.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * <pre>
 * Function：日历工具类
 *
 * Created by Joann on 17/2/28 17:44
 * QQ:411083907
 * E-mail:q8622268@163.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class DateUtil {


    /**
     * 获取两个时间段差的天，时，分(yyyy-MM-dd HH:mm:ss)
     *
     * @return string
     */
    public static String getTwoDateTime(String date1, String date2) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        long days = 0, hours = 0, minutes = 0;

        try {
            Date d1 = df.parse(date1);
            Date d2 = df.parse(date2);
            //Date d2 = new Date(System.currentTimeMillis());//你也可以获取当前时间
            //这样得到的差值是微秒级别
            long diff = Math.abs(d1.getTime() - d2.getTime());
            days = diff / (1000 * 60 * 60 * 24);
            hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (days + "天" + hours + "小时" + minutes + "分");
    }

    /**
     * 获取指定年的月份天数
     *
     * @param year  年
     * @param month 月
     * @return 天数
     */
    public static int getMonthDayCount(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);//先指定年份
        calendar.set(Calendar.MONTH, month - 1);//再指定月份 Java月份从0开始算
        int daysCountOfMonth = calendar.getActualMaximum(Calendar.DATE);//获取指定年份中指定月份有几天
        return daysCountOfMonth;
    }


    public static int getMonthDayCount(String year, String month) {
        return getMonthDayCount(Integer.parseInt(year), Integer.parseInt(month));
    }


    /**
     * 返回指定年指定月的第一天是星期几
     *
     * @return 星期
     */
    public static int getMonthDayWeek(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);//先指定年份
        calendar.set(Calendar.MONTH, month - 1);//再指定月份 Java月份从0开始算
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek - 1;
    }

    /**
     * 返回当前是本月的几号
     */

    public static int getDay() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回当前月份
     */
    public static int getMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 返回当前年份
     */
    public static int getYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    /**
     * 返回当前日期 eg.2017-05-21
     */
//    public static String getDate() {
//        Calendar c = Calendar.getInstance();
//
//        String t1 = "-";
//        String t2 = "-";
//        int year = c.get(Calendar.YEAR);
//        int month = c.get(Calendar.MONTH) + 1;
//        int day = c.get(Calendar.DAY_OF_MONTH);
//
//        if (month < 10) {
//            t1 = "-0";
//        }
//        if (day < 10) {
//            t2 = "-0";
//        }
//
//        return year + t1 + month + t2 + day;
//    }

    /**
     * 返回当前日期和时间 eg.2017-05-21
     */
    public static String getDate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(date);
    }


    /**
     * 返回当前日期和时间 eg.2017-05-21 12：12：01
     * HH 24小时制
     * hh 12小时制
     */
    public static String getDateTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return dateFormat.format(date);
    }


    /**
     * 将long型转换为日期和时间格式 eg.2017-05-21 12：12：01
     *
     * @param millisecond 毫秒数
     * @return string
     */
    public static String getDateTime(Long millisecond) {

        Date date = new Date(millisecond);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        return simpleDateFormat.format(date);
    }


    /**
     * 获取未来 第 past 天的日期
     * @param past
     * @return
     */
    public static String getFetureDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        String result = format.format(today);
        Log.e(null, result);
        return result;
    }


    /**
     * 获取相应日期的毫秒数
     * @param str 字符串
     * @return 毫秒值
     */
    public static long getDataMillis(String str){

        //先把字符串转成Date类型
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        //此处会抛异常
        try {
            Date date = sdf.parse(str);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
