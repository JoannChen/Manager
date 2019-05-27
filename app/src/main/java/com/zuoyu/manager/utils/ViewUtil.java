package com.zuoyu.manager.utils;

import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * <pre>
 * 功能说明： 百分百设置组件大小（以苹果5C的屏幕比例进行设置大小）
 * 日期：	2015年10月11日
 * 开发者：	陈丶泳佐
 * 版本信息：V5.0.0
 * 版权声明：版权所有@北京百会易泊科技有限公司
 *
 * 历史记录
 *    修改内容：
 *    修改人员：
 *    修改日期： 2015年11月6日
 * </pre>
 */
public class ViewUtil {

    /*
     * 常量
     */
    public static final int RELATIVELAYOUT = 100; // 相对布局
    public static final int LINEARLAYOUT = 200; // 线性布局

    /*
     * 设计稿的尺寸：Iphone6的屏幕
     */
    public static final int WEIGHT = 750;
    public static final int HEIGHT = 1334;

	/*
     * 华为手机虚拟键适配
	 */

    // 1280*720
    public static final int HEIGHT_1280 = 1280;
    public static final int WIDTH_720 = 720;

    // 1920*1080
    public static final int HEIGHT_1920 = 1920;
    public static final int WIDTH_1080 = 1080;

    /**
     * <pre>
     * 功能说明：获取上下边距百分比高度
     * 日期：	2015年11月23日
     * 开发者：	陈丶泳佐
     *
     * @param px
     * @return
     * </pre>
     */
    public static int getHeight(int px) {

        int screenWidth = DimensUtil.getScreenWidth();
        int screenHeight = DimensUtil.getScreenHeight();

        if (screenWidth == WIDTH_1080 && screenHeight != HEIGHT_1920) {
            screenHeight = HEIGHT_1920;
        }

        if (screenWidth == WIDTH_720 && screenHeight != HEIGHT_1280) {
            screenHeight = HEIGHT_1280;
        }

        // 将屏幕分成100份，判断输入的像素占屏幕的几份
        double iPhoneProportion = px / (double) (HEIGHT / (double) 100);
        int result = (int) ((screenHeight / (double) 100) * iPhoneProportion);
        return result;

    }

    /**
     * <pre>
     * 功能说明：获取左右边距百分比长度
     * 日期：	2015年11月23日
     * 开发者：	陈丶泳佐
     *
     * @param px
     * @return
     * </pre>
     */
    public static int getWidth(int px) {
        double iPhoneProportion = px / (double) (WEIGHT / (double) 100);
        int result = (int) ((DimensUtil.getScreenWidth() / (double) 100) * iPhoneProportion);
        return result;
    }

    /**
     * <pre>
     * 功能说明： 转换成百分比px 有时候会有一点误差的 ,该方法用来减少误差
     * 			据你当前的 宽高 计算 等比例的 长度
     * 日期：	2015年11月23日
     * 开发者：	陈丶泳佐
     *
     * @param height
     * @param width
     * @return
     * </pre>
     */
    public static int getWidth(int height, int width) {

        float proportion = (height / (float) width);

        return (int) (height / proportion);

    }

