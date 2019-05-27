package com.zuoyu.manager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.adapter.PaymentAdapter;
import com.zuoyu.manager.application.MyApplication;
import com.zuoyu.manager.application.UrlManage;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.entity.PaymentEntity;
import com.zuoyu.manager.utils.DateUtil;
import com.zuoyu.manager.utils.SharedUtil;
import com.zuoyu.manager.utils.http.HttpResult;

import java.util.Map;
import java.util.TreeMap;

/**
 * <pre>
 * Function：财务分析——支付方式子页面
 *
 * Created by JoannChen on 2017/5/24 17:24
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class PaymentChildFragment extends Fragment {
    private int day = 7;

    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.child_fragment, container, false);

        listView = (ListView) view.findViewById(R.id.listView);

        parseAnalysisPayType();

        return view;
    }


    /**
     * 【解析】近一周支付方式统计
     */
    private void parseAnalysisPayType() {

        Map<String, String> params = new TreeMap<>();
        params.put("uid", MyApplication.getUserInfo().getUid());
        params.put("parkid", SharedUtil.getString(SharedUtil.PARK_ID));
        params.put("startdate", DateUtil.getFetureDate(day));
        params.put("enddate", DateUtil.getDate());

        ((BaseActivity) getActivity()).httpUtil.post(params, UrlManage.NEW_FCANALYSIS_PW_URL, new HttpResult<PaymentEntity>() {
            @Override
            public void onLoadCatch(String json) {

            }

            @Override
            public void onSuccess(PaymentEntity result) {

                ((PaymentFragment) getParentFragment()).setPaymentEntity(result);

                PaymentAdapter adapter = new PaymentAdapter(result.getData().getPaylist(), getContext());
                listView.setAdapter(adapter);

            }

            @Override
            public void onFailed(int errCord, String errMsg) {

            }
        });
    }

    public void setDay(int day) {
        this.day = day;
    }
}
