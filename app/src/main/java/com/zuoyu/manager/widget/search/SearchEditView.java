package com.zuoyu.manager.widget.search;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.utils.ToolUtil;

/**
 * <pre>
 * Function：仿iOS风格的搜索框（带取消按钮）
 *
 * Created by Joann on 2017/3/15 17:39
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class SearchEditView extends LinearLayout {


    private Context context;
    private SearchEditText searchEditText;
    private OnClickListener mOnClickListener;
    private TextView.OnEditorActionListener mOnEditorActionListener;

    public SearchEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    private void initView() {

        LayoutInflater.from(context).inflate(R.layout.layout_search, this, true);

        searchEditText = (SearchEditText) findViewById(R.id.searchEditText);

        // 取消按钮
        final TextView cancelText = (TextView) findViewById(R.id.tv_cancel);
        searchEditText.setCancelText(cancelText);
        cancelText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                searchEditText.setText(null);
//                searchEditText.clearFocus();
//                searchEditText.hasFocus = false;
//                searchEditText.requestLayout();
//
//                if (mOnClickListener != null) {
//                    mOnClickListener.onClick(v);
//                }
//
//                cancelText.setVisibility(View.GONE);

                searchEditText.hasFocus = true;
                searchEditText.requestLayout();
                if (mOnClickListener != null) {
                    ToolUtil.hideKeyBoard(getContext(), cancelText);
                    mOnClickListener.onClick(v);
                }


            }
        });


        // 键盘上搜索按钮
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                searchEditText.hasFocus = true;
                searchEditText.requestLayout();

                if (mOnEditorActionListener != null) {
                    mOnEditorActionListener.onEditorAction(textView, i, keyEvent);
                }

                return false;
            }
        });


    }


    public void addTextChangedListener(TextWatcher watcher) {
        searchEditText.addTextChangedListener(watcher);
    }

    public String getText() {
        return searchEditText.getText().toString();
    }

    public void setText(String text) {
        searchEditText.setText(text);
    }

    /**
     * 设置搜索框提示文字
     *
     * @param searchHint 提示文字
     */
    public void setEditHint(String searchHint) {
        searchEditText.setHint(searchHint);
    }

    /**
     * 设置搜索框背景色
     */
    public void setEditBackground() {
        this.getRootView().setBackgroundColor(ContextCompat.getColor(context, R.color.white));
    }

    /**
     * 键盘上搜索按钮的回调
     *
     * @param listener 监听事件
     */
    public void setonEditorAction(TextView.OnEditorActionListener listener) {
        mOnEditorActionListener = listener;
    }

    /**
     * 搜索框右侧取消按钮的回调
     *
     * @param listener 监听事件
     */
    public void setOnCancelBtnListener(View.OnClickListener listener) {
        mOnClickListener = listener;
    }

}
