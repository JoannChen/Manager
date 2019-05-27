package com.zuoyu.manager.utils;

/**
 * <pre>
 * Function：图表区Y轴最大值计算
 *
 * Created by JoannChen on 2017/6/6 11:18
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class ChartMaxYUtil {

    /**
     * 出入场车辆统计／临停收入 Y轴
     *
     * @param max 最大值
     */
    public static float setMaxY(float max) {

        float maxValue = 0;

        if (max >= 0 && max < 100) {
            maxValue = ((int) (max / 10) + 1) * 10;
        }

        if (max >= 100 && max < 1000) {
            maxValue = ((int) (max / 100) + 1) * 100;
        }

        if (max >= 1000 && max < 10000) {
            maxValue = ((int) (max / 1000) + 1) * 1000;
        }

        if (max >= 10000 && max < 100000) {
            maxValue = ((int) (max / 10000) + 1) * 10000;
        }

        LogUtil.e("setMaxY:" + maxValue);

        return maxValue;
    }

    /**
     * 车位周转率 Y轴
     *
     * @param max 最大值
     */
    public static float setMaxY2(float max) {

        float maxValue = 0;

        if (max >= 0 && max < 1) {
            maxValue = 1;
        }

        if (max >= 1 && max < 10) {
            maxValue = 10;
        }

        if (max >= 10 && max < 100) {
            maxValue = 100;
        }

        if (max >= 100 && max < 1000) {
            maxValue = 1000;
        }

        LogUtil.e("setMaxY2:" + maxValue);

        return maxValue;
    }


}
