package com.xuan.qingya.Modules.Interview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xuan.qingya.Common.Listeners.AppBarStateChangeListener;
import com.xuan.qingya.Core.Observer.AnimationObserver.AnimationController;
import com.xuan.qingya.Core.Observer.AnimationObserver.AnimationObserverContract;
import com.xuan.qingya.Core.base.BaseActivity;
import com.xuan.qingya.Models.entity.Interview;
import com.xuan.qingya.Models.entity.ItemViewInfo;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.DensityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InterviewDetailActivity extends BaseActivity<ViewContract, InterviewDetailPresenter> implements ViewContract, AnimationObserverContract.AnimationObserver {
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.fakeAppBar)
    AppBarLayout fakeAppbarLayout;
    @BindView(R.id.cover)
    ImageView cover;
    @BindView(R.id.toolbarCloseButton)
    ImageButton closeButton;
    @BindView(R.id.toolbarMoreButton)
    ImageButton moreButton;
    @BindView(R.id.toolbarLoveButton)
    ImageButton loveButton;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.contentTitle)
    TextView title;
    @BindView(R.id.contentAuthor)
    TextView author;
    @BindView(R.id.contentText)
    TextView content;
    @BindView(R.id.backgroundLayout)
    FrameLayout backgroundLayout;

    private ItemViewInfo viewInfo;
    private Interview bean;
    private boolean isAppbarOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview_detail);
        ButterKnife.bind(this);
        isTransparentStatusBar();

        init();
        initListeners(closeButton, moreButton, loveButton);
    }

    @Override
    public void onBackPressed() {
        AnimationController.getInstance().doAnimation("EXIT");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                overridePendingTransition(0, 0);
            }
        }, 450);
    }

    public void init() {
        Intent intent = getIntent();
        bean = intent.getParcelableExtra("bean");
        viewInfo = intent.getParcelableExtra("viewInfo");
        isAppbarOverlay = intent.getBooleanExtra("isAppbarOverlay", false);

        AnimationController.getInstance().addObserver(this);

        closeButton.getDrawable().setTint(getResources().getColor(R.color.white));
        loveButton.getDrawable().setTint(getResources().getColor(R.color.white));
        moreButton.getDrawable().setTint(getResources().getColor(R.color.white));

        Glide.with(this).load(bean.getCoverContent()).into(cover);

        backgroundLayout.post(new Runnable() {
            @Override
            public void run() {
                backgroundLayout.setAlpha(0);
                backgroundLayout.setY(viewInfo.getY());
                final FrameLayout.LayoutParams backgroundLayoutParams = (FrameLayout.LayoutParams) backgroundLayout.getLayoutParams();
                backgroundLayoutParams.height = viewInfo.getHeight();
                backgroundLayout.setLayoutParams(backgroundLayoutParams);
            }
        });
    }

    @Override
    protected void initListeners(@Nullable View... views) {
        super.initListeners(views);
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.COLLAPSED) {
                    loveButton.getDrawable().setTint(getResources().getColor(R.color.defaultIconTint));
                    moreButton.getDrawable().setTint(getResources().getColor(R.color.defaultIconTint));
                    closeButton.getDrawable().setTint(getResources().getColor(R.color.defaultIconTint));
                } else {
                    loveButton.getDrawable().setTint(getResources().getColor(R.color.white));
                    moreButton.getDrawable().setTint(getResources().getColor(R.color.white));
                    closeButton.getDrawable().setTint(getResources().getColor(R.color.white));
                }

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbarCloseButton:
                closeButton.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onBackPressed();
                    }
                }, 100);
                break;
            case R.id.toolbarLoveButton:
                presenter.onLoveClick(bean);
                break;
        }
    }

    @Override
    public InterviewDetailPresenter initPresenter() {
        return new InterviewDetailPresenter();
    }

    @Override
    public void doExitAnimation() {
        final int ANIMATION_DURATION = 350;
        final float screenHeight = DensityUtil.getScreenHeight();
        final int appBarHeight = DensityUtil.dip2px(80);

        final ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(backgroundLayout, "alpha", 1, 0);
        alphaAnimation.setDuration((long) (ANIMATION_DURATION * 0.5));

        final FrameLayout.LayoutParams backgroundLayoutParams = (FrameLayout.LayoutParams) backgroundLayout.getLayoutParams();
        final Animation backgroundLayoutAnimation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                backgroundLayoutParams.height = (int) (screenHeight - (screenHeight - viewInfo.getHeight()) * interpolatedTime);
                backgroundLayout.setLayoutParams(backgroundLayoutParams);
                backgroundLayout.setY(viewInfo.getY() * interpolatedTime);
                fakeAppbarLayout.setY(-appBarHeight * (1 - interpolatedTime));
            }
        };
        backgroundLayoutAnimation.setDuration(ANIMATION_DURATION);
        backgroundLayoutAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        backgroundLayoutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                alphaAnimation.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        backgroundLayout.startAnimation(backgroundLayoutAnimation);
    }

    @Override
    public void doEnterAnimation() {
        final int ANIMATION_DURATION = 350;
        final float screenHeight = DensityUtil.getScreenHeight();
        final int appBarHeight = DensityUtil.dip2px(80);

        scrollView.setVisibility(View.VISIBLE);

        final ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(backgroundLayout, "alpha", 0, 1);
        alphaAnimation.setDuration((long) (ANIMATION_DURATION * 0.8));
        alphaAnimation.setStartDelay((long) (ANIMATION_DURATION * 0.5));

        final FrameLayout.LayoutParams backgroundLayoutParams = (FrameLayout.LayoutParams) backgroundLayout.getLayoutParams();
        final Animation backgroundLayoutAnimation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                backgroundLayoutParams.height = (int) (viewInfo.getHeight() + (screenHeight - viewInfo.getHeight()) * interpolatedTime);
                backgroundLayout.setLayoutParams(backgroundLayoutParams);
                backgroundLayout.setY(viewInfo.getY() * (1 - interpolatedTime));
                if (isAppbarOverlay) {
                    fakeAppbarLayout.setY(-appBarHeight * interpolatedTime);
                } else {
                    if (backgroundLayout.getY() <= appBarHeight) {
                        fakeAppbarLayout.setY(-(appBarHeight - backgroundLayout.getY()));
                    }
                }
            }
        };
        backgroundLayoutAnimation.setDuration(ANIMATION_DURATION);
        backgroundLayoutAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        backgroundLayoutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                alphaAnimation.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                title.setText(bean.getTitle());
                author.setText(bean.getAuthor());
                content.setText(bean.getContent());

                toolbar.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ObjectAnimator animator = ObjectAnimator.ofFloat(toolbar, "translationY", -240, 0);
                        ObjectAnimator animator1 = ObjectAnimator.ofFloat(toolbar, "alpha", 0, 1);
                        AnimatorSet set = new AnimatorSet();
                        set.playTogether(animator, animator1);
                        set.setDuration(350);
                        set.setInterpolator(new DecelerateInterpolator());
                        set.start();
                    }
                }, 50);

                scrollView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ObjectAnimator animator = ObjectAnimator.ofFloat(scrollView, "translationY", 100, 0);
                        ObjectAnimator animator1 = ObjectAnimator.ofFloat(scrollView, "alpha", 0, 1);
                        AnimatorSet set = new AnimatorSet();
                        set.playTogether(animator, animator1);
                        set.setDuration(350);
                        set.setInterpolator(new DecelerateInterpolator());
                        set.start();
                    }
                }, 50);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        backgroundLayout.startAnimation(backgroundLayoutAnimation);
    }
}
