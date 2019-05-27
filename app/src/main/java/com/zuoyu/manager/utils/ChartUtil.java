package com.zuoyu.manager.utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * <pre>
 * Function：横屏图表设置样式
 *
 * Created by JoannChen on 2017/3/31 17:47
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class ChartUtil {


    public static void setStyle(View view, TextView descText, ImageView backImg, TextView dayText) {

//        图表View
        ViewSetting.setPadding(view, ViewSetting.getHeight(54), ViewSetting.getHeight(40), ViewSetting.getHeight(54), ViewSetting.getHeight(30));

//        图表功能描述
        ViewSetting.setTextSize(descText, ViewSetting.getHeight(30));
        ViewSetting.setMargin(descText, ViewSetting.getHeight(40), ViewSetting.getHeight(50), 0, 0, ViewSetting.RELATIVELAYOUT);

//        返回按钮
        ViewSetting.setViewSize(backImg, ViewSetting.getWidth(52), ViewSetting.getWidth(52));
        ViewSetting.setMarginTop(backImg, ViewSetting.getHeight(30), ViewSetting.RELATIVELAYOUT);

//        Y轴描述文字
        ViewSetting.setTextSize(dayText, ViewSetting.getHeight(20));


    }


    public static void setStyle(View view, TextView descText, ImageView backImg, TextView inText, TextView outText) {

//        图表View
        ViewSetting.setPadding(view, ViewSetting.getHeight(54), ViewSetting.getHeight(40), ViewSetting.getHeight(54), ViewSetting.getHeight(30));

//        图表功能描述
        ViewSetting.setTextSize(descText, ViewSetting.getHeight(30));
        ViewSetting.setMargin(descText, ViewSetting.getHeight(40), ViewSetting.getHeight(50), 0, 0, ViewSetting.RELATIVELAYOUT);

//        返回按钮
        ViewSetting.setViewSize(backImg, ViewSetting.getWidth(52), ViewSetting.getWidth(52));
        ViewSetting.setMarginTop(backImg, ViewSetting.getHeight(30), ViewSetting.RELATIVELAYOUT);

//        出入场描述文字
        ViewSetting.setTextSize(inText, ViewSetting.getHeight(20));
        ViewSetting.setTextSize(outText, ViewSetting.getHeight(20));
        ViewSetting.setMarginRight(outText, 100, ViewSetting.LINEARLAYOUT);


    }

    public static void setStyle(View view, TextView descText, TextView dayRateText, ImageView backImg,
                                TextView wxText, TextView alText, TextView xjText, TextView ecText,
                                TextView wechatText, TextView alipayText, TextView cashText, TextView etcText) {

//        图表View
        ViewSetting.setMarginLeft(view, ViewSetting.getHeight(150), ViewSetting.RELATIVELAYOUT);
        ViewSetting.setMarginRight(view, ViewSetting.getHeight(120), ViewSetting.RELATIVELAYOUT);

//        图表功能描述
        ViewSetting.setTextSize(descText, ViewSetting.getHeight(30));
        ViewSetting.setMargin(descText, ViewSetting.getHeight(40), ViewSetting.getHeight(50), 0, 0, ViewSetting.RELATIVELAYOUT);

        ViewSetting.setTextSize(dayRateText, ViewSetting.getHeight(20));

//        返回按钮
        ViewSetting.setViewSize(backImg, ViewSetting.getWidth(52), ViewSetting.getWidth(52));
        ViewSetting.setMarginTop(backImg, ViewSetting.getHeight(30), ViewSetting.RELATIVELAYOUT);

//        支付方式描述文字
        ViewSetting.setTextSize(wxText, ViewSetting.getHeight(20));
        ViewSetting.setTextSize(alText, ViewSetting.getHeight(20));
        ViewSetting.setTextSize(xjText, ViewSetting.getHeight(20));
        ViewSetting.setTextSize(ecText, ViewSetting.getHeight(20));

//        支付方式比率显示
        ViewSetting.setTextSize(wechatText, ViewSetting.getHeight(20));
        ViewSetting.setTextSize(alipayText, ViewSetting.getHeight(20));
        ViewSetting.setTextSize(cashText, ViewSetting.getHeight(20));
        ViewSetting.setTextSize(etcText, ViewSetting.getHeight(20));


    }


}
