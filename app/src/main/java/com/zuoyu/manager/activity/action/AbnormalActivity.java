package com.zuoyu.manager.activity.action;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.zuoyu.manager.R;
import com.zuoyu.manager.application.MyApplication;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.fragment.ExceptionFragment;
import com.zuoyu.manager.fragment.UnpaidFragment;
import com.zuoyu.manager.utils.ViewSetting;
import com.zuoyu.manager.widget.search.SearchEditView;

/**
 * <pre>
 * Function：异常记录：异常抬杆／未支付记录
 *
 * Created by JoannChen on 2017/3/1 16:48
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class AbnormalActivity extends BaseActivity {

    private ExceptionFragment exceptionFragment;
    private UnpaidFragment unpaidFragment;
    private int index = 1;
//    RadioGroup radioGroup;
//    RadioButton leftRBtn, rightRBtn;

    private SearchEditView searchEdit;

    @Override
    public int setLayout() {
        return R.layout.abnormal_main;
    }

    @Override
    public void initBeforeLayout() {
    }


    @Override
    public void initTitle() {

        titleManage.hideTitle();


        //        搜索框
        searchEdit = (SearchEditView) findViewById(R.id.searchEditView);
        searchEdit.setEditBackground();
        searchEdit.setEditHint("请输入参考车牌号");
        searchEdit.setonEditorAction(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                    parseOrderList(pullUtil.getParams());
                    if(index == 1){
                        exceptionFragment.setKey(searchEdit.getText(),true);
                    }

                    if(index == 2){
                        unpaidFragment.setKey(searchEdit.getText(),true);
                    }
                }

                return false;
            }
        });
        searchEdit.setOnCancelBtnListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                parseOrderList(pullUtil.getParams());
                if(index == 1){
                    exceptionFragment.setKey(searchEdit.getText(),true);
                }

                if(index == 2){
                    unpaidFragment.setKey(searchEdit.getText(),true);
                }
            }
        });




        //  带选项卡的标题栏
        ImageView backImg = (ImageView) findViewById(R.id.iv_back);
        backImg.setOnClickListener(this);
        ViewSetting.setViewSize(backImg, 100, 100);


        //  异常抬杆
        RadioButton leftRBtn = (RadioButton) findViewById(R.id.rb_left);
        ViewSetting.setViewSize(leftRBtn, 0, 188);
        ViewSetting.setTextSize(leftRBtn, 36);


        //  未支付记录
        RadioButton  rightRBtn = (RadioButton) findViewById(R.id.rb_right);
//        rightRBtn.setOnClickListener(this);
        ViewSetting.setViewSize(rightRBtn, 0, 188);
        ViewSetting.setTextSize(rightRBtn, 36);

        RadioGroup  radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_left:
                        searchEdit.setText(null);
                        exceptionFragment.setKey(null,false);
                        getSupportFragmentManager().beginTransaction().replace(R.id.ll_container, exceptionFragment).commit();
                        MobclickAgent.onEvent(context, "WrongLift_Page_Load");

                        index = 1;
                        break;
                    case R.id.rb_right:
                        searchEdit.setText(null);
                        unpaidFragment.setKey(null,false);
                        getSupportFragmentManager().beginTransaction().replace(R.id.ll_container, unpaidFragment).commit();
                        MobclickAgent.onEvent(context, "WrongPaid_Page_Load");
                        index = 2;
                        break;
                }
            }
        });

    }

    @Override
    public void initView() {

        exceptionFragment = new ExceptionFragment();
        unpaidFragment = new UnpaidFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.ll_container, exceptionFragment).commit();
    }

    @Override
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;

        }
    }

    @Override
    public void initAfterLayout(Bundle savedInstanceState) {
        MyApplication.addActivity(this);
    }

    @Override
    public void close() {

    }


}
