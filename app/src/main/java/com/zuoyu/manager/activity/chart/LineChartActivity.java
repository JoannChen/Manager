package com.zuoyu.manager.activity.chart;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.application.Constant;
import com.zuoyu.manager.entity.CarInOutEntity;
import com.zuoyu.manager.entity.IncomeEntity;
import com.zuoyu.manager.entity.TurnoverEntity;
import com.zuoyu.manager.entity.UtilizationEntity;
import com.zuoyu.manager.utils.ChartMaxYUtil;
import com.zuoyu.manager.utils.ChartUtil;
import com.zuoyu.manager.widget.hellochart.LineChartUtil;
import com.zuoyu.manager.widget.hellochart.chart.LineChartView;


/**
 * <pre>
 * Function：点击放大后的折线图
 *
 * Created by JoannChen on 2/10/17 10:19
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class LineChartActivity extends Activity {


    public final int TAG_CAR = 1; // 出入场车辆
    public final int TAG_UTILIZATION = 2; // 车位利用率
    public final int TAG_TURNOVER = 3; // 车位周转率
    public final int TAG_INCOME = 4; // 车场收入

    private LineChartUtil lineChartUtil;
    private LineChartView lineChartView;

    private TextView descText, dayRateText;
    private ImageView backImg;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.line_chart_main);

        initView();

        switch (getIntent().getIntExtra(Constant.CHART_TAG, 1)) {
            case TAG_CAR:
                showCarInOutChart();
                break;
            case TAG_UTILIZATION:
                showUtilizationChart();
                break;
            case TAG_TURNOVER:
                showTurnoverChart();
                break;
            case TAG_INCOME:
                showIncomeChart();
                break;
        }

    }

    private void initView() {

        // 设置全屏
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        // 初始化折线图
        lineChartView = (LineChartView) findViewById(R.id.lineChartView);
        lineChartUtil = new LineChartUtil(lineChartView);


        // 功能描述
        descText = (TextView) findViewById(R.id.tv_desc);

        // 日平均利用率
        dayRateText = (TextView) findViewById(R.id.tv_day_rate);


        // 关闭按钮
        backImg = (ImageView) findViewById(R.id.iv_back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    /**
     * 出入场车辆统计
     */
    private void showCarInOutChart() {

        String green = "#CC8bd43f";
        String yellow = "#99f8e03f";

        lineChartUtil.setLineColor(yellow);
        lineChartUtil.setLineColor2(green);
        lineChartUtil.setBackgroundColor(yellow);
        lineChartUtil.setBackgroundColor2(green);


        lineChartUtil.setInteractive(true);  //支持滑动禁止缩放
        lineChartUtil.setHasLabels(true);

        findViewById(R.id.ll_inOutCar).setVisibility(View.VISIBLE);

        // 入场车辆 // 出场车辆
        TextView inText = (TextView) findViewById(R.id.text_4);
        TextView outText = (TextView) findViewById(R.id.text_5);

        ChartUtil.setStyle(lineChartView, descText, backImg, inText, outText);


        CarInOutEntity result = (CarInOutEntity) getIntent().getSerializableExtra(Constant.RESULT);
        lineChartUtil.setX2(result.getData().getExitlist(), result.getData().getEnterlist());
        lineChartUtil.setY(ChartMaxYUtil.setMaxY(result.getData().getPark_count()), Constant.CHART_BIG_X_COUNT);

        descText.setText(result.getData().getDesc());
        dayRateText.setVisibility(View.GONE);

    }

    /**
     * 车位利用率统计
     */
    private void showUtilizationChart() {

        lineChartUtil.setLineColor("#FCC104");
        lineChartUtil.setInteractive(true);  //支持滑动禁止缩放

        ChartUtil.setStyle(lineChartView, descText, backImg, dayRateText);

        UtilizationEntity result = (UtilizationEntity) getIntent().getSerializableExtra(Constant.RESULT);
        lineChartUtil.setX_utilization(result.getData().getOccupyList() != null ? result.getData().getOccupyList() : result.getData().getUnilizationList(), result.getData().getOccupyList() != null);
        lineChartUtil.setY(100, Constant.CHART_BIG_X_COUNT);

        descText.setText(result.getData().getDesc());
        dayRateText.setText((result.getData().getDayRate() + result.getData().getUse()));

    }

    /**
     * 车位周转率统计
     */
    private void showTurnoverChart() {

        lineChartUtil.setLineColor("#AAD1F7");
        lineChartUtil.setInteractive(true);  //支持滑动禁止缩放

        ChartUtil.setStyle(lineChartView, descText, backImg, dayRateText);


        TurnoverEntity result = (TurnoverEntity) getIntent().getSerializableExtra(Constant.RESULT);
        lineChartUtil.setX_turnover(result.getData().getTurnoverList());
        lineChartUtil.setY(ChartMaxYUtil.setMaxY2(result.getData().getMaxvelocity()), Constant.CHART_BIG_X_COUNT);

        descText.setText(result.getData().getDesc());
        dayRateText.setText((result.getData().getDayRate() + result.getData().getDayturnover()));

    }

    /**
     * 车场收入统计
     */
    private void showIncomeChart() {

        lineChartUtil.setLineColor("#FF0000");
        lineChartUtil.setBackgroundColor("#33ea5d55");
        lineChartUtil.setInteractive(true);  //支持滑动禁止缩放
        lineChartUtil.setIsDoublePoint(true);

        ChartUtil.setStyle(lineChartView, descText, backImg, dayRateText);

        IncomeEntity result = (IncomeEntity) getIntent().getSerializableExtra(Constant.RESULT);
        lineChartUtil.setX_income(result.getData().getChargeList());
        lineChartUtil.setY(ChartMaxYUtil.setMaxY(result.getData().getMaxcharge()), Constant.CHART_BIG_X_COUNT);


        // 日平均收入
        SpannableStringBuilder style = new SpannableStringBuilder(
                (getString(R.string.day_income_colon) + getString(R.string.rmb) + result.getData().getDayave()));
        style.setSpan(new ForegroundColorSpan(Color.parseColor("#ec4f39")), 6, style.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        dayRateText.setText(style);

        descText.setText(result.getData().getDesc());

    }


}

