package com.zuoyu.manager.adapter;

import android.content.Context;
import android.widget.TextView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.base.BaseAdapter;
import com.zuoyu.manager.base.ViewHolder;
import com.zuoyu.manager.entity.PaymentEntity;

import java.util.List;

/**
 * <pre>
 * Function：财务分析——支付方式适配器
 *
 * Created by JoannChen on 2017/5/24 17:24
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class PaymentAdapter extends BaseAdapter {

    public PaymentAdapter(List<?> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int setLayoutId() {
        return R.layout.turnover_item;
    }

    @Override
    public void getView(ViewHolder holder) {
        PaymentEntity.PaymentList.Payment payType = (PaymentEntity.PaymentList.Payment) list.get(holder.getPosition());

        ((TextView) holder.getView(R.id.text_1)).setText(payType.getPayway());
        ((TextView) holder.getView(R.id.text_2)).setText((context.getString(R.string.rmb) + String.valueOf(payType.getPaycount())));
    }
}
