package com.zuoyu.manager.widget.pullable;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.zuoyu.manager.utils.ToastUtil;

public class PullListView extends ListView implements Pullable {

    public boolean isOnMeasure;
    public boolean mIsScroll = true;

    public boolean isUpLoad = false;

    public PullListView(Context context) {
        super(context);
    }

    public PullListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        isOnMeasure = true;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        isOnMeasure = false;
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public boolean canPullDown() {
        if (getCount() == 0) {
            // 没有item的时候也可以下拉刷新
            return false;
        } else if (getFirstVisiblePosition() == 0
                && getChildAt(0).getTop() >= 0) {
            // 滑到ListView的顶部了
            return true;
        } else
            return false;

    }

    @Override
    public boolean canPullUp() {

        if (!isUpLoad) {
            if (getCount() == 0) {
                // 没有item的时候也可以上拉加载
                return false;
            } else if (getLastVisiblePosition() == (getCount() - 1)) {
                // 滑到底部了
                if (getChildAt(getLastVisiblePosition() - getFirstVisiblePosition()) != null
                        && getChildAt(
                        getLastVisiblePosition()
                                - getFirstVisiblePosition()).getBottom() <= getMeasuredHeight())
                    return true;
            }
        }


        return false;
    }

}
