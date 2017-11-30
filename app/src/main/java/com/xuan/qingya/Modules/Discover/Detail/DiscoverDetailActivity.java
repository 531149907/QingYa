package com.xuan.qingya.Modules.Discover.Detail;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Common.Listeners.AppBarStateChangeListener;
import com.xuan.qingya.Core.Observer.AnimationObserver.AnimationController;
import com.xuan.qingya.Core.Observer.AnimationObserver.AnimationObserverContract;
import com.xuan.qingya.Core.base.BaseActivity;
import com.xuan.qingya.Models.entity.Article;
import com.xuan.qingya.Models.entity.ItemViewInfo;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.DensityUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscoverDetailActivity extends BaseActivity<ViewContract, DiscoverDetailPresenter> implements ViewContract, AnimationObserverContract.AnimationObserver {
    NestedScrollView nestedScrollView;
    @BindView(R.id.backgroundLayout)
    FrameLayout backgroundLayout;
    @BindView(R.id.cover)
    ImageView cover;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarCloseButton)
    ImageButton closeButton;
    @BindView(R.id.toolbarLoveButton)
    ImageButton loveButton;
    @BindView(R.id.toolbarMoreButton)
    ImageButton moreButton;

    @BindView(R.id.fakeAppBar)
    AppBarLayout fakeAppbarLayout;
    @BindView(R.id.fakeAppBarWithTabLayout)
    AppBarLayout fakeAppBarLayoutWithTabLayout;
    @BindView(R.id.fakeTabLayout)
    TabLayout fakeTabLayout;

    private ItemViewInfo viewInfo;
    private Article bean;
    private boolean isAppbarOverlay;
    private String entryMode;
    private int selectedTab;
    private int appBarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preInit();
        init();
        initListeners(loveButton, closeButton, moreButton);
    }

    private void preInit() {
        Intent intent = getIntent();
        bean = intent.getParcelableExtra("bean");
        viewInfo = intent.getParcelableExtra("viewInfo");
        isAppbarOverlay = intent.getBooleanExtra("isAppbarOverlay", false);
        selectedTab = intent.getIntExtra("selectedTab", 0);
        entryMode = intent.getStringExtra("entryMode");
        setContentView(R.layout.activity_discover_detail);
        isTransparentStatusBar();
        ButterKnife.bind(this);

        if (entryMode != null && entryMode.equals("fromDiscoverList")) {
            fakeAppbarLayout.setVisibility(View.GONE);
            appBarHeight = DensityUtil.dip2px(128);
            String[] titles = new String[]{"阅读", "摄影", "音乐", "影视", "问答"};
            for (int i = 0; i < 5; i++) {
                fakeTabLayout.addTab(fakeTabLayout.newTab().setText(titles[i]), i == selectedTab);
            }
        } else {
            fakeAppBarLayoutWithTabLayout.setVisibility(View.GONE);
            appBarHeight = DensityUtil.dip2px(80);
        }

        AnimationController.getInstance().addObserver(this);
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
    public void setContent(Article bean) {
        switch (bean.getSubType()) {
            case Constant.CONTENT_SUB_TYPE_ARTICLE_IMAGE:
                nestedScrollView = $(R.id.article_scrollview);
                TextView article_image_author = $(R.id.article_author);
                TextView article_image_content = $(R.id.article_content);
                article_image_author.setText(bean.getAuthor());
                article_image_content.setText(bean.getContent());
                break;
            case Constant.CONTENT_SUB_TYPE_ARTICLE_READ:
                nestedScrollView = $(R.id.article_scrollview);
                TextView article_read_title = $(R.id.article_title);
                TextView article_read_author = $(R.id.article_author);
                TextView article_read_content = $(R.id.article_content);
                article_read_title.setText(bean.getTitle());
                article_read_author.setText(bean.getAuthor());
                article_read_content.setText(bean.getContent());
                break;
            case Constant.CONTENT_SUB_TYPE_ARTICLE_POEM:
                nestedScrollView = $(R.id.article_scrollview);
                TextView article_poem_author = $(R.id.article_author);
                TextView article_poem_content = $(R.id.article_content);
                article_poem_author.setText(bean.getAuthor());
                article_poem_content.setText(bean.getContent());
                break;
            case Constant.CONTENT_SUB_TYPE_PHOTOGRAPHY:
                nestedScrollView = $(R.id.photo_scrollview);
                TextView photo_author = $(R.id.photo_author);
                photo_author.setText(bean.getAuthor());

                List<Map<String, Object>> data_list = new ArrayList<>();
                Map<String, Object> map1 = new HashMap<>();
                map1.put("title", "相机厂商");
                map1.put("figure", "Nikon");
                data_list.add(map1);
                Map<String, Object> map2 = new HashMap<>();
                map2.put("title", "型号");
                map2.put("figure", "Nikon D3300");
                data_list.add(map2);
                Map<String, Object> map3 = new HashMap<>();
                map3.put("title", "快门速度");
                map3.put("figure", "1/4000s");
                data_list.add(map3);
                Map<String, Object> map4 = new HashMap<>();
                map4.put("title", "光圈");
                map4.put("figure", "ƒ/1.6");
                data_list.add(map4);
                Map<String, Object> map5 = new HashMap<>();
                map5.put("title", "焦距");
                map5.put("figure", "35mm");
                data_list.add(map5);
                Map<String, Object> map6 = new HashMap<>();
                map6.put("title", "ISO");
                map6.put("figure", "100");
                data_list.add(map6);
                Map<String, Object> map7 = new HashMap<>();
                map7.put("title", "发布于");
                map7.put("figure", "2017/1/17");
                data_list.add(map7);
                Map<String, Object> map8 = new HashMap<>();
                map8.put("title", "图片尺寸");
                map8.put("figure", "3902 × 5464");
                data_list.add(map8);
                String[] from = {"title", "figure"};
                int[] to = {R.id.photo_detail_text, R.id.photo_detail_figures};
                SimpleAdapter sim_adapter = new SimpleAdapter(this, data_list, R.layout.layout_item_photo_detail_grid, from, to);
                GridView gridView = $(R.id.photo_grid_view);
                gridView.setAdapter(sim_adapter);
                break;
            case Constant.CONTENT_SUB_TYPE_MUSIC:
                nestedScrollView = $(R.id.music_scrollview);
                TextView music_title = $(R.id.music_title);
                TextView music_author = $(R.id.music_author);
                TextView music_content = $(R.id.music_content);
                music_title.setText(bean.getTitle());
                music_author.setText(bean.getAuthor());
                music_content.setText(bean.getContent());
                break;
            case Constant.CONTENT_SUB_TYPE_MOVIE:
                nestedScrollView = $(R.id.movie_scrollview);
                TextView movie_title = $(R.id.movie_title);
                TextView movie_author = $(R.id.movie_author);
                TextView movie_content = $(R.id.movie_content);
                movie_title.setText(bean.getTitle());
                movie_author.setText(bean.getAuthor());
                movie_content.setText(bean.getContent());
                break;
            case Constant.CONTENT_SUB_TYPE_QUESTION:
                nestedScrollView = $(R.id.ask_scrollview);
                TextView ask_title = $(R.id.ask_title);
                TextView ask_ask_author = $(R.id.ask_ask_author);
                TextView ask_ask_content = $(R.id.ask_ask_content);
                TextView ask_answer_author = $(R.id.ask_answer_author);
                TextView ask_answer_content = $(R.id.ask_answer_content);
                ask_title.setText(bean.getTitle());
                ask_ask_author.setText(bean.getAnswerAuthor());
                ask_ask_content.setText(bean.getAskContent());
                ask_answer_author.setText(bean.getAnswerAuthor());
                ask_answer_content.setText(bean.getAnswerContent());
                break;
        }
        nestedScrollView.setAlpha(0);
        nestedScrollView.setVisibility(View.VISIBLE);
    }

    public void init() {
        loveButton.getDrawable().setTint(getResources().getColor(R.color.white));
        moreButton.getDrawable().setTint(getResources().getColor(R.color.white));
        closeButton.getDrawable().setTint(getResources().getColor(R.color.white));

        Glide.with(this).load(bean.getCoverImg()).into(cover);

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

    @Override
    public void doExitAnimation() {
        final int ANIMATION_DURATION = 350;
        final float screenHeight = DensityUtil.getScreenHeight();

        final ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(backgroundLayout, "alpha", 1, 0);
        alphaAnimation.setDuration((long) (ANIMATION_DURATION * 0.5));

        final FrameLayout.LayoutParams backgroundLayoutParams = (FrameLayout.LayoutParams) backgroundLayout.getLayoutParams();
        final Animation backgroundLayoutAnimation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                backgroundLayoutParams.height = (int) (screenHeight - (screenHeight - viewInfo.getHeight()) * interpolatedTime);
                backgroundLayout.setLayoutParams(backgroundLayoutParams);
                backgroundLayout.setY(viewInfo.getY() * interpolatedTime);
                if (entryMode != null && entryMode.equals("fromDiscoverList")) {
                    fakeAppBarLayoutWithTabLayout.setY(-appBarHeight * (1 - interpolatedTime));
                } else {
                    fakeAppbarLayout.setY(-appBarHeight * (1 - interpolatedTime));
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
                    if (entryMode != null && entryMode.equals("fromDiscoverList")) {
                        fakeAppBarLayoutWithTabLayout.setY(-appBarHeight * interpolatedTime);
                    } else {
                        fakeAppbarLayout.setY(-appBarHeight * interpolatedTime);
                    }
                } else {
                    if (entryMode != null && entryMode.equals("fromDiscoverList")) {
                        if (backgroundLayout.getY() <= appBarHeight) {
                            fakeAppBarLayoutWithTabLayout.setY(-(appBarHeight - backgroundLayout.getY()));
                        }
                    } else {
                        if (backgroundLayout.getY() <= appBarHeight) {
                            fakeAppbarLayout.setY(-(appBarHeight - backgroundLayout.getY()));
                        }
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
                setContent(bean);
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

                nestedScrollView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ObjectAnimator animator = ObjectAnimator.ofFloat(nestedScrollView, "translationY", 100, 0);
                        ObjectAnimator animator1 = ObjectAnimator.ofFloat(nestedScrollView, "alpha", 0, 1);
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
                presenter.onLoveButtonClick(bean);
                break;
            case R.id.toolbarMoreButton:
                break;
        }
    }

    @Override
    public DiscoverDetailPresenter initPresenter() {
        return new DiscoverDetailPresenter();
    }
}
