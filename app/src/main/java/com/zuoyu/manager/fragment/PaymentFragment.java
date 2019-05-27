package com.zuoyu.manager.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.zuoyu.manager.R;
import com.zuoyu.manager.activity.chart.PaymentActivity;
import com.zuoyu.manager.application.Constant;
import com.zuoyu.manager.entity.PaymentEntity;
import com.zuoyu.manager.utils.IntentUtil;
import com.zuoyu.manager.widget.hellochart.PieChartUtil;

import lecho.lib.hellocharts.view.PieChartView;

import static com.zuoyu.manager.R.string.etc;

/**
 * <pre>
 * Function：财务分析——支付方式
 *
 * Created by JoannChen on 2017/5/24 17:24
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class PaymentFragment extends Fragment {

    private PieChartUtil pieChartUtil;
    private PaymentEntity paymentEntity;
    private PaymentChildFragment day7Fragment, day30Fragment;


    private TextView weChatText, aliPayText, cashText, etcText;

    private TextView descText;
    private TextView dayRateText;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.payment_fragment, container, false);


        // 图表描述文字
        descText = (TextView) view.findViewById(R.id.tv_desc);

        // 7日/30日总收入
        dayRateText = (TextView) view.findViewById(R.id.tv_day_rate);


        // 绘制饼状图
        PieChartView pieChartView = (PieChartView) view.findViewById(R.id.pieChartView);
        pieChartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtil.start(getActivity(), PaymentActivity.class, Constant.RESULT, paymentEntity, false);
                MobclickAgent.onEvent(getActivity(), "FinancialAnalysis_SecondSection_Click");
            }
        });

        pieChartUtil = new PieChartUtil(pieChartView);

        // 微信／支付宝／现金收入占比
        weChatText = (TextView) view.findViewById(R.id.tv_wechat);
        aliPayText = (TextView) view.findViewById(R.id.tv_alipay);
        cashText = (TextView) view.findViewById(R.id.tv_cash);
        etcText = (TextView) view.findViewById(R.id.tv_etc);


        day7Fragment = new PaymentChildFragment();
        day30Fragment = new PaymentChildFragment();
        day30Fragment.setDay(30);
        showFragment(day7Fragment);


        // 默认展示7日支付方式
        RadioButton day7radio = (RadioButton) view.findViewById(R.id.tv_day7);
        day7radio.setChecked(true);

        // 选项卡切换
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.tv_day7:
                        showFragment(day7Fragment);
                        break;
                    case R.id.tv_day30:
                        showFragment(day30Fragment);
                        break;
                }
            }
        });


        return view;
    }


    /**
     * 实体类赋值
     *
     * @param entity 支付方式实体类
     */
    public void setPaymentEntity(PaymentEntity entity) {

        paymentEntity = entity;

        String payWay;
        float rate, wxRate = 0f, aliRate = 0f, cashRate = 0f, etcRate = 0f;


        for (int i = 0, size = paymentEntity.getData().getPaylist().size(); i < size; i++) {
            rate = paymentEntity.getData().getPaylist().get(i).getCount();
            payWay = paymentEntity.getData().getPaylist().get(i).getPayway();

            if (getString(R.string.wechat).endsWith(payWay)) {
                wxRate = rate;
            } else if (getString(R.string.alipay).endsWith(payWay)) {
                aliRate = rate;
            } else if (getString(R.string.cash).endsWith(payWay)) {
                cashRate = rate;
            } else if (getString(etc).endsWith(payWay)) {
                etcRate = rate;
            }
        }

        if (wxRate == 0f && aliRate == 0f && cashRate == 0f && etcRate == 0f) {
            pieChartUtil.setValue(0, 0, 1, 0);
        } else {
            pieChartUtil.setValue(wxRate, aliRate, cashRate, etcRate);
        }

        descText.setText(paymentEntity.getData().getDesc());
        dayRateText.setText((paymentEntity.getData().getDayRate() + paymentEntity.getData().getTotal()));

        weChatText.setText((wxRate + getString(R.string.percent_sign)));
        aliPayText.setText((aliRate + getString(R.string.percent_sign)));
        cashText.setText((cashRate + getString(R.string.percent_sign)));
        etcText.setText((etcRate + getString(R.string.percent_sign)));

    }


    /**
     * Fragment切换
     *
     * @param fragment fragment
     */
    private void showFragment(Fragment fragment) {
        getChildFragmentManager().beginTransaction().replace(R.id.ll_container, fragment).commit();
    }
}
