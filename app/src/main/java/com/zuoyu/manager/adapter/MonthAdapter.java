package com.zuoyu.manager.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.zuoyu.manager.R;
import com.zuoyu.manager.base.BaseAdapter;
import com.zuoyu.manager.base.ViewHolder;
import com.zuoyu.manager.entity.MonthEntity.MonthInfo;

import java.util.List;

/**
 * <pre>
 * Function：月卡管理适配器
 *
 * Created by JoannChen on 2017/3/10 17:54
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class MonthAdapter extends BaseAdapter {

    private int index = -1;

    public MonthAdapter(Context context, List<?> list) {
        super(context, list);
    }

    @Override
    public int setLayoutId() {
        return R.layout.search_month_item;
    }

    @Override
    public void getView(ViewHolder holder) {

        // 车牌号
        // 月卡面值
        // 有效期（如2017-02-21至2018-02-21）
        // 月卡类型1：新办，2：续费
        // 持卡人姓名
        // 持卡人电话
        // 停车场名字

        MonthInfo entity = (MonthInfo) list.get(holder.getPosition());

        ((TextView) holder.getView(R.id.tv_type)).setText(entity.getType() == 1 ? context.getString(R.string.newly) : context.getString(R.string.renew));
        ((TextView) holder.getView(R.id.tv_plate)).setText(entity.getPlate());
        ((TextView) holder.getView(R.id.tv_price)).setText(entity.getPrice());
        ((TextView) holder.getView(R.id.tv_name)).setText(entity.getName());
        ((TextView) holder.getView(R.id.tv_telephone)).setText(entity.getTel());


        ViewSwitcher switcher = holder.getView(R.id.viewSwitcher);
        View line = holder.getView(R.id.line_1);


        if (index != -1) {
            if (holder.getPosition() == index) {

                switcher.setVisibility(View.VISIBLE);
                switcher.setDisplayedChild(0);
                line.setVisibility(View.GONE);

                ((TextView) holder.getView(R.id.tv_long)).setText(entity.getValidity());
                ((TextView) holder.getView(R.id.tv_address)).setText(entity.getPark_name());

            } else {
                switcher.setVisibility(View.GONE);
                line.setVisibility(View.VISIBLE);
            }
        } else {
            switcher.setVisibility(View.GONE);
            line.setVisibility(View.VISIBLE);
        }


    }


    /**
     * 动态设置月卡详细信息展开与隐藏
     *
     * @param index 下标
     */
    public void setIndex(int index) {
        if (this.index == index) {
            this.index = -1;
        } else {
            this.index = index;
        }

        notifyDataSetChanged();
    }
}
