package com.zuoyu.manager.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.base.BaseAdapter;
import com.zuoyu.manager.base.ViewHolder;
import com.zuoyu.manager.entity.OrderEntity.OrderInfo;

import java.util.List;

/**
 * <pre>
 * Function：订单记录列表
 *
 * Created by JoannChen on 2017/3/8 10:13
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class OrderAdapter extends BaseAdapter {

    public OrderAdapter(Context context, List<?> list) {
        super(context, list);
    }

    @Override
    public int setLayoutId() {
        return R.layout.order_item;
    }

    @Override
    public void getView(ViewHolder holder) {

        // 订单id
        // 出场时间日期（如2017/02/21 12:20:05）
        // 车牌号
        // 停车时长（如1时20分）
        // 停车费用
        // 车辆类型1：临停，2：月卡


        OrderInfo entity = (OrderInfo) list.get(holder.getPosition());

        String orderId = "订单号：" + entity.getOrderid();
        String price = "¥ " + entity.getPrice() + "元";

        ((TextView) holder.getView(R.id.tv_orderNum)).setText(orderId);
        ((TextView) holder.getView(R.id.tv_date)).setText(entity.getDate());
        ((TextView) holder.getView(R.id.tv_plate)).setText(entity.getPlate());
        ((TextView) holder.getView(R.id.tv_long)).setText(entity.getPark_long());
        ((TextView) holder.getView(R.id.tv_price)).setText(price);
        ((ImageView) holder.getView(R.id.iv_status)).setImageResource(
                entity.getType() == 1 ? R.mipmap.label_order_temp : R.mipmap.label_order_vip);

    }
}
