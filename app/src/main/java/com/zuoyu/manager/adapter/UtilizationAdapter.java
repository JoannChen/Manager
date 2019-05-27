package com.zuoyu.manager.adapter;

import android.content.Context;

import com.zuoyu.manager.R;
import com.zuoyu.manager.base.BaseAdapter;
import com.zuoyu.manager.base.ViewHolder;
import com.zuoyu.manager.entity.UtilizationEntity;
import com.zuoyu.manager.entity.UtilizationEntity.UtilizationList.UtilizationInfo;
import com.zuoyu.manager.widget.UtilizationProgress;

import java.util.List;

/**
 * <pre>
 * Function：车位利用率 适配器
 *
 * Created by JoannChen on 2017/5/24 10:05
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class UtilizationAdapter extends BaseAdapter {

    // 判断是24小时还是7日／30日
    private boolean isHour = true;
    private float maxValue = 0;

    /**
     * true: 展示24小时数据
     * false: 展示7日／30日数据
     *
     * @param isHour 是否展示24小时数据
     */
    public void setFlag(boolean isHour) {
        this.isHour = isHour;

        for (int i = 0; i < getCount(); i++) {

            UtilizationInfo utilizationInfo = (UtilizationInfo) list.get(i);

            if (!isHour) {
                if (utilizationInfo.getUnilization() > maxValue) {
                    maxValue = utilizationInfo.getUnilization();
                }
            } else {

                if (utilizationInfo.getOccupy() > maxValue) {
                    maxValue = utilizationInfo.getOccupy();
                }
            }
        }

    }

    public UtilizationAdapter(List<?> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public int setLayoutId() {
        return R.layout.utilization_item;
    }

    @Override
    public void getView(ViewHolder holder) {
        UtilizationEntity.UtilizationList.UtilizationInfo entity = (UtilizationEntity.UtilizationList.UtilizationInfo) list.get(holder.getPosition());

        UtilizationProgress progress = holder.getView(R.id.progress);
        progress.setMaxValue(maxValue);

        if (isHour) {
            progress.setTag(entity.getOccupy());
            progress.setTime(entity.getTime());
            progress.setTextWidth(80);
        } else {
            progress.setTextWidth(160);
            progress.setTag(entity.getUnilization());
            progress.setTime(entity.getDate());
        }

        progress.setLastProgress(holder.getPosition(), list.size());
        progress.showProgress();
    }

}
