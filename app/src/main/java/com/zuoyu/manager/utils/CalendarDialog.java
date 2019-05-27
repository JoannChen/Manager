package com.zuoyu.manager.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.widget.TextView;

import com.zuoyu.manager.R;

import java.util.Calendar;

/**
 * <pre>
 * Function：日期选择对话框
 *
 * Created by JoannChen on 2017/3/23 18:11
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class CalendarDialog {

    private String dateStr;
    private Context context;
    private DatePicker datePicker;
    private DatePickerDialog datePickerDialog;


    public CalendarDialog(Context context) {
        this.context = context;
    }

    /**
     * 初始化日期选择器
     */
    public void show(final TextView dateText) {


        final Calendar calendar = Calendar.getInstance();
        int iYear = calendar.get(Calendar.YEAR);
        int iMonth = calendar.get(Calendar.MONTH);
        int iDay = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(context, null,
                iYear, iMonth, iDay);
        datePickerDialog.setCancelable(true);
        datePickerDialog.setCanceledOnTouchOutside(true);
        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, context.getString(R.string.cancel),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        datePickerDialog.dismiss();
                    }
                });
        datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, context.getString(R.string.ensure),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (datePicker == null) {
                            datePicker = datePickerDialog.getDatePicker();
                        }

                        String t1 = "—", t2 = "—";

                        if (datePicker.getMonth() < 10) {
                            t1 = "—0";
                        }
                        if (datePicker.getDayOfMonth() < 10) {
                            t2 = "—0";
                        }

                        dateStr = datePicker.getYear() + t1 + (datePicker.getMonth() + 1) + t2 + datePicker.getDayOfMonth();
                        dateText.setText(dateStr);

                    }
                });

    }

}
