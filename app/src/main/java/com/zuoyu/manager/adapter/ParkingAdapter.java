package com.zuoyu.manager.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.base.BaseAdapter;
import com.zuoyu.manager.base.ViewHolder;
import com.zuoyu.manager.entity.ParkingEntity.ParkingInfo;

import java.util.List;


/**
 * <pre>
 * Function：在场车辆列表
 *
 * Created by JoannChen on 2017/3/9 11:40
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class ParkingAdapter extends BaseAdapter {


    public ParkingAdapter(Context context, List<ParkingInfo> list) {
        super(context, list);
    }


    @Override
    public int setLayoutId() {
        return R.layout.search_parking_item;
    }

    @Override
    public void getView(ViewHolder holder) {

        //  车牌号
        //  停车费用
        //  入场时间（如2017/02/21 12:20:05）
        //  停车时长（如1时20分）

        //  订单状态 1：未支付，2：已支付，3：已超时，4：VIP
        //  车辆类型 1：临停，2：月卡

        ParkingInfo entity = (ParkingInfo) list.get(holder.getPosition());

        String parkLong = context.getString(R.string.park_colon) + entity.getPark_long();

        ((TextView) holder.getView(R.id.tv_plate)).setText(entity.getPlate());
        ((TextView) holder.getView(R.id.tv_price)).setText( entity.getPrice());

        ((TextView) holder.getView(R.id.tv_inTime)).setText(entity.getIn_time());
        ((TextView) holder.getView(R.id.tv_long)).setText(parkLong);

     /*   ((ImageView) holder.getView(R.id.iv_type)).setImageResource(
                entity.getType() == 1 ? R.mipmap.icon_park_temp : R.mipmap.icon_park_month);*/

        ((TextView) holder.getView(R.id.iv_type)).setText(entity.getType() == 1 ? "临停" : "月卡");

        ((ImageView) holder.getView(R.id.iv_status)).setImageResource(getPayStatus(entity.getStatus()));


    }


    /**
     * 获取支付状态 1：未支付，2：已支付，3：已超时，4：VIP
     *
     * @param status 支付状态
     * @return 支付状态（图标）
     */
    private int getPayStatus(int status) {

        switch (status) {
            case 1:
                status = R.mipmap.label_park_unpaid;
                break;
            case 2:
                status = R.mipmap.label_park_already_paid;
                break;
            case 3:
                status = R.mipmap.label_park_overtime;
                break;
            case 4:
                status = R.mipmap.label_park_vip;
                break;
            default:
                status = R.mipmap.label_park_unpaid;
        }

        return status;
    }
}
