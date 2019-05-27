package com.zuoyu.manager.adapter;

import android.content.Context;
import android.widget.TextView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.base.BaseAdapter;
import com.zuoyu.manager.base.ViewHolder;
import com.zuoyu.manager.entity.DetailsEntity.Details;

import java.util.List;

/**
 * <pre>
 * Function：在场车辆／订单记录详情 适配器
 *
 * Created by JoannChen on 2017/3/8 15:34
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class DetailsAdapter extends BaseAdapter {

    /**
     * 判断从哪个页面进入
     * true  订单记录
     * false 在场车辆
     */
    private boolean isFromOrderPage;

    public DetailsAdapter(Context context, List<Details> list, boolean isFromOrderPage) {
        this.list = list;
        this.context = context;
        this.isFromOrderPage = isFromOrderPage;
    }

    @Override
    public int setLayoutId() {
        return R.layout.details_main;
    }

    @Override
    public void getView(ViewHolder holder) {


        Details entity = (Details) list.get(holder.getPosition());

        // 车场名称
        // 停车费用
        // 车牌号
        // 停车时长（如02:25:12）

        ((TextView) holder.getView(R.id.tv_name)).setText(entity.getPark_name());
        ((TextView) holder.getView(R.id.tv_price)).setText(entity.getPrice());
        ((TextView) holder.getView(R.id.tv_plate)).setText(entity.getPlate());
        ((TextView) holder.getView(R.id.tv_long)).setText(entity.getPark_long());


        // 车辆类型 1：临停车，2：固定车
        // 入场时间

        ((TextView) holder.getView(R.id.tv_carType)).setText(entity.getType() == 1 ? R.string.temp_car : R.string.fixed_car);
        ((TextView) holder.getView(R.id.tv_inTime)).setText(entity.getEnter_time());

        /*
         * 【订单详情】true
         *      出场时间
         * 【车辆详情】false
         *      支付状态 1：未支付，2：已支付，3：已超时，4：VIP
         *      超时时长
         *      补缴费用
         */

        // 文本
        TextView payStatus = holder.getView(R.id.payStatus);
        int res;

        if (isFromOrderPage) {
            res = R.string.order_details;
            ((TextView) holder.getView(R.id.tv_outTime)).setText(entity.getExit_time());
        } else {
            res = R.string.parking_details;
            ((TextView) holder.getView(R.id.tv_payStatus)).setText(getPayStatus(entity.getStatus()));
            ((TextView) holder.getView(R.id.tv_overtime)).setText(entity.getOver_time());
            ((TextView) holder.getView(R.id.tv_catchUp)).setText(entity.getSupplement());
        }
        payStatus.setText(context.getString(res));


        /*
         * 以下为结果(status =2时有效) 少一个支付时停车
         */

        //  订单号编号
        //  支付时间（如2017-02-21 12:20:05）(多次支付)
        //  订单金额
        //  优惠金额
        //  余额支付
        //  实际支付
        //  支付方式 1:支付宝，2:微信，3:现金，4:多次支付

        if (entity.getStatus() == 2) {
            ((TextView) holder.getView(R.id.tv_orderNum)).setText(entity.getOrderid());
            ((TextView) holder.getView(R.id.tv_payTime)).setText(entity.getPay_time());
            ((TextView) holder.getView(R.id.tv_orderMoney)).setText(entity.getMoney());
            ((TextView) holder.getView(R.id.tv_discountMoney)).setText(entity.getDiscount());
            ((TextView) holder.getView(R.id.tv_platform)).setText(entity.getBalance_pay());
            ((TextView) holder.getView(R.id.tv_actualPay)).setText(entity.getActual_pay());
            ((TextView) holder.getView(R.id.tv_payType)).setText(getPayType(entity.getPay_type()));

        }


    }


    /**
     * 获取支付状态 1：未支付，2：已支付，3：已超时，4：VIP
     *
     * @param status 支付状态
     * @return 支付状态（文本）
     */
    private String getPayStatus(int status) {

        switch (status) {
            case 1:
                status = R.string.unpaid;
                break;
            case 2:
                status = R.string.already_paid;
                break;
            case 3:
                status = R.string.overtime;
                break;
            case 4:
                status = R.string.vip;
                break;
            default:
                status = R.string.unpaid;
        }

        return context.getString(status);
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
