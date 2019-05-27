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

import com.zuoyu.manager.R;
import com.zuoyu.manager.entity.UtilizationEntity;

/**
 * <pre>
 * Function：流量分析——车位利用率子页面
 *
 * Created by JoannChen on 2017/5/24 17:24
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class UtilizationFragment extends Fragment {

    private UtilizationChildFragment h24Fragment, day7Fragment, day30Fragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.car_fragment, container, false);

        // 实例化Fragment
        h24Fragment = new UtilizationChildFragment();
        day7Fragment = new UtilizationChildFragment();
        day30Fragment = new UtilizationChildFragment();

        day7Fragment.setDay(7);
        day30Fragment.setDay(30);
        showFragment(R.id.ll_container, h24Fragment);


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
                        showFragment(R.id.ll_container, h24Fragment);
                        break;
                    case R.id.tv_day7:
                        showFragment(R.id.ll_container, day7Fragment);
                        break;
                    case R.id.tv_day30:
                        showFragment(R.id.ll_container, day30Fragment);
                        break;
                }
            }
        });


        return view;
    }


    public void setUtilizationEntity(UtilizationEntity utilizationEntity, int day) {


        LineChartFragment lineChartFragment = new LineChartFragment();
        lineChartFragment.setUtilizationEntity(utilizationEntity, day);
        lineChartFragment.setTAG(lineChartFragment.TAG_UTILIZATION);

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
