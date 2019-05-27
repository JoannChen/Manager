package com.zuoyu.manager.activity;

import android.os.Bundle;
import android.view.View;

import com.zuoyu.manager.R;
import com.zuoyu.manager.adapter.CouponsSendAdapter;
import com.zuoyu.manager.application.UrlManage;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.entity.CouponsSendEntity;
import com.zuoyu.manager.utils.PullUtil;
import com.zuoyu.manager.utils.SharedUtil;
import com.zuoyu.manager.utils.http.HttpResult;
import com.zuoyu.manager.widget.pullable.PullListView;
import com.zuoyu.manager.widget.pullable.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Function：优惠券使用记录页面
 *
 * Created by JoannChen on 2017/7/19 15:57
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * </pre>
 */
public class CouponsUseActivity extends BaseActivity {


    private List<CouponsSendEntity.CouponsInfo> list = new ArrayList<>();
    private CouponsSendAdapter adapter;
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
        titleManage.setTitleText("优惠券使用记录");
    }

    @Override
    public void initView() {

        /*
         * 下拉刷新／上拉加载
         */
        PullToRefreshLayout refreshLayout = (PullToRefreshLayout) findViewById(R.id.refreshLayout);
        PullListView pullListView = (PullListView) findViewById(R.id.pullListView);
        pullListView.setDividerHeight(22);

        pullUtil = new PullUtil(refreshLayout);
        adapter = new CouponsSendAdapter(context, list);
        pullListView.setAdapter(adapter);

        pullUtil.setOnLoadListener(new PullUtil.onLoadListener() {
            @Override
            public void onLoad(boolean flag, Map<String, String> params) {
                parseCouponsSendRecord(params);
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

    /**
     * 【解析】获取优惠发放记录
     */
    public void parseCouponsSendRecord(Map<String, String> params) {

        params.put("uid", SharedUtil.getString(SharedUtil.USER_ID));//用户uid
        params.put("parkid", SharedUtil.getString(SharedUtil.PARK_ID));//车场id
        params.put("merchant_id", SharedUtil.getString(SharedUtil.MERCHANT_ID));//商户id

        httpUtil.post(params, UrlManage.COUPONS_SEND_URL, new HttpResult<CouponsSendEntity>() {
            @Override
            public void onLoadCatch(String json) {

            }

            @Override
            public void onSuccess(CouponsSendEntity result) {


                pullUtil.setResult(adapter, list, result.getData());
                pullUtil.setEmptyView(list, R.mipmap.icon_no_coupons, getString(R.string.sorry_no_coupons_record));

            }

            @Override
            public void onFailed(int errCord, String errMsg) {

            }
        });
    }
}
