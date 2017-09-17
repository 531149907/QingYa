package com.xuan.qingya.Modules.Interview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.xuan.qingya.Common.Listeners.AppBarStateChangeListener;
import com.xuan.qingya.Core.Base.BaseActivity;
import com.xuan.qingya.Models.Entity.InterviewBean;
import com.xuan.qingya.R;

public class InterviewDetailActivity extends BaseActivity implements InterviewDetailContract.InterviewDetailView {

    private InterviewDetailContract.InterviewDetailPresenter presenter;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private ImageButton loveButton;
    private TextView toolbarTitle;
    TextView title, author, content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview_detail);
        isTransparentStatusBar();
        init();
        initListeners();
        initAnimation();
    }

    @Override
    public void setPresenter(InterviewDetailContract.InterviewDetailPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bean_bundle");
        InterviewBean bean = bundle.getParcelable("bean");

        title = $(R.id.interview_detail_title);
        author = $(R.id.interview_detail_author);
        content = $(R.id.interview_detail_content);
        loveButton = $(R.id.love);
        toolbar = $(R.id.interview_detail_toolbar);
        appBarLayout = $(R.id.interview_detail_appbar_layout);
        toolbarTitle = $(R.id.title);

        title.setText(bean.getTitle());
        author.setText(bean.getAuthor());
        content.setText(bean.getContent());

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setTint(getResources().getColor(R.color.white));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        loveButton.getDrawable().setTint(getResources().getColor(R.color.white));
        toolbarTitle.setTextColor(getResources().getColor(R.color.white));

        ImageView cover = $(R.id.interview_detail_cover);
        Glide.with(this).load(bean.getCover())
                .transition(new DrawableTransitionOptions()
                        .crossFade(200)).into(cover);
    }

    @Override
    protected void initListeners(@Nullable View... views) {
        toolbar.setNavigationOnClickListener(this);
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

        title.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animator = ObjectAnimator.ofFloat(title, "translationY", 100, 0);
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(title, "alpha", 0, 1);
                AnimatorSet set = new AnimatorSet();
                set.playTogether(animator, animator1);
                set.setDuration(400);
                set.setInterpolator(new DecelerateInterpolator());
                set.start();
            }
        }, 600);

        author.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animator = ObjectAnimator.ofFloat(author, "translationY", 100, 0);
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(author, "alpha", 0, 1);
                AnimatorSet set = new AnimatorSet();
                set.playTogether(animator, animator1);
                set.setDuration(300);
                set.setInterpolator(new DecelerateInterpolator());
                set.start();
            }
        }, 700);

        content.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animator = ObjectAnimator.ofFloat(content, "translationY", 100, 0);
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(content, "alpha", 0, 1);
                AnimatorSet set = new AnimatorSet();
                set.playTogether(animator, animator1);
                set.setDuration(200);
                set.setInterpolator(new DecelerateInterpolator());
                set.start();
            }
        }, 800);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.interview_detail_toolbar:
                onBackPressed();
        }
    }
}
