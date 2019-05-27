package com.zuoyu.manager.activity.action;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.zuoyu.manager.R;
import com.zuoyu.manager.activity.OrderDetailsActivity;
import com.zuoyu.manager.adapter.OrderAdapter;
import com.zuoyu.manager.application.Constant;
import com.zuoyu.manager.application.MyApplication;
import com.zuoyu.manager.application.UrlManage;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.entity.OrderEntity;
import com.zuoyu.manager.fragment.OrderFragment;
import com.zuoyu.manager.utils.IntentUtil;
import com.zuoyu.manager.utils.PullUtil;
import com.zuoyu.manager.utils.SharedUtil;
import com.zuoyu.manager.utils.ToolUtil;
import com.zuoyu.manager.utils.ViewSetting;
import com.zuoyu.manager.utils.http.HttpResult;
import com.zuoyu.manager.widget.pullable.PullListView;
import com.zuoyu.manager.widget.pullable.PullToRefreshLayout;
import com.zuoyu.manager.widget.search.SearchEditView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * <pre>
 * Function：订单记录
 *
 * Created by JoannChen on 2017/2/27 15:15
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 * </pre>
 */
public class OrderActivity extends BaseActivity {

    private SearchEditView searchEdit;

    private DrawerLayout mDrawerLayout;
    private FrameLayout mDrawerContent;

    private List<OrderEntity.OrderInfo> list = new ArrayList<>();
    private OrderAdapter adapter;
    private PullUtil pullUtil;

    private String date;
    private String payType;
    private String carType;


    @Override
    public int setLayout() {
        return R.layout.order_main;
    }

    @Override
    public void initBeforeLayout() {
    }


    @Override
    public void initTitle() {
        titleManage.setTitleText(getString(R.string.order_record));
//        titleManage.setRightBtn(R.mipmap.icon_order_choice);
        titleManage.setLeftBtnOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedUtil.setEmptyFilter();
                finish();
            }
        });
//        titleManage.setRightBtnOnClick(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mDrawerLayout.openDrawer(mDrawerContent);
//                MobclickAgent.onEvent(context, "Orders_Filer_Click");
//            }
//        });
    }

    @Override
    public void initView() {


//        搜索框
        searchEdit = (SearchEditView) findViewById(R.id.searchEditView);
        searchEdit.setEditBackground();
        searchEdit.setEditHint("输入车牌号，订单号");
        searchEdit.setonEditorAction(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    parseOrderList(pullUtil.getParams());
                    MobclickAgent.onEvent(context, "Orders_Search_Click");
                }

                return false;
            }
        });
        searchEdit.setOnCancelBtnListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parseOrderList(pullUtil.getParams());

            }
        });

        // DrawerLayout布局／DrawerLayout容器
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerContent = (FrameLayout) findViewById(R.id.drawer_content);

        //关闭手势滑动
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);


//        //打开手势滑动
//        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

        ViewSetting.setViewSize(mDrawerContent, 0, 550);


        Fragment fragment = new OrderFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.drawer_content, fragment).commit();

        // 下拉刷新／上拉加载
        PullToRefreshLayout refreshLayout = (PullToRefreshLayout) findViewById(R.id.refreshLayout);
        PullListView pullListView = (PullListView) findViewById(R.id.pullListView);
        pullListView.setDividerHeight(22);
        pullListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                IntentUtil.start(activity, OrderDetailsActivity.class, Constant.ORDER_ID, list.get(position).getOrderid(), false);
                MobclickAgent.onEvent(context, "OrdersDetail_Page_Load");
            }
        });

        pullUtil = new PullUtil(refreshLayout);
        adapter = new OrderAdapter(context, list);
        pullListView.setAdapter(adapter);

        pullUtil.setOnLoadListener(new PullUtil.onLoadListener() {
            @Override
            public void onLoad(boolean flag, Map<String, String> params) {
                parseOrderList(params);
            }
        });

    }


    @Override
    public void onClickEvent(View v) {

    }


    @Override
    public void initAfterLayout(Bundle savedInstanceState) {
        MyApplication.addActivity(this);
        MobclickAgent.onEvent(context, "Orders_Page_Load");
    }

    @Override
    public void close() {
        SharedUtil.setEmptyFilter();
    }


    /**
     * 【解析】获取订单记录列表
     */
    private void parseOrderList(Map<String, String> params) {

        params.put("uid", MyApplication.getUserInfo().getUid());
        params.put("parkid", SharedUtil.getString(SharedUtil.PARK_ID));

        // 搜索功能
        if (!ToolUtil.isEmpty(searchEdit.getText())) {
            params.put("plate", searchEdit.getText().trim());// 车牌号
            params.put("orderid", searchEdit.getText().trim());// 订单号
        }

        getFilter();

        if (!ToolUtil.isEmpty(date)) {
            params.put("order_date", date);
        }

        if (!ToolUtil.isEmpty(payType)) {
            params.put("pay_type", payType);
        }

        if (!ToolUtil.isEmpty(carType)) {
            params.put("type", carType);
        }


        httpUtil.post(params, UrlManage.ORDER_LIST_URL, new HttpResult<OrderEntity>() {

            @Override
            public void onLoadCatch(String json) {

            }

            @Override
            public void onSuccess(OrderEntity result) {

                pullUtil.setResult(adapter, list, result.getData());
                if (ToolUtil.isEmpty(searchEdit.getText())) {
                    pullUtil.setEmptyView(list, 0, getString(R.string.no_order_record));
                } else {
                    pullUtil.setEmptyView(list, 0, getString(R.string.no_find_car));
                }

//                if (result.getData().size() == 0) {
//                    titleManage.setRightBtnOnClick(false);
//                } else {
//                    titleManage.setRightBtnOnClick(true);
//                }


            }


            @Override
            public void onFailed(int errCord, String errMsg) {

            }
        });
    }


    /**
     * 筛选条件
     */
    public void parseFilter() {

        parseOrderList(pullUtil.getParams());
    }


    /**
     * set筛选条件
     *
     * @param date    日期（默认为当天）
     * @param payType 支付方式 1:支付宝，2:微信，3:现金，4:其他
     * @param carType 车辆类型 1：临停，2：月卡
     */
    public void setFilter(String date, String payType, String carType) {
        SharedUtil.setString(SharedUtil.FILTER_DATE, date);
        SharedUtil.setString(SharedUtil.FILTER_PAY_TYPE, payType);
        SharedUtil.setString(SharedUtil.FILTER_CAR_TYPE, carType);
    }


    /**
     * get筛选条件
     */
    public void getFilter() {
        this.date = SharedUtil.getString(SharedUtil.FILTER_DATE);
        this.payType = SharedUtil.getString(SharedUtil.FILTER_PAY_TYPE);
        this.carType = SharedUtil.getString(SharedUtil.FILTER_CAR_TYPE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            SharedUtil.setEmptyFilter();
        }
        return super.onKeyDown(keyCode, event);
    }


}
