package com.zuoyu.manager.adapter;

import android.content.Context;
import android.widget.TextView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.base.BaseAdapter;
import com.zuoyu.manager.base.ViewHolder;
import com.zuoyu.manager.entity.ChoiceParkEntity.ParkInfo;

import java.util.List;

/**
 * <pre>
 * Function：选择停车场适配器
 *
 * Created by JoannChen on 2017/3/9 17:16
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class ChoiceParkAdapter extends BaseAdapter {

    public ChoiceParkAdapter(Context context, List<?> list) {
        super(context, list);
    }

//    public ChoiceParkAdapter(List<ParkInfo> list, Context context) {
//        this.list = list;
//        this.context = context;
//    }

    @Override
    public int setLayoutId() {
        return R.layout.search_choicepark_item;
    }

    @Override
    public void getView(ViewHolder holder) {

        ParkInfo entity = (ParkInfo) list.get(holder.getPosition());

        // 车场id
        // 停车场名称

        ((TextView) holder.getView(R.id.tv_name)).setText(entity.getPark_name());

    }

}
