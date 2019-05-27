package com.zuoyu.manager.adapter;

import android.content.Context;
import android.widget.TextView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.base.BaseAdapter;
import com.zuoyu.manager.base.ViewHolder;
import com.zuoyu.manager.entity.IncomeEntity;

import java.util.List;

/**
 * <pre>
 * Function：车场收入 适配器
 *
 * Created by JoannChen on 2017/5/24 10:05
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class IncomeAdapter extends BaseAdapter {

    public IncomeAdapter(List<IncomeEntity.IncomeList.IncomeInfo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int setLayoutId() {
        return R.layout.car_item;
    }

    @Override
    public void getView(ViewHolder holder) {

        IncomeEntity.IncomeList.IncomeInfo info = (IncomeEntity.IncomeList.IncomeInfo) list.get(holder.getPosition());

        ((TextView) holder.getView(R.id.text_1)).setText(info.getDate());
        ((TextView) holder.getView(R.id.text_2)).setText((context.getString(R.string.rmb) + info.getReceivable()));
        ((TextView) holder.getView(R.id.text_3)).setText((context.getString(R.string.rmb) + info.getCharge()));
    }
}
