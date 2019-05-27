package com.zuoyu.manager.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.umeng.analytics.MobclickAgent;
import com.zuoyu.manager.R;
import com.zuoyu.manager.application.Constant;
import com.zuoyu.manager.application.MyApplication;
import com.zuoyu.manager.application.UrlManage;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.base.BaseEntity;
import com.zuoyu.manager.utils.EncryptUtil;
import com.zuoyu.manager.utils.ToastUtil;
import com.zuoyu.manager.utils.ToolUtil;
import com.zuoyu.manager.utils.http.HttpResult;
import com.zuoyu.manager.widget.ClearEditText;

import java.util.Map;
import java.util.TreeMap;

/**
 * <pre>
 * Function：重置密码（忘记密码的第二步）
 *
 * Created by JoannChen on 2017/3/3 17:10
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class ResetActivity extends BaseActivity {


    private com.zuoyu.manager.widget.ClearEditText newPasswordEdit;
    private CheckBox newEyeImgView;
    private Button sureBtn;

    @Override
    public int setLayout() {
        return R.layout.reset_main;
    }

    @Override
    public void initBeforeLayout() {
    }


    @Override
    public void initTitle() {


        titleManage.setTitleText(getString(R.string.reset_password));

    }


    @Override
    public void initView() {


        newPasswordEdit = (ClearEditText) findViewById(R.id.et_password);
        newPasswordEdit.addTextChangedListener(watcher);


        sureBtn = (Button) findViewById(R.id.btn_sure);
        sureBtn.setOnClickListener(this);
        sureBtn.setEnabled(false);

        newEyeImgView = (CheckBox) findViewById(R.id.iv_newEye);
        newEyeImgView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    //设置EditText文本为可见的
                    newEyeImgView.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //设置EditText文本为隐藏的
                    newEyeImgView.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

                //切换后将EditText光标置于末尾
                CharSequence charSequence = newEyeImgView.getText();
                if (charSequence != null) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());

                }
            }
        });
    }

    @Override
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.btn_sure:
                parseCommitPassword();
                MobclickAgent.onEvent(this, "Forget_OK_Click");
                break;
        }
    }


    @Override
    public void initAfterLayout(Bundle savedInstanceState) {
        MyApplication.addActivity(this);
        MobclickAgent.onEvent(this, "ReplacePassword_Page_Load");
    }


    @Override
    public void close() {

    }


    /**
     * 监听文本变化
     */
    private TextWatcher watcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {

            if (newPasswordEdit.getText().length() < 6 || newPasswordEdit.getText().length() > 10) {
                ToolUtil.setBtnClick(sureBtn, false);
                return;
            }

            ToolUtil.setBtnClick(sureBtn, true);


        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    /**
     * 【解析】提交新密码
     */
    private void parseCommitPassword() {


        Map<String, String> params = new TreeMap<>();
        params.put("uid", "0");

        // 手机号,从忘记密码页传入
        params.put("phone", getIntent().getStringExtra(Constant.PHONE));
        params.put("password", EncryptUtil.getMD5(newPasswordEdit.getText().toString()));//新密码

        httpUtil.post(params, UrlManage.MODIFY_PW_URL, new HttpResult<BaseEntity>() {

            @Override
            public void onLoadCatch(String json) {

            }

            @Override
            public void onSuccess(BaseEntity result) {
                ToastUtil.show(result.getMsg());
                MyApplication.exitApplication(activity, true);
            }

            @Override
            public void onFailed(int errCord, String errMsg) {

            }
        });

    }


}
