package com.zuoyu.manager.widget.viewpager;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zuoyu.manager.R;
import com.zuoyu.manager.utils.DimensUtil;
import com.zuoyu.manager.utils.ViewSetting;
import com.zuoyu.manager.widget.BigImageDialog;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * <pre>
 * Function：自定义轮播图
 *
 * Created by JoannChen on 2017/3/27 10:12
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class ImageCycleView extends LinearLayout {
    private BigImageDialog dialog;
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 图片轮播视图
     */
    private ViewPager mAdvPager = null;

    /**
     * 图片轮播指示器控件
     */
    private ViewGroup mGroup;


    /**
     * 滚动图片指示视图列表
     */
    private ImageView[] mImageViews = null;

    /**
     * 手机密度
     */
    private boolean isStop;


    public ImageCycleView(Context context, int height) {
        super(context);
        setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, ViewSetting.getHeight(height)));
        init(context);
    }

    public ImageCycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (isInEditMode()) {
            return;
        }
        init(context);
    }


    private void init(Context context) {
        mContext = context;
//        mScale = context.getResources().getDisplayMetrics().density;
        LayoutInflater.from(context).inflate(R.layout.layout_cycle_view, this);
        mAdvPager = (ViewPager) findViewById(R.id.adv_pager);
        mAdvPager.setOffscreenPageLimit(3);
        mAdvPager.addOnPageChangeListener(new GuidePageChangeListener());
        mAdvPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        // 开始图片滚动
                        startImageTimerTask();
                        break;
                    default:
                        // 停止图片滚动
                        stopImageTimerTask();
                        break;
                }
                return false;
            }
        });

        // 滚动图片右下指示器视
        mGroup = (ViewGroup) findViewById(R.id.circles);
    }

    RelativeLayout.LayoutParams imageParams;

    public void setCycle_T(CYCLE_T T) {

        switch (T) {
            case CYCLE_VIEW_NORMAL:
                imageParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

                break;
            case CYCLE_VIEW_THREE_SCALE:
                imageParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                mAdvPager.setPageTransformer(false, new ViewPager.PageTransformer() {
                    @Override
                    public void transformPage(View page, float position) {
                        final float normalizedPosition = Math.abs(Math.abs(position) - 1);
                        page.setScaleX(normalizedPosition / 2 + 0.5f);
                        page.setScaleY(normalizedPosition / 2 + 0.5f);
                    }
                });
                break;
            case CYCLE_VIEW_ZOOM_IN:
                imageParams = null;
                mAdvPager.setPageMargin(-DimensUtil.dip2px(60));
                mAdvPager.setPageTransformer(true, new ZoomOutPageTransformer());
                imageParams = null;
                break;
        }
    }

    /**
     * 装填图片数据
     */
    public void setImageResources(List<String> imageUrlList, ImageCycleViewListener imageCycleViewListener) {

        if (imageUrlList != null && imageUrlList.size() > 1) {
            mGroup.setVisibility(View.VISIBLE);
        } else {
            mGroup.setVisibility(View.GONE);
        }

        // 清除
        mGroup.removeAllViews();

        // 图片广告数量
        final int imageCount = imageUrlList.size();


        mImageViews = new ImageView[imageCount];

        ImageView mImageView;

        for (int i = 0; i < imageCount; i++) {

            mImageView = new ImageView(mContext);
            ViewSetting.setViewSize(mImageView, 15, 15);

            mImageViews[i] = mImageView;
            if (i == 0) {
                mImageViews[i].setBackgroundResource(R.mipmap.icon_point_focus);
            } else {
                mImageViews[i].setBackgroundResource(R.mipmap.icon_point_normal);
            }
            mGroup.addView(mImageViews[i]);
            ViewSetting.setMarginRight(mImageView, 10, ViewSetting.LINEARLAYOUT);
        }

        ImageCycleAdapter mAdvAdapter = new ImageCycleAdapter(mContext, imageUrlList, imageCycleViewListener);
        mAdvPager.setAdapter(mAdvAdapter);

        if (imageCount > 1) {
            startImageTimerTask();
        }
    }

    /**
     * 图片轮播(手动控制自动轮播与否，便于资源控件）
     */
    public void startImageCycle() {
        startImageTimerTask();
    }

    /**
     * 暂停轮播—用于节省资源
     */
    public void pushImageCycle() {
        stopImageTimerTask();
    }

    /**
     * 图片滚动任务
     */
    private void startImageTimerTask() {
        stopImageTimerTask();
        // 图片滚动
        mHandler.postDelayed(mImageTimerTask, 5000);
    }

    /**
     * 停止图片滚动任务
     */
    private void stopImageTimerTask() {
        isStop = true;
        mHandler.removeCallbacks(mImageTimerTask);
    }

    private Handler mHandler = new Handler();

    /**
     * 图片自动轮播Task
     */
    private Runnable mImageTimerTask = new Runnable() {
        @Override
        public void run() {
            if (mImageViews != null) {
                mAdvPager.setCurrentItem(mAdvPager.getCurrentItem() + 1);
                if (!isStop) {  //if  isStop=true   //当你退出后 要把这个给停下来 不然 这个一直存在 就一直在后台循环
                    mHandler.postDelayed(mImageTimerTask, 5000);
                }

            }
        }
    };


    /**
     * 轮播图片监听
     *
     * @author minking
     */
    private final class GuidePageChangeListener implements OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE)
                startImageTimerTask();
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int index) {
            index = index % mImageViews.length;
            // 设置当前显示的图片
//            mImageIndex = index;
            // 设置图片滚动指示器背
            mImageViews[index].setBackgroundResource(R.mipmap.icon_point_focus);
            for (int i = 0; i < mImageViews.length; i++) {
                if (index != i) {
                    mImageViews[i].setBackgroundResource(R.mipmap.icon_point_normal);
                }
            }
        }
    }

    /**
     * 适配器
     */
    private class ImageCycleAdapter extends PagerAdapter {

        /**
         * 图片视图缓存列表
         */
        private ArrayList<View> mImageViewCacheList;

        /**
         * 图片资源列表
         */
        private List<String> mImageUrlList = new ArrayList<>();

        /**
         * 广告图片点击监听
         */
        private ImageCycleViewListener mImageCycleViewListener;

        private Context mContext;

        private ImageCycleAdapter(Context context, List<String> imageUrlList, ImageCycleViewListener imageCycleViewListener) {
            this.mContext = context;
            this.mImageUrlList = imageUrlList;
            mImageCycleViewListener = imageCycleViewListener;
            mImageViewCacheList = new ArrayList<>();
        }

        @Override
        public int getCount() {
            if (mImageUrlList.size() == 1) {
                return 1;
            }
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
//            String imageUrl = mImageUrlList.get(position % mImageUrlList.size()).getImage();

            String imageUrl = mImageUrlList.get(position % mImageUrlList.size());
            View view;
            PhotoView photoView;
            if (mImageViewCacheList.isEmpty()) {
                view = View.inflate(mContext, R.layout.item_viewpager, null);
                photoView = (PhotoView) view.findViewById(R.id.imageView);
            } else {
                view = mImageViewCacheList.remove(0);
                photoView = (PhotoView) view.findViewById(R.id.imageView);
            }

            // 设置图片点击监听
            photoView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mImageCycleViewListener.onImageClick(position % mImageUrlList.size(), v);
                }
            });

            //单击退出
            photoView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
                @Override
                public void onViewTap(View view, float x, float y) {
//                    BigImageActivity.activity.finish();
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                }
            });

            view.setTag(imageUrl);
            container.addView(view);
            mImageCycleViewListener.displayImage(imageUrl, photoView);


            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            mAdvPager.removeView(view);
            mImageViewCacheList.add(view);

        }

    }

    /**
     * 轮播控件的监听事件
     *
     * @author minking
     */
    public interface ImageCycleViewListener {
        /**
         * 加载图片资源
         *
         * @param imageURL   :url
         * @param imageView: image
         */
        void displayImage(String imageURL, ImageView imageView);

        /**
         * 单击图片事件
         *
         * @param position  :position
         * @param imageView :image
         */
        void onImageClick(int position, View imageView);
    }

    /**
     * vp 效果
     */
    public static enum CYCLE_T {

        /********
         * 普通的ViewPager
         *****/
        CYCLE_VIEW_NORMAL,
        /********
         * 放大进入
         *****/
        CYCLE_VIEW_ZOOM_IN,
        /********
         * 展示左右
         *****/
        CYCLE_VIEW_THREE_SCALE
    }

    /**
     * 是否隐藏底部
     *
     * @param hideBottom
     */
    public void hideBottom(boolean hideBottom) {
        if (hideBottom) {
            mGroup.setVisibility(View.GONE);
        } else {
            mGroup.setVisibility(View.VISIBLE);
        }
    }


    public void setDialog(BigImageDialog dialog) {
        this.dialog = dialog;
    }
}




