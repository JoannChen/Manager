package com.zuoyu.manager.widget.hellochart;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * 柱形图封装类
 * Created by ZhangYe on 2017/3/22.
 */

public class PieChartUtil {

    private PieChartView pieChartView;


    private List<SliceValue> valueList;

    public PieChartUtil(PieChartView pieChartView) {
        this.pieChartView = pieChartView;
    }

    private void initPieChart() {

        pieChartView.setChartRotationEnabled(false);
        PieChartData pd = new PieChartData();//实例化PieChartData对象
        pd.setHasCenterCircle(true);//设置饼图中间是否有第二个圈
        pd.setCenterCircleColor(Color.parseColor("#ffffff"));//设置饼图中间圈的颜色
        pd.setCenterCircleScale(0.45f);//设置第二个圈的大小比例
        pd.setSlicesSpacing(0);//设置数据间的间隙
        pd.setValues(valueList);
        pieChartView.setPieChartData(pd);

    }

    public void setValue(float wx, float ali, float cash) {

        String red = "#e95e54";
        String blue = "#4eb3e4";
        String green = "#9bc66f";

        valueList = new ArrayList<>();
        valueList.add(new SliceValue(wx, Color.parseColor(green)));
        valueList.add(new SliceValue(ali, Color.parseColor(blue)));
        valueList.add(new SliceValue(cash, Color.parseColor(red)));

        initPieChart();
    }


    public void setValue(float wx, float ali, float cash, float etc) {

        // 现金
        // 支付宝
        // 微信
        // etc
        String red = "#e95e54";
        String blue = "#4eb3e4";
        String green = "#9bc66f";
        String orange = "#F3A826";

        if (wx == 0f) {
            wx = 0.01f;
        }

        if (ali == 0f) {
            ali = 0.01f;
        }

        if (cash == 0f) {
            cash = 0.01f;
        }

        if (etc == 0f) {
            etc = 0.01f;
        }

        valueList = new ArrayList<>();
        valueList.add(new SliceValue(wx, Color.parseColor(green)));
        valueList.add(new SliceValue(ali, Color.parseColor(blue)));
        valueList.add(new SliceValue(cash, Color.parseColor(red)));
        valueList.add(new SliceValue(etc, Color.parseColor(orange)));

        initPieChart();
    }

}
