package com.zuoyu.manager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.adapter.UnpaidAdapter;
import com.zuoyu.manager.application.MyApplication;
import com.zuoyu.manager.application.UrlManage;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.entity.UnpaidEntity;
import com.zuoyu.manager.utils.PullUtil;
import com.zuoyu.manager.utils.SharedUtil;
import com.zuoyu.manager.utils.http.HttpResult;
import com.zuoyu.manager.widget.pullable.PullListView;
import com.zuoyu.manager.widget.pullable.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.zuoyu.manager.R.id.listView;


/**
 * <pre>
 * Function：未支付记录
 *
 * Created by JoannChen on 2017/3/1 16:48
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class UnpaidFragment extends Fragment {

    private BaseActivity baseActivity;

    private List<UnpaidEntity.UnpaidInfo> list = new ArrayList<>();
    private UnpaidAdapter adapter;
    private PullUtil pullUtil;
    private String key; //搜索关键字

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_pull, container, false);

        baseActivity = (BaseActivity) getActivity();


        // 下拉刷新／上拉加载
        PullToRefreshLayout refreshLayout = (PullToRefreshLayout) view.findViewById(R.id.refreshLayout);
        PullListView pullListView = (PullListView) view.findViewById(R.id.pullListView);
        pullListView.setDividerHeight(22);
        pullUtil = new PullUtil(refreshLayout);
        adapter = new UnpaidAdapter(getContext(), list);
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
                parseGetUnpaidList(params);
            }
        });

        return view;
    }


    /**
     * 【解析】未支付记录列表
     */
    private void parseGetUnpaidList(Map<String, String> params) {
        if(params == null){
            params = new TreeMap<>();
            params.put("page","1");
            params.put("per_page","15");
        }
        params.put("uid", MyApplication.getUserInfo().getUid());
        params.put("parkid", SharedUtil.getString(SharedUtil.PARK_ID));

        if(key != null){
            params.put("plate", key);
        }

        baseActivity.httpUtil.post(params, UrlManage.UNPAID_URL, new HttpResult<UnpaidEntity>() {

            @Override
            public void onLoadCatch(String json) {

            }

            @Override
            public void onSuccess(UnpaidEntity result) {

                pullUtil.setResult(adapter, list, result.getData());
                pullUtil.setEmptyView(list,0,getString(R.string.no_unpaid_record));

            }

            @Override
            public void onFailed(int errCord, String errMsg) {

            }
        });

    }

    public void setKey(String key,boolean flag) {
        if("".equals(key)){
            this.key = null;
        }else{
            this.key = key;
        }
        list.clear();

        if(flag){
            parseGetUnpaidList(null);
        }
    }
}
