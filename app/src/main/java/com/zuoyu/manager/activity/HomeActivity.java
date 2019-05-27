package com.zuoyu.manager.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.zuoyu.manager.R;
import com.zuoyu.manager.activity.action.CheckoutActivity;
import com.zuoyu.manager.activity.action.FlowAnalysisActivity;
import com.zuoyu.manager.adapter.HomeAdapter;
import com.zuoyu.manager.application.Constant;
import com.zuoyu.manager.application.MyApplication;
import com.zuoyu.manager.application.UrlManage;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.entity.HomeEntity;
import com.zuoyu.manager.entity.LoginEntity;
import com.zuoyu.manager.entity.UpdateEntity;
import com.zuoyu.manager.utils.ClickUtil;
import com.zuoyu.manager.utils.DialogUtil;
import com.zuoyu.manager.utils.IntentUtil;
import com.zuoyu.manager.utils.LogUtil;
import com.zuoyu.manager.utils.SharedUtil;
import com.zuoyu.manager.utils.ToastUtil;
import com.zuoyu.manager.utils.ToolUtil;
import com.zuoyu.manager.utils.http.HttpResult;
import com.zuoyu.manager.widget.MyGridView;
import com.zuoyu.manager.widget.hellochart.PieChartUtil;
import com.zuoyu.manager.widget.pullable.PullToRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import lecho.lib.hellocharts.view.PieChartView;


