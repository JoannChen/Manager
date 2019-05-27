package com.zuoyu.manager.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.zuoyu.manager.R;
import com.zuoyu.manager.application.MyApplication;
import com.zuoyu.manager.application.UrlManage;
import com.zuoyu.manager.base.BaseActivity;
import com.zuoyu.manager.entity.ParkInfoEntity;
import com.zuoyu.manager.entity.ParkInfoEntity.ParkInfo.PriceInfo;
import com.zuoyu.manager.utils.ImageUtil;
import com.zuoyu.manager.utils.SharedUtil;
import com.zuoyu.manager.utils.ToolUtil;
import com.zuoyu.manager.utils.ViewSetting;
import com.zuoyu.manager.utils.http.HttpResult;
import com.zuoyu.manager.widget.AlphaTitleScrollView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.zuoyu.manager.R.string.day;
import static com.zuoyu.manager.R.string.night;


/**
 * <pre>
 * Function：停车场详情
 *
 * Created by JoannChen on 2/7/17 10:57
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class ParkInfoActivity extends BaseActivity {


    // 停车场图片url地址 // 车位总数目 // 停车场名称 // 停车场地址 // 停车场电话 // 停车场编号 // 停车场类型 // 营业时间
    private ImageView descImg;
    private TextView countText, nameText, addressText, phoneText, numberText, typeText, timeText;
    private String telephone;

    /**
     * 收费规则
     */
    private TextView dayText, nightText;
    private LinearLayout dayLLayout, nightLLayout;
    private List<PriceInfo> todayList = new ArrayList<>();
    private List<PriceInfo> nightList = new ArrayList<>();

    private RelativeLayout titleRLayout;


    @Override
    public int setLayout() {
        return R.layout.parkinfo_main;
    }

    @Override
    public void initBeforeLayout() {
    }


    @Override
    public void initTitle() {
        titleManage.hideTitle();

    }


    @Override
    public void initView() {

        // 可滑动的标题栏

        AlphaTitleScrollView scrollView = (AlphaTitleScrollView) findViewById(R.id.scrollView);
        titleRLayout = (RelativeLayout) findViewById(R.id.title);
        descImg = (ImageView) findViewById(R.id.iv_desc);
        scrollView.setTitleAndHead(titleRLayout, descImg);
        titleRLayout.getBackground().setAlpha(0);

        // 返回按钮
        ImageView backImg = (ImageView) findViewById(R.id.iv_back);
        backImg.setOnClickListener(this);
        ViewSetting.setViewSize(backImg, 88, 100);


        // 标题栏
        TextView titleText = (TextView) findViewById(R.id.tv_title);
        ViewSetting.setViewSize(titleText, 88, 0);
        ViewSetting.setTextSize(titleText, 36);


        // 车位数
        countText = (TextView) findViewById(R.id.tv_count);
        ViewSetting.setTextSize(countText, 26);
        ViewSetting.setViewSize(countText, 50, 164);
        ViewSetting.setMarginLeft(countText, 526, ViewSetting.RELATIVELAYOUT);
        ViewSetting.setMarginTop(countText, 287, ViewSetting.RELATIVELAYOUT);

        nameText = (TextView) findViewById(R.id.tv_name);
        addressText = (TextView) findViewById(R.id.tv_address);
        phoneText = (TextView) findViewById(R.id.tv_telephone);
        phoneText.setOnClickListener(this);

        numberText = (TextView) findViewById(R.id.tv_num);
        typeText = (TextView) findViewById(R.id.tv_type);
        timeText = (TextView) findViewById(R.id.tv_time);


        // 白天 / 夜间价格显示
        dayText = (TextView) findViewById(R.id.tv_day);
        nightText = (TextView) findViewById(R.id.tv_night);

        // 白天 / 夜间价格列表容器
        dayLLayout = (LinearLayout) findViewById(R.id.ll_day);
        nightLLayout = (LinearLayout) findViewById(R.id.ll_night);

    }

    @Override
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                titleRLayout.getBackground().setAlpha(255);
                break;
            case R.id.tv_telephone:
                ToolUtil.callPhone(context, telephone);
                MobclickAgent.onEvent(context, "ParkInfo_PhoneNum_Click");
                break;
        }

    }


    @Override
    public void initAfterLayout(Bundle savedInstanceState) {

        MyApplication.addActivity(this);
        MobclickAgent.onEvent(context, "ParkInfo_Page_Load");

        parseParkInfo();

    }

    @Override
    public void close() {

    }


    /**
     * 展示收费规则
     */
    private void showChargeRule() {

        // 白天价格所有item  eg. 首小时 1.5元／15分钟

        for (int i = 0; i < todayList.size(); i++) {

            RelativeLayout rl = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.parkinfo_rule, dayLLayout, false);

            ((TextView) rl.findViewById(R.id.text_1)).setText(todayList.get(i).getInfo());
            ((TextView) rl.findViewById(R.id.text_2)).setText(todayList.get(i).getPrice());
            dayLLayout.addView(rl);
        }

        // 晚上天价格所有item

        for (int i = 0; i < nightList.size(); i++) {

            RelativeLayout rl = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.parkinfo_rule, nightLLayout, false);

            ((TextView) rl.findViewById(R.id.text_1)).setText(nightList.get(i).getInfo());
            ((TextView) rl.findViewById(R.id.text_2)).setText(nightList.get(i).getPrice());
            nightLLayout.addView(rl);
        }

    }


    /**
     * 【解析】获取停车场详情
     */
    private void parseParkInfo() {

        Map<String, String> params = new TreeMap<>();
        params.put("uid", MyApplication.getUserInfo().getUid());
        params.put("parkid", SharedUtil.getString(SharedUtil.PARK_ID));


        httpUtil.post(params, UrlManage.PARK_INFO_URL, new HttpResult<ParkInfoEntity>() {

            @Override
            public void onLoadCatch(String json) {

            }

            @Override
            public void onSuccess(ParkInfoEntity result) {

                ParkInfoEntity.ParkInfo entity = result.getData();

                ImageUtil.load(context, descImg, entity.getImage(), R.mipmap.bg_parkinfo);

                telephone = entity.getPark_tel();

                // 赋值
                countText.setText((entity.getPark_count() + getString(R.string.park_space)));
                nameText.setText(entity.getPark_name());
                addressText.setText(entity.getAddress());
                phoneText.setText(telephone);
                numberText.setText((getString(R.string.park_num) + entity.getPark_num()));
                typeText.setText((getString(R.string.park_type) + entity.getPark_type()));
                timeText.setText((getString(R.string.business_hours) + entity.getService_time()));

                // 白天／夜间收费时间  eg. 白天（08：00-20：00）
                dayText.setText((getString(day) + entity.getDpricedaytime()));
                nightText.setText((getString(night) + entity.getDpricenighttime()));

                todayList = entity.getDpricedaylist();
                nightList = entity.getDpricenightlist();

                showChargeRule();

            }

            @Override
            public void onFailed(int errCord, String errMsg) {

            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            titleRLayout.getBackground().setAlpha(255);
        }
        return super.onKeyDown(keyCode, event);
    }
}
