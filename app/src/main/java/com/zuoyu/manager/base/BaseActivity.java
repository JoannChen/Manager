package com.zuoyu.manager.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.umeng.analytics.MobclickAgent;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.utils.AutoUtils;
import com.zuoyu.manager.R;
import com.zuoyu.manager.application.TitleManage;
import com.zuoyu.manager.utils.http.HttpUtil;
import com.zuoyu.manager.widget.LoadingProgress;


/**
 * <pre>
 * Function：Activity 父类
 *
 * Created by Joann on 17/1/22 16:35
 * QQ:411083907
 * E-mail:q8622268@163.com
 * Version Information：V 1.0
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public abstract class BaseActivity extends AutoLayoutActivity implements View.OnClickListener {

    public Context context;
    public Activity activity;

    // 初始化网络请求
    public HttpUtil httpUtil;

    // 加载进度条
    public LoadingProgress loadingProgress;


    // 标题栏管理类
    public TitleManage titleManage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBeforeLayout();

        //强制屏幕
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        context = this;
        activity = this;


        httpUtil = new HttpUtil(this);
        loadingProgress = new LoadingProgress(this);


        // 配置标题栏管理
        titleManage = new TitleManage(this);

        // 初始化Root布局（最外层父布局）
        LinearLayout rootView = new LinearLayout(this);
        rootView.setFitsSystemWindows(true);
        rootView.setBackgroundResource(R.color.gray_bg);
        rootView.setOrientation(LinearLayout.VERTICAL);

        // 初始化子类布局（除去标题栏以外的布局）
        View childView = View.inflate(this, setLayout(), null);
        childView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));

        AutoUtils.autoSize(childView);
        rootView.addView(childView);

        // 添加标题栏
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                (int) getResources().getDimension(R.dimen.px90));

        rootView.addView(titleManage.getTitleView(), 0, params);
        AutoUtils.autoSize(titleManage.getTitleView());

        setContentView(rootView);


        // 子类需要重写的方法
        initTitle();
        initView();
        initAfterLayout(savedInstanceState);

//        StatusBarUtil.setColor(this, ContextCompat.getColor(context, R.color.white), 0);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        }
    }


    /**
     * 设置布局文件
     */
    public abstract int setLayout();

    /**
     * 在加载布局前初始化数据
     */
    public abstract void initBeforeLayout();

    /**
     * 初始化标题栏
     */
    public abstract void initTitle();

    /**
     * 初始化View组件
     */
    public abstract void initView();

    /**
     * 组件点击事件
     */
    public abstract void onClickEvent(View v);

    /**
     * 在加载布局后初始化数据
     */
    public abstract void initAfterLayout(Bundle savedInstanceState);


    public abstract void close();


    @Override
    public void onClick(View v) {
        onClickEvent(v);
//        ClickUtil clickUtil = new ClickUtil(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onClickEvent(v);
//            }
//        });
//
//        clickUtil.onClick(v);

    }


    /**
     * 以下为生命周期
     */


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        close();
        httpUtil.cancel();
        super.onDestroy();

    }


    /**
     * Intent跳转
     *
     * @param activity 当前的Activity
     * @param clazz    跳转的Activity
     * @param isFinish 是否关闭当前的Activity
     */
    public void showIntent(Activity activity, Class<?> clazz,
                           boolean isFinish) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
        if (isFinish) {
            activity.finish();
        }
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    public void showIntent(Activity activity, Class<?> clazz, String key, String value,
                           boolean isFinish) {
        Intent intent = new Intent(activity, clazz);
        intent.putExtra(key, value);
        activity.startActivity(intent);
        if (isFinish) {
            activity.finish();
        }
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    public void showIntent(Activity activity, Class<?> clazz, String key, boolean value,
                           boolean isFinish) {
        Intent intent = new Intent(activity, clazz);
        intent.putExtra(key, value);
        activity.startActivity(intent);
        if (isFinish) {
            activity.finish();
        }
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }


}

