package com.zuoyu.manager.widget.hellochart.chart;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;

import lecho.lib.hellocharts.listener.DummyLineChartOnValueSelectListener;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.ChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SelectedValue;
import lecho.lib.hellocharts.view.AbstractChartView;
/**
 * <pre>
 * Function：自定义折线图，修改部分源码
 *
 * Created by JoannChen on 2017/3/24 10:04
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class LineChartView extends AbstractChartView implements LineChartDataProvider {
    private static final String TAG = "MyLineChartView";
    protected LineChartData data;
    protected LineChartOnValueSelectListener onValueTouchListener;

    public LineChartView(Context context) {
        this(context, (AttributeSet)null, 0);
    }

    public LineChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.onValueTouchListener = new DummyLineChartOnValueSelectListener();
        this.setChartRenderer(new MyLineChartRenderer(context, this, this));
        this.setLineChartData(LineChartData.generateDummyData());
    }

    public LineChartData getLineChartData() {
        return this.data;
    }

    public void setLineChartData(LineChartData data) {
        if(null == data) {
            this.data = LineChartData.generateDummyData();
        } else {
            this.data = data;
        }
        invalidate();
        super.onChartDataChange();
    }

    public ChartData getChartData() {
        return this.data;
    }

    public void callTouchListener() {
        SelectedValue selectedValue = this.chartRenderer.getSelectedValue();
        if(selectedValue.isSet()) {
            PointValue point = (PointValue)((Line)this.data.getLines().get(selectedValue.getFirstIndex())).getValues().get(selectedValue.getSecondIndex());
            this.onValueTouchListener.onValueSelected(selectedValue.getFirstIndex(), selectedValue.getSecondIndex(), point);
        } else {
            this.onValueTouchListener.onValueDeselected();
        }

    }

    public LineChartOnValueSelectListener getOnValueTouchListener() {
        return this.onValueTouchListener;
    }

    public void setOnValueTouchListener(LineChartOnValueSelectListener touchListener) {
        if(null != touchListener) {
            this.onValueTouchListener = touchListener;
        }

    }


}
