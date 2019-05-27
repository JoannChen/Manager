package com.zuoyu.manager.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.widget.TextView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.base.BaseAdapter;
import com.zuoyu.manager.base.ViewHolder;
import com.zuoyu.manager.entity.BusinessCouponsEntity;
import com.zuoyu.manager.utils.ViewUtil;

import java.util.List;

/**
 * <pre>
 * Function：商家优惠券列表适配器
 *
 * Created by JoannChen on 2017/7/19 15:46
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * </pre>
 */
public class BusinessCouponsAdapter extends BaseAdapter {

    public BusinessCouponsAdapter(Context context, List<?> list) {
        super(context, list);
    }


    @Override
    public int setLayoutId() {
        return R.layout.item_business_coupons;
    }

    @Override
    public void getView(ViewHolder holder) {

        final BusinessCouponsEntity.BusinessCoupons.BusinessCouponsInfo entity = (BusinessCouponsEntity.BusinessCoupons.BusinessCouponsInfo) list.get(holder.getPosition());

        // 今日优惠券总数
        // 剩余优惠券
        // 有效期
        // 优惠券(面额)

        ((TextView) holder.getView(R.id.tv_nickname)).setText(entity.getNickname());
        ((TextView) holder.getView(R.id.tv_total)).setText((entity.getTotal() + " 张"));
        ((TextView) holder.getView(R.id.tv_surplus)).setText((entity.getUse() + " 张"));
        ((TextView) holder.getView(R.id.tv_valid)).setText((context.getString(R.string.valid_colon) + entity.getEnddate()));

        TextView couponsText = holder.getView(R.id.tv_coupons);

        String coupons = entity.getCoupons_name();
        SpannableStringBuilder style = null;


        if (coupons.contains("优惠券")) {

            coupons = coupons.split("优惠券")[0];

            style = new SpannableStringBuilder(coupons);

            if (coupons.contains("元")) {
                style.setSpan(new AbsoluteSizeSpan(ViewUtil.getHeight(44)),
                        coupons.length() - 1, coupons.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            }

            if (coupons.contains("折")) {
                style.setSpan(new AbsoluteSizeSpan(ViewUtil.getHeight(44)),
                        coupons.length() - 1, coupons.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            }

            if (coupons.contains("小时")) {
                style.setSpan(new AbsoluteSizeSpan(ViewUtil.getHeight(44)),
                        coupons.length() - 2, coupons.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            if (coupons.contains("全免")) {
                style.setSpan(new AbsoluteSizeSpan(ViewUtil.getHeight(60)),
                        0, coupons.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            }

        }

        couponsText.setText(style == null ? coupons : style);


        if (holder.getPosition() == 0) {
            ViewUtil.setMarginTop(holder.getView(R.id.rl_item), 30, ViewUtil.RELATIVELAYOUT);
        }


    }

}
