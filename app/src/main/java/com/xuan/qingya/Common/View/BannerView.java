package com.xuan.qingya.Common.View;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by zhouzhixuan on 2017/10/1.
 */

public class BannerView extends RelativeLayout implements ViewPager.OnPageChangeListener {
    private Context context;
    private ArrayList<View> bannerViews;
    private LinearLayout indicatorsView;

    private BannerViewPager mViewPager;
    private PagerAdapter adapter;

    private LinearLayout.LayoutParams params;

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        bannerViews = new ArrayList<>();
        adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((BannerItemView) object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                if (!bannerViews.isEmpty()) {
                    int positionInList = position % bannerViews.size();
                    View image = bannerViews.get(positionInList);
                    container.addView(image);
                    return image;
                }
                return null;
            }
        };

        //动态添加ViewPager实例
        mViewPager = new BannerViewPager(context);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(this);
        addView(mViewPager);

        //动态添加一个LinearLayout
        indicatorsView = new LinearLayout(context);
        RelativeLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(ALIGN_PARENT_BOTTOM);
        params.addRule(CENTER_HORIZONTAL);
        params.setMargins(10, 10, 10, 10);
        addView(indicatorsView, params);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int positionInList = position % bannerViews.size();
        refreshDots(positionInList);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void addImage(View view) {
        if (params == null) {
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(9, 0, 9, 36);
        }
        IndicatorDotView dot = new IndicatorDotView(getContext());
        indicatorsView.addView(dot, params);
        bannerViews.add(view);
        adapter.notifyDataSetChanged();
        mViewPager.setCurrentItem(bannerViews.size() * 1024);
        refreshDots(0);
    }

    private void refreshDots(int positionInReal) {
        for (int position = 0; position < indicatorsView.getChildCount(); position++) {
            ((IndicatorDotView) indicatorsView.getChildAt(position)).setDotColor(IndicatorDotView.DEFAULT_DOT_COLOR_UNSELECTED);
        }
        ((IndicatorDotView) indicatorsView.getChildAt(positionInReal)).setDotColor(IndicatorDotView.DEFAULT_DOT_COLOR_SELECTED);
    }

}
