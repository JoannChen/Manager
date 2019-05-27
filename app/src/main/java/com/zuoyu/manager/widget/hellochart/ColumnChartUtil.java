package com.zuoyu.manager.widget.hellochart;

import android.graphics.Color;

import com.zuoyu.manager.entity.UtilizationEntity;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * <pre>
 * Function：柱状图工具类
 *
 * Created by JoannChen on 2017/3/24 10:04
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class ColumnChartUtil {

    private ColumnChartView columnChartView;
    private List<AxisValue> mAxisXValues;
    private List<Column> columns;

    private boolean hasLabels = false;

    public ColumnChartUtil(ColumnChartView columnChartView) {
        this.columnChartView = columnChartView;
        mAxisXValues = new ArrayList<>();
        columns = new ArrayList<>();
    }

    public void setHasLabels(boolean hasLabels) {
        this.hasLabels = hasLabels;
    }

    /**
     * 设置y轴
     * @param maxY Y 轴最大值
     * @param count 一屏幕显示X轴的数据
     */
    public void setY(int maxY, int count) {

        final Viewport v = new Viewport(columnChartView.getMaximumViewport());
        v.top = maxY;
        v.bottom = 0;
        columnChartView.setMaximumViewport(v);

        v.left = 0;
        v.right = count;

        columnChartView.setCurrentViewport(v);
    }

    public void setX(List<UtilizationEntity.UtilizationList.UtilizationInfo> list){

        if(list != null){
            for (int i = 0; i < list.size(); i++){
                mAxisXValues.add(new AxisValue(i).setLabel(list.get(i).getTime()));

                List<SubcolumnValue> values = new ArrayList<>();
                values.add(new SubcolumnValue(list.get(i).getOccupy(), Color.parseColor("#e30f98e6")));
                columns.add(new Column(values).setHasLabels(hasLabels));
            }

            initColumnChart();
        }
    }

    public void initColumnChart() {
        ColumnChartData chartData = new ColumnChartData(columns);


        Axis axisX = new Axis(); //X轴
//        axisX.setTextColor(Color.BLACK);  //设置字体颜色
        axisX.setTextSize(10);//设置字体大小
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        axisX.setHasLines(false); //x 轴分割线
        chartData.setAxisXBottom(axisX); //x 轴在底部

        Axis axisY = new Axis();  //Y轴
        axisY.setTextSize(10);//设置字体大小
        axisY.setHasLines(true);
        chartData.setAxisYLeft(axisY);  //Y轴设置在左边


        columnChartView.setColumnChartData(chartData);

        columnChartView.setInteractive(true);
        columnChartView.setZoomEnabled(false);


        Viewport v = new Viewport(columnChartView.getMaximumViewport());

        v.left = 0;
        v.right = 11;
        columnChartView.setCurrentViewport(v);
    }


}
