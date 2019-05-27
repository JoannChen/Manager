package com.zuoyu.manager.application;

/**
 * <pre>
 * Function：常量类
 *
 * Created by Joann on 2017/2/28 14:23
 * QQ:411083907
 * E-mail:q8622268@163.com
 * Version Information：V 1.0
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class Constant {


    /**
     * App配置参数
     */
    public static final String PROJECT_ID = "saas";
    public static final String PROJECT_KEY = "8257f71fc72d9e63911a052871fc4bed";


    /**
     * App更新提供
     */
    public static final String APP_ID = "saas_AN";

    /**
     * 首页功能区反射类所在的包名
     */
    public static final String PACKAGE_NAME = "com.zuoyu.manager.activity.action.";

    /**
     * 联系电话
     */
    public static final String Company_Tel = "400-709-5151";

    /**
     * 折线图X轴一屏展示的个数
     */
    public static final int CHART_X_COUNT = 14;
    public static final int CHART_BIG_X_COUNT = 13;

    /**
     * 读取手机状态权限
     */
    public static final int PERMISSION_READ_PHONE_STATE = 1;

    //============================= Intent跳转传参数 ======================================


    /**
     * 首页点击跳转到图表标识
     */
    public static final String CHART_TAG = "chartTag";

    /**
     * 判断是从《登录》还是《首页》进入选择停车场页面
     */
    public static final String IS_FROM_LOGIN = "isFormLoginPage";


    /**
     * 判断是从《日报表》还是《月报表》进入
     */
    public static final String IS_FROM_DAY_REPORT = "isFromDayReport";

    /**
     * 订单id／父订单id
     */
    public static final String ORDER_ID = "orderId";

    /**
     * 手机号
     */
    public static final String PHONE = "phone";


    /**
     * 将解析结果传参给图表放大页
     */
    public static final String RESULT = "result";

    /**
     * 日/月报表日期
     */
    public static final String START_DATE = "startDate";
    public static final String END_DATE = "endDate";
    public static final String YEAR_MONTH = "yearMonth";


}
