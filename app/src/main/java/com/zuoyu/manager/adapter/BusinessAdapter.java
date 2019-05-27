package com.zuoyu.manager.adapter;

import android.content.Context;
import android.widget.TextView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.base.BaseAdapter;
import com.zuoyu.manager.base.ViewHolder;
import com.zuoyu.manager.entity.BusinessEntity;

import java.util.List;

/**
 * <pre>
 * Function：商家管理列表适配器
 *
 * Created by JoannChen on 2017/7/19 15:46
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * </pre>
 */
public class BusinessAdapter extends BaseAdapter {

    public BusinessAdapter(Context context, List<?> list) {
        super(context, list);
    }


    @Override
    public int setLayoutId() {
        return R.layout.item_business_list;
    }

    @Override
    public void getView(ViewHolder holder) {

        //店名
        //商家余额(小时分钟)
        //最后充值金额
        //最后充值时间

        final BusinessEntity.Business.BusinessInfo entity = (BusinessEntity.Business.BusinessInfo) list.get(holder.getPosition());

        ((TextView) holder.getView(R.id.tv_name)).setText(entity.getName());
        ((TextView) holder.getView(R.id.tv_balance)).setText(("商家余额：" + entity.getMttime()));
        ((TextView) holder.getView(R.id.tv_money)).setText(("最后充值金额：" + entity.getLastadd()));
        ((TextView) holder.getView(R.id.tv_time)).setText(("最后充值时间：" + entity.getAddtime()));

    }
}
