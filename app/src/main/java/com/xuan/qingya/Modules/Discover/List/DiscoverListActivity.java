package com.xuan.qingya.Modules.Discover.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xuan.qingya.Core.Base.BaseActivity;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.DensityUtil;

public class DiscoverListActivity extends BaseActivity implements DiscoverListContract.DiscoverListView {

    private DiscoverListContract.DiscoverListPresenter presenter;

    private LinearLayout selector;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private RelativeLayout bookEntry, photoEntry, musicEntry, movieEntry, questionEntry;
    private TextView title;
    private ImageView title_arrow;
    private RecyclerView recyclerView;
    private DiscoverListRecyclerViewAdapter adapter;

    private boolean isAppbarExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_list);
        isNotTransparentStatusBar();

        initListeners(bookEntry, photoEntry, musicEntry, movieEntry, questionEntry, selector);

    }

    @Override
    public void onClick(View view) {
        int clickId = 0;
        switch (view.getId()) {
            case R.id.discover_list_toolbar_title:
                AppbarExpandedAnimation();
                break;
            case R.id.discover_book:
                clickId = 0;
                AppbarExpandedAnimation();
                break;
            case R.id.discover_photo:
                clickId = 1;
                AppbarExpandedAnimation();
                break;
            case R.id.discover_music:
                clickId = 2;
                AppbarExpandedAnimation();
                break;
            case R.id.discover_movie:
                clickId = 3;
                AppbarExpandedAnimation();
                break;
            case R.id.discover_question:
                clickId = 4;
                AppbarExpandedAnimation();
                break;
            case R.id.discover_list_toolbar:
                onBackPressed();
                break;
        }

    }

    @Override
    public void onBackPressed() {
        if (isAppbarExpanded) {
            AppbarExpandedAnimation();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void AppbarExpandedAnimation() {
        if (!isAppbarExpanded) {
            final int newHeight = DensityUtil.dip2px(100);
            final int originalHeight = DensityUtil.dip2px(56);
            final CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();

            Interpolator interpolator = AnimationUtils.loadInterpolator(getApplicationContext(), R.interpolator.msf_interpolator);
            Animation animation = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    params.height = originalHeight + (int) (newHeight * interpolatedTime);
                    appBarLayout.setLayoutParams(params);
                    title_arrow.setRotation(180 * interpolatedTime);

                }
            };
            animation.setInterpolator(interpolator);
            animation.setDuration(300);
            appBarLayout.startAnimation(animation);
            isAppbarExpanded = true;
        } else {
            final int newHeight = DensityUtil.dip2px(100);
            final int originalHeight = DensityUtil.dip2px(156);
            final CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();

            Interpolator interpolator = AnimationUtils.loadInterpolator(getApplicationContext(), R.interpolator.msf_interpolator);
            Animation animation = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    params.height = originalHeight - (int) (newHeight * interpolatedTime);
                    appBarLayout.setLayoutParams(params);
                    title_arrow.setRotation(180 - 180 * interpolatedTime);
                }
            };
            animation.setInterpolator(interpolator);
            animation.setDuration(300);
            appBarLayout.startAnimation(animation);
            isAppbarExpanded = false;
        }
    }

    @Override
    public void ShowHistoryFragment() {

    }

    @Override
    public void ShowMusicPlayerFragment() {

    }

    @Override
    public void setPresenter(DiscoverListContract.DiscoverListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {
        toolbar = $(R.id.discover_list_toolbar);
        title = $(R.id.discover_list_toolbar_title_text);
        title_arrow = $(R.id.discover_list_toolbar_title_arrow);
        bookEntry = $(R.id.discover_book);
        photoEntry = $(R.id.discover_photo);
        musicEntry = $(R.id.discover_music);
        movieEntry = $(R.id.discover_movie);
        questionEntry = $(R.id.discover_question);
        selector = $(R.id.discover_list_toolbar_title);
        appBarLayout = $(R.id.discover_list_appbar_layout);
        recyclerView = $(R.id.discover_list_recyclerview);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title_arrow.setPivotX(36);
        title_arrow.setPivotY(36);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
        params.height = DensityUtil.dip2px(56);
        appBarLayout.setLayoutParams(params);

        adapter = new DiscoverListRecyclerViewAdapter();
    }

    @Override
    protected void initListeners(@Nullable View... views) {
        super.initListeners(views);
        toolbar.setNavigationOnClickListener(this);
    }
}