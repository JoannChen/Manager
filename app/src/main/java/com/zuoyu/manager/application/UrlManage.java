package com.zuoyu.manager.application;

/**
 * <pre>
 * Function：网络请求Url管理类
 *
 * Created by JoannChen on 2017/3/7 16:01
 * QQ:411083907
 * E-mail:q8622268@163.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class UrlManage {


    /**
     * 正式环境
     */
    private static final String HOST = "http://cloudcenter.51park.cn/CloudCenter/";

    /**
     * alpha测试环境
     */
//    private static final String HOST = "http://alphacloudcenter.51park.cn/CloudCenter/";

    /**
     * beta测试环境
     */
//    private static final String HOST = "http://betacloudcenter.51park.cn/CloudCenter/";


    /**
     * 100。是否升级询问
     */
    public static final String IS_UPDATE_URL = HOST + "isupdate";

    /**
     * 33.优惠券发放记录
     */
    public static final String COUPONS_SEND_URL = HOST + "mtdiscountlog";

    /**
     * 32．余额使用记录
     */
    public static final String BALANCE_USE_RECORD_URL = HOST + "mtusetimelog";

    /**
     * 31.充值记录
     */
    public static final String RECHARGE_RECORD_URL = HOST + "mtaddlog";

    /**
     * 30.商家详情
     */
    public static final String BUSINESS_INFO_URL = HOST + "mtinfo";

    /**
     * 29.商家列表
     */
    public static final String BUSINESS_LIST_URL = HOST + "mtmanage";

    /**
     * 28.新结账统计
     */
    public static final String NEW_CHECKOUT_INFO_URL = HOST + "newcheckoutinfo";

    /**
     * 27.财务分析—新支付方式统计
     */
    public static final String NEW_FCANALYSIS_PW_URL = HOST + "newfcanalysispw";

    /**
     * 26.流量分析-日车位周转率(7日／30日)
     */
    public static final String FLOW_ANALYSIS_TURNOVER_URL = HOST + "flowanalysisturnover";

    /**
     * 25.流量分析—日出入车辆统计(7日／30日)
     */
    public static final String FLOW_ANALYSIS_CAR_DAYS_URL = HOST + "flowanalysiscardays";

    /**
     * 24.获取月报表总计金额
     */
    public static final String MONTHS_COUNT_URL = HOST + "monthscount";

    /**
     * 23.获取用户信息
     */
    public static final String GET_USER_INFO_URL = HOST + "getuserinfo";

    /**
     * 22.结账信息【V1.2.0废弃】
     */
//    public static final String CHECKOUT_INFO_URL = HOST + "checkoutinfo";

    /**
     * 21.财务分析—支付方式统计【V1.2.0废弃】
     */
//    public static final String FC_ANALYSIS_PW_URL = HOST + "fcanalysispw";

    /**
     * 20.财务分析—车场收费统计
     */
    public static final String FC_ANALYSIS_CHARGE_URL = HOST + "fcanalysischarge";

    /**
     * 19.流量分析—日车位利用率(7日／30日)
     */
    public static final String FLOW_ANALYSIS_USE_URL = HOST + "flowanalysisuse";

    /**
     * 18.流量分析—近24小时车位利用率
     * 18.流量分析—24小时车位占用率【V1.2.0废弃】
     */
    public static final String FLOW_ANALYSIS_PLACE_URL = HOST + "flowanalysisplace";

    /**
     * 17.流量分析—24小时出入车辆统计
     * 17.流量分析—出入车辆统计【V1.2.0废弃】
     */
    public static final String FLOW_ANALYSIS_CAR_URL = HOST + "flowanalysiscar";

    /**
     * 16.日/月报表
     */
    public static final String DAY_REPORT_URL = HOST + "dayreport";

    /**
     * 15.未支付记录
     */
    public static final String UNPAID_URL = HOST + "unpaid";

    /**
     * 14.异常抬杆
     */
    public static final String EXCEPTION_URL = HOST + "exception";

    /**
     * 13.停车场详细信息
     */
    public static final String PARK_INFO_URL = HOST + "parkinfo";

    /**
     * 12.退出登录
     */
    public static final String EXIT_URL = HOST + "exit";

    /**
     * 11.月卡管理
     */
    public static final String MONTH_LIST_URL = HOST + "monthlist";

    /**
     * 10.订单记录
     */
    public static final String ORDER_LIST_URL = HOST + "orderlist";

    /**
     * 09.子订单详情+列表
     */
    public static final String PARK_RECORD_URL = HOST + "parkrecord";

    /**
     * 08.在场车辆/订单详情
     */
    public static final String DETAILS_URL = HOST + "details";

    /**
     * 07.在场车辆
     */
    public static final String IN_PARK_LIST_URL = HOST + "inparklist";

    /**
     * 06.意见反馈
     */
    public static final String FEEDBACK_URL = HOST + "feedback";

    /**
     * 05.首页
     */
    public static final String PARK_INDEX_URL = HOST + "parkindex";

    /**
     * 04.停车场列表
     */
    public static final String PARK_LIST_URL = HOST + "parklist";

    /**
     * 03.修改密码
     */
    public static final String MODIFY_PW_URL = HOST + "midfypw";

    /**
     * 02.获取验证
     */
    public static final String VERIFY_CODE_URL = HOST + "verifycode";

    /**
     * 01.登录
     */
    public static final String LOGIN_URL = HOST + "login";



}
