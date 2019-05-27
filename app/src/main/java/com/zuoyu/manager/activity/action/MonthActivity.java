package com.zuoyu.manager.activity.action;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.zuoyu.manager.R;
import com.zuoyu.manager.adapter.MonthAdapter;
import com.zuoyu.manager.application.MyApplication;
import com.zuoyu.manager.application.UrlManage;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.entity.MonthEntity;
import com.zuoyu.manager.utils.PullUtil;
import com.zuoyu.manager.utils.SharedUtil;
import com.zuoyu.manager.utils.ToolUtil;
import com.zuoyu.manager.utils.http.HttpResult;
import com.zuoyu.manager.widget.pullable.PullListView;
import com.zuoyu.manager.widget.pullable.PullToRefreshLayout;
import com.zuoyu.manager.widget.search.SearchEditView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.zuoyu.manager.R.id.listView;

/**
 * <pre>
 * Function：月卡管理
 *
 * Created by JoannChen on 2017/3/13 15:07
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class MonthActivity extends BaseActivity {

    private SearchEditView searchEdit;

    private List<MonthEntity.MonthInfo> list = new ArrayList<>();
    private MonthAdapter adapter;
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
        MobclickAgent.onEvent(this, "MonthCards_Page_Load");
    }


    @Override
    public void initTitle() {
        titleManage.setTitleText(getString(R.string.month_manage));
    }

    @Override
    public void initView() {

        LinearLayout searchLLayout = (LinearLayout) findViewById(R.id.ll_search);
        searchLLayout.setBackgroundColor(ContextCompat.getColor(context,R.color.white));

//        搜索框
        searchEdit = (SearchEditView) findViewById(R.id.searchEditView);
        searchEdit.setEditBackground();
        searchEdit.setonEditorAction(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    parseMonthList(pullUtil.getParams());
                    MobclickAgent.onEvent(context, "MonthCards_Search_Click");
                }
                return false;
            }
        });
        searchEdit.setOnCancelBtnListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseMonthList(pullUtil.getParams());
            }
        });


//       下拉刷新／上拉加载
        PullToRefreshLayout refreshLayout = (PullToRefreshLayout) findViewById(R.id.refreshLayout);
        PullListView pullListView = (PullListView) findViewById(R.id.pullListView);
        pullListView.setDividerHeight(2);

        pullUtil = new PullUtil(refreshLayout);
        adapter = new MonthAdapter(context, list);
        pullListView.setAdapter(adapter);
        pullListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setIndex(position);
            }
        });

        pullUtil.setOnLoadListener(new PullUtil.onLoadListener() {
            @Override
            public void onLoad(boolean flag, Map<String, String> params) {
                parseMonthList(params);
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
     * 【解析】获取月卡列表
     */
    private void parseMonthList(Map<String, String> params) {

        params.put("uid", MyApplication.getUserInfo().getUid());
        params.put("parkid", SharedUtil.getString(SharedUtil.PARK_ID));

        if (!ToolUtil.isEmpty(searchEdit.getText())) {
            params.put("plate", searchEdit.getText());// 搜索车牌号
        }


        httpUtil.post(params, UrlManage.MONTH_LIST_URL, new HttpResult<MonthEntity>() {

            @Override
            public void onLoadCatch(String json) {

            }

            @Override
            public void onSuccess(MonthEntity result) {

                pullUtil.setResult(adapter, list, result.getData());

                if (ToolUtil.isEmpty(searchEdit.getText())) {
                    pullUtil.setEmptyView(list,0, getString(R.string.no_month_record));
                } else {
                    pullUtil.setEmptyView(list, 0,getString(R.string.no_find_month));
                }
            }


            @Override
            public void onFailed(int errCord, String errMsg) {

            }
        });

    }

}
