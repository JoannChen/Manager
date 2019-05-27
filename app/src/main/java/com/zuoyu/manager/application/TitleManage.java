package com.zuoyu.manager.application;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.utils.ViewSetting;

/**
 * <pre>
 * Function：标题管理类，布局中不需要include标题文件，在父类中配置即可
 *
 * Created by JoannChen on 2017/3/9 09:24
 * QQ:411083907
 * E-mail:q8622268@163.com
 * Version Information：V 1.0
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class TitleManage implements View.OnClickListener {

    // 上下文对象
    private Context context;

    // 标题栏所在的父布局
    private View titleView;

    // 标题栏文字
    private TextView titleText;

    // 图标按钮（返回按钮默认是显示的）
    private ImageView leftImg, rightImg;

    // 文字按钮
    private TextView leftText, rightText;


    public TitleManage(Context context) {
        this.context = context;
        initView();
    }

    /**
     * 初始化组件
     */
    private void initView() {

        titleView = View.inflate(context, R.layout.layout_title, null);

        titleText = (TextView) titleView.findViewById(R.id.tv_title);

        // 图标按钮
        leftImg = (ImageView) titleView.findViewById(R.id.iv_left);
        rightImg = (ImageView) titleView.findViewById(R.id.iv_right);

        // 文字按钮
        leftText = (TextView) titleView.findViewById(R.id.tv_left);
        rightText = (TextView) titleView.findViewById(R.id.tv_right);


        //  返回按钮默认是显示的
        leftImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) context).finish();
            }
        });

    }

    @Override
    public void onClick(View view) {

    }

    /**
     * 获取当前标题栏视图
     *
     * @return titleView
     */
    public View getTitleView() {
        return titleView;
    }

    /**
     * 隐藏标题栏，用于自定义标题栏样式时
     */
    public void hideTitle() {
        titleView.setVisibility(View.GONE);
    }


    /**
     * 显示标题栏，仅显示标题文字，左侧按钮隐藏
     *
     * @param title 标题文字
     */
    public void showTitle(String title) {
        titleView.setVisibility(View.VISIBLE);
        if (title != null) {
            titleText.setText(title);
            leftImg.setVisibility(View.GONE);
        }
    }

    /**
     * 设置背景颜色
     *
     * @param bgColor 背景颜色
     */
    public void setTitleBgRes(int bgColor) {
        titleView.setBackgroundResource(bgColor);
    }

    /**
     * 设置标题栏文字
     *
     * @param title 文字
     */
    public void setTitleText(String title) {
        if (!TextUtils.isEmpty(title)) {
            titleText.setText(title);
        }
    }

    /**
     * 设置标题栏文字和颜色
     *
     * @param title     文字
     * @param textColor 文字颜色
     */
    public void setTitleText(String title, int textColor) {
        if (!TextUtils.isEmpty(title)) {
            titleText.setText(title);
            titleText.setTextColor(textColor);

        }
    }

    /**
     * 设置左侧按钮显示的图标
     * 默认显示返回按钮，
     * 若传入值为0，则不显示左侧按钮
     *
     * @param icon 图标资源id
     */
    public void setLeftBtn(int icon) {
        leftImg.setVisibility(icon > 0 ? View.VISIBLE : View.GONE);
        leftImg.setImageResource(icon);
    }


    /**
     * 设置左侧按钮显示的文字
     *
     * @param text 文字
     */
    public void setLeftText(String text) {
        leftText.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        leftText.setText(text);
    }

    /**
     * 设置左侧按钮显示的文字和颜色
     *
     * @param text  文字
     * @param color 文字颜色
     */
    public void setLeftText(String text, int color) {
        leftText.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        leftText.setText(text);
        leftText.setTextColor(color);
    }


    /**
     * 设置右侧按钮显示的图标
     *
     * @param icon 图标资源id
     */
    public void setRightBtn(int icon) {
        rightImg.setVisibility(icon > 0 ? View.VISIBLE : View.GONE);
        rightImg.setImageResource(icon);
    }

    /**
     * 设置右侧按钮显示的图标
     *
     * @param icon 图标资源id
     */
    public void setRightBtn(int icon, int width, int height, int marginRight) {
        rightImg.setVisibility(icon > 0 ? View.VISIBLE : View.GONE);
        rightImg.setImageResource(icon);
        ViewSetting.setViewSize(rightImg, height, width);
        ViewSetting.setMarginRight(rightImg, marginRight, ViewSetting.LINEARLAYOUT);
    }

    /**
     * 设置右侧按钮显示的文字
     *
     * @param text 文字
     */
    public void setRightText(String text) {
        rightText.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        rightText.setText(text);
    }

    /**
     * 设置右侧按钮显示的文字和颜色
     *
     * @param text  文字
     * @param color 文字颜色
     */
    public void setRightText(String text, int color) {
        rightText.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        rightText.setText(text);
        rightText.setTextColor(color);
    }

    /**
     * 设置左侧按钮的点击事件
     *
     * @param listener 监听事件
     */
    public void setLeftBtnOnClick(final View.OnClickListener listener) {


        if (leftImg.getVisibility() == View.VISIBLE) {
            leftImg.setOnClickListener(listener);
        } else if (leftText.getVisibility() == View.VISIBLE) {
            leftText.setOnClickListener(listener);
        }

//        //  判断当前是图片按钮显示还是文字按钮显示
//        if (leftImg.getVisibility() == View.VISIBLE) {
//
//            ClickUtil clickUtil = new ClickUtil(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    leftImg.setOnClickListener(listener);
//                }
//            });
//
//            clickUtil.onClick(leftImg);
//
//        } else if (leftText.getVisibility() == View.VISIBLE) {
//
//            ClickUtil clickUtil = new ClickUtil(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    leftText.setOnClickListener(listener);
//                }
//            });
//
//            clickUtil.onClick(leftText);
//        }
    }


    /**
     * 设置右侧按钮的点击事件
     *
     * @param listener 监听事件
     */
    public void setRightBtnOnClick(final View.OnClickListener listener) {

        if (rightImg.getVisibility() == View.VISIBLE) {
            rightImg.setOnClickListener(listener);
        } else if (rightText.getVisibility() == View.VISIBLE) {
            rightText.setOnClickListener(listener);
        }

//        //  判断当前是图片按钮显示还是文字按钮显示
//        if (rightImg.getVisibility() == View.VISIBLE) {
//
//            ClickUtil clickUtil = new ClickUtil(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    rightImg.setOnClickListener(listener);
//                }
//            });
//
//            clickUtil.onClick(rightImg);
//
//        } else if (rightText.getVisibility() == View.VISIBLE) {
//            ClickUtil clickUtil = new ClickUtil(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    rightText.setOnClickListener(listener);
//                }
//            });
//
//            clickUtil.onClick(rightText);
//        }
    }

    /**
     * 设置标题栏的点击事件
     *
     * @param listener 监听事件
     */
    public void setTitleOnClick(final View.OnClickListener listener) {

        //int left, int top, int right, int bottom
        Drawable icon = ContextCompat.getDrawable(MyApplication.getContext(), R.mipmap.icon_home_down);
        icon.setBounds(100, 0, icon.getMinimumWidth(), icon.getMinimumHeight());
        titleText.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);
        titleText.setCompoundDrawablePadding(10);

        titleText.setOnClickListener(listener);

//        ClickUtil clickUtil = new ClickUtil(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                titleText.setOnClickListener(listener);
//            }
//        });
//
//        clickUtil.onClick(titleText);

    }


    /**
     * 判断右侧按钮是否可点击
     *
     * @param isClick true or false
     */
    public void setRightBtnOnClick(boolean isClick) {
        rightImg.setEnabled(isClick);
        if (isClick) {
            rightImg.setAlpha(1f);
        } else {
            rightImg.setAlpha(0.2f);
        }

    }


}


