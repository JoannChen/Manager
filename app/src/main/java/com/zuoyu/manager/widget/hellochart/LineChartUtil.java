package com.zuoyu.manager.widget.hellochart;

import android.graphics.Color;

import com.zuoyu.manager.entity.CarInOutEntity;
import com.zuoyu.manager.entity.IncomeEntity;
import com.zuoyu.manager.entity.TurnoverEntity;
import com.zuoyu.manager.entity.UtilizationEntity;
import com.zuoyu.manager.utils.LogUtil;
import com.zuoyu.manager.widget.hellochart.chart.Line;
import com.zuoyu.manager.widget.hellochart.chart.LineChartData;
import com.zuoyu.manager.widget.hellochart.chart.LineChartView;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;

/**
 * <pre>
 * Function：折线图工具类
 *
 * Created by JoannChen on 2017/3/24 10:04
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class LineChartUtil {

    private LineChartView lineChartView;

    private List<PointValue> mPointValues = new ArrayList<>();
    private List<PointValue> mPointValues2 = new ArrayList<>();
    private List<AxisValue> mAxisXValues = new ArrayList<>();
    private String linColor;
    private String linColor2;
    private String bgColor;
    private String bgColor2;
    private boolean hasLabels = false;
    private boolean isDoublePoint;

    public LineChartUtil(LineChartView myLineChartView) {
        this.lineChartView = myLineChartView;
    }

    public void setLineColor(String linColor) {
        this.linColor = linColor;
    }

    public void setLineColor2(String linColor2) {
        this.linColor2 = linColor2;
    }

    public void setBackgroundColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public void setIsDoublePoint(boolean isDoublePoint) {
        this.isDoublePoint = isDoublePoint;
    }

    public void setBackgroundColor2(String bgColor2) {
        this.bgColor2 = bgColor2;
    }

    public void setHasLabels(boolean hasLabels) {
        this.hasLabels = hasLabels;
    }

    /**
     * 设置y轴
     *
     * @param maxY  Y 轴最大值
     * @param count 一屏幕显示X轴的数据
     */
    public void setY(float maxY, int count) {

        final Viewport v = new Viewport(lineChartView.getMaximumViewport());
        v.top = maxY;
        v.bottom = 0;
        lineChartView.setMaximumViewport(v);

        v.left = 0;
        v.right = count;

        lineChartView.setCurrentViewport(v);
    }


    /**
     * 利用率统计
     *
     * @param list     坐标点
     * @param isOccupy true 显示占有率／false 显示利用率
     */
    public void setX_utilization(List<UtilizationEntity.UtilizationList.UtilizationInfo> list, boolean isOccupy) {
        clear();
        for (int i = 0; i < list.size(); i++) {

            // 填充坐标点数据
            mPointValues.add(new PointValue(i, isOccupy ? list.get(i).getOccupy() : list.get(i).getUnilization()));

            // 填充X轴数据
            mAxisXValues.add(new AxisValue(i).setLabel(list.get(i).getTime()));

        }

        initLineChart(false);
    }


    /**
     * 周转率统计
     */
    public void setX_turnover(List<TurnoverEntity.TurnoverList.TurnoverInfo> list) {
        clear();

        for (int i = 0; i < list.size(); i++) {

            // 填充坐标点数据
            mPointValues.add(new PointValue(i, list.get(i).getTurnover()));

            // 填充X轴数据
            mAxisXValues.add(new AxisValue(i).setLabel(list.get(i).getTime()));

        }

        initLineChart(false);
    }


    /**
     * 收费统计
     *
     * @param list 坐标点
     */
    public void setX_income(List<IncomeEntity.IncomeList.IncomeInfo> list) {
        clear();
        for (int i = 0; i < list.size(); i++) {

            // 填充坐标点数据
            mPointValues.add(new PointValue(i, list.get(i).getCharge()));

            // 填充X轴数据
            mAxisXValues.add(new AxisValue(i).setLabel(list.get(i).getTime()));
            LogUtil.i(list.get(i).getTime());
        }

        initLineChart(false);
    }


    /**
     * 出入场统计
     *
     * @param outList 出场坐标点
     * @param inList  入场坐标点
     */
    public void setX2(List<CarInOutEntity.FlowCar.FlowCarInfo> outList, List<CarInOutEntity.FlowCar.FlowCarInfo> inList) {
        clear();
        for (int i = 0; i < outList.size(); i++) {

            // 填充坐标点数据
            mPointValues.add(new PointValue(i, outList.get(i).getCount()));

            mPointValues2.add(new PointValue(i, inList.get(i).getCount()));


            // 填充X轴数据
            if (outList.size() > inList.size()) {
                mAxisXValues.add(new AxisValue(i).setLabel(outList.get(i).getTime()));
            } else {
                mAxisXValues.add(new AxisValue(i).setLabel(inList.get(i).getTime()));
            }


        }
        initLineChart(true);

    }


    /**
     * 初始化折线图
     *
     * @param isTwoLine 展示两条线？true:false
     */
    private void initLineChart(boolean isTwoLine) {
        List<Line> lines = new ArrayList<>();
        Line line = new Line(mPointValues);

        line.setColor(Color.parseColor(linColor));
        line.setDoublePoint(isDoublePoint);
        line.setStrokeWidth(1);
        line.setPointRadius(3);
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）

        if (bgColor != null) {
            line.setBackgroundColor(Color.parseColor(bgColor));
            line.setFilled(true);//是否填充曲线的面积
        }
        line.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line.setHasLabels(hasLabels);//曲线的数据坐标是否加上备注
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        lines.add(line);


        if (isTwoLine) {

            line.setHasPoints(false);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）

            Line line2 = new Line(mPointValues2);

            line2.setColor(Color.parseColor(linColor2));
            line2.setStrokeWidth(1);

            line2.setPointRadius(3);
            line2.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
            line2.setBackgroundColor(Color.parseColor(bgColor2));
            line2.setCubic(false);//曲线是否平滑，即是曲线还是折线
            line2.setFilled(true);//是否填充曲线的面积
            line2.setHasLabels(hasLabels);//曲线的数据坐标是否加上备注
            line2.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
            line2.setHasPoints(false);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
            lines.add(line2);
        }

        LineChartData data = new LineChartData();
        data.setLines(lines);
        data.setValueLabelBackgroundEnabled(false);//设置是否有数据背景
        data.setValueLabelsTextColor(Color.RED);//设置数据文字颜色

        //坐标轴
        Axis axisX = new Axis(); //X轴
//        axisX.setTextColor(Color.BLACK);  //设置字体颜色
        axisX.setTextSize(10);//设置字体大小
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        axisX.setHasLines(false); //x 轴分割线
        data.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        axisY.setTextSize(10);//设置字体大小
        axisY.setHasLines(true);

        data.setAxisYLeft(axisY);  //Y轴设置在左边
//        data.setAxisYRight(axisY);  //y轴设置在右边

        lineChartView.setLineChartData(data);
        lineChartView.setViewportCalculationEnabled(false);


    }


    /**
     * 设置行为属性
     * 支持滑动
     * 不支持缩放
     *
     * @param flag boolean
     */
    public void setInteractive(boolean flag) {
        lineChartView.setInteractive(flag);
        lineChartView.setZoomEnabled(false);
    }

    private void clear() {
        mPointValues.clear();

        mPointValues2.clear();

        mAxisXValues.clear();
    }
}
