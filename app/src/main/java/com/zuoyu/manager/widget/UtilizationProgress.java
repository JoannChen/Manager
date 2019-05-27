package com.zuoyu.manager.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.utils.LogUtil;
import com.zuoyu.manager.utils.ViewUtil;


/**
 * <pre>
 * Function：车位利用率（仿余额宝收益进度条）
 *
 * Created by JoannChen on 2/10/17 10:19
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class UtilizationProgress extends RelativeLayout {

    private Context context;
    private TextView timeText, progressText;

    private View bgView;
    private View progressView;
    private float maxValue;

    public UtilizationProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public void setTime(String time) {
        timeText.setText(time);
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    public void setTextWidth(int textWidth) {
        timeText.getLayoutParams().width = ViewUtil.getWidth(textWidth);
    }

    /**
     * 集合最后一条红色显示
     *
     * @param position 当前下标
     * @param listSize 集合长度
     */
    public void setLastProgress(final int position, final int listSize) {
        progressView.setBackgroundColor(ContextCompat.getColor(context, position == listSize - 1 ? R.color.red_bg : R.color.font_pink));

    }

    private void initView() {

        // 时间
        timeText = new TextView(context);
        timeText.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
        timeText.setId(R.id.text_1);
        ViewUtil.setTextSize(timeText, 26);
        LayoutParams tmParams = new LayoutParams(ViewUtil.getWidth(80), LayoutParams.MATCH_PARENT);
        tmParams.leftMargin = ViewUtil.getWidth(40);

        addView(timeText, tmParams);

        // 默认背景
        bgView = new View(context);
        bgView.setBackgroundResource(R.color.gray_bg);
        LayoutParams bgParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        bgParams.rightMargin = ViewUtil.getWidth(40);
        bgParams.addRule(RelativeLayout.RIGHT_OF, R.id.text_1);

        addView(bgView, bgParams);

        // 进度条进度颜色
        progressView = new View(context);
        progressView.setId(R.id.text_2);
        progressView.setBackgroundColor(ContextCompat.getColor(context, R.color.font_pink));
        LayoutParams progressParams = new LayoutParams(0, LayoutParams.MATCH_PARENT);
        progressParams.addRule(RelativeLayout.RIGHT_OF, R.id.text_1);
        addView(progressView, progressParams);

        progressText = new TextView(context);
        ViewUtil.setTextSize(progressText, 26);

        addView(progressText);
    }


    public void showProgress() {

        bgView.postDelayed(new Runnable() {
            @Override
            public void run() {

                float progress = (float) getTag();
                LogUtil.i("进度：" + progress);
                progressText.setText((String.valueOf(progress) + "%"));

                LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

                if (progress == 0) {
                    LayoutParams progressParams = (LayoutParams) progressView.getLayoutParams();
                    progressParams.width = 0;
                    layoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.text_1);
                    layoutParams.leftMargin = ViewUtil.getWidth(10);
                    progressText.setTextColor(ContextCompat.getColor(context, R.color.font_pink));

                } else {

                    LayoutParams progressParams = (LayoutParams) progressView.getLayoutParams();

                    // 进度条宽度太小时，加80px
                    int width = (int) (bgView.getWidth() / maxValue * progress);
                    progressParams.width = width < ViewUtil.getWidth(80) ? ViewUtil.getWidth(80) : width;

                    progressText.setTextColor(Color.WHITE);
                    layoutParams.addRule(RelativeLayout.ALIGN_RIGHT, R.id.text_2);
                    layoutParams.rightMargin = ViewUtil.getWidth(15);

                }


                progressText.setLayoutParams(layoutParams);
            }
        }, 0);
    }


}
