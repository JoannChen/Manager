package com.zuoyu.manager.activity;

import android.os.Bundle;
import android.view.View;

import com.zuoyu.manager.R;
import com.zuoyu.manager.application.MyApplication;
import com.zuoyu.manager.base.BaseActivity;

/**
 * <pre>
 * Function：日报表
 *
 * Created by JoannChen on 2017/3/1 10:30
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class DailyReportActivity extends BaseActivity {


    @Override
    public int setLayout() {
        return R.layout.abnormal_main;
    }

    @Override
    public void initBeforeLayout() {}

    @Override
    public void initAfterLayout(Bundle savedInstanceState) {
        MyApplication.addActivity(this);}


    @Override
    public void initTitle() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void onClickEvent(View v) {

    }

    @Override
    public void close() {

    }
}
