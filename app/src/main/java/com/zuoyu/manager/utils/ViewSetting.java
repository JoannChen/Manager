package com.zuoyu.manager.utils;

import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * <pre>
 * Function：百分百设置组件大小（以苹果5C的屏幕比例进行设置大小）
 *
 * Created by JoannChen on 2017/3/9 09:24
 * QQ:411083907
 * E-mail:q8622268@163.com
 * Version Information：V 1.0
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class ViewSetting {


    /**
     * 设计稿的尺寸：Iphone6的屏幕
     */
    public static final int DESIGN_WEIGHT = 750; // Iphone的屏幕长度像素
    public static final int DESIGN_HEIGHT = 1334; // Iphone的屏幕高度像素 - 状态栏高度

    public static final int VIEWGROUP = 1;
    public static final int RADIOGROUP = 2;// 单选分组
    public static final int ABSLISTVIEW = 3; //listView中子条目类型
    public static final int LINEARLAYOUT = 4; // 线性布局
    public static final int RELATIVELAYOUT = 5; // 相对布局
    public static final int FRAMELAYOUT = 6; // 帧布局

    /**
     * android 主流分辨率
     */
    public static final int WIDTH_1080 = 1080;
    public static final int HEIGHT_1920 = 1920;

    /**
     * 获取上下边距百分比高度
     *
     * @param px px
     * @return px
     */
    public static int getHeight(int px) {
        int screenHeight = DimensUtil.getScreenHeight();

        if (DimensUtil.getScreenWidth() == WIDTH_1080) {
            screenHeight = HEIGHT_1920;
        }

        if (DimensUtil.getScreenWidth() == 720) {
            screenHeight = 1280;
        }

        if (DimensUtil.getScreenWidth() == 600) {
            screenHeight = 800;
        }

        if (DimensUtil.getScreenWidth() == 540) {
            screenHeight = 960;
        }

        double iPhoneproportion = px / (double) (DESIGN_HEIGHT / (double) 100);
        int result = (int) ((screenHeight / (double) 100) * iPhoneproportion);
        return result;

    }

    /**
     * 获取左右边距百分比长度
     *
     * @param px px
     * @return px
     */
    public static int getWidth(int px) {
        double iPhoneproportion = px / (double) (DESIGN_WEIGHT / (double) 100);
        int result = (int) ((DimensUtil.getScreenWidth() / (double) 100) * iPhoneproportion);
        return result;
    }

    /**
     * 获取比例视图的长度
     *
     * @param width  px
     * @param height px
     * @return px
     */
    public static int getWidth(int width, int height) {

        float proportion = (height / (float) width);

        return (int) (height / proportion);

    }

    /**
     * 设置TextView文字的大小
     *
     * @param view
     * @param px
     */
    public static void setTextSize(TextView view, int px) {
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, getWidth(px));
    }

    /**
     * 设置editText文字大小
     *
     * @param view
     * @param px
     */
    public static void setTextSize(EditText view, int px) {
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, getHeight(px));
    }


    public static void setTextSize(Button button, int px) {
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, getHeight(px));
    }


    /**
     * 设置组件大小方法
     *
     * @param view   组件对象
     * @param height 高度
     * @param width  宽度
     */
    public static void setViewProportionSize(View view, int height, int width) {
        if (view != null) {
            if (view.getLayoutParams() != null) {
                if (height != 0) {
                    view.getLayoutParams().height = getHeight(height);
                }

                if (width != 0) {
                    view.getLayoutParams().width = getWidth(width);
                }

            } else {

                int mHeight = LayoutParams.WRAP_CONTENT;
                int mWidth = LayoutParams.WRAP_CONTENT;
                if (height != 0) {
                    mHeight = getHeight(height);
                }

                if (width != 0) {
                    mWidth = getWidth(width);
                }
                LayoutParams params = new LayoutParams(mWidth, mHeight);
                view.setLayoutParams(params);

            }

        }
    }

    /**
     * 设置组件大小方法
     *
     * @param view   组件对象
     * @param height 高度
     * @param width  宽度
     */
    public static void setViewSize(View view, int height, int width) {
        if (view != null) {
            if (view.getLayoutParams() != null) {
                if (height != 0) {
                    view.getLayoutParams().height = getHeight(height);
                }

                if (width != 0) {
                    view.getLayoutParams().width = getWidth(width);
                }

            } else {

                int mHeight = LayoutParams.WRAP_CONTENT;
                int mWidth = LayoutParams.WRAP_CONTENT;
                if (height != 0) {
                    mHeight = getHeight(height);
                }

                if (width != 0) {
                    mWidth = getWidth(width);
                }
                LayoutParams params = new LayoutParams(mWidth, mHeight);
                view.setLayoutParams(params);

            }

        }
    }


    /**
     * @param view
     * @param height
     * @param width
     */
    public static void setViewSize(View view, int height, int width, int type) {
        if (view != null) {
            if (view.getLayoutParams() != null) {
                if (height != 0) {
                    view.getLayoutParams().height = getHeight(height);
                }

                if (width != 0) {
                    view.getLayoutParams().width = getWidth(width);
                }

            } else {

                if (type == LINEARLAYOUT) {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            getWidth(width), getHeight(height));
                    view.setLayoutParams(params);
                }

                if (type == RELATIVELAYOUT) {
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                            getWidth(width), getHeight(height));
                    view.setLayoutParams(params);
                }
                if (type == FRAMELAYOUT) {
                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                            getWidth(width), getHeight(height));
                    view.setLayoutParams(params);
                }

                if (type == ABSLISTVIEW) {
                    AbsListView.LayoutParams params = new AbsListView.LayoutParams(getWidth(width), getHeight(height));
                    view.setLayoutParams(params);
                }

            }

        }
    }


    public static void setMargin(View view, int left, int top, int right, int bottom, int type) {
        if (left != 0) {
            setMarginLeft(view, left, type);
        }

        if (top != 0) {
            setMarginTop(view, top, type);
        }

        if (right != 0) {
            setMarginRight(view, right, type);
        }

        if (bottom != 0) {
            setMarginBottom(view, bottom, type);
        }
    }


    /**
     * 按比例设置组件左边距
     *
     * @param view   当前组件
     * @param margin 左边距
     * @param type   所在父布局类型
     */
    public static void setMarginLeft(View view, int margin, int type) {
        if (type == RELATIVELAYOUT) {

            if (view.getLayoutParams() == null) {
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                params.leftMargin = getWidth(margin);
                view.setLayoutParams(params);
            } else {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();
                params.leftMargin = getWidth(margin);
            }

        }

        if (type == LINEARLAYOUT) {

            if (view.getLayoutParams() == null) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                params.leftMargin = getWidth(margin);
                view.setLayoutParams(params);
            } else {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view
                        .getLayoutParams();
                params.leftMargin = getWidth(margin);
            }

        }

        if (type == FRAMELAYOUT) {

            if (view.getLayoutParams() == null) {
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                params.leftMargin = getWidth(margin);
                view.setLayoutParams(params);
            } else {
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view
                        .getLayoutParams();
                params.leftMargin = getWidth(margin);
            }

        }

        if (type == VIEWGROUP) {
            if (view.getLayoutParams() == null) {
                setMarginLeft(view, margin, RELATIVELAYOUT);
            } else {
                LayoutParams layoutParams = view.getLayoutParams();
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                params.height = layoutParams.height;
                params.width = layoutParams.width;
                params.leftMargin = getWidth(margin);
                view.setLayoutParams(params);
            }
        }

        if (type == RADIOGROUP) {
            if (view.getLayoutParams() == null) {
                RadioGroup.LayoutParams layoutParmas = new RadioGroup.LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                layoutParmas.leftMargin = getWidth(margin);
                view.setLayoutParams(layoutParmas);
            } else {
                RadioGroup.LayoutParams layoutParams = (RadioGroup.LayoutParams) view.getLayoutParams();
                layoutParams.leftMargin = getWidth(margin);
            }
        }

    }

    /**
     * 按比例设置组件右边距
     *
     * @param view   当前组件
     * @param margin 右边距
     * @param type   所在父布局类型
     */
    public static void setMarginRight(View view, int margin, int type) {

        if (type == RELATIVELAYOUT) {
            if (view.getLayoutParams() == null) {
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                params.rightMargin = getWidth(margin);
                view.setLayoutParams(params);
            } else {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();
                params.rightMargin = getWidth(margin);
            }
        }

        if (type == LINEARLAYOUT) {

            if (view.getLayoutParams() == null) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                params.rightMargin = getWidth(margin);
                view.setLayoutParams(params);
            } else {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view
                        .getLayoutParams();
                params.rightMargin = getWidth(margin);
            }

        }

        if (type == FRAMELAYOUT) {

            if (view.getLayoutParams() == null) {
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                params.rightMargin = getWidth(margin);
                view.setLayoutParams(params);
            } else {
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view
                        .getLayoutParams();
                params.rightMargin = getWidth(margin);
            }

        }

        if (type == VIEWGROUP) {
            if (view.getLayoutParams() == null) {
                setMarginRight(view, margin, RELATIVELAYOUT);
            } else {
                LayoutParams layoutParams = view.getLayoutParams();
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                params.height = layoutParams.height;
                params.width = layoutParams.width;
                params.rightMargin = getWidth(margin);
                view.setLayoutParams(params);
            }
        }
    }

    /**
     * 按比例设置组件的上边距
     *
     * @param view   当前组件
     * @param margin 上边距
     * @param type   所在父布局类型
     */
    public static void setMarginTop(View view, int margin, int type) {

        if (type == RELATIVELAYOUT) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view
                    .getLayoutParams();
            params.topMargin = getHeight(margin);
        }

        if (type == LINEARLAYOUT) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view
                    .getLayoutParams();
            params.topMargin = getHeight(margin);
        }

        if (type == FRAMELAYOUT) {
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view
                    .getLayoutParams();
            params.topMargin = getHeight(margin);
        }
    }

    /**
     * 按比例设置组件的下边距
     *
     * @param view   当前组件
     * @param margin 下边距
     * @param type   所在父布局类型
     */
    public static void setMarginBottom(View view, int margin, int type) {

        if (view.getLayoutParams() != null) {

            if (type == RELATIVELAYOUT) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();
                params.bottomMargin = getHeight(margin);
            }
            if (type == LINEARLAYOUT) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view
                        .getLayoutParams();
                params.bottomMargin = getHeight(margin);
            }
        } else {
            if (type == RELATIVELAYOUT) {
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                params.bottomMargin = getHeight(margin);
                view.setLayoutParams(params);
            }
            if (type == LINEARLAYOUT) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                params.bottomMargin = getHeight(margin);
                view.setLayoutParams(params);
            }
        }
    }

    /**
     * 按比例设置组件左边距
     *
     * @param view    view
     * @param padding 天辰
     */
    public static void setPaddingLeft(View view, int padding) {
        view.setPadding(getWidth(padding), view.getPaddingTop(),
                view.getPaddingRight(), view.getPaddingBottom());
    }

    public static void setPaddingBottom(View view, int padding) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(),
                view.getPaddingRight(), getHeight(padding));
    }

    public static void setPaddingRight(View view, int padding) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(),
                getWidth(padding), view.getPaddingBottom());
    }

    public static void setPaddingTop(View view, int padding) {
        view.setPadding(view.getPaddingLeft(), getWidth(padding),
                view.getPaddingRight(), view.getPaddingBottom());
    }

    public static void setPadding(View view, int padding) {
        view.setPadding(getWidth(padding), getWidth(padding), getWidth(padding), getWidth(padding));
    }

    public static void setPadding(View view, int left, int top, int right, int bottom) {
        view.setPadding(getWidth(left), getWidth(top), getWidth(right), getWidth(bottom));
    }

}
