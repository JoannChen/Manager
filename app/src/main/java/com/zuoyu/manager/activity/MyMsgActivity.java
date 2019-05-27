package com.zuoyu.manager.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.zuoyu.manager.R;
import com.zuoyu.manager.application.MyApplication;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.utils.ViewSetting;

/**
 * <pre>
 * Function：个人中心 我的消息
 *
 * Created by Joann on 2017/2/23 11:02
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class MyMsgActivity extends BaseActivity {


    @Override
    public int setLayout() {
        return R.layout.mymsg_main;
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


        //  带选项卡的标题栏
        ImageView backImg = (ImageView) findViewById(R.id.iv_back);
        backImg.setOnClickListener(this);
        ViewSetting.setViewSize(backImg, 100, 100);


        //  系统消息
        RadioButton leftRBtn = (RadioButton) findViewById(R.id.rb_left);
        leftRBtn.setText(getString(R.string.system_message));
        leftRBtn.setOnClickListener(this);
        ViewSetting.setViewSize(leftRBtn, 0, 188);
        ViewSetting.setTextSize(leftRBtn, 36);


        //  即时消息
        RadioButton rightRBtn = (RadioButton) findViewById(R.id.rb_right);
        rightRBtn.setText(R.string.instant_message);
        rightRBtn.setOnClickListener(this);
        ViewSetting.setViewSize(rightRBtn, 0, 188);
        ViewSetting.setTextSize(rightRBtn, 36);

        //  全部已读
        TextView readText = (TextView) findViewById(R.id.tv_right);
        readText.setOnClickListener(this);


    }

    @Override
    public void initView() {

    }

    @Override
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rb_left:
                MobclickAgent.onEvent(this, "MessageSystem_Page_Load");
                break;
            case R.id.rb_right:
                MobclickAgent.onEvent(this, "MessageInstant_Page_Load");
                break;
            case R.id.tv_right:
                MobclickAgent.onEvent(this, "Message_ReadAll_Click");
                break;
        }
    }

    @Override
    public void close() {

    }

}