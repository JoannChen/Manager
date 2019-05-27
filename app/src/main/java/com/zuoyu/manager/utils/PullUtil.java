package com.zuoyu.manager.utils;


import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.base.BaseAdapter;
import com.zuoyu.manager.base.BaseModel;
import com.zuoyu.manager.widget.pullable.PullListView;
import com.zuoyu.manager.widget.pullable.PullToRefreshLayout;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * <pre>
 * Function：封装下拉刷新／上拉加载工具类
 *
 * Created by Joann on 17/3/16 17:44
 * QQ:411083907
 * E-mail:q8622268@163.com
 * Version Information：V 1.0
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class PullUtil {

    // 字段名：每页数量／第几页
    private String PER_PAGE_KEY = "per_page";
    private String PAGE_KEY = "page";
    private int page;
    private Map<String, String> params;

    // flag? 下拉刷新：上拉加载
    private boolean isRefresh = true;

    private PullToRefreshLayout refreshLayout;
    private RelativeLayout emptyRLayout;
    private PullListView pullListView;

    private onLoadListener onLoadListener;
    private int icon;


    public PullUtil(PullToRefreshLayout refreshLayout) {
        this.refreshLayout = refreshLayout;
    }

    public void setOnLoadListener(PullUtil.onLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
        initView();
        initParams(true, true);
    }

    private void initView() {
        refreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                initParams(true, true);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                initParams(false, true);
            }
        });

    }

    private void initParams(boolean isRefresh, boolean isRequest) {

        this.isRefresh = isRefresh;

        if (isRefresh) {
            page = 1;
            params = new TreeMap<>();
            params.put(PAGE_KEY, Integer.toString(page));
        } else {
            page++;
            params = new TreeMap<>();
            params.put(PAGE_KEY, Integer.toString(page));
        }

        params.put(PER_PAGE_KEY, "15");

        if (isRequest) {
            onLoadListener.onLoad(isRefresh, params);
        }

    }

    public interface onLoadListener {
        void onLoad(boolean flag, Map<String, String> params);
    }


    /**
     * 返回数据集合
     *
     * @param adapter 适配器
     * @param list    集合列表
     */
    public <T extends BaseModel> void setResult(BaseAdapter adapter, List<T> oldList, List<T> list) {

        if (isRefresh) {
            oldList.clear();
            refreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
        } else {
            refreshLayout.loadMoreFinish(PullToRefreshLayout.SUCCEED);
        }

        if (list != null) {
            if (list.size() == 0) {
                if (!isRefresh) {
                    page--;
                    ToastUtil.show("不能再滑了，人家也是有底线的～");
                }

            } else {
                oldList.addAll(list);
            }
        }


        adapter.notifyDataSetChanged();

    }


    /**
     * 设置每页显示条数的字段名
     *
     * @param perPage 字段名
     */
    public void setPageSize(String perPage) {
        PER_PAGE_KEY = perPage;
    }

    /**
     * 设置显示第几页的字段名
     *
     * @param page 字段名
     */
    public void setPage(String page) {
        PAGE_KEY = page;
    }


    public Map<String, String> getParams() {
        initParams(true, false);
        return params;
    }


    /**
     * ListView数据为空时处理
     *
     * @param list 原始list
     * @param icon 图标，传0 则显示默认图片
     * @param desc 文字描述
     */
    public void setEmptyView(List<?> list, int icon, String desc) {

        View view = (View) refreshLayout.getParent();

        if (emptyRLayout == null) {
            emptyRLayout = (RelativeLayout) view.findViewById(R.id.emptyRLayout);
        }

        if (pullListView == null) {
            pullListView = (PullListView) refreshLayout.findViewById(R.id.pullListView);
        }

        if (list == null || list.size() == 0) {

            pullListView.setVisibility(View.GONE);
            emptyRLayout.setVisibility(View.VISIBLE);

            // 图标
            if (icon != 0) {
                ImageView emptyIcon = (ImageView) view.findViewById(R.id.emptyIcon);
                emptyIcon.setImageResource(icon);
            }

            // 文字
            TextView emptyText = (TextView) view.findViewById(R.id.emptyText);
            emptyText.setText(desc);

        } else {
            pullListView.setVisibility(View.VISIBLE);
            emptyRLayout.setVisibility(View.GONE);
        }
    }

}
