package com.zuoyu.manager.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.zuoyu.manager.R;
import com.zuoyu.manager.adapter.ChoiceParkAdapter;
import com.zuoyu.manager.application.Constant;
import com.zuoyu.manager.application.MyApplication;
import com.zuoyu.manager.application.UrlManage;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.entity.ChoiceParkEntity;
import com.zuoyu.manager.entity.ChoiceParkEntity.ParkInfo;
import com.zuoyu.manager.utils.IntentUtil;
import com.zuoyu.manager.utils.PullUtil;
import com.zuoyu.manager.utils.SharedUtil;
import com.zuoyu.manager.utils.ToolUtil;
import com.zuoyu.manager.utils.http.HttpResult;
import com.zuoyu.manager.widget.pullable.PullListView;
import com.zuoyu.manager.widget.pullable.PullToRefreshLayout;
import com.zuoyu.manager.widget.search.SearchEditView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Function：选择停车场
 *
 * Created by JoannChen on 2017/3/2 10:29
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class ChoiceParkActivity extends BaseActivity {

    private boolean isFromLogin;

    private SearchEditView searchEdit;

    private List<ParkInfo> list = new ArrayList<>();
    private ChoiceParkAdapter adapter;
    private PullUtil pullUtil;

    @Override
    public int setLayout() {
        return R.layout.search_main;
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

        isFromLogin = getIntent().getBooleanExtra(Constant.IS_FROM_LOGIN, true);

        // 从登录页进入隐藏左侧按钮，首页进入显示叉号按钮
        titleManage.setLeftBtn(isFromLogin ? 0 : R.mipmap.btn_delete);
        titleManage.setTitleText(getString(R.string.choice_park));
        MobclickAgent.onEvent(this, "ChoosePark_Page_Load");

    }

    @Override
    public void initView() {

        LinearLayout searchLLayout = (LinearLayout) findViewById(R.id.ll_search);
        searchLLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.white));

        // 搜索框
        searchEdit = (SearchEditView) findViewById(R.id.searchEditView);
        searchEdit.addTextChangedListener(watcher);
        searchEdit.setEditBackground();
        searchEdit.setEditHint(getString(R.string.search_park));
        searchEdit.setonEditorAction(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    parseParkList(pullUtil.getParams());
                }

                return false;
            }
        });

        searchEdit.setOnCancelBtnListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parseParkList(pullUtil.getParams());

            }
        });

        // 下拉刷新／上拉加载
        PullToRefreshLayout refreshLayout = (PullToRefreshLayout) findViewById(R.id.refreshLayout);
        PullListView listView = (PullListView) findViewById(R.id.pullListView);
        listView.setDividerHeight(2);


        pullUtil = new PullUtil(refreshLayout);
        adapter = new ChoiceParkAdapter(context, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                SharedUtil.setString(SharedUtil.PARK_ID, list.get(position).getParkid());
                SharedUtil.setString(SharedUtil.PARK_NAME, list.get(position).getPark_name());


                IntentUtil.start(activity, HomeActivity.class, true);
                EventBus.getDefault().post("home");

            }
        });


        pullUtil.setOnLoadListener(new PullUtil.onLoadListener() {
            @Override
            public void onLoad(boolean flag, Map<String, String> params) {
                parseParkList(params);
            }
        });


    }

    @Override
    public void onClickEvent(View v) {

    }

    @Override
    public void close() {

    }


    /**
     * 监听文本内容
     */
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
//            parseParkList(pullUtil.getParams());
            MobclickAgent.onEvent(context, "ChoosePark_Search_Click");
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    /**
     * 【解析】获取停车场列表
     */
    private void parseParkList(Map<String, String> params) {


        params.put("uid", SharedUtil.getString(SharedUtil.USER_ID));

        if (!ToolUtil.isEmpty(searchEdit.getText())) {
            params.put("park_name", searchEdit.getText().trim());// 搜索停车场名称
        }


        httpUtil.post(params, UrlManage.PARK_LIST_URL, new HttpResult<ChoiceParkEntity>() {

            @Override
            public void onLoadCatch(String json) {

            }

            @Override
            public void onSuccess(ChoiceParkEntity result) {

                if (isFromLogin && result.getData().size() == 1) {

                    SharedUtil.setString(SharedUtil.PARK_ID, result.getData().get(0).getParkid());
                    SharedUtil.setString(SharedUtil.PARK_NAME, result.getData().get(0).getPark_name());
                    IntentUtil.start(activity, HomeActivity.class, true);

                    return;
                }

                pullUtil.setResult(adapter, list, result.getData());
                if (ToolUtil.isEmpty(searchEdit.getText())) {
                    pullUtil.setEmptyView(list, 0, getString(R.string.no_add_park));
                } else {
                    pullUtil.setEmptyView(list, 0, getString(R.string.no_find_park));
                }

            }

            @Override
            public void onFailed(int errCord, String errMsg) {

            }
        });

    }


//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        if (getIntent().getBooleanExtra(Constant.IS_FROM_LOGIN, true)) {
//
//            if (keyCode == KeyEvent.KEYCODE_BACK) {
//                ToastUtil.show("请选择停车场");
//                return true;
//            }
//        }
//        return super.onKeyDown(keyCode, event);
//    }


}
