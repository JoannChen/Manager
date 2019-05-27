package com.zuoyu.manager.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.activity.chart.LineChartActivity;
import com.zuoyu.manager.application.Constant;
import com.zuoyu.manager.entity.CarInOutEntity;
import com.zuoyu.manager.entity.IncomeEntity;
import com.zuoyu.manager.entity.TurnoverEntity;
import com.zuoyu.manager.entity.UtilizationEntity;
import com.zuoyu.manager.utils.ChartMaxYUtil;
import com.zuoyu.manager.utils.ViewUtil;
import com.zuoyu.manager.widget.hellochart.LineChartUtil;
import com.zuoyu.manager.widget.hellochart.chart.LineChartView;

/**
 * <pre>
 * Function： 【公共】图表折线图子页面
 *
 * Created by JoannChen on 2017/6/1 17:24
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class LineChartFragment extends Fragment {

    private long mLastClickTime = 0;   // 上次点击的时间

    public final int TAG_CAR = 1; // 出入场车辆
    public final int TAG_UTILIZATION = 2; // 车位利用率
    public final int TAG_TURNOVER = 3; // 车位周转率
    public final int TAG_INCOME = 4; // 车场收入

    private int TAG;// 图表功能区分的标识

    private LineChartView lineChartView;
    private LineChartUtil lineChartUtil;


    //区分7日／30日
    private int day;


    // 实体类
    private CarInOutEntity carEntity;
    private UtilizationEntity utilizationEntity;
    private TurnoverEntity turnoverEntity;
    private IncomeEntity incomeEntity;

    // 近24小时统计，日收入：¥100
    private TextView descText, dayRateText, countText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.linechart_fragment, container, false);

        // 图表描述
        descText = (TextView) view.findViewById(R.id.tv_desc);

        // 图表数据统计展示：日平均收入／利用率／周转率
        dayRateText = (TextView) view.findViewById(R.id.tv_day_rate);
        countText = (TextView) view.findViewById(R.id.tv_count);


        // 出入场车辆标识是否展示
        if (TAG == TAG_CAR) {
            view.findViewById(R.id.ll_inOutCar).setVisibility(View.VISIBLE);
            dayRateText.setVisibility(View.GONE);
        } else {
            view.findViewById(R.id.ll_inOutCar).setVisibility(View.GONE);
            dayRateText.setVisibility(View.VISIBLE);
        }


        lineChartView = (LineChartView) view.findViewById(R.id.lineChartView);
        lineChartView.setOnTouchListener(new OnChartTouchListener());
        lineChartUtil = new LineChartUtil(lineChartView);


        // 初始化折线图
        lineChartUtil = new LineChartUtil(lineChartView);
        lineChartUtil.setInteractive(true);

        switch (TAG) {
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


        return view;
    }

    /**
     * 重写图表的OnTouchListener事件，区分点击和滑动事件
     */
    private class OnChartTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mLastClickTime = System.currentTimeMillis();
                    break;
                case MotionEvent.ACTION_UP:
                    if (System.currentTimeMillis() - mLastClickTime < 120) {
                        Intent intent = new Intent(getActivity(), LineChartActivity.class);
                        switch (TAG) {
                            case TAG_CAR:
                                intent.putExtra(Constant.CHART_TAG, 1);
                                intent.putExtra(Constant.RESULT, carEntity);
                                break;
                            case TAG_UTILIZATION:
                                intent.putExtra(Constant.CHART_TAG, 2);
                                intent.putExtra(Constant.RESULT, utilizationEntity);
                                break;
                            case TAG_TURNOVER:
                                intent.putExtra(Constant.CHART_TAG, 3);
                                intent.putExtra(Constant.RESULT, turnoverEntity);
                                break;
                            case TAG_INCOME:
                                intent.putExtra(Constant.CHART_TAG, 4);
                                intent.putExtra(Constant.RESULT, incomeEntity);
                                break;
                        }

                        getContext().startActivity(intent);
                    }
                    break;
            }

            return false;
        }

    }


    /**
     * 图表功能区分的标识
     *
     * @param TAG 1:出入场车辆
     *            2:车位利用率
     *            3:车位周转率
     *            4:车场收入
     */
    public void setTAG(int TAG) {
        this.TAG = TAG;
    }


    /**
     * 实体类赋值
     */
    public void setCarEntity(CarInOutEntity carEntity) {
        this.carEntity = carEntity;
    }

    public void setUtilizationEntity(UtilizationEntity utilizationEntity, int day) {
        this.utilizationEntity = utilizationEntity;
        this.day = day;
    }

    public void setTurnoverEntity(TurnoverEntity turnoverEntity) {
        this.turnoverEntity = turnoverEntity;
    }

    public void setIncomeEntity(IncomeEntity incomeEntity) {
        this.incomeEntity = incomeEntity;
    }


    /**
     * 出入场车辆统计
     */
    private void showCarInOutChart() {

        // 出场车辆【黄色】(#99f8e03f)
        // 入场车辆【绿色】(#CC8bd43f)
        // 先画出场车辆，再画入场车辆

        String green = "#CC8bd43f";
        String yellow = "#99f8e03f";

        lineChartUtil.setLineColor(yellow);
        lineChartUtil.setLineColor2(green);
        lineChartUtil.setBackgroundColor(yellow);
        lineChartUtil.setBackgroundColor2(green);

        lineChartUtil.setX2(carEntity.getData().getExitlist(), carEntity.getData().getEnterlist());
        lineChartUtil.setY(ChartMaxYUtil.setMaxY(carEntity.getData().getPark_count()), Constant.CHART_X_COUNT);

        descText.setText(carEntity.getData().getDesc());
        dayRateText.setText(carEntity.getData().getDayRate());

    }

    /**
     * 车位利用率统计
     */
    private void showUtilizationChart() {

        lineChartUtil.setLineColor("#FCC104");

        lineChartUtil.setX_utilization(day == 0 ? utilizationEntity.getData().getOccupyList() : utilizationEntity.getData().getUnilizationList(), day == 0);
        lineChartUtil.setY(100, Constant.CHART_X_COUNT);

        ViewUtil.setPaddingTop(lineChartView, 0);

        descText.setText(utilizationEntity.getData().getDesc());
        dayRateText.setText(utilizationEntity.getData().getDayRate());
        countText.setText(utilizationEntity.getData().getUse());

    }

    /**
     * 车位周转率统计
     */
    private void showTurnoverChart() {

        lineChartUtil.setLineColor("#AAD1F7");

        lineChartUtil.setX_turnover(turnoverEntity.getData().getTurnoverList());
        lineChartUtil.setY(ChartMaxYUtil.setMaxY2(turnoverEntity.getData().getMaxvelocity()), Constant.CHART_X_COUNT);

        descText.setText(turnoverEntity.getData().getDesc());
        dayRateText.setText(turnoverEntity.getData().getDayRate());
        countText.setText(turnoverEntity.getData().getDayturnover());

    }

    /**
     * 车场收入统计
     */
    private void showIncomeChart() {

        lineChartUtil.setLineColor("#FF0000");
        lineChartUtil.setBackgroundColor("#33ea5d55");
        lineChartUtil.setIsDoublePoint(true);

        lineChartUtil.setX_income(incomeEntity.getData().getChargeList());
        lineChartUtil.setY(ChartMaxYUtil.setMaxY(incomeEntity.getData().getMaxcharge()), Constant.CHART_X_COUNT);

        descText.setText(incomeEntity.getData().getDesc());
        dayRateText.setText(incomeEntity.getData().getDayRate());
        countText.setText((getString(R.string.rmb) + incomeEntity.getData().getDayave()));

    }

}
