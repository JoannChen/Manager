package com.zuoyu.manager.activity.action;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;

import com.umeng.analytics.MobclickAgent;
import com.zuoyu.manager.R;
import com.zuoyu.manager.adapter.CalendarAdapter;
import com.zuoyu.manager.application.MyApplication;
import com.zuoyu.manager.application.UrlManage;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.entity.CalendarEntity;
import com.zuoyu.manager.utils.DateUtil;
import com.zuoyu.manager.utils.SharedUtil;
import com.zuoyu.manager.utils.ToastUtil;
import com.zuoyu.manager.utils.http.HttpResult;
import com.zuoyu.manager.widget.pullable.PullListView;
import com.zuoyu.manager.widget.pullable.PullToRefreshLayout;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * <pre>
 * Function：报表查看界面
 *
 * Created by JoannChen on 2017/2/27 16:29
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class ReportActivity extends BaseActivity {

    private CalendarAdapter dap;
    private PullListView pullListView;
    private PullToRefreshLayout refreshLayout;
    private int defaultYear = 2016;
    private int year;
    private List<CalendarEntity.CalendarInfo> list;

    @Override
    public int setLayout() {
        return R.layout.calendar_main;
    }

    @Override
    public void initBeforeLayout() {

    }


    @Override
    public void initTitle() {
        titleManage.setTitleText(getString(R.string.report_view));
    }

    @Override
    public void initView() {

        year = DateUtil.getYear();

        refreshLayout =  findViewById(R.id.refreshLayout);
        pullListView =  findViewById(R.id.pullListView);
        pullListView.setDividerHeight(22);
        pullListView.isUpLoad = true;

        pullListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    pullListView.mIsScroll = true;
                    dap.notifyDataSetChanged();
                } else {
                    pullListView.mIsScroll = false;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });


        refreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                year--;
                if (year < defaultYear) {
                    refreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                    ToastUtil.show("目前只能查看2016年之后的报表");
                    return;
                }

                parseGetCalendar(true);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {

            }
        });

    }

    @Override
    public void onClickEvent(View v) {

    }


    @Override
    public void initAfterLayout(Bundle savedInstanceState) {
        MyApplication.addActivity(this);
        MobclickAgent.onEvent(context, "Report_Page_Load");

        parseGetCalendar(false);
    }


    @Override
    public void close() {

    }


    private void parseGetCalendar(final boolean isRefreshLastYear) {

        Map<String, String> params = new TreeMap<>();

        params.put("uid", MyApplication.getUserInfo().getUid());
        params.put("parkid", SharedUtil.getString(SharedUtil.PARK_ID));
        params.put("year", year + "");


        httpUtil.post(params, UrlManage.MONTHS_COUNT_URL, new HttpResult<CalendarEntity>() {
            @Override
            public void onLoadCatch(String json) {

            }

            @Override
            public void onSuccess(CalendarEntity result) {


                if (isRefreshLastYear) {
                    result.getData().addAll(list);
                    list = result.getData();
                    dap = new CalendarAdapter(context, list);
                    pullListView.setAdapter(dap);
                    pullListView.setSelection(11);
                } else {
                    list = result.getData();
                    dap = new CalendarAdapter(context, list);
                    pullListView.setAdapter(dap);
                    pullListView.setSelection(dap.getCount() - 1);

                }

                refreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            }

            @Override
            public void onFailed(int errCord, String errMsg) {
                refreshLayout.refreshFinish(PullToRefreshLayout.FAIL);
            }
        });
    }

}
