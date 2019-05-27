package com.zuoyu.manager.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.utils.ImageUtil;
import com.zuoyu.manager.widget.viewpager.ImageCycleView;

import java.util.List;

/**
 * <pre>
 * Function：点击图片查看放大，显示的对话框
 *
 * Created by JoannChen on 2017/5/15 11:46
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class BigImageDialog extends Dialog {
    private ImageCycleView imageCycleView;

    public BigImageDialog(@NonNull Context context) {
        super(context, R.style.Dialog_Fullscreen);
        init();
    }

    private void init() {
        setContentView(R.layout.layout_viewpager);
        imageCycleView = (ImageCycleView) findViewById(R.id.imageCycleView);
        imageCycleView.setDialog(this);
    }

    public void setList(List<String> imageUrlList) {

        imageCycleView.setImageResources(imageUrlList, new ImageCycleView.ImageCycleViewListener() {
            @Override
            public void displayImage(String imageURL, ImageView imageView) {
                ImageUtil.load(getContext(), imageView, imageURL, R.mipmap.bg_default);
            }

            @Override
            public void onImageClick(int position, View imageView) {

            }
        });
    }


}
