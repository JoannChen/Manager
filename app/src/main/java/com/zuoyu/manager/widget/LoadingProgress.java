package com.zuoyu.manager.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.zuoyu.manager.R;


/**
 * <pre>
 * Function：自定义加载进度框
 *
 * Created by JoannChen on 2017/3/10 15:48
 * QQ:411083907
 * E-mail:q8622268@163.com
 * Version Information：V 1.0
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class LoadingProgress extends ProgressDialog {

    // 加载文字（传值）
    private String loadingContent;

    // 加载框
    private ImageView loadingImg;
    private TextView loadingText;

    // 加载动画
    private AnimationDrawable animation;

    /**
     * 自定义无忧停车动画加载进度的对话框
     *
     * @param context 上下文对象
     */
    public LoadingProgress(Context context) {
        super(context, R.style.Loading_Dialog_Style);
        this.loadingContent = context.getString(R.string.loading_in);
        setCanceledOnTouchOutside(true);
    }

    /**
     * 自定义无忧停车动画加载进度的对话框
     *
     * @param context 上下文对象
     * @param content 提示加载的内容
     */
    public LoadingProgress(Context context, String content) {
        super(context);
        this.loadingContent = content;
        setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {

        setContentView(R.layout.layout_loading);


        // 加载动画展示
        loadingImg = (ImageView) findViewById(R.id.iv_icon);
        loadingImg.setFocusable(false);

        // 加载提示文字
        loadingText = (TextView) findViewById(R.id.tv_text);

    }

    private void initData() {


//     设置图标
        loadingImg.setBackgroundResource(R.drawable.loading_progress);

        // 通过ImageView对象拿到背景显示的AnimationDrawable
        animation = (AnimationDrawable) loadingImg.getBackground();


        // 为了防止在onCreate方法中只显示第一帧的解决方案之一
        loadingImg.post(new Runnable() {
            @Override
            public void run() {
                animation.start();
            }
        });

//        设置文字
        loadingText.setText(loadingContent);
    }

//    public void setContent(String str) {
//
//        loadingText.setText(str);
//    }

}
