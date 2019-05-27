package com.zuoyu.manager.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.zuoyu.manager.R;
import com.zuoyu.manager.application.Constant;
import com.zuoyu.manager.application.MyApplication;
import com.zuoyu.manager.application.UrlManage;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.entity.ChildOrderEntity;
import com.zuoyu.manager.entity.ChildOrderEntity.ChildOrder.ChildInfo;
import com.zuoyu.manager.utils.http.HttpResult;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * <pre>
 * Function：子订单明细
 *
 * Created by JoannChen on 2017/3/9 10:41
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class ChildOrderActivity extends BaseActivity {

    private String mOrderId;
    /*
    private TextView nameText, priceText, plateText, longText;
    */

    private List<ChildInfo> list;
    private LinearLayout containerLLayout;

    @Override
    public int setLayout() {
        return R.layout.child_main;
    }

    @Override
    public void initBeforeLayout() {
    }


    @Override
    public void initTitle() {

        titleManage.setTitleText(getString(R.string.transaction));
    }

    @Override
    public void initView() {

        mOrderId = getIntent().getStringExtra(Constant.ORDER_ID);


        /*
        nameText = (TextView) findViewById(R.id.tv_name);
        priceText = (TextView) findViewById(R.id.tv_price);
        plateText = (TextView) findViewById(R.id.tv_plate);
        longText = (TextView) findViewById(R.id.tv_long);
        */

        containerLLayout = (LinearLayout) findViewById(R.id.ll_container);


    }

    @Override
    public void onClickEvent(View v) {

    }

    @Override
    public void initAfterLayout(Bundle savedInstanceState) {
        MyApplication.addActivity(this);
        MobclickAgent.onEvent(context, "OrdersDetail_SonOrder_Page_Load");
        MobclickAgent.onEvent(context, "CarsDetail_SonOrder_Page_Load");

        parseChildOrder();
    }

    @Override
    public void close() {

    }

    private void showChildOrder() {

        for (int i = 0; i < list.size(); i++) {

            RelativeLayout rl = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.child_item, containerLLayout, false);

            ((TextView) rl.findViewById(R.id.tv_orderNum)).setText(list.get(i).getChild_orderid());
            ((TextView) rl.findViewById(R.id.tv_payTime)).setText(list.get(i).getPay_time());

           /*
            ((TextView) rl.findViewById(R.id.tv_payingPark)).setText(list.get(i).getPay_park_long());
            */

            ((TextView) rl.findViewById(R.id.tv_orderMoney)).setText((getString(R.string.rmb) + list.get(i).getMoney()));
            ((TextView) rl.findViewById(R.id.tv_discountMoney)).setText((getString(R.string.rmb) + list.get(i).getDiscount()));
            ((TextView) rl.findViewById(R.id.tv_actualPay)).setText((getString(R.string.rmb) + list.get(i).getActual_pay()));
            ((TextView) rl.findViewById(R.id.tv_platform)).setText((list.get(i).getPay_channel()));

            ((TextView) rl.findViewById(R.id.tv_payType)).setText(getPayType(list.get(i).getPay_type()));


            containerLLayout.addView(rl);
        }

    }

    /**
     * 获取支付方式 1:支付宝，2:微信，3:现金，other：其他
     *
     * @param payType 支付方式
     * @return 支付方式（文本）
     */
    private String getPayType(int payType) {

        switch (payType) {
            case 1:
                payType = R.string.alipay;
                break;
            case 2:
                payType = R.string.wechat;
                break;
            case 3:
                payType = R.string.cash;
                break;
            default:
                payType = R.string.other;
        }

        return getString(payType);
    }


    /**
     * 【解析】获取子订单列表
     */
    private void parseChildOrder() {

        Map<String, String> params = new TreeMap<>();
        params.put("uid", MyApplication.getUserInfo().getUid());
        params.put("orderid", mOrderId);// 父订单id

        httpUtil.post(params, UrlManage.PARK_RECORD_URL, new HttpResult<ChildOrderEntity>() {

            @Override
            public void onLoadCatch(String json) {

            }

            @Override
            public void onSuccess(ChildOrderEntity result) {

               /*
                nameText.setText(result.getData().getPark_name());
                priceText.setText((getString(R.string.rmb) + result.getData().getPrice()));
                plateText.setText(result.getData().getPlate());
                longText.setText(result.getData().getPark_long());
                */

                list = result.getData().getOrderlist();

                showChildOrder();


            }

            @Override
            public void onFailed(int errCord, String errMsg) {

            }
        });

    }
}
