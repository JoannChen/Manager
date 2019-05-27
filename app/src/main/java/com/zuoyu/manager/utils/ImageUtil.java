package com.zuoyu.manager.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * <pre>
 * Function：图片加载框架
 *
 * Created by JoannChen on 2017/3/10 15:53
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class ImageUtil {

    /**
     * 加载图片
     * @param context 上下文对象
     * @param imageView 组件
     * @param url 图片地址
     */
    public static void load(Context context, ImageView imageView, String url) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .crossFade()
                .into(imageView);
    }

    /**
     * 加载图片
     * @param context 上下文对象
     * @param imageView 组件
     * @param url 图片地址
     * @param res 默认图片
     */
    public static void load(Context context, ImageView imageView, String url, int res) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(res)
                .crossFade()
                .into(imageView);
    }

}
