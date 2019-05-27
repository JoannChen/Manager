package com.zuoyu.manager.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.zuoyu.manager.R;
import com.zuoyu.manager.application.Constant;
import com.zuoyu.manager.application.MyApplication;
import com.zuoyu.manager.application.UrlManage;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.entity.DetailsEntity;
import com.zuoyu.manager.utils.IntentUtil;
import com.zuoyu.manager.utils.LogUtil;
import com.zuoyu.manager.utils.ToolUtil;
import com.zuoyu.manager.utils.http.HttpResult;
import com.zuoyu.manager.widget.BigImageDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * <pre>
 * Function：订单记录详情
 *
 * Created by JoannChen on 2017/2/27 10:43
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class OrderDetailsActivity extends BaseActivity {

    /**
     * 订单id,Intent传值,订单记录/在场车辆页面传入
     */
    private String mOrderId;

    // 车场名称
    // 停车费用
    // 车牌号
    // 停车时长（如02:25:12）

    private TextView nameText, priceText, plateText, longText;

    // 订单详情
    private TextView orderNumText, tickedIdText, inTimeText, outTimeText, discountText, actualPayText;

    // VIP出入场车辆
    private TextView inTimeText2, outTimeText2;

    /**
     * 查看子订单明细，仅在支付方式为多次支付时显示
     * 支付时停车，有子订单时不显示
     */
    private TextView childOrderText;

    // 查看图片集合
    private List<String> imageUrlList = new ArrayList<>();

    @Override
    public int setLayout() {
        return R.layout.order_details_main;
    }

    @Override
    public void initBeforeLayout() {
    }


    @Override
    public void initTitle() {

        mOrderId = getIntent().getStringExtra(Constant.ORDER_ID);

        titleManage.setTitleText(getString(R.string.order_details));
    }

    @Override
    public void initView() {


        nameText = (TextView) findViewById(R.id.tv_name);
        priceText = (TextView) findViewById(R.id.tv_price);
        plateText = (TextView) findViewById(R.id.tv_plate);
        longText = (TextView) findViewById(R.id.tv_long);

        // 订单内容
        orderNumText = (TextView) findViewById(R.id.tv_orderNum);
        tickedIdText = (TextView) findViewById(R.id.tv_tickedId);
        inTimeText = (TextView) findViewById(R.id.tv_inTime);
        outTimeText = (TextView) findViewById(R.id.tv_outTime);
        discountText = (TextView) findViewById(R.id.tv_discount);
        actualPayText = (TextView) findViewById(R.id.tv_actualPay);

        // VIP出入场车辆
        inTimeText2 = (TextView) findViewById(R.id.tv_inTime2);
        outTimeText2 = (TextView) findViewById(R.id.tv_outTime2);

        // 查看子订单明细，仅在支付方式为多次支付时显示
        childOrderText = (TextView) findViewById(R.id.tv_more);
        childOrderText.setOnClickListener(this);


        // 查看图片
        TextView seeImageText = (TextView) findViewById(R.id.tv_image);
        seeImageText.setOnClickListener(this);

        findViewById(R.id.divisionView).setVisibility(View.GONE);

    }

    @Override
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.tv_more:
                IntentUtil.start(activity, ChildOrderActivity.class, Constant.ORDER_ID, mOrderId, false);
                MobclickAgent.onEvent(context, "OrdersDetail_SonOrder_Click");
                break;
            case R.id.tv_image:
                if (imageUrlList.size() != 0) {
                    BigImageDialog dialog = new BigImageDialog(context);
                    dialog.setList(imageUrlList);
                    dialog.show();
                } else {
                    Dialog dialog = new Dialog(context, R.style.Dialog_Theme);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCanceledOnTouchOutside(true);
                    dialog.setContentView(R.layout.layout_no_image_dialog);
                    dialog.show();
                    WindowManager windowManager = getWindowManager();
                    Display display = windowManager.getDefaultDisplay();
                    WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
                    lp.width = display.getWidth(); //设置宽度
                    dialog.getWindow().setAttributes(lp);
                }
                break;
        }
    }


    @Override
    public void initAfterLayout(Bundle savedInstanceState) {
        MyApplication.addActivity(this);
        parseDetails();
    }

    @Override
    public void close() {

    }


    /**
     * 【解析】获取在场车辆／订单记录详情信息
     */
    public void parseDetails() {

        Map<String, String> params = new TreeMap<>();
        params.put("uid", MyApplication.getUserInfo().getUid());
        params.put("orderid", mOrderId);// 订单id

        httpUtil.post(params, UrlManage.DETAILS_URL, new HttpResult<DetailsEntity>() {
            @Override
            public void onLoadCatch(String json) {

            }

            @Override
            public void onSuccess(DetailsEntity result) {


                nameText.setText(result.getData().getPark_name());

                String price = getString(R.string.rmb) + result.getData().getPrice();
                priceText.setText(price);

                plateText.setText(result.getData().getPlate());
                longText.setText(result.getData().getPark_long());


                /*
                 * 订单状态 1：未支付，2：已支付，3：已超时，4：VIP
                 *
                 * 未支付／vip；不显示订单信息和超时缴费布局
                 * 已支付：判断有无子订单
                 */

                int status = result.getData().getStatus();


                if (status == 2 || status == 3) {
                    findViewById(R.id.ic_childOrder).setVisibility(View.VISIBLE);

                    // 订单内容
                    orderNumText.setText(result.getData().getOrderid());
                    tickedIdText.setText(result.getData().getTicket_id());
                    inTimeText.setText(result.getData().getEnter_time());
                    outTimeText.setText(result.getData().getExit_time());
                    discountText.setText((getString(R.string.rmb) + result.getData().getDiscount()));
                    actualPayText.setText((getString(R.string.rmb) + result.getData().getActual_pay()));

                    // 实际金额大于0时，显示查看交易流水
                    if (Float.parseFloat(result.getData().getActual_pay()) > 0) {
                        childOrderText.setVisibility(View.VISIBLE);
                    }
                }

                if (status == 4) {
                    findViewById(R.id.rl_inOutTime).setVisibility(View.VISIBLE);

                    inTimeText2.setText(result.getData().getEnter_time());
                    outTimeText2.setText(result.getData().getExit_time());
                }

                // 查看图片

                if (!ToolUtil.isEmpty(result.getData().getEnter_image())) {
                    imageUrlList.add(result.getData().getEnter_image());

                }
                if (!ToolUtil.isEmpty(result.getData().getExit_image())) {
                    imageUrlList.add(result.getData().getExit_image());
                }


                LogUtil.e("changdu:"+ imageUrlList.size());


            }

            @Override
            public void onFailed(int errCord, String errMsg) {

            }
        });

    }


}
