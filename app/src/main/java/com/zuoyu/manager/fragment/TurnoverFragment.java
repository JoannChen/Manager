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
import com.zuoyu.manager.entity.TurnoverEntity;

/**
 * <pre>
 * Function：流量分析——车位周转率
 *
 * Created by JoannChen on 2017/5/24 17:24
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class TurnoverFragment extends Fragment {

    private TurnoverChildFragment day7Fragment, day30Fragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.turnover_fragment, container, false);


        day7Fragment = new TurnoverChildFragment();
        day7Fragment.setDay(7);

        day30Fragment = new TurnoverChildFragment();
        day30Fragment.setDay(30);
        showFragment(R.id.ll_container, day7Fragment);


        // 默认选中第一项
        RadioButton day7 = (RadioButton) view.findViewById(R.id.tv_day7);
        day7.setChecked(true);

        // 选项卡切换
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
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


    public void setTurnoverEntity(TurnoverEntity turnoverEntity) {


        LineChartFragment lineChartFragment = new LineChartFragment();

        lineChartFragment.setTurnoverEntity(turnoverEntity);
        lineChartFragment.setTAG(lineChartFragment.TAG_TURNOVER);


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
