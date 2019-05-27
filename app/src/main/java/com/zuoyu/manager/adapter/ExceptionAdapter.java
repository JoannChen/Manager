package com.zuoyu.manager.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zuoyu.manager.R;
import com.zuoyu.manager.activity.BigImageActivity;
import com.zuoyu.manager.base.BaseAdapter;
import com.zuoyu.manager.base.ViewHolder;
import com.zuoyu.manager.entity.ExceptionEntity.ExceptionInfo;
import com.zuoyu.manager.utils.IntentUtil;
import com.zuoyu.manager.utils.ToolUtil;
import com.zuoyu.manager.widget.BigImageDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Function：异常抬杆适配器
 *
 * Created by JoannChen on 2017/3/13 10:12
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class ExceptionAdapter extends BaseAdapter {


    public ExceptionAdapter(Context context, List<?> list) {
        super(context, list);
    }

    @Override
    public int setLayoutId() {
        return R.layout.abnormal_exception_item;
    }

    @Override
    public void getView(ViewHolder holder) {

        // 操作时间
        // 操作人
        // 操作地址
        // 操作性质
        // 参考车牌号

        final ExceptionInfo entity = (ExceptionInfo) list.get(holder.getPosition());

        ((TextView) holder.getView(R.id.tv_time)).setText(entity.getTime());
        ((TextView) holder.getView(R.id.tv_address)).setText(entity.getAddress());
        ((TextView) holder.getView(R.id.tv_plate)).setText(entity.getPlate());
        ((TextView) holder.getView(R.id.tv_operator)).setText(entity.getOperation());
        ((TextView) holder.getView(R.id.tv_type)).setText(entity.getType());

        // 图片
//        (holder.getView(R.id.tv_image)).setTag(holder.getPosition());
//        (holder.getView(R.id.tv_image)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Dialog dialog = new Dialog(context);
//
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setContentView(R.layout.layout_image);
//
//                int tag = ((Integer)view.getTag());
//
//                ImageView img = (ImageView) dialog.findViewById(R.id.iv_desc);
//                ImageUtil.load(context,img ,((ExceptionEntity.ExceptionInfo)list.get(tag)).getImage(),R.mipmap.bg_default);
//
//                dialog.show();
//            }
//        });



        /*
         * 查看图片
         */

        // 图片
        final List<String> imageUrlList = new ArrayList<>();

        if (!ToolUtil.isEmpty(entity.getImage())) {
            imageUrlList.add(entity.getImage());

        }
        if (!ToolUtil.isEmpty(entity.getPlateimage())) {
            imageUrlList.add(entity.getPlateimage());
        }


        (holder.getView(R.id.tv_image)).setTag(holder.getPosition());
        (holder.getView(R.id.tv_image)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                IntentUtil.start((Activity) context, BigImageActivity.class, "list", imageUrlList, false);


                BigImageDialog dialog = new BigImageDialog(context);
                dialog.setList(imageUrlList);
                dialog.show();


//                Dialog dialog = new Dialog(context);
//
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setContentView(R.layout.layout_viewpager);
//                ImageCycleView imageCycleView = (ImageCycleView) dialog.findViewById(R.id.imageCycleView);
//
//                imageCycleView.setImageResources(imageUrlList, new ImageCycleView.ImageCycleViewListener() {
//                    @Override
//                    public void displayImage(String imageURL, ImageView imageView) {
//
//                        ImageUtil.load(context, imageView, imageURL, R.mipmap.bg_default);
//                    }
//
//                    @Override
//                    public void onImageClick(int position, View imageView) {
//
//                    }
//                });
//
//
//                dialog.show();
            }
        });


    }
}
