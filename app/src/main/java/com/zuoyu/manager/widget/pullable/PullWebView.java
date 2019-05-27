package com.zuoyu.manager.widget.pullable;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

public class PullWebView extends WebView implements Pullable {

    public PullWebView(Context context) {
        super(context);
    }

    public PullWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean canPullDown() {
        return getScrollY() == 0;
    }

    @Override
    public boolean canPullUp() {

        return getScrollY() >= getContentHeight() * getScale()
                - getMeasuredHeight();
    }
}
