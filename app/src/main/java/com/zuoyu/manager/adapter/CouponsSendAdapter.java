package com.zuoyu.manager.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.widget.TextView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.base.BaseAdapter;
import com.zuoyu.manager.base.ViewHolder;
import com.zuoyu.manager.entity.CouponsSendEntity;
import com.zuoyu.manager.utils.ToolUtil;
import com.zuoyu.manager.utils.ViewUtil;

import java.util.List;

/**
 * <pre>
 * Function：优惠券使用记录适配器
 *
 * Created by JoannChen on 2017/7/19 15:46
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * </pre>
 */
public class CouponsSendAdapter extends BaseAdapter {

    public CouponsSendAdapter(Context context, List<?> list) {
        super(context, list);
    }


    @Override
    public int setLayoutId() {
        return R.layout.item_coupons_send_record;
    }

    @Override
    public void getView(ViewHolder holder) {
        // 优惠券名称
        // 车牌号
        // 小票
        // 发放时间
        // 使用时间
        // 优惠券(面额)

        final CouponsSendEntity.CouponsInfo entity = (CouponsSendEntity.CouponsInfo) list.get(holder.getPosition());

        ((TextView) holder.getView(R.id.tv_plate)).setText(entity.getLicense_plate());
        ((TextView) holder.getView(R.id.tv_ticket)).setText((context.getString(R.string.ticket_colon) + entity.getTicketno()));
        ((TextView) holder.getView(R.id.tv_sendTime)).setText((context.getString(R.string.hand_out_colon) + (ToolUtil.isEmpty(entity.getStart_time()) ? "1970-01-01 00:00:00" : entity.getStart_time())));

        ((TextView) holder.getView(R.id.tv_nickname)).setText(entity.getCoupons_nickname());

        TextView couponsText = holder.getView(R.id.tv_coupons);

        String coupons = entity.getDiscount_details();
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

        couponsText.setText(style);
    }
}
