package com.zuoyu.manager.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.zuoyu.manager.R;
import com.zuoyu.manager.application.Constant;
import com.zuoyu.manager.application.MyApplication;
import com.zuoyu.manager.application.UrlManage;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.base.BaseEntity;
import com.zuoyu.manager.utils.DialogUtil;
import com.zuoyu.manager.utils.IntentUtil;
import com.zuoyu.manager.utils.PermissionUtil;
import com.zuoyu.manager.utils.ToastUtil;
import com.zuoyu.manager.utils.ToolUtil;
import com.zuoyu.manager.utils.http.HttpResult;

import java.util.Map;
import java.util.TreeMap;

/**
 * <pre>
 * Function：
 *
 * Created by JoannChen on 2/22/17 10:17
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class MineActivity extends BaseActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    private Dialog dialog;

    @Override
    public int setLayout() {
        return R.layout.mine_main;
    }

    @Override
    public void initBeforeLayout() {
    }


    @Override
    public void initTitle() {
        titleManage.hideTitle();
        ImageView backImg = (ImageView) findViewById(R.id.iv_back);
        backImg.setOnClickListener(this);
    }

    @Override
    public void initView() {

        TextView usernameText, roleText;
        RelativeLayout modifyRLayout, msgRLayout, contactRLayout, feedbackRLayout;
        Button exitBtn;

//        角色
        roleText = (TextView) findViewById(R.id.tv_role);
        roleText.setText(MyApplication.getUserInfo().getRole_name());

//        账号,隐藏手机号中间四位
        usernameText = (TextView) findViewById(R.id.tv_username);
        usernameText.setText(ToolUtil.isEmpty(MyApplication.getUserInfo().getPhone()) ? "" : ToolUtil.hidePhoneFour(MyApplication.getUserInfo().getPhone()));

//        修改密码
        modifyRLayout = (RelativeLayout) findViewById(R.id.rl_modify);
        modifyRLayout.setOnClickListener(this);

//        我的消息
        msgRLayout = (RelativeLayout) findViewById(R.id.rl_msg);
        msgRLayout.setOnClickListener(this);

//        联系无忧
        contactRLayout = (RelativeLayout) findViewById(R.id.rl_contact);
        contactRLayout.setOnClickListener(this);

//        意见反馈
        feedbackRLayout = (RelativeLayout) findViewById(R.id.rl_feedback);
        feedbackRLayout.setOnClickListener(this);

//        当前版本
        TextView versionText = (TextView) findViewById(R.id.tv_version);
        versionText.setText(("V" + ToolUtil.getVersionName()));

//        退出登录按钮
        exitBtn = (Button) findViewById(R.id.btn_exit);
        exitBtn.setOnClickListener(this);

    }

    @Override
    public void onClickEvent(View v) {

        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_modify:
                IntentUtil.start(this, ModifyActivity.class, false);
                MobclickAgent.onEvent(this, "My_ReplacePassword_Click");
                break;
            case R.id.rl_msg:
                IntentUtil.start(this, MyMsgActivity.class, false);
                MobclickAgent.onEvent(this, "My_MyMessage_Click");
                break;
            case R.id.rl_contact:
                PermissionUtil.requestPermission(this, PermissionUtil.CODE_CALL_PHONE, mPermissionGrant);
                MobclickAgent.onEvent(this, "My_PhoneNum_Click");
                break;
            case R.id.rl_feedback:
                IntentUtil.start(this, FeedbackActivity.class, false);
                MobclickAgent.onEvent(this, "My_FeedBack_Click");
                break;
            case R.id.btn_exit:
                showExitDialog();
                MobclickAgent.onEvent(this, "My_Logout_Click");
                break;
        }

    }

    @Override
    public void initAfterLayout(Bundle savedInstanceState) {
        MyApplication.addActivity(this);
        MobclickAgent.onEvent(this, "My_Page_Load");
    }

    @Override
    public void close() {

    }


    /**
     * 显示退出登录对话框
     */
    private void showExitDialog() {

        final DialogUtil dialog = new DialogUtil(context);
        dialog.setTitle(R.mipmap.icon_exit_dialog);
        dialog.setContent("你确定要退出登录？");
        dialog.setLeftButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setRightButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parseExit();
                dialog.dismiss();
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionUtil.requestPermissionsResult(this, requestCode, permissions, grantResults, mPermissionGrant);
    }


    private PermissionUtil.PermissionGrant mPermissionGrant = new PermissionUtil.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            if (requestCode == PermissionUtil.CODE_CALL_PHONE) {
                try {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + Constant.Company_Tel));
                    startActivity(intent);
                } catch (SecurityException e) {
                    ToastUtil.show("请开启拨打电话权限");
                }
            }
        }
    };

    /**
     * 【解析】退出登录
     */
    private void parseExit() {

        Map<String, String> params = new TreeMap<>();
        params.put("uid", MyApplication.getUserInfo().getUid());

        httpUtil.post(params, UrlManage.EXIT_URL, new HttpResult<BaseEntity>() {

            @Override
            public void onLoadCatch(String json) {

            }

            @Override
            public void onSuccess(BaseEntity result) {

                MyApplication.exitApplication(activity, true);

            }

            @Override
            public void onFailed(int errCord, String errMsg) {

            }
        });


    }

}
