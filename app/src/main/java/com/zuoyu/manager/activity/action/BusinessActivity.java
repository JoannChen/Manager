package com.zuoyu.manager.activity.action;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.TextView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.activity.BusinessCouponsActivity;
import com.zuoyu.manager.adapter.BusinessAdapter;
import com.zuoyu.manager.application.UrlManage;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.entity.BusinessEntity;
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
 * Function：商家管理界面
 *
 * Created by JoannChen on 2017/7/14 11:21
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * </pre>
 */
public class BusinessActivity extends BaseActivity {

    private List<BusinessEntity.Business.BusinessInfo> list = new ArrayList<>();
    private BusinessAdapter adapter;
    private PullUtil pullUtil;

    private SearchEditView searchEdit;
    private TextView businessText, couponsText, rechargeText;

    @Override
    public int setLayout() {
        return R.layout.business_main;
    }

    @Override
    public void initBeforeLayout() {

    }

    @Override
    public void initTitle() {
        titleManage.setTitleText("商家管理");
    }

    @Override
    public void initView() {

        // 商家总数
        // 优惠券总数
        // 充值总数
        businessText = (TextView) findViewById(R.id.tv_business);
        couponsText = (TextView) findViewById(R.id.tv_coupons);
        rechargeText = (TextView) findViewById(R.id.tv_recharge);

        // 搜索框
        searchEdit = (SearchEditView) findViewById(R.id.searchEditView);
        searchEdit.setEditBackground();
        searchEdit.setEditHint(getString(R.string.search_business_name));
        searchEdit.setonEditorAction(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    parseBusiness(pullUtil.getParams());
                }

                return false;
            }
        });

        searchEdit.setOnCancelBtnListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parseBusiness(pullUtil.getParams());

            }
        });

        // 下拉刷新／上拉加载
        PullToRefreshLayout refreshLayout = (PullToRefreshLayout) findViewById(R.id.refreshLayout);
        PullListView pullListView = (PullListView) findViewById(R.id.pullListView);
        pullListView.setDividerHeight(2);


        pullUtil = new PullUtil(refreshLayout);
        adapter = new BusinessAdapter(context, list);
        pullListView.setAdapter(adapter);
        pullListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                SharedUtil.setString(SharedUtil.MERCHANT_ID, list.get(position).getMerchant_id());
                SharedUtil.setString(SharedUtil.MERCHANT_NAME, list.get(position).getName());
                IntentUtil.start(activity, BusinessCouponsActivity.class, false);

            }
        });


        pullUtil.setOnLoadListener(new PullUtil.onLoadListener() {
            @Override
            public void onLoad(boolean flag, Map<String, String> params) {
                parseBusiness(params);
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
     * 【解析】获取商家管理列表
     */
    public void parseBusiness(Map<String, String> params) {

        params.put("uid", SharedUtil.getString(SharedUtil.USER_ID));//用户uid
        params.put("parkid", SharedUtil.getString(SharedUtil.PARK_ID));//车场id

        if (!ToolUtil.isEmpty(searchEdit.getText())) {
            params.put("name", searchEdit.getText().trim());//商户名称
        }

        httpUtil.post(params, UrlManage.BUSINESS_LIST_URL, new HttpResult<BusinessEntity>() {
            @Override
            public void onLoadCatch(String json) {

            }

            @Override
            public void onSuccess(BusinessEntity result) {

                pullUtil.setResult(adapter, list, result.getData().getList());
                pullUtil.setEmptyView(list, R.mipmap.icon_no_coupons, getString(R.string.sorry_no_business));

                businessText.setText(result.getData().getMtcount());
                couponsText.setText(result.getData().getDiscount());
                rechargeText.setText(result.getData().getHour());


            }

            @Override
            public void onFailed(int errCord, String errMsg) {

            }
        });
    }
}
