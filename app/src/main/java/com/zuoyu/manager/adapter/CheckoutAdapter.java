package com.zuoyu.manager.adapter;

import android.content.Context;

import com.zuoyu.manager.R;
import com.zuoyu.manager.base.BaseAdapter;
import com.zuoyu.manager.base.ViewHolder;

import java.util.List;

/**
 * Created by zhangye on 17-3-16.
 */

public class CheckoutAdapter extends BaseAdapter {

    public CheckoutAdapter(Context context, List<?> list) {
        super(context, list);
    }

    @Override
    public int setLayoutId() {
        return R.layout.checkout_item2;
    }

    @Override
    public void getView(ViewHolder holder) {

    }
}
