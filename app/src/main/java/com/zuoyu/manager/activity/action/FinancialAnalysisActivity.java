package com.zuoyu.manager.activity.action;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zuoyu.manager.R;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.fragment.IncomeFragment;
import com.zuoyu.manager.fragment.PaymentFragment;
import com.zuoyu.manager.utils.ViewUtil;

/**
 * <pre>
 * Function：财务分析界面
 *
 * Created by JoannChen on 2017/3/24 10:04
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class FinancialAnalysisActivity extends BaseActivity {

    private IncomeFragment incomeFragment;
    private PaymentFragment paymentFragment;

    RadioGroup radioGroup;
    RadioButton incomeRBtn, paymentRBtn;

    @Override
    public int setLayout() {
        return R.layout.flow_main;
    }

    @Override
    public void initBeforeLayout() {

    }

    @Override
    public void initTitle() {
        titleManage.setTitleText("财务分析");
    }

    @Override
    public void initView() {

        incomeFragment = new IncomeFragment();
        paymentFragment = new PaymentFragment();

        showFragment(incomeFragment);

        incomeRBtn = (RadioButton) findViewById(R.id.rb_left);
        incomeRBtn.setText(getString(R.string.park_income));
        incomeRBtn.setChecked(true);
        setRBtnStyle(incomeRBtn);

        paymentRBtn = (RadioButton) findViewById(R.id.rb_right);
        paymentRBtn.setText(getString(R.string.payment));
        setRBtnStyle(paymentRBtn);


        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_left:
                        showFragment(incomeFragment);
                        break;
                    case R.id.rb_right:
                        showFragment(paymentFragment);
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
        ViewUtil.setViewSize(radioButton, 0, 180);
        ViewUtil.setTextSize(radioButton, 36);
        ViewUtil.setMarginLeft(radioButton, 50, ViewUtil.LINEARLAYOUT);
    }
}
