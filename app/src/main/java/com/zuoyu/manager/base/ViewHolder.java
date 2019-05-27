package com.zuoyu.manager.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.autolayout.utils.AutoUtils;

/**
 * <pre>
 * Function：ViewHolder 临时存储器，配合BaseAdapter 使用
 *
 * Created by Joann on 17/2/28 17:44
 * QQ:411083907
 * E-mail:q8622268@163.com
 * Version Information：V 1.0
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class ViewHolder {
    private SparseArray<View> views;
    private View convertView;
    private int position;

    private ViewHolder() {

    }

    private ViewHolder(Context context, int position, int layoutId, ViewGroup parent) {
        this.position = position;
        views = new SparseArray<>();
        convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        convertView.setTag(this);
        AutoUtils.autoSize(convertView);

    }


    /**
     * 获取ViewHolder实例
     *
     * @param context     上下文对象
     * @param position    下标
     * @param convertView View
     * @param layoutId    布局
     * @param parent      父布局
     * @return ViewHolder
     */
    public static ViewHolder getInstance(Context context, int position, View convertView, int layoutId, ViewGroup parent) {

        if (convertView == null) {
            return new ViewHolder(context, position, layoutId, parent);
        } else {
            ViewHolder vh = (ViewHolder) convertView.getTag();
            vh.position = position;
            return vh;
        }
    }


    /**
     * 根据ID获取View
     *
     * @param id  组件id
     * @param <T> 组件类型
     * @return 组件对应的view
     */
    public <T extends View> T getView(int id) {
        View view = views.get(id);

        if (view == null) {
            view = convertView.findViewById(id);
            views.put(id, view);
        }

        return (T) view;
    }

    /**
     * 获取复用视图
     *
     * @return convertView
     */
    public View getConvertView() {

        return convertView;
    }

    public int getPosition() {
        return position;
    }

}
