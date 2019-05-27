package com.zuoyu.manager.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

import com.zuoyu.manager.R;


/**
 * <pre>
 * Function：自定义EditText，带清除按钮和震动效果
 *
 * Created by Joann on 17/1/20 17:44
 * QQ:411083907
 * E-mail:q8622268@163.com
 * Version Information：V 1.0
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class ClearEditText extends AppCompatEditText implements View.OnFocusChangeListener, TextWatcher {


    /**
     * 定义有参的构造
     * 实现OnFocusChangeListener监听事件
     * 定义清除图标是否可见
     * 添加文本内容监听
     */

//    删除按钮的引用
    private Drawable mClearDrawable;

    //    控件是否有焦点
    private boolean hasFoucs;

    public ClearEditText(Context context) {
        super(context);
        initView();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

        // 获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null) {
            mClearDrawable = ContextCompat.getDrawable(getContext(), R.drawable.draw_clear_edit);
        }

        //设置图标的位置以及大小,getIntrinsicWidth()获取显示出来的大小而不是原图片的大小
        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());

        // 默认设置图标隐藏
        setClearIconVisible(false);

        // 设置焦点改变的监听
        setOnFocusChangeListener(this);

        // 设置变的监听
        addTextChangedListener(this);


    }

    /**
     * 因为我们不能直接给EditText设置点击事件
     * 所以我们用记住我们按下的位置来模拟点击事件
     * 当我们按下的位置
     * 在 （EditText的宽度 - 图标到控件右边的间距 - 图标的宽度） 和 （EditText的宽度 - 图标到控件右边的间距）之间
     * 我们就算点击了图标，竖直方向没有考虑
     *
     * @param event 触摸事件
     * @return boolean
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {

//            如果右侧图标不为空
            if (getCompoundDrawables()[2] != null) {

                //getPaddingRight()图标右边缘至控件右边缘的距离
                //getTotalPaddingRight()图标左边缘至控件右边缘的距离
                //getWidth() - getTotalPaddingRight()表示从最左边到图标左边缘的位置
                //getWidth() - getPaddingRight()表示最左边到图标右边缘的位置
                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
                        && (event.getX() < ((getWidth() - getPaddingRight())));

                if (touchable) {
                    this.setText("");
                }
            }
        }

        return super.onTouchEvent(event);
    }

    /**
     * 当ClearEditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏
     *
     * @param view     当前组件
     * @param hasFocus 是否获取焦点
     */
    @Override
    public void onFocusChange(View view, boolean hasFocus) {

        this.hasFoucs = hasFocus;
        if (hasFocus) {
//            如果按钮有焦点且有文本内容，则显示按钮图标
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }

    }

    /**
     * 当输入框里面内容发生变化的时候回调的方法
     *
     * @param text         文本内容
     * @param start        字符开始的位置
     * @param lengthBefore c变化前的总字节数
     * @param lengthAfter  变化后的字节数
     */
    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (hasFoucs) {
            setClearIconVisible(text.length() > 0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int lengthBefore, int lengthAfter) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }


    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     *
     * @param visible 判断参数
     */
    private void setClearIconVisible(boolean visible) {
        Drawable rightIcon = visible ? mClearDrawable : null;
        setCompoundDrawables(null, null, rightIcon, null);
//        setCompoundDrawables(getCompoundDrawables()[0],
//                getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }



    /**
     * 设置晃动动画
     */
    public void setShakeAnimation() {
        this.startAnimation(shakeAnimation(5));
    }


    /**
     * 震动动画，错误后提示
     *
     * @param counts 特定事件内震动的次数
     * @return 平移动画
     */
    public static Animation shakeAnimation(int counts) {
        Animation anim = new TranslateAnimation(0, 10, 0, 0);
        anim.setInterpolator(new CycleInterpolator(counts));
        anim.setDuration(1000);
        return anim;
    }


}
