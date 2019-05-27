package com.zuoyu.manager.activity.chart;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.application.Constant;
import com.zuoyu.manager.entity.PaymentEntity;
import com.zuoyu.manager.utils.ChartUtil;
import com.zuoyu.manager.widget.hellochart.PieChartUtil;

import lecho.lib.hellocharts.view.PieChartView;

/**
 * <pre>
 * Function：横屏饼状图
 *
 * Created by JoannChen on 2/10/17 10:19
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V
 * Copyright Notice：版权所有@北京百会易泊科技有限公司x
 * </pre>
 */
public class PaymentActivity extends Activity {

    private PieChartUtil pieChartUtil;
    private TextView weChatText, aliPayText, cashText, etcText;

    private TextView descText;
    private TextView dayRateText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_payment_main);

        initView();

        // Intent赋值
        PaymentEntity result = (PaymentEntity) getIntent().getSerializableExtra(Constant.RESULT);


        descText.setText(result.getData().getDesc());
        dayRateText.setText((result.getData().getDayRate() + result.getData().getTotal()));


        // 计算比例
        float rate, wxRate = 0f, aliRate = 0f, cashRate = 0f, etcRate = 0f;
        String payWay;

        for (int i = 0, size = result.getData().getPaylist().size(); i < size; i++) {
            rate = result.getData().getPaylist().get(i).getCount();
            payWay = result.getData().getPaylist().get(i).getPayway();
            if (getString(R.string.wechat).endsWith(payWay)) {
                wxRate = rate;
            } else if (getString(R.string.alipay).endsWith(payWay)) {
                aliRate = rate;
            } else if (getString(R.string.cash).endsWith(payWay)) {
                cashRate = rate;
            } else if (getString(R.string.etc).endsWith(payWay)) {
                etcRate = rate;
            }
        }

        if (wxRate == 0f && aliRate == 0f && cashRate == 0f && etcRate == 0f) {
            pieChartUtil.setValue(0, 0, 1, 0);
        } else {
            pieChartUtil.setValue(wxRate, aliRate, cashRate, etcRate);
        }


        // 每种支付方式所占比例
        weChatText.setText((wxRate + getString(R.string.percent_sign)));
        aliPayText.setText((aliRate + getString(R.string.percent_sign)));
        cashText.setText((cashRate + getString(R.string.percent_sign)));
        etcText.setText((etcRate + getString(R.string.percent_sign)));

    }


    private void initView() {

        // 设置全屏
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        // 初始化图表
        PieChartView pieChartView = (PieChartView) findViewById(R.id.pieChartView);
        pieChartUtil = new PieChartUtil(pieChartView);


        // 微信，支付宝，现金,ETC显示比率
        weChatText = (TextView) findViewById(R.id.tv_wechat);
        aliPayText = (TextView) findViewById(R.id.tv_alipay);
        cashText = (TextView) findViewById(R.id.tv_cash);
        etcText = (TextView) findViewById(R.id.tv_etc);

        // 关闭按钮
        ImageView backImg = (ImageView) findViewById(R.id.iv_back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // 功能描述
        descText = (TextView) findViewById(R.id.tv_desc);

        // 总收入
        dayRateText = (TextView) findViewById(R.id.tv_day_rate);


        TextView wxText = (TextView) findViewById(R.id.text_2);
        TextView alText = (TextView) findViewById(R.id.text_3);
        TextView xjText = (TextView) findViewById(R.id.text_4);
        TextView ecText = (TextView) findViewById(R.id.text_5);

        ChartUtil.setStyle(pieChartView, descText, dayRateText, backImg,
                wxText, alText, xjText,ecText,
                weChatText, aliPayText, cashText,etcText);

    }


}
