package com.zuoyu.manager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.adapter.UtilizationAdapter;
import com.zuoyu.manager.application.MyApplication;
import com.zuoyu.manager.application.UrlManage;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.entity.UtilizationEntity;
import com.zuoyu.manager.utils.DateUtil;
import com.zuoyu.manager.utils.SharedUtil;
import com.zuoyu.manager.utils.http.HttpResult;

import java.util.Map;
import java.util.TreeMap;

/**
 * <pre>
 * Function：流量分析——车位利用率子页面
 *
 * Created by JoannChen on 2017/5/24 17:24
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class UtilizationChildFragment extends Fragment {

    private int day;

    private ListView listView;
    private UtilizationAdapter adapter;

    public void setDay(int day) {
        this.day = day;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.child_fragment, container, false);

        listView = (ListView) view.findViewById(R.id.listView);
        listView.setDivider(null);

        parseAnalysisPlace();

        return view;
    }

    private void parseAnalysisPlace() {

        Map<String, String> params = new TreeMap<>();
        params.put("uid", MyApplication.getUserInfo().getUid());
        params.put("parkid", SharedUtil.getString(SharedUtil.PARK_ID));

        String url = UrlManage.FLOW_ANALYSIS_PLACE_URL;

        if (day != 0) {
            params.put("startdate", DateUtil.getFetureDate(day));
            params.put("enddate", DateUtil.getDate());
            url = UrlManage.FLOW_ANALYSIS_USE_URL;
        }

        ((BaseActivity) getActivity()).httpUtil.post(params, url, new HttpResult<UtilizationEntity>() {
            @Override
            public void onLoadCatch(String json) {

            }

            @Override
            public void onSuccess(UtilizationEntity result) {

                ((UtilizationFragment) getParentFragment()).setUtilizationEntity(result, day);

                int size;

                if (day != 0) {
                    adapter = new UtilizationAdapter(result.getData().getUnilizationList(), getContext());
                    adapter.setFlag(false);
                } else {
                    adapter = new UtilizationAdapter(result.getData().getOccupyList(), getContext());
                    adapter.setFlag(true);
                }

                listView.setAdapter(adapter);
            }

            @Override
            public void onFailed(int errCord, String errMsg) {

            }
        });
    }


}
