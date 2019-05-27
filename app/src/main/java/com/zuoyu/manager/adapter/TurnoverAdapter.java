package com.zuoyu.manager.adapter;

import android.content.Context;
import android.widget.TextView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.base.BaseAdapter;
import com.zuoyu.manager.base.ViewHolder;
import com.zuoyu.manager.entity.TurnoverEntity;

import java.util.List;

/**
 * <pre>
 * Function：车位周转率 适配器
 *
 * Created by JoannChen on 2017/5/24 10:05
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class TurnoverAdapter extends BaseAdapter {

    public TurnoverAdapter(List<?> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int setLayoutId() {
        return R.layout.turnover_item;
    }

    @Override
    public void getView(ViewHolder holder) {

        TurnoverEntity.TurnoverList.TurnoverInfo info = (TurnoverEntity.TurnoverList.TurnoverInfo) list.get(holder.getPosition());
        ((TextView) holder.getView(R.id.text_1)).setText(String.valueOf(info.getDate()));
        ((TextView) holder.getView(R.id.text_2)).setText(String.valueOf(info.getTurnover()));
    }

}
