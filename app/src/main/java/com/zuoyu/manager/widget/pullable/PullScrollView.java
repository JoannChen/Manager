package com.zuoyu.manager.widget.pullable;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class PullScrollView extends ScrollView implements Pullable {

    public PullScrollView(Context context) {
        super(context);
    }

    public PullScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean canPullDown() {

        return getScrollY() == 0;
    }

    @Override
    public boolean canPullUp() {

//        return getScrollY() >= (getChildAt(0).getHeight() - getMeasuredHeight());
        return false;
    }


    //禁止scrollView内布局变化后自动滚动
    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        return 0;
    }

}

