package com.xuan.qingya.Modules.Discover.Topic;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xuan.qingya.Common.Listeners.AppBarStateChangeListener;
import com.xuan.qingya.Core.Base.BaseActivity;
import com.xuan.qingya.R;

public class DiscoverTopicActivity extends BaseActivity implements DiscoverTopicContract.DiscoverTopicView {

    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private RecyclerView recyclerView;
    private TopicDetailAdapter adapter;
    private DiscoverTopicContract.DiscoverTopicPresenter presenter;
    private NestedScrollView scrollView;
    private ImageView cover;
    private ImageButton loveButton;
    private TextView toolbarTitle;
    private TextView contentTitle, content;
    private LinearLayout recyclerViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isTransparentStatusBar();
        setContentView(R.layout.activity_discover_topic);

        new DiscoverTopicPresenter(this);

        init();
        initAnimation();
        initListeners();
    }

    @Override
    public void setPresenter(DiscoverTopicContract.DiscoverTopicPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {
        recyclerView = $(R.id.recyclerView);
        appBarLayout = $(R.id.appbar);
        toolbar = $(R.id.toolbar);
        scrollView = $(R.id.scrollview);
        cover = $(R.id.cover);
        loveButton = $(R.id.love);
        toolbarTitle = $(R.id.title);
        contentTitle = $(R.id.contentTitle);
        content = $(R.id.content);
        recyclerViewContainer = $(R.id.recyclerViewContainer);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Glide.with(this).load(R.drawable.a17).into(cover);

        scrollView.setVisibility(View.VISIBLE);

        toolbar.getNavigationIcon().setTint(getResources().getColor(R.color.white));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        loveButton.getDrawable().setTint(getResources().getColor(R.color.white));
        toolbarTitle.setTextColor(getResources().getColor(R.color.white));

        adapter = new TopicDetailAdapter(this, presenter.getData(), presenter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initListeners(@Nullable View... views) {
        super.initListeners(views);
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.COLLAPSED) {
                    toolbar.getNavigationIcon().setTint(getResources().getColor(R.color.defaultIconTint));
                    toolbarTitle.setTextColor(getResources().getColor(R.color.black));
                    loveButton.getDrawable().setTint(getResources().getColor(R.color.defaultIconTint));
                } else {
                    toolbar.getNavigationIcon().setTint(getResources().getColor(R.color.white));
                    loveButton.getDrawable().setTint(getResources().getColor(R.color.white));
                    toolbarTitle.setTextColor(getResources().getColor(R.color.white));
                }

            }
        });
    }

    private void initAnimation() {
        toolbar.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animator = ObjectAnimator.ofFloat(toolbar, "translationY", -240, 0);
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(toolbar, "alpha", 0, 1);
                AnimatorSet set = new AnimatorSet();
                set.playTogether(animator, animator1);
                set.setDuration(400);
                set.setInterpolator(new DecelerateInterpolator());
                set.start();
            }
        }, 600);

        contentTitle.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animator = ObjectAnimator.ofFloat(contentTitle, "translationY", 100, 0);
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(contentTitle, "alpha", 0, 1);
                AnimatorSet set = new AnimatorSet();
                set.playTogether(animator, animator1);
                set.setDuration(400);
                set.setInterpolator(new DecelerateInterpolator());
                set.start();
            }
        }, 600);

        content.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animator = ObjectAnimator.ofFloat(content, "translationY", 100, 0);
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(content, "alpha", 0, 1);
                AnimatorSet set = new AnimatorSet();
                set.playTogether(animator, animator1);
                set.setDuration(300);
                set.setInterpolator(new DecelerateInterpolator());
                set.start();
            }
        }, 700);
    }


}
