package com.zuoyu.manager.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.zuoyu.manager.R;
import com.zuoyu.manager.application.Constant;
import com.zuoyu.manager.application.MyApplication;
import com.zuoyu.manager.application.UrlManage;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.entity.ReportEntity;
import com.zuoyu.manager.entity.ReportEntity.Report.ReportInfo;
import com.zuoyu.manager.utils.SharedUtil;
import com.zuoyu.manager.utils.ToolUtil;
import com.zuoyu.manager.utils.ViewSetting;
import com.zuoyu.manager.utils.http.HttpResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * <pre>
 * Function：日报表界面
 *
 * Created by JoannChen on 2017/3/20 10:55
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class ReportDayActivity extends BaseActivity {

    private String mStartDate, mEndDate;

    // 停车场名称
    // 总实收／总应收／总优惠
    private TextView nameText;
    private TextView shouldText, actualText, discountText;

    // 本地现金详情
    // 本地非现金详情
    // 月卡详情
    private List<ReportInfo> localCashList = new ArrayList<>();
    private List<ReportInfo> localNonCashList = new ArrayList<>();
    private List<ReportInfo> monthCardList = new ArrayList<>();

    // 无忧平台详情
    /*private List<ReportInfo> unisParkList = new ArrayList<>();*/


    // 本地现金
    // 本地非现金
    // 月卡收入
    // 第三方平台
    private TextView localCashText, localNonCashText, unisParkText, monthCardText, thirdChargeText;
    private LinearLayout localCashLLayout, localNonCashLLayout, monthCardLLayout;

    // 无忧平台
    /*private LinearLayout unisParkLLayout;*/


    @Override
    public int setLayout() {
        return R.layout.report_day_main;
    }

    @Override
    public void initBeforeLayout() {

    }


    @Override
    public void initTitle() {

        mStartDate = getIntent().getStringExtra(Constant.START_DATE);
        mEndDate = getIntent().getStringExtra(Constant.END_DATE);
        String yearMonth = getIntent().getStringExtra(Constant.YEAR_MONTH);


        int date;
        boolean isFromDayReport = getIntent().getBooleanExtra(Constant.IS_FROM_DAY_REPORT, false);
        if (isFromDayReport) {
            date = R.string.day_report;
        } else {
            date = R.string.month_report;
        }

        titleManage.setTitleText(ToolUtil.isEmpty(yearMonth) ? getString(date) : (yearMonth + getString(date)));

    }

    @Override
    public void initView() {

        nameText = (TextView) findViewById(R.id.tv_name);
        shouldText = (TextView) findViewById(R.id.tv_should);
        actualText = (TextView) findViewById(R.id.tv_actual);
        discountText = (TextView) findViewById(R.id.tv_discount);


        localCashText = (TextView) findViewById(R.id.tv_localCash);
        localCashLLayout = (LinearLayout) findViewById(R.id.ll_localCash);

        localNonCashText = (TextView) findViewById(R.id.tv_localNonCash);
        localNonCashLLayout = (LinearLayout) findViewById(R.id.ll_localNonCash);

        unisParkText = (TextView) findViewById(R.id.tv_unisPark);
        /*unisParkLLayout = (LinearLayout) findViewById(R.id.ll_unisPark);*/

        monthCardText = (TextView) findViewById(R.id.tv_monthCard);
        monthCardLLayout = (LinearLayout) findViewById(R.id.ll_monthCard);

        thirdChargeText = (TextView) findViewById(R.id.tv_third);

    }


    @Override
    public void onClickEvent(View v) {

    }


    @Override
    public void initAfterLayout(Bundle savedInstanceState) {
        MyApplication.addActivity(this);
        MobclickAgent.onEvent(context, "ReportDay_Page_Load");

        parseGetDayReport();

    }

    @Override
    public void close() {

    }


    /**
     * 动态加载收费详情列表
     */
    private void showChargeList() {

        // 本地现金
        for (int i = 0, size = localCashList.size(); i < size; i++) {
            RelativeLayout rl = (RelativeLayout) LayoutInflater.from(this)
                    .inflate(R.layout.report_day_item2, localCashLLayout, false);

            ((TextView) rl.findViewById(R.id.text_1)).setText(localCashList.get(i).getInfo());
            ((TextView) rl.findViewById(R.id.text_2)).setText(localCashList.get(i).getPrice());

            if (i == size - 1) {
                ViewSetting.setPaddingBottom((rl.findViewById(R.id.text_1)), 20);
//                ViewSetting.setPaddingBottom((rl.findViewById(R.id.text_2)), 20);
            }

            localCashLLayout.addView(rl);
        }

        // 本地非现金
        for (int i = 0, size = localNonCashList.size(); i < size; i++) {
            RelativeLayout rl = (RelativeLayout) LayoutInflater.from(this)
                    .inflate(R.layout.report_day_item2, localNonCashLLayout, false);

            ((TextView) rl.findViewById(R.id.text_1)).setText(localNonCashList.get(i).getInfo());
            ((TextView) rl.findViewById(R.id.text_2)).setText(localNonCashList.get(i).getPrice());

            if (i == size - 1) {
                ViewSetting.setPaddingBottom((rl.findViewById(R.id.text_1)), 20);
//                ViewSetting.setPaddingBottom((rl.findViewById(R.id.text_2)), 20);
            }

            localNonCashLLayout.addView(rl);
        }

        // 无忧平台
        /*for (int i = 0; i < unisParkList.size(); i++) {
            RelativeLayout rl = (RelativeLayout) LayoutInflater.from(this)
                    .inflate(R.layout.report_day_item2, unisParkLLayout, false);

            ((TextView) rl.findViewById(R.id.text_1)).setText(unisParkList.get(i).getInfo());
            ((TextView) rl.findViewById(R.id.text_2)).setText(unisParkList.get(i).getPrice());
            unisParkLLayout.addView(rl);
        }*/

        // 月卡收入
        for (int i = 0, size = monthCardList.size(); i < size; i++) {
            RelativeLayout rl = (RelativeLayout) LayoutInflater.from(this)
                    .inflate(R.layout.report_day_item2, monthCardLLayout, false);

            ((TextView) rl.findViewById(R.id.text_1)).setText(monthCardList.get(i).getInfo());
            ((TextView) rl.findViewById(R.id.text_2)).setText(monthCardList.get(i).getPrice());

            if (i == size - 1) {
                ViewSetting.setPaddingBottom((rl.findViewById(R.id.text_1)), 20);
//                ViewSetting.setPaddingBottom((rl.findViewById(R.id.text_2)), 20);
            }


            monthCardLLayout.addView(rl);
        }

    }


    /**
     * 【解析】获取日／月报表
     * 日报表：开始日期和结束日期一致
     * 月报表：eg. 2017-02-01 - 2017-02-28
     */
    private void parseGetDayReport() {

        Map<String, String> params = new TreeMap<>();
        params.put("uid", MyApplication.getUserInfo().getUid());
        params.put("parkid", SharedUtil.getString(SharedUtil.PARK_ID));
        params.put("startdate", mStartDate);// 日期（格式 yyyy-MM-dd）
        params.put("enddate", mEndDate);// 日期（格式 yyyy-MM-dd）
//        params.put("startdate", "2017-02-01");// 日期（格式 yyyy-MM-dd）
//        params.put("enddate", "2017-02-28");// 日期（格式 yyyy-MM-dd）

        httpUtil.post(params, UrlManage.DAY_REPORT_URL, new HttpResult<ReportEntity>() {

            @Override
            public void onLoadCatch(String json) {

            }

            @Override
            public void onSuccess(ReportEntity result) {

                nameText.setText(result.getData().getPark_name());

                shouldText.setText((getString(R.string.rmb) + result.getData().getReceivable()));
                actualText.setText((getString(R.string.rmb) + result.getData().getCharge()));
                discountText.setText((getString(R.string.rmb) + result.getData().getDiscount()));


                localCashText.setText(result.getData().getLocalcash());
                localNonCashText.setText(result.getData().getLocalnocash());
                unisParkText.setText(result.getData().getUnispark());
                monthCardText.setText(result.getData().getMonthcard());
                thirdChargeText.setText(result.getData().getThirdcharge());

                // 集合赋值
                localCashList = result.getData().getLocalcashdetail();
                localNonCashList = result.getData().getLocalnocashdetail();
                monthCardList = result.getData().getMonthcarddetail();
                /*unisParkList = result.getData().getUnisparkdetail();*/

                showChargeList();


            }

            @Override
            public void onFailed(int errCord, String errMsg) {

            }
        });


    }
}
