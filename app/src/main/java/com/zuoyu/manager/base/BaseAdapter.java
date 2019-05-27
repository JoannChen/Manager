package com.zuoyu.manager.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.zuoyu.manager.utils.LogUtil;
import com.zuoyu.manager.widget.MyGridView;
import com.zuoyu.manager.widget.pullable.PullListView;

import java.util.List;

/**
 * <pre>
 * Function：Adapter 父类
 *
 * Created by Joann on 17/2/28 17:44
 * QQ:411083907
 * E-mail:q8622268@163.com
 * Version Information：V 1.0
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public abstract class BaseAdapter extends android.widget.BaseAdapter {

    public Context context;
    public List<?> list;

    public BaseAdapter(){

    }

    public BaseAdapter(Context context, List<?> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        if (list == null) {
            LogUtil.i("集合为null");
            return 0;
        }
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

        ViewHolder holder = ViewHolder.getInstance(context, position, convertView, setLayoutId(), parent);

        if( parent instanceof MyGridView){
            if(((MyGridView) parent).isOnMeasure){
                return holder.getConvertView();
            }

            LogUtil.i("MyGridView成立啦");
        }

        if( parent instanceof PullListView){

            if(((PullListView) parent).isOnMeasure){
                return holder.getConvertView();
            }
            LogUtil.i("PullListView成立啦");
        }

        getView(holder);
        return holder.getConvertView();
    }

    /**
     * 设置布局ID
     *
     * @return id
     */
    public abstract int setLayoutId();


    /**
     * 处理数据显示
     *
     * @param holder 临时储存器
     */
    public abstract void getView(ViewHolder holder);

}
