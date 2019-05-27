package com.zuoyu.manager.utils;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zuoyu.manager.R;

/**
 * <pre>
 * Function：对话框工具类
 *
 * Created by Joann on 17/2/28 17:44
 * QQ:411083907
 * E-mail:q8622268@163.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class DialogUtil {

    //    private AlertDialog dialog;
    private Dialog dialog;

    // 标题// 内容
    private ImageView titleImgView;
    private TextView titleText, contentText;

    // 左侧按钮// 右侧按钮
    private Button leftBtn, rightBtn;
    private String leftStr, rightStr;

    // 横线//竖线
    private View hLine, vLine;


    public DialogUtil(Context context) {


        dialog = new Dialog(context, R.style.Dialog_Theme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.layout_dialog);

        dialog.show();


        // 对话框布局
        LinearLayout dialogRLayout = (LinearLayout) dialog.findViewById(R.id.rl_dialog);
        ViewUtil.setViewSize(dialogRLayout, 0, 540);


        // 标题
        titleText = (TextView) dialog.findViewById(R.id.tv_title);
        titleImgView = (ImageView) dialog.findViewById(R.id.iv_title);

        // 内容
        contentText = (TextView) dialog.findViewById(R.id.tv_content);

        // 左侧按钮
        leftBtn = (Button) dialog.findViewById(R.id.btn_left);

        // 右侧按钮
        rightBtn = (Button) dialog.findViewById(R.id.btn_right);

        // 横线
        hLine = dialog.findViewById(R.id.h_line);

        // 竖线
        vLine = dialog.findViewById(R.id.v_line);

    }


    /**
     * 设置内容位置
     * @param gravity 居中／居右／居左
     */
    public void setGravity(int gravity) {
        contentText.setGravity(gravity);
    }


    /**
     * 设置标题图标
     *
     * @param icon 图标资源
     */
    public void setTitle(int icon) {
        titleImgView.setVisibility(icon > 0 ? View.VISIBLE : View.GONE);
        titleImgView.setImageResource(icon);
    }

    /**
     * 设置标题文字
     *
     * @param text 文字
     */
    public void setTitle(String text) {
        titleText.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        titleText.setText(text);

    }

    /**
     * 设置主体提示文字
     *
     * @param text 文字
     */
    public void setContent(String text) {
        contentText.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        contentText.setText(text);
    }


    /**
     * 左侧按钮事件
     *
     * @param text     按钮上显示的字
     * @param listener 点击事件
     */
    public void setLeftButton(String text, final View.OnClickListener listener) {
        leftStr = text;

        if (!TextUtils.isEmpty(text)) {
            leftBtn.setVisibility(View.VISIBLE);
            leftBtn.setText(leftStr);
            leftBtn.setOnClickListener(listener);
            hLine.setVisibility(View.VISIBLE);

            vLine.setVisibility((TextUtils.isEmpty(leftStr) || TextUtils.isEmpty(rightStr)) ? View.GONE : View.VISIBLE);

        }
    }


    /**
     * 右侧按钮事件
     *
     * @param text     按钮上显示的字
     * @param listener 点击事件
     */
    public void setRightButton(String text, final View.OnClickListener listener) {
        rightStr = text;
        if (!TextUtils.isEmpty(text)) {
            rightBtn.setText(rightStr);
            rightBtn.setOnClickListener(listener);
            hLine.setVisibility(View.VISIBLE);

            vLine.setVisibility((TextUtils.isEmpty(leftStr) || TextUtils.isEmpty(rightStr)) ? View.GONE : View.VISIBLE);

        }
    }

    /**
     * 是否禁止点击返回键
     *
     * @param cancelable false 禁止点击返回
     */
    public void setCancelable(boolean cancelable) {
        dialog.setCancelable(cancelable);
    }

    /**
     * 关闭对话框
     */
    public void dismiss() {
        dialog.dismiss();
    }

}