    /**
     * <pre>
     * 功能说明：设置TextView文字的大小
     * 日期：	2015年11月23日
     * 开发者：	陈丶泳佐
     *
     * @param view
     * @param px
     * </pre>
     */
    public static void setTextSize(TextView view, int px) {
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, getWidth(px));
    }

    /**
     * <pre>
     * 功能说明：设置EditText文字大小
     * 日期：	2015年11月23日
     * 开发者：	陈丶泳佐
     *
     * @param view
     * @param px
     * </pre>
     */
    public static void setTextSize(EditText view, int px) {
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, getWidth(px));
    }

    /**
     * <pre>
     * 功能说明：设置Button文字大小
     * 日期：	2015年11月23日
     * 开发者：	陈丶泳佐
     *
     * @param view
     * @param px
     * </pre>
     */
    public static void setTextSize(Button view, int px) {
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, getWidth(px));
    }

    /**
     * <pre>
     * 功能说明：设置RadioButton文字大小
     * 日期：	2015年11月23日
     * 开发者：	陈丶泳佐
     *
     * @param view
     * @param px
     * </pre>
     */
    public static void setTextSize(RadioButton view, int px) {
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, getWidth(px));
    }

    /**
     * <pre>
     * 功能说明：设置组件大小方法
     * 日期：	2015年11月23日
     * 开发者：	陈丶泳佐
     *
     * @param view
     * @param height
     * @param width
     * </pre>
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

                LayoutParams params = new LayoutParams(getWidth(width),
                        getHeight(height));
                view.setLayoutParams(params);

            }

        }
    }

    /**
     * <pre>
     * 功能说明：设置布局大小方法
     * 日期：	2015年11月23日
     * 开发者：	陈丶泳佐
     *
     * @param view
     * @param height
     * @param width
     * @param type
     * </pre>
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

//				LogUtil.e("LayoutParams对象为空，创建新的布局参数");
            }

        } else {
//			LogUtil.e("View对象为空，无法获取布局参数");
        }
    }

    /**
     * <pre>
     * 功能说明：按比例设置组件左边距
     * 日期：	2015年11月23日
     * 开发者：	陈丶泳佐
     *
     * @param view
     * @param margin
     * @param type
     * </pre>
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
    }

    /**
     * <pre>
     * 功能说明：按比例设置组件右边距
     * 日期：	2015年11月23日
     * 开发者：	陈丶泳佐
     *
     * @param view
     * @param margin
     * @param type
     * </pre>
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
    }

    /**
     * <pre>
     * 功能说明：按比例设置组件的四个边距(相同)
     * 日期：	2015年11月23日
     * 开发者：	陈丶泳佐
     *
     * @param view    当前组件
     * @param margin 上下左右边距
     * @param type    外层布局的类型
     * </pre>
     */
    public static void setMargin(View view, int margin, int type) {
        setMarginTop(view, margin, type);
        setMarginBottom(view, margin, type);
        setMarginLeft(view, margin, type);
        setMarginRight(view, margin, type);
    }

    /**
     * <pre>
     * 功能说明：按比例设置组件的四个边距(不同)
     * 日期：	2015年11月23日
     * 开发者：	陈丶泳佐
     *
     * @param view    当前组件
     * @param marginTB    上下边距
     * @param marginLR    左右边距
     * @param type    外层布局的类型
     * </pre>
     */
    public static void setMargin(View view, int marginTB, int marginLR, int type) {

        if (marginTB != 0) {
            setMarginTop(view, marginTB, type);
            setMarginBottom(view, marginTB, type);
        }

        if (marginLR != 0) {
            setMarginLeft(view, marginLR, type);
            setMarginRight(view, marginLR, type);
        }
    }

    /**
     * <pre>
     * 功能说明：按比例设置组件的四个边距(不同)
     * 日期：	2015年11月23日
     * 开发者：	陈丶泳佐
     *
     * @param view    当前组件
     * @param marginT    上边距
     * @param marginR 右边距
     * @param marginB 下边距
     * @param marginL 左边距
     * @param type    外层布局的类型
     * </pre>
     */
    public static void setMargin(View view, int marginT, int marginR,
                                 int marginB, int marginL, int type) {
        if (marginT != 0) {
            setMarginTop(view, marginT, type);
        }

        if (marginR != 0) {
            setMarginRight(view, marginR, type);
        }

        if (marginB != 0) {
            setMarginBottom(view, marginB, type);
        }

        if (marginL != 0) {
            setMarginLeft(view, marginL, type);
        }
    }

    /**
     * <pre>
     * 功能说明：按比例设置组件的上边距
     * 日期：	2015年11月23日
     * 开发者：	陈丶泳佐
     *
     * @param view
     * @param margin
     * @param type
     * </pre>
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
    }

    /**
     * <pre>
     * 功能说明：按比例设置组件的四个填充值(不同)
     * 日期：	2015年11月23日
     * 开发者：	陈丶泳佐
     *
     * @param view    当前组件
     * @param paddingTB    上下填充
     * @param paddingLR    左右填充
     * </pre>
     */
    public static void setPadding(View view, int paddingTB, int paddingLR) {

        if (paddingTB != 0) {
            setPaddingTop(view, paddingTB);
            setPaddingBottom(view, paddingTB);
        }

        if (paddingLR != 0) {
            setPaddingLeft(view, paddingLR);
            setPaddingRight(view, paddingLR);
        }
    }

    /**
     * <pre>
     * 功能说明：按比例设置组件的下边距
     * 日期：	2015年11月23日
     * 开发者：	陈丶泳佐
     *
     * @param view
     * @param margin
     * @param type
     * </pre>
     */
    public static void setMarginBottom(View view, int margin, int type) {
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
    }

    /**
     * <pre>
     * 功能说明：按比例设置组件四边填充值(不同)
     * 日期：	2015年11月23日
     * 开发者：	陈丶泳佐
     *
     * @param view
     * </pre>
     */
    public static void setPadding(View view, int paddingTop, int paddingRight,
                                  int paddingBottom, int paddingLeft) {

        if (paddingTop != 0) {
            setPaddingTop(view, paddingTop);
        }

        if (paddingRight != 0) {
            setPaddingRight(view, paddingRight);
        }

        if (paddingBottom != 0) {
            setPaddingBottom(view, paddingBottom);
        }

        if (paddingLeft != 0) {
            setPaddingLeft(view, paddingLeft);
        }
    }

    /**
     * <pre>
     * 功能说明：按比例设置组件四边填充值(相同)
     * 日期：	2015年11月23日
     * 开发者：	陈丶泳佐
     *
     * @param view
     * @param padding
     * </pre>
     */
    public static void setPadding(View view, int padding) {
        setPaddingTop(view, padding);
        setPaddingBottom(view, padding);
        setPaddingLeft(view, padding);
        setPaddingRight(view, padding);
    }

    /**
     * <pre>
     * 功能说明：按比例设置组件左填充值
     * 日期：	2015年11月23日
     * 开发者：	陈丶泳佐
     *
     * @param view
     * @param padding
     * </pre>
     */
    public static void setPaddingLeft(View view, int padding) {
        view.setPadding(getWidth(padding), view.getPaddingTop(),
                view.getPaddingRight(), view.getPaddingBottom());
    }

    /**
     * <pre>
     * 功能说明：按比例设置组件右填充值
     * 日期：	2015年11月23日
     * 开发者：	陈丶泳佐
     *
     * @param view
     * @param padding
     * </pre>
     */
    public static void setPaddingRight(View view, int padding) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(),
                getWidth(padding), view.getPaddingBottom());
    }

    /**
     * <pre>
     * 功能说明：按比例设置组件上填充值
     * 日期：	2015年11月23日
     * 开发者：	陈丶泳佐
     *
     * @param view
     * @param padding
     * </pre>
     */
    public static void setPaddingTop(View view, int padding) {
        view.setPadding(view.getPaddingLeft(), getWidth(padding),
                view.getPaddingRight(), view.getPaddingBottom());
    }

    /**
     * <pre>
     * 功能说明：按比例设置组件下填充值
     * 日期：	2015年11月23日
     * 开发者：	陈丶泳佐
     *
     * @param view
     * @param padding
     * </pre>
     */
    public static void setPaddingBottom(View view, int padding) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(),
                view.getPaddingRight(), getWidth(padding));
    }

    /**
     * <pre>
     * 功能说明：设置组件DrawablePadding属性
     * 日期：	2015年11月23日
     * 开发者：	陈丶泳佐
     *
     * @param view
     * @param padding
     * </pre>
     */
    public static void setDrawablePadding(TextView view, int padding) {
        view.setCompoundDrawablePadding(getWidth(padding));
    }

}
