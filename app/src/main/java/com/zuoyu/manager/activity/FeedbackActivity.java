package com.zuoyu.manager.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.zuoyu.manager.R;
import com.zuoyu.manager.application.MyApplication;
import com.zuoyu.manager.application.UrlManage;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.base.BaseEntity;
import com.zuoyu.manager.utils.ToastUtil;
import com.zuoyu.manager.utils.ToolUtil;
import com.zuoyu.manager.utils.http.HttpResult;

import java.util.Map;
import java.util.TreeMap;

import static com.zuoyu.manager.R.id.et_input;

/**
 * <pre>
 * Function：意见反馈
 *
 * Created by JoannChen on 2017/2/23 11:03
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class FeedbackActivity extends BaseActivity {

    private TextView numText;
    private EditText inputEdit;
    private Button submitBtn;


    @Override
    public int setLayout() {
        return R.layout.feedback_main;
    }

    @Override
    public void initBeforeLayout() {
    }

    @Override
    public void initAfterLayout(Bundle savedInstanceState) {
        MyApplication.addActivity(this);
        MobclickAgent.onEvent(this, "FeedBack_Page_Load");
    }


    @Override
    public void initTitle() {
        titleManage.setTitleText(getString(R.string.feedback));
    }

    @Override
    public void initView() {

//        输入框
        inputEdit = (EditText) findViewById(et_input);
        inputEdit.addTextChangedListener(watcher);

//        字数
        numText = (TextView) findViewById(R.id.tv_num);

        submitBtn = (Button) findViewById(R.id.btn_sure);
        submitBtn.setOnClickListener(this);
        submitBtn.setEnabled(false);
    }


    @Override
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.btn_sure:
                parseSubmitFeedback();
                MobclickAgent.onEvent(this, "FeedBack_Commit_Click");
                break;
        }
    }

    @Override
    public void close() {

    }

    private TextWatcher watcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            if (inputEdit.getText().length() == 0) {
                ToolUtil.setBtnClick(submitBtn, false);
            } else {
                ToolUtil.setBtnClick(submitBtn, true);
            }

            String num = (200 - charSequence.length()) + "字";
            numText.setText(num);

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    /**
     * 【解析】提交意见反馈
     */
    private void parseSubmitFeedback() {

        Map<String, String> params = new TreeMap<>();
        params.put("uid", MyApplication.getUserInfo().getUid());//用户id
        params.put("content", inputEdit.getText().toString());//意见反馈内容

        httpUtil.post(params, UrlManage.FEEDBACK_URL, new HttpResult<BaseEntity>() {

            @Override
            public void onLoadCatch(String json) {

            }

            @Override
            public void onSuccess(BaseEntity result) {
                ToastUtil.show("感谢您的反馈！");
                finish();
            }

            @Override
            public void onFailed(int errCord, String errMsg) {

            }
        });

    }
}
