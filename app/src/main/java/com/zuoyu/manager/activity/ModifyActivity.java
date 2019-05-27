package com.zuoyu.manager.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.umeng.analytics.MobclickAgent;
import com.zuoyu.manager.R;
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
 * Function：修改密码
 *
 * Created by JoannChen on 2017/3/3 17:10
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class ModifyActivity extends BaseActivity {


    private ClearEditText oldPasswordEdit, newPasswordEdit;
    private Button sureBtn;
    CheckBox oldEyeImgView, newEyeImgView;

    @Override
    public int setLayout() {
        return R.layout.modify_main;
    }

    @Override
    public void initBeforeLayout() {
    }


    @Override
    public void initTitle() {


        titleManage.setTitleText(getString(R.string.modify_password));

    }


    @Override
    public void initView() {

        oldPasswordEdit = (ClearEditText) findViewById(R.id.et_old_password);
        oldPasswordEdit.addTextChangedListener(watcher);

        newPasswordEdit = (ClearEditText) findViewById(R.id.et_password);
        newPasswordEdit.addTextChangedListener(watcher);

        oldEyeImgView = (CheckBox) findViewById(R.id.iv_oldEye);
        newEyeImgView = (CheckBox) findViewById(R.id.iv_newEye);
        ToolUtil.isShowPassword(oldEyeImgView, oldPasswordEdit);
        ToolUtil.isShowPassword(newEyeImgView, newPasswordEdit);

        sureBtn = (Button) findViewById(R.id.btn_sure);
        sureBtn.setOnClickListener(this);
        sureBtn.setEnabled(false);


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

            if (oldPasswordEdit.getText().length() < 6 || oldPasswordEdit.getText().length() > 10) {
                ToolUtil.setBtnClick(sureBtn, false);
                return;
            } else if (newPasswordEdit.getText().length() < 6 || newPasswordEdit.getText().length() > 10) {
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
        params.put("uid", MyApplication.getUserInfo().getUid());

        // 手机号,从忘记密码页传入
        params.put("phone", MyApplication.getUserInfo().getPhone());
        params.put("oldpwd", EncryptUtil.getMD5(oldPasswordEdit.getText().toString()));//旧密码
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
