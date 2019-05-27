package com.zuoyu.manager.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.zuoyu.manager.R;
import com.zuoyu.manager.base.BaseAdapter;
import com.zuoyu.manager.base.ViewHolder;
import com.zuoyu.manager.entity.UnpaidEntity.UnpaidInfo;
import com.zuoyu.manager.utils.DateUtil;
import com.zuoyu.manager.utils.ToolUtil;
import com.zuoyu.manager.widget.BigImageDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Function：未支付记录适配器
 *
 * Created by JoannChen on 2017/3/13 10:12
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class UnpaidAdapter extends BaseAdapter {

    private int index = -1;

    public UnpaidAdapter(Context context, List<UnpaidInfo> list) {
        super(context, list);
    }


    @Override
    public int setLayoutId() {
        return R.layout.abnormal_unpaid_item;
    }

    @Override
    public void getView(final ViewHolder holder) {

        final UnpaidInfo entity = (UnpaidInfo) list.get(holder.getPosition());

        // 车牌号
        ((TextView) holder.getView(R.id.tv_plate)).setText(entity.getPlate());


        // 票号
        if (!ToolUtil.isEmpty(entity.getTickid())) {
            String ticketId = "票号：" + entity.getTickid();
            ((TextView) holder.getView(R.id.tv_num)).setText(ticketId);
        }


        // 有无进场记录
        /* ((TextView) holder.getView(R.id.tv_status)).setText(entity.getIs_record());*/


        // 订单金额
        // 优惠金额
        ((TextView) holder.getView(R.id.tv_should)).setText((context.getString(R.string.should_colon) + context.getString(R.string.rmb) + entity.getMoney()));
        ((TextView) holder.getView(R.id.tv_discount)).setText((context.getString(R.string.discount_colon) + context.getString(R.string.rmb) + entity.getDiscount()));

        // 逃单金额,动态修改金额颜色为红色
        SpannableStringBuilder style = new SpannableStringBuilder((context.getString(R.string.escape_colon) + context.getString(R.string.rmb) + entity.getEscape()));
        style.setSpan(new ForegroundColorSpan(Color.parseColor("#ec4f39")), 3, style.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ((TextView) holder.getView(R.id.tv_escape)).setText(style);

        // 出场时间 // 出口名称
        ((TextView) holder.getView(R.id.tv_outTime)).setText((context.getString(R.string.out_time_colon) + entity.getLeave_time()));
        ((TextView) holder.getView(R.id.tv_out)).setText(entity.getLeave_name());


        /*
         * 点击展示隐藏项
         */

        CheckBox moreCBox = holder.getView(R.id.iv_more);
        moreCBox.setEnabled(false);
        ViewSwitcher switcher = holder.getView(R.id.viewSwitcher);

        if (index != -1) {
            if (holder.getPosition() == index) {

                switcher.setVisibility(View.VISIBLE);
                switcher.setDisplayedChild(0);
                moreCBox.setChecked(true);


                String inTime = "无入场记录";
                String parkLong = "--";

                // 若：服务器返回的出入场时间一致，则显示无入场记录，停车时长用"--"表示
                if (!(entity.getEnter_time()).endsWith(entity.getLeave_time())) {
                    inTime = entity.getEnter_time();
                    parkLong = DateUtil.getTwoDateTime(entity.getEnter_time(), entity.getLeave_time());
                }

                // 入场时间  // 入口名称
                ((TextView) holder.getView(R.id.tv_inTime)).setText((context.getString(R.string.in_time_colon) + inTime));
                ((TextView) holder.getView(R.id.tv_in)).setText(entity.getEnter_name());

                // 停车时长
                ((TextView) holder.getView(R.id.tv_long)).setText((context.getString(R.string.park_long_colon) + parkLong));

                // 操作人
                ((TextView) holder.getView(R.id.tv_operator)).setText(("操作员：" + entity.getOperation()));


            } else {
                switcher.setVisibility(View.GONE);
                moreCBox.setChecked(false);
            }
        } else {
            switcher.setVisibility(View.GONE);
            moreCBox.setChecked(false);
        }


        // 图片
        final List<String> imageUrlList = new ArrayList<>();

        if (!ToolUtil.isEmpty(entity.getEnter_image())) {
            imageUrlList.add(entity.getEnter_image());

        }
        if (!ToolUtil.isEmpty(entity.getExit_image())) {
            imageUrlList.add(entity.getExit_image());
        }


        /*
         * 查看图片
         */
        (holder.getView(R.id.tv_image)).setTag(holder.getPosition());
        (holder.getView(R.id.tv_image)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BigImageDialog dialog = new BigImageDialog(context);
                dialog.setList(imageUrlList);
                dialog.show();
            }
        });


    }


    /**
     * 动态设置未支付详细信息展开与隐藏
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
