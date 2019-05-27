package com.zuoyu.manager.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.application.MyApplication;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.utils.IntentUtil;
import com.zuoyu.manager.utils.SharedUtil;
import com.zuoyu.manager.utils.ToolUtil;

/**
 * <pre>
 * Function：启动页面
 *
 * Created by JoannChen on 2017/2/28 16:04
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class SplashActivity extends BaseActivity {

    private Handler handler = new Handler();

    @Override
    public int setLayout() {
        return R.layout.splash_main;
    }

    @Override
    public void initBeforeLayout() {

    }

    @Override
    public void initAfterLayout(Bundle savedInstanceState) {
        MyApplication.addActivity(this);
    }

    @Override
    public void initTitle() {
        titleManage.hideTitle();
    }

    @Override
    public void initView() {

        // 动画
        ImageView imageView = (ImageView) findViewById(R.id.iv_splash);
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.splash_anim);
        imageView.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        // 已经登录 ？首页：选择停车场
                        if (SharedUtil.getBoolean(SharedUtil.IS_LOGIN)) {
                            // 停车场id是否为空 ？选择停车场：首页
                            if (ToolUtil.isEmpty(SharedUtil.getString(SharedUtil.PARK_ID))) {
                                IntentUtil.start(activity, ChoiceParkActivity.class, true);
                            } else {
                                IntentUtil.start(activity, HomeActivity.class, true);
                            }
                        } else {
                            IntentUtil.start(activity, LoginActivity.class, true);

                        }
                    }
                }, 500);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    @Override
    public void onClickEvent(View v) {

    }

    @Override
    public void close() {

    }


}
