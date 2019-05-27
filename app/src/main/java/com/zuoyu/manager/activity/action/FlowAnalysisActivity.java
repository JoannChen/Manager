package com.zuoyu.manager.activity.action;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zuoyu.manager.R;
import com.zuoyu.manager.application.Constant;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.fragment.CarFragment;
import com.zuoyu.manager.fragment.TurnoverFragment;
import com.zuoyu.manager.fragment.UtilizationFragment;
import com.zuoyu.manager.utils.ToolUtil;
import com.zuoyu.manager.utils.ViewUtil;

/**
 * <pre>
 * Function：流量分析界面
 *
 * Created by JoannChen on 2017/3/24 10:04
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class FlowAnalysisActivity extends BaseActivity {

    private CarFragment carFragment;
    private UtilizationFragment useFragment;
    private TurnoverFragment turnoverFragment;

    RadioGroup radioGroup;
    RadioButton carRBtn, useRBtn, turnoverRBtn;

    @Override
    public int setLayout() {
        return R.layout.flow_main;
    }

    @Override
    public void initBeforeLayout() {

    }

    @Override
    public void initTitle() {
        titleManage.setTitleText("流量分析");
    }

    @Override
    public void initView() {


        carFragment = new CarFragment();
        useFragment = new UtilizationFragment();
        turnoverFragment = new TurnoverFragment();

        carRBtn = (RadioButton) findViewById(R.id.rb_left);
        setRBtnStyle(carRBtn);

        useRBtn = (RadioButton) findViewById(R.id.rb_center);
        useRBtn.setVisibility(View.VISIBLE);
        setRBtnStyle(useRBtn);

        turnoverRBtn = (RadioButton) findViewById(R.id.rb_right);
        setRBtnStyle(turnoverRBtn);


        // 首页具体模块跳转判断
        String chartTag = getIntent().getStringExtra(Constant.CHART_TAG);
        if (ToolUtil.isEmpty(chartTag)) {
            showFragment(carFragment);
            carRBtn.setChecked(true);
        } else {
            if (chartTag.equals("利用率")) {
                showFragment(useFragment);
                useRBtn.setChecked(true);
            }
            if (chartTag.equals("周转率")) {
                showFragment(turnoverFragment);
                turnoverRBtn.setChecked(true);
            }
        }

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_left:
                        showFragment(carFragment);
                        break;
                    case R.id.rb_center:
                        showFragment(useFragment);
                        break;
                    case R.id.rb_right:
                        showFragment(turnoverFragment);
                        break;

                }
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

    /**
     * Fragment切换
     *
     * @param fragment fragment
     */
    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.ll_container, fragment).commit();
    }


    /**
     * 设置RadioButton的样式
     *
     * @param radioButton button
     */
    private void setRBtnStyle(RadioButton radioButton) {
        ViewUtil.setViewSize(radioButton, 0, 200);
        ViewUtil.setTextSize(radioButton, 36);
        ViewUtil.setMarginLeft(radioButton, 30, ViewUtil.LINEARLAYOUT);
    }

}
