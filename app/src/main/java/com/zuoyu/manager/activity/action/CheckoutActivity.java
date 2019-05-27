package com.zuoyu.manager.activity.action;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.zuoyu.manager.R;
import com.zuoyu.manager.application.MyApplication;
import com.zuoyu.manager.application.UrlManage;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.entity.CheckoutEntity;
import com.zuoyu.manager.utils.DateUtil;
import com.zuoyu.manager.utils.LogUtil;
import com.zuoyu.manager.utils.SharedUtil;
import com.zuoyu.manager.utils.ToastUtil;
import com.zuoyu.manager.utils.ToolUtil;
import com.zuoyu.manager.utils.http.HttpResult;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * <pre>
 * Function：结账统计界面
 *
 * Created by JoannChen on 2017/3/13 16:01
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class CheckoutActivity extends BaseActivity {

    private TextView dateText;
    private LinearLayout containerLLayout;

    // 日期选择器
    private String dateStr;
    private DatePicker datePicker;
    private DatePickerDialog datePickerDialog;

    private TextView incomeText, shouldText, discountText;

    private List<CheckoutEntity.Checkout.CheckoutList> checkoutList = new ArrayList<>();

    @Override
    public int setLayout() {
        return R.layout.checkout_main;
    }

    @Override
    public void initBeforeLayout() {
    }


    @Override
    public void initTitle() {
        titleManage.setTitleText(getString(R.string.checkout_statistics));
        titleManage.setRightBtn(R.mipmap.icon_calendar);
        titleManage.setRightBtnOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void initView() {

        dateStr = DateUtil.getDate();

        // 日期
        dateText = (TextView) findViewById(R.id.tv_date);
        dateText.setText(dateStr);

        // 停车场名称,接口未提供字段,从shared里获取
        TextView nameText = (TextView) findViewById(R.id.tv_name);
        nameText.setText(SharedUtil.getString(SharedUtil.PARK_NAME));

        // 今日收入// 今日应收 // 今日优惠
        incomeText = (TextView) findViewById(R.id.tv_income);
        shouldText = (TextView) findViewById(R.id.tv_should);
        discountText = (TextView) findViewById(R.id.tv_discount);


        containerLLayout = (LinearLayout) findViewById(R.id.ll_container);

    }

    @Override
    public void onClickEvent(View v) {
    }

    @Override
    public void initAfterLayout(Bundle savedInstanceState) {
        MyApplication.addActivity(this);
        MobclickAgent.onEvent(context, "CheckOut_Page_Load");

        initDate();
        parseGetCheckout();
    }

    @Override
    public void close() {
    }

    /**
     * 初始化日期选择器
     */
    private void initDate() {

        final Calendar calendar = Calendar.getInstance();
        int iYear = calendar.get(Calendar.YEAR);
        int iMonth = calendar.get(Calendar.MONTH);
        int iDay = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(context, null,
                iYear, iMonth, iDay);
        datePickerDialog.setCancelable(true);
        datePickerDialog.setCanceledOnTouchOutside(true);
        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.cancel),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        datePickerDialog.dismiss();
                    }
                });
        datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.ensure),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (datePicker == null) {
                            datePicker = datePickerDialog.getDatePicker();
                        }

                        String t1 = "-", t2 = "-";

                        if (datePicker.getMonth() + 1 < 10) {
                            t1 = "-0";
                        }
                        if (datePicker.getDayOfMonth() < 10) {
                            t2 = "-0";
                        }

                        dateStr = datePicker.getYear() + t1 + (datePicker.getMonth() + 1) + t2 + datePicker.getDayOfMonth();


                        if (DateUtil.getDataMillis(dateStr) <= DateUtil.getDataMillis(DateUtil.getDate())) {
                            dateText.setText(dateStr);
                            parseGetCheckout();
                        } else {
                            ToastUtil.show("请选择今天及以前的日期！");
                        }


                    }
                });


    }


    /**
     * 展示每笔收费详情
     */
    private void showCheckoutList() {

        containerLLayout.removeAllViews();

        for (int i = 0; i < checkoutList.size(); i++) {

            RelativeLayout rl = (RelativeLayout) LayoutInflater.from(this)
                    .inflate(R.layout.checkout_item1, containerLLayout, false);

            // 内部容器
            LinearLayout containerInnerLLayout = (LinearLayout) rl.findViewById(R.id.ll_container_inner);

            // 收费方式（岗亭、自助缴费、中央缴费）
            // 现金总和
            ((TextView) rl.findViewById(R.id.tv_type)).setText(checkoutList.get(i).getName());
            ((TextView) rl.findViewById(R.id.tv_price)).setText(checkoutList.get(i).getCashcount());

            List<CheckoutEntity.Checkout.CheckoutList.CheckoutInfo> checkoutInfoList = checkoutList.get(i).getChargeinfolist();

            if (checkoutInfoList != null) {

                for (int k = 0; k < checkoutInfoList.size(); k++) {

                    RelativeLayout ll = (RelativeLayout) LayoutInflater.from(this)
                            .inflate(R.layout.checkout_item2, containerInnerLLayout, false);

                    // 收费员
                    // 出口
                    // 电子收费金额
                    // 现金
                    ((TextView) ll.findViewById(R.id.tv_operator)).setText(checkoutInfoList.get(k).getName());

                    ((TextView) ll.findViewById(R.id.tv_out)).setText(ToolUtil.isEmpty(checkoutInfoList.get(k).getExit()) ? "" : "(" + checkoutInfoList.get(k).getExit() + ")");

                    ((TextView) ll.findViewById(R.id.tv_electronics)).setText(("电子:￥" + checkoutInfoList.get(k).getNocash()));
                    ((TextView) ll.findViewById(R.id.tv_cash)).setText(("现金:￥" + checkoutInfoList.get(k).getCash()));

                    containerInnerLLayout.addView(ll);
                }

            }

            containerLLayout.addView(rl);
        }
    }


    /**
     * 【解析】获取结账统计
     */
    private void parseGetCheckout() {

        Map<String, String> params = new TreeMap<>();
        params.put("uid", MyApplication.getUserInfo().getUid());
        params.put("parkid", SharedUtil.getString(SharedUtil.PARK_ID));
        params.put("startdate", dateStr);// 日期（格式 yyyy-MM-dd HH:mm:ss）
        params.put("enddate", dateStr);// 日期（格式 yyyy-MM-dd HH:mm:ss）

        LogUtil.e("dateStr:" + dateStr);

        httpUtil.post(params, UrlManage.NEW_CHECKOUT_INFO_URL, new HttpResult<CheckoutEntity>() {

            @Override
            public void onLoadCatch(String json) {

            }

            @Override
            public void onSuccess(CheckoutEntity result) {

                incomeText.setText((getString(R.string.rmb) + result.getData().getToday_earn()));
                shouldText.setText((getString(R.string.rmb) + result.getData().getReceivable()));
                discountText.setText((getString(R.string.rmb) + result.getData().getDiscount()));
                checkoutList = result.getData().getTypelist();

                showCheckoutList();


            }

            @Override
            public void onFailed(int errCord, String errMsg) {

            }
        });

    }


}
