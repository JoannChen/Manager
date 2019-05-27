package com.zuoyu.manager.activity.action;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.zuoyu.manager.R;
import com.zuoyu.manager.activity.ParkingDetailsActivity;
import com.zuoyu.manager.adapter.ParkingAdapter;
import com.zuoyu.manager.application.Constant;
import com.zuoyu.manager.application.MyApplication;
import com.zuoyu.manager.application.UrlManage;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.entity.ParkingEntity;
import com.zuoyu.manager.entity.ParkingEntity.ParkingInfo;
import com.zuoyu.manager.utils.IntentUtil;
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


/**
 * <pre>
 * Function：在场车辆
 *
 * Created by JoannChen on 2017/2/28 17:47
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class ParkingActivity extends BaseActivity {

    private SearchEditView searchEdit;

    private List<ParkingInfo> list = new ArrayList<>();
    private ParkingAdapter adapter;
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
        MobclickAgent.onEvent(this, "Cars_Page_Load");

    }

    @Override
    public void initTitle() {
        titleManage.setTitleText(getString(R.string.parking));
    }

    @Override
    public void initView() {

        // 搜索框
        searchEdit = (SearchEditView) findViewById(R.id.searchEditView);
        searchEdit.setEditBackground();
        searchEdit.setonEditorAction(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    parseParkingList(pullUtil.getParams());
                    MobclickAgent.onEvent(context, "Cars_Search_Click");
                }

                return false;
            }
        });

        // 取消按钮
        searchEdit.setOnCancelBtnListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parseParkingList(pullUtil.getParams());
            }
        });

//        下拉刷新／上拉加载
        PullToRefreshLayout refreshLayout = (PullToRefreshLayout) findViewById(R.id.refreshLayout);
        final PullListView pullListView = (PullListView) findViewById(R.id.pullListView);
        pullListView.setDividerHeight(2);

        pullUtil = new PullUtil(refreshLayout);
        adapter = new ParkingAdapter(context, list);
        pullListView.setAdapter(adapter);
        pullListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                IntentUtil.start(activity, ParkingDetailsActivity.class, Constant.ORDER_ID, list.get(position).getOrderid(), false);
                MobclickAgent.onEvent(context, "CarsDetail_Page_Load");

            }
        });

        pullUtil.setOnLoadListener(new PullUtil.onLoadListener() {
            @Override
            public void onLoad(boolean flag, Map<String, String> params) {
                parseParkingList(params);
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
     * 【解析】获取在场车辆列表
     */
    private void parseParkingList(Map<String, String> params) {

        params.put("uid", MyApplication.getUserInfo().getUid());
        params.put("parkid", SharedUtil.getString(SharedUtil.PARK_ID));

        if (!ToolUtil.isEmpty(searchEdit.getText())) {
            params.put("plate", searchEdit.getText());// 搜索车牌号
        }

        httpUtil.post(params, UrlManage.IN_PARK_LIST_URL, new HttpResult<ParkingEntity>() {

            @Override
            public void onLoadCatch(String json) {

            }

            @Override
            public void onSuccess(ParkingEntity result) {

                pullUtil.setResult(adapter, list, result.getData());
                if (ToolUtil.isEmpty(searchEdit.getText())) {
                    pullUtil.setEmptyView(list, 0, getString(R.string.no_parking_car));
                } else {
                    pullUtil.setEmptyView(list, 0, getString(R.string.no_find_car));
                }
            }


            @Override
            public void onFailed(int errCord, String errMsg) {

            }
        });

    }

}
