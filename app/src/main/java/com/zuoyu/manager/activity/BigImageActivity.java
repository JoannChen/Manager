package com.zuoyu.manager.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.utils.ImageUtil;
import com.zuoyu.manager.widget.viewpager.ImageCycleView;

import java.util.List;

import static com.zuoyu.manager.application.MyApplication.getContext;

/**
 * <pre>
 * Function：查看大图
 *
 * Created by JoannChen on 2017/5/18 17:57
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class BigImageActivity extends BaseActivity {

    private ImageCycleView imageCycleView;
    public static BigImageActivity activity;

    @Override
    public int setLayout() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.layout_viewpager;
    }

    @Override
    public void initBeforeLayout() {

    }

    @Override
    public void initTitle() {
        titleManage.hideTitle();
    }

    @Override
    public void initView() {

        List<String> imageUrlList = getIntent().getStringArrayListExtra("list");
        imageCycleView = (ImageCycleView) findViewById(R.id.imageCycleView);

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

    @Override
    public void onClickEvent(View v) {

    }

    @Override
    public void initAfterLayout(Bundle savedInstanceState) {

    }

    @Override
    public void close() {

    }
}
