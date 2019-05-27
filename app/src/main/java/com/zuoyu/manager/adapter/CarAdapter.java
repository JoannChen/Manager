package com.zuoyu.manager.adapter;

import android.content.Context;
import android.widget.TextView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.base.BaseAdapter;
import com.zuoyu.manager.base.ViewHolder;
import com.zuoyu.manager.entity.CarInOutEntity.FlowCar.FlowCarInfo;

import java.util.List;

/**
 * <pre>
 * Function：出入场车辆统计 适配器
 *
 * Created by JoannChen on 2017/5/24 10:05
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class CarAdapter extends BaseAdapter {

    private List<FlowCarInfo> exitList; //出场
    private boolean flag;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public CarAdapter(List<FlowCarInfo> enterList, List<FlowCarInfo> exitList, Context context) {
        this.list = enterList;
        this.exitList = exitList;
        this.context = context;
    }

    @Override
    public int setLayoutId() {
        return R.layout.car_item;
    }

    @Override
    public void getView(ViewHolder holder) {

        FlowCarInfo enterInfo = (FlowCarInfo) list.get(holder.getPosition());
        FlowCarInfo exitInfo = exitList.get(holder.getPosition());

        if (flag) {
            ((TextView) holder.getView(R.id.text_1)).setText(enterInfo.getDate());
        } else {
            ((TextView) holder.getView(R.id.text_1)).setText(enterInfo.getTime());
        }

        // 出场时间
        ((TextView) holder.getView(R.id.text_2)).setText(String.valueOf(exitInfo.getCount()));

        // 入场时间
        ((TextView) holder.getView(R.id.text_3)).setText(String.valueOf(enterInfo.getCount()));
    }
}
