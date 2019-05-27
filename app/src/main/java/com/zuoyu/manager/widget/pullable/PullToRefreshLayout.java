package com.zuoyu.manager.widget.pullable;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zuoyu.manager.R;

import java.util.Timer;
import java.util.TimerTask;



/**
 * <pre>
 * Function：自定义的布局，用来管理三个子控件，其中一个是下拉头，一个是包含内容的PullableView（可以是实现Pullable接口的的任何View），
 * 还有一个上拉头，更多详解见博客http://blog.csdn.net/zhongkejingwang/article/details/38868463
 *
 * Created by Joann on 17/1/20 17:44
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Version Information：V 1.0
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class PullToRefreshLayout extends RelativeLayout {

    /**
     * last y
     */
    private int mLastMotionY;
    /**
     * last x
     */
    private int mLastMotionX;

    // 初始状态
    public static final int INIT = 0;
    // 释放刷新
    public static final int RELEASE_TO_REFRESH = 1;
    // 正在刷新
    public static final int REFRESHING = 2;
    // 释放加载
    public static final int RELEASE_TO_LOAD = 3;
    // 正在加载
    public static final int LOADING = 4;
    // 操作完毕
    public static final int DONE = 5;


    // 当前状态
    private int state = INIT;
    // 刷新回调接口
    private OnRefreshListener mListener;
    // 刷新成功
    public static final int SUCCEED = 0;
    // 刷新失败
    public static final int FAIL = 1;
    // 按下Y坐标，上一个事件点Y坐标
    private float downY, lastY;

    // 下拉的距离。注意：pullDownY和pullUpY不可能同时不为0
    public float pullDownY = 0;
    // 上拉的距离
    private float pullUpY = 0;

    // 释放刷新的距离
    private float refreshDist = 200;
    // 释放加载的距离
    private float loadmoreDist = 200;

    private MyTimer timer;
    // 回滚速度
    public float MOVE_SPEED = 8;
    // 第一次执行布局
    private boolean isLayout = false;
    // 在刷新过程中滑动操作
    private boolean isTouch = false;
    // 手指滑动距离与下拉头的滑动距离比，中间会随正切函数变化
    private float radio = 2;

    // 下拉箭头的转180°动画
    private RotateAnimation rotateAnimation;
    // 均匀旋转动画
    private RotateAnimation refreshingAnimation;

    /**
     * 下拉刷新
     */
    private View refreshView;// 下拉头View
    private View refreshImgView;// 下拉的箭头

    // 刷新结果：成功或失败
    private TextView refreshStateText;

    /**
     * 上拉加载
     */
    private View loadMoreView;// 上拉头View
    private View loadImgView;// 上拉的箭头

    // 加载结果：成功或失败
    private TextView loadStateText;

    // 实现了Pullable接口的View
    private View pullableView;

    // 过滤多点触碰
    private int mEvents;
    // 这两个变量用来控制pull的方向，如果不加控制，当情况满足可上拉又可下拉时没法下拉
    private boolean canPullDown = true;
    private boolean canPullUp = true;

    private Handler handler = new Handler();

    /**
     * 执行自动回滚的handler
     */
    Handler updateHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // 回弹速度随下拉距离moveDeltaY增大而增大
            MOVE_SPEED = (float) (8 + 5 * Math.tan(Math.PI / 2
                    / getMeasuredHeight() * (pullDownY + Math.abs(pullUpY))));
            if (!isTouch) {
                // 正在刷新，且没有往上推的话则悬停，显示"正在刷新..."
                if (state == REFRESHING && pullDownY <= refreshDist) {
                    pullDownY = refreshDist;
                    timer.cancel();
                } else if (state == LOADING && -pullUpY <= loadmoreDist) {
                    pullUpY = -loadmoreDist;
                    timer.cancel();
                }

            }
            if (pullDownY > 0)
                pullDownY -= MOVE_SPEED;
            else if (pullUpY < 0)
                pullUpY += MOVE_SPEED;
            if (pullDownY < 0) {
                // 已完成回弹
                pullDownY = 0;
                refreshImgView.clearAnimation();
                // 隐藏下拉头时有可能还在刷新，只有当前状态不是正在刷新时才改变状态
                if (state != REFRESHING && state != LOADING)
                    changeState(INIT);
                timer.cancel();
            }
            if (pullUpY > 0) {
                // 已完成回弹
                pullUpY = 0;
                loadImgView.clearAnimation();
                // 隐藏下拉头时有可能还在刷新，只有当前状态不是正在刷新时才改变状态
                if (state != REFRESHING && state != LOADING)
                    changeState(INIT);
                timer.cancel();
            }
            // 刷新布局,会自动调用onLayout
            requestLayout();
        }

    };

    /**
     * 初始化布局
     */
    private void initView() {

        // 初始化下拉布局
        refreshImgView = refreshView.findViewById(R.id.iv_pull_down);
        refreshStateText = (TextView) refreshView.findViewById(R.id.tv_pull_down);

        // 初始化上拉布局
        loadImgView = loadMoreView.findViewById(R.id.iv_pull_up);
        loadStateText = (TextView) loadMoreView.findViewById(R.id.tv_pull_up);
    }

    public void setOnRefreshListener(OnRefreshListener listener) {

        mListener = listener;
    }

    public PullToRefreshLayout(Context context) {
        super(context);
        initView(context);
    }

    public PullToRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PullToRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        timer = new MyTimer(updateHandler);

        // 添加旋转动画
        rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(context, R.anim.pull_reverse);
        refreshingAnimation = (RotateAnimation) AnimationUtils.loadAnimation(context, R.anim.pull_rotating);

        // 添加匀速转动动画
        LinearInterpolator lir = new LinearInterpolator();
        rotateAnimation.setInterpolator(lir);
        refreshingAnimation.setInterpolator(lir);
    }

    private void hide() {
        timer.schedule(5);
    }

    /**
     * 完成刷新操作，显示刷新结果。注意：刷新完成后一定要调用这个方法
     *
     * @param refreshResult PullToRefreshLayout.SUCCEED代表成功，PullToRefreshLayout.FAIL代表失败
     */
    public void refreshFinish(int refreshResult) {

        if (refreshImgView == null) {
            return;
        }
        refreshImgView.clearAnimation();
        switch (refreshResult) {
            case SUCCEED:
                // 刷新成功
                refreshStateText.setText(R.string.refresh_succeed);
                refreshImgView.setBackgroundResource(R.mipmap.icon_pull_succeed);
                break;
            case FAIL:
            default:
                // 刷新失败
                refreshStateText.setText(R.string.refresh_failed);
                refreshImgView.setBackgroundResource(R.mipmap.icon_pull_failed);
                break;
        }
        // 刷新结果停留1秒
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeState(DONE);
                hide();
            }
        }, 1000);

