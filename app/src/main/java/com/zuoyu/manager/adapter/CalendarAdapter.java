package com.zuoyu.manager.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zuoyu.manager.R;
import com.zuoyu.manager.activity.ReportDayActivity;
import com.zuoyu.manager.application.Constant;
import com.zuoyu.manager.entity.CalendarEntity;
import com.zuoyu.manager.utils.DateUtil;
import com.zuoyu.manager.utils.SharedUtil;
import com.zuoyu.manager.utils.ToastUtil;
import com.zuoyu.manager.utils.ToolUtil;
import com.zuoyu.manager.widget.CalendarView;

import java.util.List;


/**
 * <pre>
 * Function：报表查看适配器
 *
 * Created by JoannChen on 2017/3/22 18:24
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class CalendarAdapter extends BaseAdapter {

    private List<CalendarEntity.CalendarInfo> list;
    private Context context;

    public CalendarAdapter(Context context, List<CalendarEntity.CalendarInfo> list) {
        this.list = list;
        this.context = context;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.calendar_item, parent, false);
            holder = new ViewHolder();
            holder.calendarView = (CalendarView) convertView.findViewById(R.id.calendarView);


            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();
        }


//        if(((PullListView)parent).isOnMeasure || !((PullListView)parent).mIsScroll){
//
//            return convertView;
//        }

        final CalendarEntity.CalendarInfo entity = list.get(position);

        // 显示每月收费总额
        holder.calendarView.setMoney(entity.getPrice());

        // 显示年／月
        if (!ToolUtil.isEmpty(entity.getDate())) {
            String[] date = entity.getDate().split("/");
            holder.year = Integer.parseInt(date[0]);
            holder.month = Integer.parseInt(date[1]);
            holder.calendarView.setDate(holder.year, holder.month);
            holder.calendarView.getMoreText().setTag(holder);
        }


        // 查看月报表
        holder.calendarView.setTag(position);
        holder.calendarView.setMoreOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewHolder holder = (ViewHolder) v.getTag();

                Intent intent = new Intent(context, ReportDayActivity.class);
                intent.putExtra(Constant.YEAR_MONTH, entity.getDate());
                intent.putExtra(Constant.START_DATE, holder.year + "-" + holder.month + "-1");
                intent.putExtra(Constant.END_DATE, holder.year + "-" + holder.month + "-" + (holder.calendarView.getDays().get((holder.calendarView.getDays().size()) - 1) + 1));
                intent.putExtra(Constant.IS_FROM_DAY_REPORT, false);
                context.startActivity(intent);
            }
        });

        // 查看日报表
        holder.calendarView.setOnItemClickListener(new CalendarView.OnItemClickListener() {
            @Override
            public void onClick(CalendarView v,int year, int month, int day) {

                SharedUtil.setInt("calendarIndex", (int) v.getTag());
                notifyDataSetChanged();
                if (day >= 0) {

                    int currentDay = DateUtil.getDay();

                    //设置本日日期样式
                    if (year == DateUtil.getYear() && month == DateUtil.getMonth()) {
                        if (day + 1 == currentDay) {
                            ToastUtil.show("今天的报表还没有出来哦～");
                            return;
                        }
                        //设置灰色字
                        if (day + 1 > currentDay) {
                            return;
                        }
                    }
                } else {
                    return;
                }



                String date = year + "-" + month + "-" + (day + 1);
                String date2 = year + "/" + month + "/" + (day + 1);

                Intent intent = new Intent(context, ReportDayActivity.class);
                intent.putExtra(Constant.YEAR_MONTH, date2);
                intent.putExtra(Constant.START_DATE, date);
                intent.putExtra(Constant.END_DATE, date);
                intent.putExtra(Constant.IS_FROM_DAY_REPORT, true);
                context.startActivity(intent);
            }
        });


        return convertView;
    }

    public class ViewHolder {

        private CalendarView calendarView;
        private int year;
        private int month;
    }

}
