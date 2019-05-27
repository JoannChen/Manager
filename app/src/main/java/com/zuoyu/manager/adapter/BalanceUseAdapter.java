package com.zuoyu.manager.adapter;

import android.content.Context;
import android.widget.TextView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.base.BaseAdapter;
import com.zuoyu.manager.base.ViewHolder;
import com.zuoyu.manager.entity.BalanceUseEntity;

import java.util.List;

/**
 * <pre>
 * Function：余额使用记录适配器
 *
 * Created by JoannChen on 2017/7/19 15:46
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * </pre>
 */
public class BalanceUseAdapter extends BaseAdapter {

    public BalanceUseAdapter(Context context, List<?> list) {
        super(context, list);
    }


    @Override
    public int setLayoutId() {
        return R.layout.item_balance_use_record;
    }

    @Override
    public void getView(ViewHolder holder) {

        //车牌号
        //优惠详情(5元、2小时、8折)
        //小票id
        //发放日期(2017.4.10 12:09:34)
        //使用日期(2017-4-10 12:09:34)

        final BalanceUseEntity.Balance entity = (BalanceUseEntity.Balance) list.get(holder.getPosition());

        ((TextView) holder.getView(R.id.tv_plate)).setText(entity.getLicense_plate());
        ((TextView) holder.getView(R.id.tv_money)).setText(("优惠时长：" + entity.getDiscount_details()));
        ((TextView) holder.getView(R.id.tv_ticket)).setText(("票号：" + entity.getTicketno()));
        ((TextView) holder.getView(R.id.tv_long)).setText(("发放时间：" + entity.getStart_time()));

    }
}
