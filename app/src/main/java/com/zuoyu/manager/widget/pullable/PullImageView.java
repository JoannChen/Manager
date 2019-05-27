package com.zuoyu.manager.widget.pullable;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;


public class PullImageView extends ImageView implements Pullable
{

	public PullImageView(Context context)
	{
		super(context);
	}

	public PullImageView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public PullImageView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	@Override
	public boolean canPullDown()
	{
		return true;
	}

	@Override
	public boolean canPullUp()
	{
		return true;
	}

}

