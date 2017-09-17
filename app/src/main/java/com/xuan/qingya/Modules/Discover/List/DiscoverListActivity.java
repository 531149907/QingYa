package com.xuan.qingya.Modules.Discover.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
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

import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Core.Base.BaseActivity;
import com.xuan.qingya.Models.Entity.DiscoverListBean;
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
    private int default_id = Constant.SIMPLIFY_CONTENT_TYPE_ARTICLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_list);
        isNotTransparentStatusBar();

        init();
        initListeners(bookEntry, photoEntry, musicEntry, movieEntry, questionEntry, selector);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.discover_list_toolbar:
                onBackPressed();
                return;
            case R.id.discover_list_toolbar_title:
                AppbarExpandedAnimation();
                return;
            case R.id.discover_book:
                default_id = Constant.SIMPLIFY_CONTENT_TYPE_ARTICLE;
                break;
            case R.id.discover_photo:
                default_id = Constant.SIMPLIFY_CONTENT_TYPE_PHOTOGRAPHY;
                break;
            case R.id.discover_music:
                default_id = Constant.SIMPLIFY_CONTENT_TYPE_MUSIC;
                break;
            case R.id.discover_movie:
                default_id = Constant.SIMPLIFY_CONTENT_TYPE_MOVIE;
                break;
            case R.id.discover_question:
                default_id = Constant.SIMPLIFY_CONTENT_TYPE_QUESTION;
                break;

        }
        adapter.setData(presenter.getData(default_id, 0));
        adapter.notifyDataSetChanged();
        AppbarExpandedAnimation();
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
            final ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) appBarLayout.getLayoutParams();

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
            final ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) appBarLayout.getLayoutParams();

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
        Intent intent = getIntent();

        new DiscoverListPresenter(this);

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

        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) appBarLayout.getLayoutParams();
        params.height = DensityUtil.dip2px(56);
        appBarLayout.setLayoutParams(params);

        int discoverID = intent.getIntExtra("discoverID", 0);

        adapter = new DiscoverListRecyclerViewAdapter(this, presenter, presenter.getData(discoverID, 8));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.getItemAnimator().setChangeDuration(0);
    }

    @Override
    protected void initListeners(@Nullable View... views) {
        super.initListeners(views);
        toolbar.setNavigationOnClickListener(this);
        adapter.addOnClickListener(new DiscoverListRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, DiscoverListBean bean, int position) {

            }
        });
    }
}