//        new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                changeState(DONE);
//                hide();
//            }
//        }.sendEmptyMessageDelayed(0, 1000);
    }

    /**
     * 加载完毕，显示加载结果。注意：加载完成后一定要调用这个方法
     *
     * @param refreshResult PullToRefreshLayout.SUCCEED代表成功，PullToRefreshLayout.FAIL代表失败
     */
    public void loadMoreFinish(int refreshResult) {
        loadImgView.clearAnimation();
        switch (refreshResult) {
            case SUCCEED:
                // 加载成功
                loadStateText.setText(R.string.loading_succeed);
                loadImgView.setBackgroundResource(R.mipmap.icon_pull_succeed);
                break;
            case FAIL:
            default:
                // 加载失败
                loadStateText.setText(R.string.loading_failed);
                loadImgView.setBackgroundResource(R.mipmap.icon_pull_failed);
                break;
        }
        // 刷新结果停留1秒

        // 刷新结果停留1秒
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeState(DONE);
                hide();
            }
        }, 1000);

//        new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                changeState(DONE);
//                hide();
//            }
//        }.sendEmptyMessageDelayed(0, 1000);
    }

    private void changeState(int to) {
        state = to;
        switch (state) {
            case INIT:
                // ===下拉布局初始状态
                refreshImgView.setBackgroundResource(R.mipmap.icon_pull_header_pull_down);
                refreshStateText.setText(R.string.pull_down_to_refresh);

                // ===上拉布局初始状态
                loadImgView.setBackgroundResource(R.mipmap.icon_pull_footer_pull_up);
                loadStateText.setText(R.string.pull_up_to_loading);
                break;
            case RELEASE_TO_REFRESH:
                // ======================释放刷新状态
                refreshImgView.startAnimation(rotateAnimation);
                refreshStateText.setText(R.string.release_to_refresh);
                break;
            case RELEASE_TO_LOAD:
                // ======================释放加载状态
                loadImgView.startAnimation(rotateAnimation);
                loadStateText.setText(R.string.release_to_load);
                break;
            case REFRESHING:
                // ========正在刷新状态
                refreshImgView.setBackgroundResource(R.mipmap.icon_pull_progress);
                refreshImgView.startAnimation(refreshingAnimation);
                refreshStateText.setText(R.string.refreshing);
                break;
            case LOADING:
                // ========正在加载状态
                loadImgView.setBackgroundResource(R.mipmap.icon_pull_progress);
                loadImgView.startAnimation(refreshingAnimation);
                loadStateText.setText(R.string.loading);
                break;
            case DONE:
                // 刷新或加载完毕，啥都不做
                break;
        }
    }

    /**
     * 不限制上拉或下拉
     */
    private void releasePull() {
        canPullDown = true;
        canPullUp = true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int y = (int) e.getRawY();
        int x = (int) e.getRawX();
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 首先拦截down事件,记录y坐标
                mLastMotionY = y;
                mLastMotionX = x;
                break;
            case MotionEvent.ACTION_MOVE:
                // deltaY > 0 是向下运动< 0是向上运动
                int deltaY = y - mLastMotionY;
                int deltaX = x - mLastMotionX;
                if (Math.abs(deltaX * 3) > Math.abs(deltaY)) {
                    return false;
                }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return false;
    }

    /*
     * （非 Javadoc）由父控件决定是否分发事件，防止事件冲突
     *
     * @see android.view.ViewGroup#dispatchTouchEvent(android.view.MotionEvent)
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                downY = ev.getY();
                lastY = downY;
                timer.cancel();
                mEvents = 0;
                releasePull();
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_POINTER_UP:
                // 过滤多点触碰
                mEvents = -1;
                break;
            case MotionEvent.ACTION_MOVE:

                if (mEvents == 0) {
                    if (((Pullable) pullableView).canPullDown() && canPullDown
                            && state != LOADING) {
                        // 可以下拉，正在加载时不能下拉
                        // 对实际滑动距离做缩小，造成用力拉的感觉
                        pullDownY = pullDownY + (ev.getY() - lastY) / radio;
                        if (pullDownY < 0) {
                            pullDownY = 0;
                            canPullDown = false;
                            canPullUp = true;
                        }
                        if (pullDownY > getMeasuredHeight())
                            pullDownY = getMeasuredHeight();
                        if (state == REFRESHING) {
                            // 正在刷新的时候触摸移动
                            isTouch = true;
                        }
                    } else if (((Pullable) pullableView).canPullUp() && canPullUp
                            && state != REFRESHING) {
                        // 可以上拉，正在刷新时不能上拉
                        pullUpY = pullUpY + (ev.getY() - lastY) / radio;
                        if (pullUpY > 0) {
                            pullUpY = 0;
                            canPullDown = true;
                            canPullUp = false;
                        }
                        if (pullUpY < -getMeasuredHeight())
                            pullUpY = -getMeasuredHeight();
                        if (state == LOADING) {
                            // 正在加载的时候触摸移动
                            isTouch = true;
                        }
                    } else
                        releasePull();
                } else
                    mEvents = 0;
                lastY = ev.getY();
                // 根据下拉距离改变比例
                radio = (float) (2 + 2 * Math.tan(Math.PI / 2 / getMeasuredHeight()
                        * (pullDownY + Math.abs(pullUpY))));
                requestLayout();
                if (pullDownY <= refreshDist && state == RELEASE_TO_REFRESH) {
                    // 如果下拉距离没达到刷新的距离且当前状态是释放刷新，改变状态为下拉刷新
                    changeState(INIT);
                }
                if (pullDownY >= refreshDist && state == INIT) {
                    // 如果下拉距离达到刷新的距离且当前状态是初始状态刷新，改变状态为释放刷新
                    changeState(RELEASE_TO_REFRESH);
                }
                // 下面是判断上拉加载的，同上，注意pullUpY是负值
                if (-pullUpY <= loadmoreDist && state == RELEASE_TO_LOAD) {
                    changeState(INIT);
                }
                if (-pullUpY >= loadmoreDist && state == INIT) {
                    changeState(RELEASE_TO_LOAD);
                }
                // 因为刷新和加载操作不能同时进行，所以pullDownY和pullUpY不会同时不为0，因此这里用(pullDownY +
                // Math.abs(pullUpY))就可以不对当前状态作区分了
                if ((pullDownY + Math.abs(pullUpY)) > 8) {
                    // 防止下拉过程中误触发长按事件和点击事件
                    ev.setAction(MotionEvent.ACTION_CANCEL);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (pullDownY > refreshDist || -pullUpY > loadmoreDist)
                    // 正在刷新时往下拉（正在加载时往上拉），释放后下拉头（上拉头）不隐藏
                    isTouch = false;
                if (state == RELEASE_TO_REFRESH) {
                    changeState(REFRESHING);
                    // 刷新操作
                    if (mListener != null)
                        mListener.onRefresh(this);
                } else if (state == RELEASE_TO_LOAD) {
                    changeState(LOADING);
                    // 加载操作
                    if (mListener != null)
                        mListener.onLoadMore(this);
                }
                hide();
            default:
                break;
        }
        // 事件分发交给父类

        return super.dispatchTouchEvent(ev);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (!isLayout) {
            // 这里是第一次进来的时候做一些初始化
            refreshView = getChildAt(0);
            pullableView = getChildAt(1);
            loadMoreView = getChildAt(2);
            isLayout = true;
            initView();
            refreshDist = ((ViewGroup) refreshView).getChildAt(0)
                    .getMeasuredHeight();
            loadmoreDist = ((ViewGroup) loadMoreView).getChildAt(0)
                    .getMeasuredHeight();
        }
        // 改变子控件的布局，这里直接用(pullDownY + pullUpY)作为偏移量，这样就可以不对当前状态作区分
        refreshView.layout(0,
                (int) (pullDownY + pullUpY) - refreshView.getMeasuredHeight(),
                refreshView.getMeasuredWidth(), (int) (pullDownY + pullUpY));
        pullableView.layout(0, (int) (pullDownY + pullUpY),
                pullableView.getMeasuredWidth(), (int) (pullDownY + pullUpY)
                        + pullableView.getMeasuredHeight());
        loadMoreView.layout(0,
                (int) (pullDownY + pullUpY) + pullableView.getMeasuredHeight(),
                loadMoreView.getMeasuredWidth(),
                (int) (pullDownY + pullUpY) + pullableView.getMeasuredHeight()
                        + loadMoreView.getMeasuredHeight());
    }

    private class MyTimer {
        private Handler handler;
        private Timer timer;
        private MyTask mTask;

        private MyTimer(Handler handler) {
            this.handler = handler;
            timer = new Timer();
        }

        private void schedule(long period) {
            if (mTask != null) {
                mTask.cancel();
                mTask = null;
            }
            mTask = new MyTask(handler);
            timer.schedule(mTask, 0, period);
        }

        private void cancel() {
            if (mTask != null) {
                mTask.cancel();
                mTask = null;
            }
        }

        class MyTask extends TimerTask {
            private Handler handler;

            private MyTask(Handler handler) {
                this.handler = handler;
            }

            @Override
            public void run() {
                handler.obtainMessage().sendToTarget();
            }

        }
    }

    /**
     * 刷新加载回调接口
     *
     * @author chenjing
     */
    public interface OnRefreshListener {
        /**
         * 刷新操作
         */
        void onRefresh(PullToRefreshLayout pullToRefreshLayout);

        /**
         * 加载操作
         */
        void onLoadMore(PullToRefreshLayout pullToRefreshLayout);
    }

}
