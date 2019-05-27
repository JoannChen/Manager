package com.zuoyu.manager.widget;

import android.app.DatePickerDialog;
import android.content.Context;

/**
 * <pre>
 * Function：
 *
 * Created by JoannChen on 2017/3/7 11:51
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class MyDatePickerDialog extends DatePickerDialog {


    public MyDatePickerDialog(Context context, OnDateSetListener listener, int year, int month, int dayOfMonth) {
        super(context, listener, year, month, dayOfMonth);
    }

    public MyDatePickerDialog(Context context, int themeResId, OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth) {
        super(context, themeResId, listener, year, monthOfYear, dayOfMonth);
    }


    @Override
    protected void onStop() {
//         super.onStop();//注释掉
    }


}
