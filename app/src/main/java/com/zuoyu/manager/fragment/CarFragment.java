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

import com.zuoyu.manager.R;
import com.zuoyu.manager.entity.CarInOutEntity;


/**
 * <pre>
 * Function：流量分析——出入场车辆
 *
 * Created by JoannChen on 2017/5/24 17:24
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class CarFragment extends Fragment {

    private CarChildFragment h24Fragment, day7Fragment, day30Fragment;

    private TextView dateText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.car_fragment, container, false);

        // 24小时显示"时间"／7日或30日显示"日期"
        dateText = (TextView) view.findViewById(R.id.tv_date);

        // 实例化Fragment
        h24Fragment = new CarChildFragment();
        day7Fragment = new CarChildFragment();
        day30Fragment = new CarChildFragment();

        day7Fragment.setDay(7);
        day30Fragment.setDay(30);
        showFragment(R.id.ll_container, h24Fragment);

        view.findViewById(R.id.rl_title).setVisibility(View.VISIBLE);
        view.findViewById(R.id.line_1).setVisibility(View.VISIBLE);



        // 选项卡切换
//        NavAnimation navAnimation = (NavAnimation) view.findViewById(R.id.navAnimation);
//        navAnimation.setData(new String[]{getString(R.string.hour24), getString(R.string.day7), getString(R.string.day30)});
//        navAnimation.setTextColor(R.color.white,R.color.font_pink);
//        navAnimation.setSelectBackground(R.drawable.shape_chart_checked_bg);
//        navAnimation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
//                switch (checkedId) {
//                    case 0:
//                        dateText.setText(getContext().getString(R.string.time));
//                        showFragment(R.id.ll_container, h24Fragment);
//                        break;
//                    case 1:
//                        dateText.setText(getContext().getString(R.string.date));
//                        showFragment(R.id.ll_container, day7Fragment);
//                        break;
//                    case 2:
//                        dateText.setText(getContext().getString(R.string.date));
//                        showFragment(R.id.ll_container, day30Fragment);
//                        break;
//                }
//
//            }
//        });



        // 默认选中第一项
        RadioButton h24 = (RadioButton) view.findViewById(R.id.tv_hour24);
        h24.setChecked(true);

        // 选项卡切换
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.tv_hour24:
                        dateText.setText(getContext().getString(R.string.time));
                        showFragment(R.id.ll_container, h24Fragment);
                        break;
                    case R.id.tv_day7:
                        dateText.setText(getContext().getString(R.string.date));
                        showFragment(R.id.ll_container, day7Fragment);
                        break;
                    case R.id.tv_day30:
                        dateText.setText(getContext().getString(R.string.date));
                        showFragment(R.id.ll_container, day30Fragment);
                        break;
                }

            }
        });


        return view;
    }


    /**
     * 实体类赋值
     */
    public void setCarEntity(CarInOutEntity carEntity) {

        LineChartFragment lineChartFragment = new LineChartFragment();
        lineChartFragment.setCarEntity(carEntity);
        lineChartFragment.setTAG(1);

        showFragment(R.id.ll_chart, lineChartFragment);

    }


    /**
     * Fragment切换
     *
     * @param fragment fragment
     */
    private void showFragment(int container, Fragment fragment) {
        getChildFragmentManager().beginTransaction().replace(container, fragment).commit();
    }

}
