package com.zuoyu.manager.adapter;

import android.content.Context;
import android.widget.TextView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.base.BaseAdapter;
import com.zuoyu.manager.base.ViewHolder;
import com.zuoyu.manager.entity.RechargeRecordEntity;
import com.zuoyu.manager.utils.ToolUtil;

import java.util.List;

/**
 * <pre>
 * Function：充值记录适配器
 *
 * Created by JoannChen on 2017/7/19 15:46
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * </pre>
 */
public class RechargeRecordAdapter extends BaseAdapter {

    public RechargeRecordAdapter(Context context, List<?> list) {
        super(context, list);
    }


    @Override
    public int setLayoutId() {
        return R.layout.item_recharge_record;
    }

    @Override
    public void getView(ViewHolder holder) {

        //商户余额（XX小时XX分钟）
        //充值金额
        //充值时间（2017-07-07 13：23：22）


        final RechargeRecordEntity.Recharge entity = (RechargeRecordEntity.Recharge) list.get(holder.getPosition());

        ((TextView) holder.getView(R.id.tv_long)).setText(entity.getMttime());
        ((TextView) holder.getView(R.id.tv_money)).setText(ToolUtil.isEmpty(entity.getAddinfo()) ? "" : ("+" + entity.getAddinfo()));
        ((TextView) holder.getView(R.id.tv_time)).setText(entity.getAddtime());

    }
}
