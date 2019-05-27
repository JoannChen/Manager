package com.zuoyu.manager.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.zuoyu.manager.utils.LogUtil;

/**
 * <pre>
 * Function：自定义ScrollView，实现滑动标题栏渐变效果
 *
 * Created by JoannChen on 2017/5/16 14:20
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class AlphaTitleScrollView extends ScrollView {

    private int mSlop;
    private ImageView headView;
    private RelativeLayout toolbar;

    public AlphaTitleScrollView(Context context, AttributeSet attrs,
                                int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public AlphaTitleScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO Auto-generated constructor stub
    }

    public AlphaTitleScrollView(Context context) {
        this(context, null);
    }

    private void init(Context context) {

        /*
         * 这个值好坑爹啊，之前看大神们写说是触动滑动事件最好是超过这个距离再来触发，
         * 我就用了这个，发现不对，log出来一下mSlop是300px,总共这个头部才没多少，所以就不用他了，
         * 直接自己定义为10px就差不多了，设这个主要是为了到达这个值的时候直接设置为全透明了，
         * 免得还要使劲滑到0才设置透明度的话，那样体验就不好了，给个缓冲值会更好点。
         */
        // mSlop = ViewConfiguration.get(context).getScaledDoubleTapSlop();
        
        mSlop = 10;
        LogUtil.i(mSlop + "");
    }

    /**
     * @param toolbar  标题
     * @param headView 头部布局
     */

    public void setTitleAndHead(RelativeLayout toolbar, ImageView headView) {
        this.toolbar = toolbar;
        this.headView = headView;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        float headHeight = headView.getMeasuredHeight()
                - toolbar.getMeasuredHeight();
        int alpha = (int) (((float) t / headHeight) * 255);
        if (alpha >= 255)
            alpha = 255;
        if (alpha <= mSlop)
            alpha = 0;
        toolbar.getBackground().setAlpha(alpha);

        super.onScrollChanged(l, t, oldl, oldt);
    }
}