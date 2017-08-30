package com.xuan.qingya.Common.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.xuan.qingya.Utils.LogUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by zhouzhixuan on 2017/8/26.
 */

public class NestedViewPager extends ViewPager {

    private int current;
    private int height = 0;

    @SuppressLint("UseSparseArrays")
    private HashMap<Integer, View> mChildrenViews = new HashMap<>();
    @SuppressLint("UseSparseArrays")
    private HashMap<Integer, Integer> mChildrenHight = new HashMap<>();

    public NestedViewPager(Context context) {
        super(context);
    }

    public NestedViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (current < mChildrenViews.size()) {
            View child = mChildrenViews.get(current);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            height = child.getMeasuredHeight();
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void resetHeight(int current) {
        this.current = current;

        if (mChildrenViews.size() > current) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, height);
            } else {
                layoutParams.height = height;
            }
            setLayoutParams(layoutParams);
        }
    }

    public void setObjectForPosition(View view, int position) {
        mChildrenViews.put(position, view);
        mChildrenHight.put(position, 0);
    }

    private boolean noScroll = true;

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (noScroll)
            return false;
        else
            return super.onTouchEvent(arg0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (noScroll)
            return false;
        else
            return super.onInterceptTouchEvent(arg0);
    }

}
