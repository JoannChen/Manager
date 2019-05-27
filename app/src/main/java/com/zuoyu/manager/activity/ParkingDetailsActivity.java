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
import com.zuoyu.manager.utils.ToolUtil;
import com.zuoyu.manager.utils.http.HttpResult;
import com.zuoyu.manager.widget.BigImageDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * <pre>
 * Function：在场车辆详情
 *
 * Created by JoannChen on 2017/2/27 10:43
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class ParkingDetailsActivity extends BaseActivity {


    /**
     * 订单id,Intent传值,订单记录/在场车辆页面传入
     */
    private String mOrderId;

    // 车场名称
    // 停车费用
    // 车牌号
    // 停车时长（如02:25:12）

    private TextView nameText, priceText, plateText, longText;

    // 车辆类型 1：临停车，2：固定车
    // 入场时间
    private TextView carTypeText, inTimeText;

    // 支付状态
    private TextView payStatusText;


    /**
     * 查看子订单明细，仅在支付方式为多次支付时显示
     * 支付时停车，有子订单时不显示
     */
    private TextView childOrderText;


    // 超时时长
    // 补缴费用
    private TextView overtimeText, catchUpText;

    // 查看图片集合
    private List<String> imageUrlList = new ArrayList<>();


    @Override
    public int setLayout() {
        return R.layout.details_main;
    }

    @Override
    public void initBeforeLayout() {
    }


    @Override
    public void initTitle() {

        mOrderId = getIntent().getStringExtra(Constant.ORDER_ID);

        titleManage.setTitleText(getString(R.string.parking_details));
    }

    @Override
    public void initView() {


        nameText = (TextView) findViewById(R.id.tv_name);
        priceText = (TextView) findViewById(R.id.tv_price);
        plateText = (TextView) findViewById(R.id.tv_plate);
        longText = (TextView) findViewById(R.id.tv_long);

        carTypeText = (TextView) findViewById(R.id.tv_carType);
        inTimeText = (TextView) findViewById(R.id.tv_inTime);
        payStatusText = (TextView) findViewById(R.id.tv_payStatus);

        // 超时时长／补缴费用
        overtimeText = (TextView) findViewById(R.id.tv_overtime);
        catchUpText = (TextView) findViewById(R.id.tv_catchUp);


        // 查看子订单明细，仅在支付方式为多次支付时显示
        childOrderText = (TextView) findViewById(R.id.tv_more);
        childOrderText.setOnClickListener(this);


        // 查看图片
        TextView seeImageText = (TextView) findViewById(R.id.tv_image);
        seeImageText.setOnClickListener(this);

    }

    @Override
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.tv_more:
                IntentUtil.start(activity, ChildOrderActivity.class, Constant.ORDER_ID, mOrderId, false);
                MobclickAgent.onEvent(context, "CarsDetail_SonOrder_Click");
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
     * 获取支付状态 1：未支付，2：已支付，3：已超时，4：VIP
     *
     * @param status 支付状态
     * @return 支付状态（文本）
     */
    private String getPayStatus(int status) {

        switch (status) {
            case 1:
                status = R.string.unpaid;
                break;
            case 2:
                status = R.string.already_paid;
                break;
            case 3:
                status = R.string.overtime;
                break;
            case 4:
                status = R.string.vip;
                break;
            default:
                status = R.string.unpaid;
        }

        return getString(status);
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

                carTypeText.setText(result.getData().getType() == 1 ? R.string.temp_car : R.string.fixed_car);
                inTimeText.setText(result.getData().getEnter_time());

                /*
                 * 订单状态 1：未支付，2：已支付，3：已超时，4：VIP
                 *
                 * 未支付／vip；不显示订单信息和超时缴费布局
                 * 已支付：判断有无子订单
                 */

                int status = result.getData().getStatus();


                if (status == 2 || status == 3) {
                    childOrderText.setVisibility(View.VISIBLE);
                }


                // 显示超时和补缴
                payStatusText.setText(getPayStatus(result.getData().getStatus()));
                if (status == 3) {
                    findViewById(R.id.ic_Overtime).setVisibility(View.VISIBLE);
                    overtimeText.setText(result.getData().getOver_time());
                    catchUpText.setText((getString(R.string.rmb) + result.getData().getSupplement()));
                }


                // 查看图片

//                if (ToolUtil.isEmpty(result.getData().getEnter_image()) && ToolUtil.isEmpty(result.getData().getExit_image())) {
//                    imageUrlList.add(result.getData().getEnter_image());
//
//                }
//
                if (!ToolUtil.isEmpty(result.getData().getEnter_image())) {
                    imageUrlList.add(result.getData().getEnter_image());

                }

                if (!ToolUtil.isEmpty(result.getData().getExit_image())) {
                    imageUrlList.add(result.getData().getExit_image());
                }


            }

            @Override
            public void onFailed(int errCord, String errMsg) {

            }
        });

    }


}