/**
 * <pre>
 * Function：首页 功能模块
 *
 * Created by JoannChen on 2017/2/23 16:29
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class HomeActivity extends BaseActivity {

    public static final int MIN_CLICK_DELAY_TIME = 2000;
    private long mLastClickTime = 0;// 上次点击的时间

    private TextView incomeText;
    private float aliRate, wxRate, cashRate, etcRate;// 支付宝占比 // 微信占比 // 现金占比 //ETC占比
    private TextView shouldText, discountText;
    private TextView turnoverText, utilizationText;


    private MyGridView gridView;
    private HomeAdapter adapter;
    private List<HomeEntity.HomeInfo.Power> list;

    private PieChartUtil pieChartUtil;

    private PullToRefreshLayout pullToRefreshLayout;

    @Override
    public int setLayout() {
        return R.layout.home_main;
    }


    @Override
    public void initBeforeLayout() {
    }

    @Override
    public void initTitle() {

        titleManage.setTitleText(ToolUtil.isEmpty(SharedUtil.getString(SharedUtil.PARK_NAME)) ? getString(R.string.app_name) : SharedUtil.getString(SharedUtil.PARK_NAME));
        titleManage.setLeftBtn(R.mipmap.icon_home_mine);
        titleManage.setRightBtn(R.mipmap.icon_home_park);
        titleManage.setTitleOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 选择停车场
                IntentUtil.start(activity, ChoiceParkActivity.class, Constant.IS_FROM_LOGIN, false, false);
                MobclickAgent.onEvent(context, "Home_Switch_Click");
            }
        });
        titleManage.setLeftBtnOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 个人中心
                IntentUtil.start(activity, MineActivity.class, false);
                MobclickAgent.onEvent(context, "Home_User_Click");
            }
        });
        titleManage.setRightBtnOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 车场详情
                IntentUtil.start(activity, ParkInfoActivity.class, false);
                MobclickAgent.onEvent(context, "Home_Info_Click");
            }
        });


        MobclickAgent.onEvent(this, "Home_Page_Load");

    }

    @Override
    public void initView() {

        EventBus.getDefault().register(this);

        pullToRefreshLayout = (PullToRefreshLayout) findViewById(R.id.refreshLayout);

        // 饼状图
        PieChartView pieChartView = (PieChartView) findViewById(R.id.pieChartView);
        pieChartView.setOnClickListener(this);
        pieChartUtil = new PieChartUtil(pieChartView);

        // 今日收入
        incomeText = (TextView) findViewById(R.id.tv_income);
        incomeText.setOnClickListener(this);
        incomeText.setEnabled(false);


        // 应收金额
        shouldText = (TextView) findViewById(R.id.tv_should);
        shouldText.setOnClickListener(this);
        shouldText.setEnabled(false);

        // 优惠金额
        discountText = (TextView) findViewById(R.id.tv_discount);
        discountText.setOnClickListener(this);
        discountText.setEnabled(false);

        // 周转率
        turnoverText = (TextView) findViewById(R.id.tv_turnover);
        turnoverText.setOnClickListener(this);
        turnoverText.setEnabled(false);

        // 利用率
        utilizationText = (TextView) findViewById(R.id.tv_utilization);
        utilizationText.setOnClickListener(this);
        utilizationText.setEnabled(false);

        // 底线
        TextView baselineText = (TextView) findViewById(R.id.tv_baseline);
        baselineText.setText(getResources().getString(R.string.baseline));

        gridView = (MyGridView) findViewById(R.id.gridView);

        ClickUtil clickUtil = new ClickUtil(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Intent it = new Intent();
                    String classPath = Constant.PACKAGE_NAME + list.get(position).getAction();
                    it.setClass(activity, Class.forName(classPath));
                    startActivity(it);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });

        clickUtil.onItemClick(gridView);


        pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                parseGetUserInfo();
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {

            }
        });


    }

    @Override
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.tv_income:
                IntentUtil.start(activity, CheckoutActivity.class, false);
                MobclickAgent.onEvent(context, "Home_TodayIncome_Click");
                break;
            case R.id.tv_should:
                IntentUtil.start(activity, CheckoutActivity.class, false);
                MobclickAgent.onEvent(context, "Home_IncomeAll_Click");
                break;
            case R.id.tv_discount:
                IntentUtil.start(activity, CheckoutActivity.class, false);
                MobclickAgent.onEvent(context, "Home_IncomeFree_Click");
                break;
            case R.id.tv_turnover:
                IntentUtil.start(activity, FlowAnalysisActivity.class, Constant.CHART_TAG, "周转率", false);
                MobclickAgent.onEvent(context, "Home_YesterdayTurnsRate_Click");
                break;
            case R.id.tv_utilization:
                IntentUtil.start(activity, FlowAnalysisActivity.class, Constant.CHART_TAG, "利用率", false);
                MobclickAgent.onEvent(context, "Home_YesterdayUsageRate_Click");
                break;
        }
    }

    @Override
    public void close() {
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void initAfterLayout(Bundle savedInstanceState) {

        MyApplication.addActivity(this);

        parseGetUserInfo();

    }

    @Override
    protected void onResume() {
        super.onResume();
        showUpdate();

    }

    /**
     * 显示更新提示
     */
    private void showUpdate() {

        //如果 updateTime 为空，则 updateTime = currentTime
        long difference = 24 * 60 * 60 * 1000;
        long currentTime = System.currentTimeMillis();
        long updateTime = ToolUtil.isEmpty(SharedUtil.getString(SharedUtil.UPDATE_TIME)) ? currentTime : Long.parseLong(SharedUtil.getString(SharedUtil.UPDATE_TIME));



        /*
         * 首次进入应用提示更新
         * updateTime == 0 （第一次提示更新）
         * currentTime - updateTime >= difference  (24小时后再次更新)
         */
        if (SharedUtil.getBoolean(SharedUtil.IS_UPDATE, true) || (currentTime - updateTime >= difference)) {
            parseUpdate();
        }

        LogUtil.e("【---------------】currentTime:" + currentTime);
        LogUtil.e("【---------------】updateTime:" + updateTime);
        LogUtil.e("【---------------】isUpdate:" + SharedUtil.getBoolean(SharedUtil.IS_UPDATE));
        LogUtil.e("【---------------】difference:" + difference);
        LogUtil.e("【---------------】difference:" + (currentTime - updateTime));
    }


    /**
     * 【解析】获取首页信息
     */
    private void parseGetHomeInfo() {

        Map<String, String> params = new TreeMap<>();
        params.put("uid", MyApplication.getUserInfo().getUid());
        params.put("parkid", SharedUtil.getString(SharedUtil.PARK_ID));


        httpUtil.post(params, UrlManage.PARK_INDEX_URL, new HttpResult<HomeEntity>() {

            @Override
            public void onLoadCatch(String json) {

            }

            @Override
            public void onSuccess(HomeEntity result) {

                wxRate = result.getData().getWx_count();
                aliRate = result.getData().getAli_count();
                cashRate = result.getData().getXj_count();
                etcRate = result.getData().getEtc_count();
                if (wxRate == 0f && aliRate == 0f && cashRate == 0f && etcRate == 0f) {
                    pieChartUtil.setValue(0, 0, 1, 0);
                } else {
                    pieChartUtil.setValue(wxRate, aliRate, cashRate, etcRate);
                }


                incomeText.setText((getString(R.string.rmb) + result.getData().getToday_earn()));
                /*titleManage.setTitleText(result.getData().getPark_name());*/

                shouldText.setText((getString(R.string.rmb) + result.getData().getReceivable()));
                discountText.setText((getString(R.string.rmb) + result.getData().getDiscount()));

                turnoverText.setText(result.getData().getTurnover());
                utilizationText.setText(result.getData().getUtilization_hour());


                list = result.getData().getNodelist();
                adapter = new HomeAdapter(context, list);
                gridView.setAdapter(adapter);

                // 禁止点击事件
                incomeText.setEnabled(false);
                shouldText.setEnabled(false);
                discountText.setEnabled(false);
                turnoverText.setEnabled(false);
                utilizationText.setEnabled(false);

                // 如果有该权限，则进行点击跳转
                for (int i = 0, size = list.size(); i < size; i++) {
                    if ("CheckoutActivity".endsWith(list.get(i).getAction())) {
                        incomeText.setEnabled(true);
                        shouldText.setEnabled(true);
                        discountText.setEnabled(true);
                    } else if ("FlowAnalysisActivity".endsWith(list.get(i).getAction())) {
                        turnoverText.setEnabled(true);
                        utilizationText.setEnabled(true);
                    }
                }


            }

            @Override
            public void onFailed(int errCord, String errMsg) {

            }
        });

    }


    /**
     * 【解析】初始化用户信息
     */
    private void parseGetUserInfo() {

        Map<String, String> params = new TreeMap<>();
        params.put("uid", SharedUtil.getString(SharedUtil.USER_ID));

        httpUtil.post(params, UrlManage.GET_USER_INFO_URL, new HttpResult<LoginEntity>() {


            @Override
            public void onLoadCatch(String json) {

            }

            @Override
            public void onSuccess(LoginEntity result) {
                MyApplication.setUserInfo(result.getData());
                parseGetHomeInfo();

                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            }

            @Override
            public void onFailed(int errCord, String errMsg) {
                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.FAIL);
            }
        });

    }


    /**
     * 【解析】自动更新提示
     */
    private void parseUpdate() {

        LogUtil.e("当前版本：" + ToolUtil.getVersionName());


        final String appId = Constant.APP_ID; // 应用id（APPID_AN）
        final String appVersion = "V" + ToolUtil.getVersionName();// 版本号

        Map<String, String> params = new TreeMap<>();
        params.put("uid", SharedUtil.getString(SharedUtil.USER_ID));
        params.put("appid", appId);
        params.put("c_version", appVersion);

        httpUtil.post(params, UrlManage.IS_UPDATE_URL, new HttpResult<UpdateEntity>() {

            @Override
            public void onLoadCatch(String json) {

            }

            @Override
            public void onSuccess(final UpdateEntity result) {

                // 校验AppId是否和服务器一致
                if (appId.endsWith(result.getData().getAppid())) {

                    // 判断本地版本号和服务器最新的版本号是否一致 ? 不做更新提示 ： 更新提示
                    if (!appVersion.endsWith(result.getData().getN_version())) {

                        // 判断当前本地版本是否在线（0 下线 1 在线） ？ 提示更新 ： 强制更新
                        if (result.getData().getIs_online() == 1) {
                            LogUtil.e("当前版本在线");
                            final DialogUtil dialog = new DialogUtil(context);
                            dialog.setTitle("新版本上线了！");
//                            dialog.setContent("你不试试么？");
                            dialog.setContent(result.getData().getIllustrate());
                            dialog.setGravity(Gravity.START);
                            dialog.setLeftButton("忽略", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //开始计时，24 小时后再次提醒
                                    SharedUtil.setString(SharedUtil.UPDATE_TIME, System.currentTimeMillis() + "");
                                    SharedUtil.setBoolean(SharedUtil.IS_UPDATE, false);
                                    dialog.dismiss();
                                }
                            });
                            dialog.setRightButton("马上尝试", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    // 将计时置空
                                    SharedUtil.setString(SharedUtil.UPDATE_TIME, "");
                                    SharedUtil.setBoolean(SharedUtil.IS_UPDATE, true);

                                    // 下载最新安卓包
                                    Uri uri = Uri.parse(result.getData().getUrl());
                                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(intent);

                                    dialog.dismiss();
                                }
                            });
                        } else {
                            LogUtil.e("当前版本已下线");
                            final DialogUtil dialog = new DialogUtil(context);
                            dialog.setTitle("发现新版本！");
//                            dialog.setContent("无忧停车管家又带了新惊喜，快来更新吧～");
                            dialog.setContent(result.getData().getIllustrate());
                            dialog.setCancelable(false);
                            dialog.setRightButton("体验惊喜", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    // 下载最新安卓包
                                    Uri uri = Uri.parse(result.getData().getUrl());
                                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(intent);

                                    dialog.dismiss();
                                }
                            });

                        }
                    } else {
                        LogUtil.e("当前版本已经是最新啦！");
                    }

                } else {
                    LogUtil.e("AppId验证失败");
                }


            }

            @Override
            public void onFailed(int errCord, String errMsg) {

            }
        });


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                if ((System.currentTimeMillis() - mLastClickTime) > MIN_CLICK_DELAY_TIME) {
                    ToastUtil.show("再按一次退出程序");
                    mLastClickTime = System.currentTimeMillis();
                } else {
                    finish();
                    return false;
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {
        if (event.equals("home")) {
            parseGetUserInfo();
            titleManage.setTitleText(
                    ToolUtil.isEmpty(SharedUtil.getString(SharedUtil.PARK_NAME)) ? getString(R.string.app_name) : SharedUtil.getString(SharedUtil.PARK_NAME));

        }
    }

}
