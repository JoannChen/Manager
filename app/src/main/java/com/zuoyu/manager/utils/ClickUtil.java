package com.zuoyu.manager.utils;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.zuoyu.manager.application.Constant;
import com.zuoyu.manager.widget.pullable.PullListView;

import static android.R.id.list;
import static com.zuoyu.manager.R.id.gridView;

/**
 * <pre>
 * Function：防止多次点击工具类
 *
 * Created by Joann on 17/3/31 17:44
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class ClickUtil {

    private final int MIN_CLICK_DELAY_TIME = 2000;
    private long mLastClickTime = 0;// 上次点击的时间
    private View.OnClickListener mOnClickListener;
    private AdapterView.OnItemClickListener mOnItemClickListener;

    public ClickUtil(final View.OnClickListener listener) {
        mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((System.currentTimeMillis() - mLastClickTime) > MIN_CLICK_DELAY_TIME) {
                    mLastClickTime = System.currentTimeMillis();
                    try {
                        listener.onClick(v);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    public ClickUtil(final AdapterView.OnItemClickListener listener) {
        mOnItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if ((System.currentTimeMillis() - mLastClickTime) > MIN_CLICK_DELAY_TIME) {
                    mLastClickTime = System.currentTimeMillis();
                    try {
                        listener.onItemClick(parent, view, position, id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    /**
     *
     * @param view v
     */
    public void onClick(View view){
        view.setOnClickListener(mOnClickListener);
    }

    /**
     *
     * @param view v
     */
    public void onItemClick(View view) {

        if (view instanceof ListView) {
            ((ListView) view).setOnItemClickListener(mOnItemClickListener);
        }

        if (view instanceof PullListView) {
            ((PullListView) view).setOnItemClickListener(mOnItemClickListener);
        }

        if (view instanceof GridView) {
            ((GridView) view).setOnItemClickListener(mOnItemClickListener);
        }


    }


}
