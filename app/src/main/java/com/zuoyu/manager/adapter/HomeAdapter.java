package com.zuoyu.manager.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.base.BaseAdapter;
import com.zuoyu.manager.base.ViewHolder;
import com.zuoyu.manager.entity.HomeEntity;

import java.util.List;

/**
 * <pre>
 * Function：
 *
 * Created by JoannChen on 2017/3/23 14:37
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class HomeAdapter extends BaseAdapter {

//    在场车辆：ParkingActivity
//    订单记录：OrderActivity
//    月卡管理：MonthActivity
//    异常记录：AbnormalActivity
//    报表查看：ReportActivity
//    财务分析：FinancialAnalysisActivity
//    流量分析：FlowAnalysisActivity
//    结账统计：CheckoutActivity
//    全部：   AllActivity


    public HomeAdapter(Context context, List<?> list) {
        super(context, list);
    }

    @Override
    public int setLayoutId() {
        return R.layout.home_item;
    }

    @Override
    public void getView(ViewHolder holder) {

        HomeEntity.HomeInfo.Power entity = (HomeEntity.HomeInfo.Power) list.get(holder.getPosition());


        ((TextView) holder.getView(R.id.tv_name)).setText(entity.getNode());

        int icon = R.mipmap.ic_launcher;

        if (("ParkingActivity").endsWith(entity.getAction())) {
            icon = R.mipmap.icon_home_list_parking;

        } else if (("OrderActivity").endsWith(entity.getAction())) {
            icon = R.mipmap.icon_home_list_order;

        } else if (("MonthActivity").endsWith(entity.getAction())) {
            icon = R.mipmap.icon_home_list_month;

        } else if (("AbnormalActivity").endsWith(entity.getAction())) {
            icon = R.mipmap.icon_home_list_abnormal;

        } else if (("ReportActivity").endsWith(entity.getAction())) {
            icon = R.mipmap.icon_home_list_report;

        } else if (("FinancialAnalysisActivity").endsWith(entity.getAction())) {
            icon = R.mipmap.icon_home_list_financial;

        } else if (("FlowAnalysisActivity").endsWith(entity.getAction())) {
            icon = R.mipmap.icon_home_list_flow;

        } else if (("CheckoutActivity").endsWith(entity.getAction())) {
            icon = R.mipmap.icon_home_list_checkout;

        } else if (("BusinessActivity").endsWith(entity.getAction())) {
            icon = R.mipmap.icon_home_list_business;

        } else if (("AllActivity").endsWith(entity.getAction())) {
            icon = R.mipmap.icon_home_list_all;

        }

        ((ImageView) holder.getView(R.id.iv_icon)).setImageResource(icon);


    }
}
