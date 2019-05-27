package com.zuoyu.manager.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.adapter.BusinessCouponsAdapter;
import com.zuoyu.manager.application.UrlManage;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.entity.BusinessCouponsEntity;
import com.zuoyu.manager.utils.IntentUtil;
import com.zuoyu.manager.utils.PullUtil;
import com.zuoyu.manager.utils.SharedUtil;
import com.zuoyu.manager.utils.ToolUtil;
import com.zuoyu.manager.utils.http.HttpResult;
import com.zuoyu.manager.widget.pullable.PullListView;
import com.zuoyu.manager.widget.pullable.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Function：商家优惠券界面
 *
 * Created by JoannChen on 2017/7/14 11:21
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * </pre>
 */
public class BusinessCouponsActivity extends BaseActivity {

    private List<BusinessCouponsEntity.BusinessCoupons.BusinessCouponsInfo> list = new ArrayList<>();
    private BusinessCouponsAdapter adapter;
    private PullUtil pullUtil;

    private TextView balanceText, couponsText;

    @Override
    public int setLayout() {
        return R.layout.business_details_main;
    }

    @Override
    public void initBeforeLayout() {

    }

    @Override
    public void initTitle() {
        titleManage.setTitleText(SharedUtil.getString(SharedUtil.MERCHANT_NAME));
    }

    @Override
    public void initView() {

        // 商家余额
        // 剩余优惠券

        balanceText = (TextView) findViewById(R.id.tv_balance);
        couponsText = (TextView) findViewById(R.id.tv_coupons);

        // 充值记录
        TextView rechargeRecordText = (TextView) findViewById(R.id.tv_recharge_record);
        rechargeRecordText.setOnClickListener(this);

        // 余额使用记录
        TextView balanceRecordText = (TextView) findViewById(R.id.tv_balance_record);
        balanceRecordText.setOnClickListener(this);

        // 优惠券使用记录
        TextView couponsRecordText = (TextView) findViewById(R.id.tv_coupons_record);
        couponsRecordText.setOnClickListener(this);


        // 下拉刷新／上拉加载
        PullToRefreshLayout refreshLayout = (PullToRefreshLayout) findViewById(R.id.refreshLayout);
        PullListView pullListView = (PullListView) findViewById(R.id.pullListView);
        pullListView.setDividerHeight(2);


        pullUtil = new PullUtil(refreshLayout);
        adapter = new BusinessCouponsAdapter(context, list);
        pullListView.setAdapter(adapter);


        pullUtil.setOnLoadListener(new PullUtil.onLoadListener() {
            @Override
            public void onLoad(boolean flag, Map<String, String> params) {
                parseBusiness(params);
            }
        });
    }

    @Override
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.tv_recharge_record:
                IntentUtil.start(activity, RechargeRecordActivity.class, false);
                break;
            case R.id.tv_balance_record:
                IntentUtil.start(activity, BalanceUseActivity.class, false);
                break;
            case R.id.tv_coupons_record:
                IntentUtil.start(activity, CouponsUseActivity.class, false);
                break;
        }
    }

    @Override
    public void initAfterLayout(Bundle savedInstanceState) {

    }

    @Override
    public void close() {

    }

    /**
     * 【解析】获取商家优惠券列表
     */
    public void parseBusiness(Map<String, String> params) {

        params.put("uid", SharedUtil.getString(SharedUtil.USER_ID));//用户uid
        params.put("parkid", SharedUtil.getString(SharedUtil.PARK_ID));//车场id
        params.put("merchant_id", SharedUtil.getString(SharedUtil.MERCHANT_ID));//商户id


        httpUtil.post(params, UrlManage.BUSINESS_INFO_URL, new HttpResult<BusinessCouponsEntity>() {
            @Override
            public void onLoadCatch(String json) {

            }

            @Override
            public void onSuccess(BusinessCouponsEntity result) {


                balanceText.setText(ToolUtil.isEmpty(result.getData().getMttime()) ? "未充值" : result.getData().getMttime());
                couponsText.setText(ToolUtil.isEmpty(result.getData().getDiscount()) ? "" : (result.getData().getDiscount() + "张"));

                pullUtil.setResult(adapter, list, result.getData().getList());
                pullUtil.setEmptyView(list, R.mipmap.icon_no_coupons, getString(R.string.sorry_no_coupons));

            }

            @Override
            public void onFailed(int errCord, String errMsg) {

            }
        });
    }
}
