package com.zuoyu.manager.widget.search;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.utils.ToolUtil;

/**
 * <pre>
 * Function：仿iOS风格的搜索框
 *
 * Created by Joann on 2017/3/1 17:39
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class SearchEditText extends AppCompatEditText implements View.OnFocusChangeListener, View.OnKeyListener, TextWatcher {

    private static final String TAG = "SearchEditText";

    private TextView cancelText;


    /**
     * 图标是否默认在左边
     */
    public boolean hasFocus = false;

    /**
     * 是否点击软键盘搜索
     */
    private boolean pressSearch = false;

    /**
     * 软键盘搜索键监听
     */
    private OnSearchClickListener listener;


    // 控件的图片资源
    private Drawable[] drawables;


    // 搜索图标和删除按钮图标
    private Drawable leftDrawable, delDrawable;

    // 控件区域
    private Rect rect;


    public void setOnSearchClickListener(OnSearchClickListener listener) {
        this.listener = listener;
    }

    public void setCancelText(TextView cancelText) {
        this.cancelText = cancelText;
    }

    public interface OnSearchClickListener {
        void onSearchClick(View view);
    }

    public SearchEditText(Context context) {
        this(context, null);
        init();
    }


    public SearchEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
        init();
    }


    public SearchEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnFocusChangeListener(this);
        setOnKeyListener(this);
        addTextChangedListener(this);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        // 如果是默认样式，直接绘制
        if (hasFocus) {
            if (length() < 1) {
                delDrawable = null;
            }
            this.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null, delDrawable, null);
            super.onDraw(canvas);
        } else {
            // 如果不是默认样式，需要将图标绘制在中间
            if (drawables == null) drawables = getCompoundDrawables();
            if (leftDrawable == null) leftDrawable = drawables[0];
            float textWidth = getPaint().measureText(getHint().toString());
            int drawablePadding = getCompoundDrawablePadding();
            int drawableWidth = leftDrawable.getIntrinsicWidth();
            float bodyWidth = textWidth + drawableWidth + drawablePadding;
            canvas.translate((getWidth() - bodyWidth - getPaddingLeft() - getPaddingRight()) / 2, 0);
            super.onDraw(canvas);
        }

    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        // 被点击时，恢复默认样式
//        if (!pressSearch && TextUtils.isEmpty(getText().toString())) {
            this.hasFocus = hasFocus;
            requestLayout();
//        }

        if(hasFocus){
            cancelText.setVisibility(View.VISIBLE);

        }else{
//            cancelText.setVisibility(View.GONE);
            ToolUtil.hideKeyBoard(getContext(),this);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        pressSearch = (keyCode == KeyEvent.KEYCODE_ENTER);
        if (pressSearch && listener != null) {

            /*隐藏软键盘*/
            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
            }
            listener.onSearchClick(v);
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // 记录点击坐标
        int eventX, eventY;

        // 清空edit内容
        if (delDrawable != null && event.getAction() == MotionEvent.ACTION_UP) {
            eventX = (int) event.getRawX();
            eventY = (int) event.getRawY();
            Log.i(TAG, "eventX = " + eventX + "; eventY = " + eventY);
            if (rect == null) rect = new Rect();
            getGlobalVisibleRect(rect);
            rect.left = rect.right - delDrawable.getIntrinsicWidth();
            if (rect.contains(eventX, eventY)) {
                setText("");
            }
        }

        // 删除按钮被按下时改变图标样式
        if (delDrawable != null && event.getAction() == MotionEvent.ACTION_DOWN) {

            eventX = (int) event.getRawX();
            eventY = (int) event.getRawY();
            Log.i(TAG, "eventX = " + eventX + "; eventY = " + eventY);
            if (rect == null) rect = new Rect();
            getGlobalVisibleRect(rect);
            rect.left = rect.right - delDrawable.getIntrinsicWidth();
            if (rect.contains(eventX, eventY))
                delDrawable = ContextCompat.getDrawable(getContext(), R.mipmap.btn_clear);
        } else {
            delDrawable = ContextCompat.getDrawable(getContext(), R.mipmap.btn_clear);
        }

        return super.onTouchEvent(event);
    }


    @Override
    public void afterTextChanged(Editable arg0) {
        if (this.length() < 1) {
            delDrawable = null;
        } else {
            delDrawable = ContextCompat.getDrawable(getContext(), R.mipmap.btn_clear);
        }
    }


    @Override
    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {
    }

    @Override
    public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                              int arg3) {
    }


}