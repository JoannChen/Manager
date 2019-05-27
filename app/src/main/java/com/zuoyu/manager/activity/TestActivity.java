package com.zuoyu.manager.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.adapter.ChoiceParkAdapter;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.entity.ChoiceParkEntity;
import com.zuoyu.manager.utils.IntentUtil;
import com.zuoyu.manager.utils.PullUtil;
import com.zuoyu.manager.utils.SharedUtil;
import com.zuoyu.manager.widget.pullable.PullListView;
import com.zuoyu.manager.widget.pullable.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 加载数据封装
 * Created by ZhangYe on 2017/1/18.
 */

public class TestActivity extends BaseActivity {

    private List<ChoiceParkEntity.ParkInfo> list = new ArrayList<>();
    private ChoiceParkAdapter adapter;
    private PullUtil pullUtil;

    @Override
    public int setLayout() {
        return R.layout.layout_pull;
    }

    @Override
    public void initBeforeLayout() {

    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initView() {

//
////        ListView数据为空时处理
//        RelativeLayout dataNullRLayout = (RelativeLayout) findViewById(R.id.rl_data_null);
//        TextView dataNullText = (TextView) findViewById(R.id.tv_data_null);
//        dataNullText.setText(getString(R.string.no_add_park));
//
//        ListView listView = (ListView) findViewById(R.id.listView);
//        listView.setEmptyView(dataNullRLayout);



        // 下拉刷新／上拉加载
        PullToRefreshLayout refreshLayout = (PullToRefreshLayout) findViewById(R.id.refreshLayout);
        PullListView pullListView = (PullListView) findViewById(R.id.pullListView);
        pullListView.setDividerHeight(2);


        pullUtil = new PullUtil(refreshLayout);
        adapter = new ChoiceParkAdapter(context, list);
        pullListView.setAdapter(adapter);
        pullListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                SharedUtil.setString(SharedUtil.PARK_ID, list.get(position).getParkid());
                SharedUtil.setString(SharedUtil.PARK_NAME, list.get(position).getPark_name());
                IntentUtil.start(activity, HomeActivity.class, true);

            }
        });


        pullUtil.setOnLoadListener(new PullUtil.onLoadListener() {
            @Override
            public void onLoad(boolean flag, Map<String, String> params) {
//                parseParkList(params);
            }
        });




    }


    @Override
    public void onClickEvent(View v) {

    }

    @Override
    public void initAfterLayout(Bundle savedInstanceState) {

    }

    @Override
    public void close() {

    }
}
