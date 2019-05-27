package com.zuoyu.manager.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.activity.action.OrderActivity;
import com.zuoyu.manager.utils.LogUtil;
import com.zuoyu.manager.utils.SharedUtil;
import com.zuoyu.manager.utils.ViewSetting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * <pre>
 * Function：订单记录筛选功能
 *
 * Created by Joann on 2017/2/28 11:34
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class OrderFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private Context context;
    private DrawerLayout mDrawerLayout;
    private FrameLayout mDrawerContent;

    private String dateStr = "", payType = "", carType = "";

    private TextView choiceText;
    private CheckBox weChatCBox, aliPayCBox, cashCBox, otherCBox, fixedCarCBox, tempCarCBox;


    // 日期选择器
    private DatePicker datePicker;
    private DatePickerDialog datePickerDialog;
    private List<CheckBox> payTypeList, carTypeList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.order_slide, null);

        context = getActivity();
//        dateStr = DateUtil.getDate();

        initView(view);
        initDate();


        return view;
    }


    private void initView(View v) {

        // 选择日期
        choiceText = (TextView) v.findViewById(R.id.tv_choice);
//        choiceText.setText(dateStr);
        choiceText.setOnClickListener(this);
        ViewSetting.setMarginLeft(choiceText, 120, ViewSetting.RELATIVELAYOUT);

        // 支付类型
        weChatCBox = (CheckBox) v.findViewById(R.id.cb_weChat);
        weChatCBox.setOnCheckedChangeListener(this);


        aliPayCBox = (CheckBox) v.findViewById(R.id.cb_aliPay);
        aliPayCBox.setOnCheckedChangeListener(this);

        cashCBox = (CheckBox) v.findViewById(R.id.cb_cash);
        cashCBox.setOnCheckedChangeListener(this);

        otherCBox = (CheckBox) v.findViewById(R.id.cb_other);
        otherCBox.setOnCheckedChangeListener(this);


        // 车辆类型：固定车／临时车
        fixedCarCBox = (CheckBox) v.findViewById(R.id.cb_fixedCar);
        fixedCarCBox.setOnCheckedChangeListener(this);

        tempCarCBox = (CheckBox) v.findViewById(R.id.cb_tempCar);
        tempCarCBox.setOnCheckedChangeListener(this);


        // 重置／确认按钮
        Button resetBtn = (Button) v.findViewById(R.id.btn_reset);
        resetBtn.setOnClickListener(this);

        Button ensureBtn = (Button) v.findViewById(R.id.btn_sure);
        ensureBtn.setOnClickListener(this);


        // 抽屉
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        mDrawerContent = (FrameLayout) getActivity().findViewById(R.id.drawer_content);


        // 将组件添加到集合中
        payTypeList = new ArrayList<>();
        payTypeList.add(aliPayCBox);
        payTypeList.add(weChatCBox);
        payTypeList.add(cashCBox);
        payTypeList.add(otherCBox);

        carTypeList = new ArrayList<>();
        carTypeList.add(tempCarCBox);
        carTypeList.add(fixedCarCBox);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_choice:
                datePickerDialog.show();
                break;
            case R.id.btn_reset:
                resetData();
                break;
            case R.id.btn_sure:
                loadData();
                mDrawerLayout.closeDrawer(mDrawerContent);
                break;
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        switch (compoundButton.getId()) {
            case R.id.cb_weChat:
                setFontColor(weChatCBox);
                break;
            case R.id.cb_aliPay:
                setFontColor(aliPayCBox);
                break;
            case R.id.cb_cash:
                setFontColor(cashCBox);
                break;
            case R.id.cb_other:
                setFontColor(otherCBox);
                break;
            case R.id.cb_fixedCar:
                setFontColor(fixedCarCBox);
                break;
            case R.id.cb_tempCar:
                setFontColor(tempCarCBox);
                break;
        }
    }


    /**
     * 初始化日期选择器
     */
    private void initDate() {


        final Calendar calendar = Calendar.getInstance();
        int iYear = calendar.get(Calendar.YEAR);
        int iMonth = calendar.get(Calendar.MONTH);
        int iDay = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(getActivity(), null,
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
                        choiceText.setText(dateStr);
                    }
                });


    }


    /**
     * 根据筛选条件加载数据
     */
    private void loadData() {

        for (int i = 1, size = payTypeList.size(); i <= size; i++) {


            if (payTypeList.get(i - 1).isChecked()) {
                if (payType.length() >= 1) {
                    payType += ",";
                }

                payType += i;
            }

        }

        for (int i = 1, size = carTypeList.size(); i <= size; i++) {

            if (carTypeList.get(i - 1).isChecked()) {
                if (carType.length() >= 1) {
                    carType += "," + i;
                }

                carType += i;

            }
        }

        LogUtil.e("【筛选条件】date:" + dateStr + "   carType:" + carType + "   payTyp:" + payType);

        ((OrderActivity) context).setFilter(dateStr, payType, carType);
        ((OrderActivity) context).parseFilter();

//        dateStr = "";
        payType = "";
        carType = "";

    }

    /**
     * 重置数据
     */
    private void resetData() {

        choiceText.setText(getString(R.string.please_choice_date));

        dateStr = "";
//        payType = "";
//        carType = "";

        resetPayTypeData();
        resetCarTypeData();

        // 清除筛选条件,调用网络请求
        SharedUtil.setEmptyFilter();
        ((OrderActivity) context).parseFilter();


    }

    /**
     * 重置支付方式数据
     */
    private void resetPayTypeData() {

        for (CheckBox cb : payTypeList) {
            if (cb.isChecked()) {
                cb.setChecked(false);
                setFontColor(cb);
            }
        }

    }

    /**
     * 重置车辆类型数据
     */
    private void resetCarTypeData() {

        for (CheckBox cb : carTypeList) {
            if (cb.isChecked()) {
                cb.setChecked(false);
                setFontColor(cb);
            }
        }
    }


    /**
     * 选中显示红色，未选中显示灰色
     * 支付类型--分为微信、支付宝、现金、其他。
     * 互斥条件：选中支付类型后，车辆类型主动选中为临时车。
     * 车辆类型--分为固定车、临时车。
     * 互斥条件：选中固定车后，支付类型所选中立即失效。
     *
     * @param cb 组件
     */
    private void setFontColor(CheckBox cb) {

        if (cb.isChecked()) {
            cb.setTextColor(ContextCompat.getColor(getContext(), R.color.red_font));

            if (cb == fixedCarCBox) {
                resetPayTypeData();
            }

            if (cb == weChatCBox || cb == aliPayCBox || cb == cashCBox || cb == otherCBox) {
                tempCarCBox.setChecked(true);
                fixedCarCBox.setChecked(false);
            }
        } else {
            cb.setTextColor(ContextCompat.getColor(getContext(), R.color.black_font));
        }
    }


}
