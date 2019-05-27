package com.zuoyu.manager.adapter;

import android.content.Context;
import android.widget.TextView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.base.BaseAdapter;
import com.zuoyu.manager.base.ViewHolder;
import com.zuoyu.manager.entity.ChildOrderEntity.ChildOrder.ChildInfo;

import java.util.List;

/**
 * <pre>
 * Function：在场车辆／订单记录 子订单明细适配器
 *
 * Created by JoannChen on 2017/3/8 15:34
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class ChildOrderAdapter extends BaseAdapter {

    public ChildOrderAdapter(Context context, List<ChildInfo> list) {
        super(context, list);
    }

    @Override
    public int setLayoutId() {
        return R.layout.child_item;
    }

    @Override
    public void getView(ViewHolder holder) {

        // 子订单号
        // 支付时间（如2017-02-21 12:20:05）
        // 支付时停车时长（如02:25:12
        // 订单金额
        // 优惠金额
        // 余额支付
        // 实际支付
        // 支付方式1：支付宝，2：微信，3：现金

        ChildInfo entity = (ChildInfo) list.get(holder.getPosition());

        ((TextView) holder.getView(R.id.tv_orderNum)).setText(entity.getChild_orderid());
        ((TextView) holder.getView(R.id.tv_payTime)).setText(entity.getPay_time());
//        ((TextView) holder.getView(R.id.tv_payingPark)).setText(entity.getPay_park_long());
        ((TextView) holder.getView(R.id.tv_orderMoney)).setText(entity.getMoney());
        ((TextView) holder.getView(R.id.tv_discountMoney)).setText(entity.getDiscount());
        ((TextView) holder.getView(R.id.tv_platform)).setText(entity.getBalance_pay());
        ((TextView) holder.getView(R.id.tv_actualPay)).setText(entity.getActual_pay());
        ((TextView) holder.getView(R.id.tv_payType)).setText(getPayType(entity.getPay_type()));


    }

    /**
     * 获取支付方式 1:支付宝，2:微信，3:现金，4:多次支付
     *
     * @param payType 支付方式
     * @return 支付方式（文本）
     */
    private String getPayType(int payType) {

        switch (payType) {
            case 1:
                payType = R.string.alipay;
                break;
            case 2:
                payType = R.string.wechat;
                break;
            case 3:
                payType = R.string.cash;
                break;
            case 4:
                payType = R.string.pay_more;
                break;
            default:
                payType = R.string.other;
        }

        return context.getString(payType);
    }
}
