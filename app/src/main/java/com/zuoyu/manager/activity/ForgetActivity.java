package com.zuoyu.manager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.zuoyu.manager.R;
import com.zuoyu.manager.application.Constant;
import com.zuoyu.manager.application.MyApplication;
import com.zuoyu.manager.application.UrlManage;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.entity.Verify;
import com.zuoyu.manager.utils.DateUtil;
import com.zuoyu.manager.utils.LogUtil;
import com.zuoyu.manager.utils.ToastUtil;
import com.zuoyu.manager.utils.ToolUtil;
import com.zuoyu.manager.utils.http.HttpResult;
import com.zuoyu.manager.widget.ClearEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/**
 * <pre>
 * Function：忘记密码
 *
 * Created by JoannChen on 17/2/6 10:48
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class ForgetActivity extends BaseActivity {

    private String phone;
    private ClearEditText phoneEdit, verifyEdit;
    private TextView verifyText;
    private Button nextBtn;

    //  服务器返回的验证码和创建时间
    private String verifyCode;
    private String createTime;

    private TimerCount timer;


    @Override
    public int setLayout() {
        return R.layout.forget_main;
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
        titleManage.setTitleText(getString(R.string.forget_password));

        MyApplication.addActivity(this);
        MobclickAgent.onEvent(this, "Forget_Page_First_Load");
    }

    @Override
    public void initView() {

        timer = new TimerCount();

        //  手机号输入框
        phoneEdit = (ClearEditText) findViewById(R.id.et_username);
        phoneEdit.addTextChangedListener(watcher);

        //  验证码输入框
        verifyEdit = (ClearEditText) findViewById(R.id.et_verify);
        verifyEdit.addTextChangedListener(watcher);

        //  获取验证码,手机号未输入时置灰
        verifyText = (TextView) findViewById(R.id.tv_verify);
        verifyText.setOnClickListener(this);
        verifyText.setEnabled(false);

        //  下一步按钮,手机号和验证码为空时置灰
        nextBtn = (Button) findViewById(R.id.btn_next);
        nextBtn.setOnClickListener(this);
        nextBtn.setEnabled(false);


    }

    @Override
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.tv_verify:
                parseGetVerifyCode();
                MobclickAgent.onEvent(this, "Forget_GetVerifyCode_Click");
                break;
            case R.id.btn_next:
                if (isValidCode()) {
                    Intent intent = new Intent(this, ResetActivity.class);
                    intent.putExtra(Constant.PHONE, phone);
                    startActivity(intent);
                }
                MobclickAgent.onEvent(this, "Forget_Page_Second_Load");
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
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {

            // 交互：当手机号和验证码均不为空时，登录按钮高亮显示
            if (phoneEdit.getText().length() == 0
                    || verifyEdit.getText().length() == 0) {
                ToolUtil.setBtnClick(nextBtn, false);
            } else {
                ToolUtil.setBtnClick(nextBtn, true);
            }

            // 交互：当手机号输入完成时，获取验证码高亮显示
            if (phoneEdit.getText().length() == 11) {
                verifyText.setTextColor(ContextCompat.getColor(context,
                        R.color.red_font));
                verifyText.setEnabled(true);
            } else {
                verifyText.setTextColor(ContextCompat.getColor(context,
                        R.color.gray_font));
                verifyText.setEnabled(false);
            }


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
     * 发送验证码
     */
    private class TimerCount extends CountDownTimer {

        private TimerCount() {
            super(60 * 1000, 1000);
        }

       /* private TimerCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }*/

        @Override
        public void onTick(long millisUntilFinished) {
            String timer = getString(R.string.get_again) + "（" + millisUntilFinished / 1000 + "s）";
            verifyText.setText(timer);
            verifyText.setClickable(false);
        }

        @Override
        public void onFinish() {
            verifyText.setText(getString(R.string.get_verify_again));
            verifyText.setClickable(true);

        }

    }

    /**
     * 检验验证码是否有效
     * 1。和服务器返回的一致
     * 2。验证码尚在有效期
     */
    private boolean isValidCode() {

        try {

            // 时间差
            long difference;

            // 验证码有效期30分钟（毫秒数）
            long valid = 30 * 60 * 1000;

            // 当前时间
            String currentTime = DateUtil.getDateTime();

            // 将String类型转换成Date类型
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

            // 如果未点击获取验证码按钮，直接填写的时候，createTime为空
            if (ToolUtil.isEmpty(createTime)) {
                ToastUtil.show("请先获取验证码");
                return false;
            }

            Date createDate = format.parse(createTime);
            Date currentDate = format.parse(currentTime);

            // 获取时间差（距离1970年01月01日8点的毫秒数）
            difference = currentDate.getTime() - createDate.getTime();

            LogUtil.e("时间差：" + (difference < valid ? "true" : "false"));


            if (difference > valid) {

                ToastUtil.show("验证码已过期");
                return false;
            }

            if (!verifyEdit.getText().toString().endsWith(verifyCode)) {

                ToastUtil.show("验证码有误");
               return false;
            }

            if (!phone.endsWith(phoneEdit.getText().toString())) {
                ToastUtil.show("手机号和验证码不匹配");
                return false;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


        return true;
    }


    /**
     * 【解析】获取验证码
     */
    private void parseGetVerifyCode() {


        //  校验手机号格式，错误震动提示
        if (!ToolUtil.isMobileNum(phoneEdit.getText().toString())) {
            phoneEdit.setShakeAnimation();
            ToastUtil.show("手机号格式错误！");
            return;
        }

        // 手机号格式正确时，验证码输入框自动获取焦点
        verifyEdit.setFocusable(true);
        verifyEdit.setFocusableInTouchMode(true);
        verifyEdit.requestFocus();
        verifyEdit.findFocus();

        Map<String, String> params = new TreeMap<>();
        params.put("phone", phoneEdit.getText().toString());

        httpUtil.post(params, UrlManage.VERIFY_CODE_URL, new HttpResult<Verify>() {
            @Override
            public void onLoadCatch(String json) {

            }

            @Override
            public void onSuccess(Verify result) {

                phone = phoneEdit.getText().toString();

                LogUtil.e("success：" + phone);

                timer.start();

                verifyCode = result.getData().getVerify();
                createTime = result.getData().getCreatetime();
                LogUtil.e(result.getData().getVerify());
            }


            @Override
            public void onFailed(int errCord, String errMsg) {

            }
        });
    }


}
