package com.xuan.qingya.Modules.Fab;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;

import com.xuan.qingya.Core.base.BaseActivity;
import com.xuan.qingya.Modules.Fab.FabAnimation.FabAnimation;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.DensityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FabActivity extends BaseActivity<ViewContract, FabPresenter> implements ViewContract {

    @BindView(R.id.main_fab)
    FloatingActionButton mainFab;
    @BindView(R.id.fab_playing)
    FloatingActionButton playFab;
    @BindView(R.id.fab_history)
    FloatingActionButton historyFab;
    @BindView(R.id.card_playing)
    CardView playCard;
    @BindView(R.id.card_history)
    CardView historyCard;
    @BindView(R.id.history_panel)
    CardView historyPanel;
    @BindView(R.id.play_panel)
    CardView playPanel;
    @BindView(R.id.select_year)
    ImageButton select_year;
    @BindView(R.id.select_month)
    ImageButton select_month;

    private FabAnimation fabAnimation, fabAnimation2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab);
        ButterKnife.bind(this);
        isTransparentStatusBar();

        overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_alpha_out);
        init();
        initListeners(mainFab, historyFab, playFab, select_year, select_month);
    }

    private void init() {
        fabAnimation = new FabAnimation(historyFab, historyPanel);
        fabAnimation2 = new FabAnimation(playFab, playPanel);

        mainFab.postDelayed(new Runnable() {
            @Override
            public void run() {
                startFabAnimation();
            }
        }, 100);
    }

    private void startFabAnimation() {
        playFab.setPivotX(60);
        playFab.setPivotY(120);

        historyFab.setPivotX(60);
        historyFab.setPivotY(120);

        AnimatorSet playItem = new AnimatorSet();
        AnimatorSet historyItem = new AnimatorSet();

        final int fabTranslationY = DensityUtil.dip2px(5);
        Path fabTranslationPath = new Path();
        fabTranslationPath.moveTo(0, fabTranslationY);
        fabTranslationPath.lineTo(0, 0);

        final int cardTranslationY = DensityUtil.dip2px(10);
        Path cardTranslationPath = new Path();
        cardTranslationPath.moveTo(0, cardTranslationY);
        cardTranslationPath.lineTo(0, 0);

        ObjectAnimator animator = ObjectAnimator.ofFloat(mainFab, "rotation", 0, 135);

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(playFab, "alpha", 0, 1);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(playFab, "scaleY", 0, 1);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(playFab, "scaleX", 0, 1);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(playFab, "translationX", "translationY", fabTranslationPath);
        ObjectAnimator animator5 = ObjectAnimator.ofFloat(playCard, "alpha", 0, 1);
        ObjectAnimator animator6 = ObjectAnimator.ofFloat(playCard, "translationX", "translationY", cardTranslationPath);

        ObjectAnimator animator7 = ObjectAnimator.ofFloat(historyFab, "alpha", 0, 1);
        ObjectAnimator animator8 = ObjectAnimator.ofFloat(historyFab, "scaleY", 0, 1);
        ObjectAnimator animator9 = ObjectAnimator.ofFloat(historyFab, "scaleX", 0, 1);
        ObjectAnimator animator10 = ObjectAnimator.ofFloat(historyFab, "translationX", "translationY", fabTranslationPath);
        ObjectAnimator animator11 = ObjectAnimator.ofFloat(historyCard, "alpha", 0, 1);
        ObjectAnimator animator12 = ObjectAnimator.ofFloat(historyCard, "translationX", "translationY", cardTranslationPath);

        playItem.playTogether(animator, animator1, animator2, animator3, animator4, animator5, animator6);
        playItem.setDuration(160);
        playItem.setInterpolator(new DecelerateInterpolator());

        historyItem.playTogether(animator7, animator8, animator9, animator10, animator11, animator12);
        historyItem.setStartDelay(35);
        historyItem.setDuration(150);
        historyItem.setInterpolator(new DecelerateInterpolator());

        playItem.start();
        historyItem.start();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_fab:
                finish();
                break;
            case R.id.fab_history:
                fabAnimation.startAnimation();
                break;
            case R.id.fab_playing:
                fabAnimation2.startAnimation();
                break;
            case R.id.select_year:
                PopupMenu popupMenu = new PopupMenu(this, view);
                //popupMenu.setOnMenuItemClickListener(this);
                popupMenu.inflate(R.menu.menu_year_select);
                popupMenu.show();
                break;
            case R.id.select_month:
                PopupMenu popupMenu2 = new PopupMenu(this, view);
                //popupMen2.setOnMenuItemClickListener(this);
                popupMenu2.inflate(R.menu.menu_month_select);
                popupMenu2.show();
                break;
        }
    }

    @Override
    public FabPresenter initPresenter() {
        return new FabPresenter();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_alpha_out);
    }

    @Override
    public void updateMusicTitle(String musicTitle) {

    }

    @Override
    protected void initListeners(@Nullable View... views) {
        super.initListeners(views);
        fabAnimation.setOnAnimateListener(new FabAnimation.OnAnimateListener() {
            @Override
            public void onAnimationEnd() {
                playFab.setVisibility(View.GONE);
                historyFab.setVisibility(View.GONE);
                playCard.setVisibility(View.GONE);
                historyCard.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart() {
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(historyCard, "alpha", 1, 0);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(playCard, "alpha", 1, 0);
                ObjectAnimator animator3 = ObjectAnimator.ofFloat(playFab, "alpha", 1, 0);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(animator1, animator2, animator3);
                animatorSet.setDuration(200);
                animatorSet.start();

            }
        });

        fabAnimation2.setOnAnimateListener(new FabAnimation.OnAnimateListener() {
            @Override
            public void onAnimationEnd() {
                playFab.setVisibility(View.GONE);
                historyFab.setVisibility(View.GONE);
                playCard.setVisibility(View.GONE);
                historyCard.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart() {
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(historyCard, "alpha", 1, 0);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(playCard, "alpha", 1, 0);
                ObjectAnimator animator3 = ObjectAnimator.ofFloat(historyFab, "alpha", 1, 0);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(animator1, animator2, animator3);
                animatorSet.setDuration(200);
                animatorSet.start();
            }
        });
    }
}
