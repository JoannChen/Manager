package com.zuoyu.manager.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.zuoyu.manager.R;
import com.zuoyu.manager.application.Constant;
import com.zuoyu.manager.application.MyApplication;
import com.zuoyu.manager.application.UrlManage;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.entity.LoginEntity;
import com.zuoyu.manager.utils.EncryptUtil;
import com.zuoyu.manager.utils.IntentUtil;
import com.zuoyu.manager.utils.LogUtil;
import com.zuoyu.manager.utils.SharedUtil;
import com.zuoyu.manager.utils.ToastUtil;
import com.zuoyu.manager.utils.ToolUtil;
import com.zuoyu.manager.utils.http.HttpResult;
import com.zuoyu.manager.widget.ClearEditText;

import java.util.Map;
import java.util.TreeMap;

import static com.zuoyu.manager.R.id.tv_forget;

/**
 * <pre>
 * Function：登录
 *
 * Created by JoannChen on 17/1/20 17:31
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class LoginActivity extends BaseActivity {

    // 手机号／密码输入框
    private EditText usernameEdit, passwordEdit;

    // 登录按钮
    private Button loginBtn;
    private String imei = "";

    @Override
    public int setLayout() {
        return R.layout.login_main;
    }

    @Override
    public void initBeforeLayout() {
    }

    @Override
    public void initAfterLayout(Bundle savedInstanceState) {
        MyApplication.addActivity(this);
        MobclickAgent.onEvent(this, "Login_Page_Load");
    }


    @Override
    public void initTitle() {
        titleManage.hideTitle();
    }

    @Override
    public void initView() {

        // 账号输入框
        usernameEdit = (ClearEditText) findViewById(R.id.et_username);
        usernameEdit.addTextChangedListener(watcher);

        // 密码输入框
        passwordEdit = (ClearEditText) findViewById(R.id.et_password);
        passwordEdit.addTextChangedListener(watcher);

        // 登录按钮
        loginBtn = (Button) findViewById(R.id.btn_login);
        loginBtn.setOnClickListener(this);
//        loginBtn.setEnabled(false);

        // 忘记密码
        TextView forgerText = (TextView) findViewById(tv_forget);
        forgerText.setOnClickListener(this);

        // 小眼睛
        CheckBox eyeCBox = (CheckBox) findViewById(R.id.iv_eye);
        ToolUtil.isShowPassword(eyeCBox, passwordEdit);
    }

    @Override
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                parseLogin();
                MobclickAgent.onProfileSignIn("userID");
                MobclickAgent.onEvent(this, "Login_Login_Click");
                break;
            case R.id.tv_forget:
                IntentUtil.start(activity, ForgetActivity.class, false);
                MobclickAgent.onEvent(this, "Login_ForgetPassword_Click");
                break;
        }
    }


    @Override
    public void close() {

    }


    /**
     * 监听文本变化
     */
    private TextWatcher watcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

            if (passwordEdit.hasFocus()) {
                if (start < 6) {
                    ToastUtil.show(getString(R.string.please_input_password_length));
                }
            }

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {

            // 交互：当手机号和验证码均不为空时，且手机号长度为11位，登录按钮高亮显示
            if (usernameEdit.getText().length() == 0
                    || passwordEdit.getText().length() < 6) {
                ToolUtil.setBtnClick(loginBtn, false);
            } else {
                ToolUtil.setBtnClick(loginBtn, true);
            }

        }


        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    /**
     * 获取设备唯一标识
     *
     * @return IMEI
     */
    public String getImei() {

        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
                    imei = telephonyManager.getDeviceId();

                }
            }
        } catch (NullPointerException e) {
            LogUtil.e(e.getMessage());
        }
        return imei;
    }

    /**
     * 【解析】用户登录
     */
    private void parseLogin() {

        Map<String, String> params = new TreeMap<>();
        params.put("request", "1");// 1:android，2:ios
        params.put("phone", usernameEdit.getText().toString());// 账户
        params.put("password", EncryptUtil.getMD5(passwordEdit.getText().toString()));// 密码
        params.put("type", "3");// 1:用户端 2:收费员端 3:管家端
        if (!ToolUtil.isEmpty(getImei())) {
            params.put("registration", getImei());// 设备唯一标识
        } else {
            params.put("registration", usernameEdit.getText().toString());// 设备唯一标识
        }

        httpUtil.post(params, UrlManage.LOGIN_URL, new HttpResult<LoginEntity>() {

            @Override
            public void onLoadCatch(String json) {

            }

            @Override
            public void onSuccess(LoginEntity result) {


                // 保存用户信息
                MyApplication.setUserInfo(result.getData());

                // 保存登录状态 , 用户Uid , token
                SharedUtil.setBoolean(SharedUtil.IS_LOGIN, true);
                SharedUtil.setString(SharedUtil.TOKEN, result.getData().getToken());
                SharedUtil.setString(SharedUtil.USER_ID, result.getData().getUid());

                IntentUtil.start(activity, ChoiceParkActivity.class, Constant.IS_FROM_LOGIN, true, true);


            }

            @Override
            public void onFailed(int errCord, String errMsg) {
                ToastUtil.show(errMsg);
            }
        });
    }

}


