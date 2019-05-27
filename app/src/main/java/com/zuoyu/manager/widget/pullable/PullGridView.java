package com.zuoyu.manager.widget.pullable;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;


public class PullGridView extends GridView implements Pullable {

    public PullGridView(Context context) {
        super(context);
    }

    public PullGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean canPullDown() {
        if (getCount() == 0) {
            // 没有item的时候也可以下拉刷新
            return true;
        } else if (getFirstVisiblePosition() == 0 && getChildAt(0) != null
                && getChildAt(0).getTop() >= 0) {
            // 滑到顶部了
//            ToastUtil.show("已经到顶部了");
            return true;
        } else
            return false;
    }

    @Override
    public boolean canPullUp() {
        if (getCount() == 0) {
            // 没有item的时候也可以上拉加载
            return true;
        } else if (getLastVisiblePosition() == (getCount() - 1)) {
            // 滑到底部了
            if (getChildAt(getLastVisiblePosition() - getFirstVisiblePosition()) != null
                    && getChildAt(
                    getLastVisiblePosition()
                            - getFirstVisiblePosition()).getBottom() <= getMeasuredHeight())
                return true;
        }
        return false;
    }

}

