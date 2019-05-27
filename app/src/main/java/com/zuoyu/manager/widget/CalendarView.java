package com.zuoyu.manager.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;
import com.zuoyu.manager.R;
import com.zuoyu.manager.utils.DateUtil;
import com.zuoyu.manager.utils.LogUtil;
import com.zuoyu.manager.utils.SharedUtil;
import com.zuoyu.manager.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * <pre>
 * Function：自定义日历控件
 *
 * Created by Joann on 17/2/28 17:44
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class CalendarView extends LinearLayout {

    private TextView dateText;
    private TextView moneyText;
    private TextView moreText;
    private GridView gridView;
    private List<Integer> days;
    private OnItemClickListener listener;
    private CalendarAdapter adapter;
    private int year, month;
    private int parentSelect = -1;

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_canlendar, this, true);

        //日期
        dateText = (TextView) findViewById(R.id.tv_date);

        //金额
        moneyText = (TextView) findViewById(R.id.tv_price);

        //查看月报表
        moreText = (TextView) findViewById(R.id.tv_more);

        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listener != null) {
                    listener.onClick(CalendarView.this,year, month, days.get(position));
                }
                adapter.changeSelected(position);//刷新
            }
        });

    }


    public void setDate(String date) {
        dateText.setText(date);
    }

    public void setMoney(String money) {
        money = "￥" + money;
        moneyText.setText(money);
    }

    public void setOnItemClickListener(final OnItemClickListener listener) {
        this.listener = listener;

    }

    public void setMoreOnClickListener(OnClickListener listener) {
        moreText.setOnClickListener(listener);
    }


    public void setDate(int year, int month) {
        this.year = year;
        this.month = month;
        dateText.setText(year + "/" + month);
        days = new ArrayList<>();
        int count = DateUtil.getMonthDayCount(year, month);

        int week = DateUtil.getMonthDayWeek(year, month);

        for (int i = 0 - week; i < count + week; i++) {
            if (i < count) {
                days.add(i);
            }
        }
        setList(year, month);
//        CalendarAdapter dap = new CalendarAdapter(days, getContext(), listener);
    }


    public void setList(int year, int month) {
        if (adapter == null) {
            LogUtil.i("创建适配器");
            adapter = new CalendarAdapter();
            adapter.setData(year, month);
            gridView.setAdapter(adapter);
        } else {
            LogUtil.i("刷新适配器");
            adapter.setData(year, month);
            adapter.notifyDataSetChanged();
        }
    }


    private class CalendarAdapter extends BaseAdapter implements OnClickListener {
        private int year;
        private int month;
        private OnItemClickListener listener;
        private int tSelect;

        public void setData(int year, int month) {
            this.year = year;
            this.month = month;
        }


        int mSelect = 0;   //选中项

        private void changeSelected(int position) { //刷新方法
            if (position != mSelect) {
                mSelect = position;
                notifyDataSetChanged();
            }
        }

        @Override
        public int getCount() {
            return days.size();
        }

        @Override
        public Object getItem(int position) {
            return days.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = View.inflate(getContext(), R.layout.layout_calendar_item, null);
            AutoUtils.autoSize(convertView);


            if (((MyGridView) parent).isOnMeasure) {
                return convertView;
            }

            int day = days.get(position);

            int mYear = DateUtil.getYear();

            int mMonth = DateUtil.getMonth();

            TextView text_day = (TextView) convertView.findViewById(R.id.tv_day);

            parentSelect = (int) getTag();

            if (parentSelect == SharedUtil.getInt("calendarIndex") && mSelect == position ) {
                text_day.setBackgroundResource(R.mipmap.bg_calendar_no_today);  //选中项背景
//                ToastUtil.show(parentSelect+"点击的坐标");
            } else {
                text_day.setBackgroundResource(R.mipmap.bg_transparent);  //其他项背景
            }


            if (day >= 0) {

                text_day.setText(String.valueOf(day + 1));
                int currentDay = DateUtil.getDay();

                //设置本日日期样式
                if (year == mYear && month == mMonth) {
                    if (day + 1 == currentDay) {
                        LinearLayout todayLLayout = (LinearLayout) convertView.findViewById(R.id.ll_today);
                        TextView todayText = (TextView) convertView.findViewById(R.id.tv_today);
                        todayText.setText(String.valueOf(day + 1));
                        todayLLayout.setVisibility(View.VISIBLE);
                        todayLLayout.setOnClickListener(this);
                        text_day.setVisibility(GONE);
//                        ToastUtil.show("今天的报表还没有出来哦～");
                    }
                    //设置灰色字
                    if (day + 1 > currentDay) {
                        text_day.setTextColor(ContextCompat.getColor(getContext(), R.color.gray_font));
                        text_day.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.transparent));
                        text_day.setOnClickListener(this);
                        text_day.setEnabled(false);
                    }
                }
            } else {
                text_day.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.transparent));
                text_day.setOnClickListener(this);
                text_day.setEnabled(false);
            }
            return convertView;

        }

        @Override
        public void onClick(View v) {

        }
    }


    public interface OnItemClickListener {
        void onClick(CalendarView v,int year, int month, int day);
    }


    public CalendarAdapter getAdapter() {
        return adapter;
    }

    public List<Integer> getDays() {
        return days;
    }

    public TextView getMoreText() {
        return moreText;
    }
}
