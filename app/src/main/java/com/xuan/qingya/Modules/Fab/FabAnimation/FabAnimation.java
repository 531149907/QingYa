package com.xuan.qingya.Modules.Fab.FabAnimation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by zhouzhixuan on 2017/9/10.
 */

public class FabAnimation {
    private static final float SCALE_FACT = 3.0f;
    private static final int POSITION_FIX = 70;

    private FloatingActionButton fab;
    private CardView card;
    private MotionInfo motionInfo;

    private boolean isAnimating;
    private OnAnimateListener onAnimateListener;

    public FabAnimation(FloatingActionButton fab, CardView cardView) {
        this.fab = fab;
        this.card = cardView;
        this.motionInfo = new MotionInfo();
        isAnimating = false;
        initParams();
    }

    private void initParams() {
        if (card.getVisibility() != View.INVISIBLE) {
            card.setVisibility(View.INVISIBLE);
        }
        fab.post(new Runnable() {
            @Override
            public void run() {
                motionInfo.fabRadius = fab.getHeight() / 2;
                motionInfo.fabTop = fab.getTop();
                motionInfo.fabLeft = fab.getLeft();
            }
        });
        card.post(new Runnable() {
            @Override
            public void run() {
                motionInfo.cardHeight = card.getHeight();
                motionInfo.cardWidth = card.getWidth();
                motionInfo.cardTop = card.getTop();
                motionInfo.cardLeft = card.getLeft();
            }
        });
    }

    private void performAnimation() {
        int fabEndY = (int) (motionInfo.fabTop - motionInfo.cardTop -
                (motionInfo.cardHeight - SCALE_FACT * 2 * motionInfo.fabRadius - POSITION_FIX));
        int fabEndX = motionInfo.fabLeft - motionInfo.cardLeft - motionInfo.cardWidth / 2 + motionInfo.fabRadius;

        Path fabPath = new Path();
        fabPath.moveTo(0, 0);
        fabPath.quadTo(-fabEndX, 0, -fabEndX, -fabEndY);

        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator fabPositionAnimator = ObjectAnimator.ofFloat(fab, "translationX", "translationY", fabPath);
        ObjectAnimator fabIconAlphaAnimator = ObjectAnimator.ofInt(fab, "imageAlpha", 100, 0);
        ObjectAnimator fabScaleXAnimator = ObjectAnimator.ofFloat(fab, "scaleX", 1, SCALE_FACT);
        ObjectAnimator fabScaleYAnimator = ObjectAnimator.ofFloat(fab, "scaleY", 1, SCALE_FACT);

        fabPositionAnimator.setDuration(400);
        fabScaleXAnimator.setDuration(300);
        fabScaleYAnimator.setDuration(300);
        fabIconAlphaAnimator.setDuration(250);

        fabPositionAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        fabScaleXAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                fab.setPivotX(fab.getWidth() / 2);
                fab.setPivotY(fab.getWidth() / 2);

            }
        });

        animatorSet.playTogether(fabPositionAnimator, fabIconAlphaAnimator, fabScaleXAnimator, fabScaleYAnimator);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                isAnimating = true;
                fab.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        motionInfo.cy = motionInfo.cardTop + (int) fab.getY() + fab.getHeight() / 2;
                        Animator circularRevealAnimator = ViewAnimationUtils.createCircularReveal(card, motionInfo.cardWidth / 2, motionInfo.cy, motionInfo.fabRadius * SCALE_FACT, 800);
                        circularRevealAnimator.setDuration(300);
                        circularRevealAnimator.setInterpolator(new DecelerateInterpolator());
                        circularRevealAnimator.start();
                        card.setVisibility(View.VISIBLE);
                        fab.setVisibility(View.GONE);
                    }
                }, 280);
                if (onAnimateListener != null) {
                    onAnimateListener.onAnimationStart();
                }
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                isAnimating = false;
                if (onAnimateListener != null) {
                    onAnimateListener.onAnimationEnd();
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                isAnimating = false;
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                isAnimating = true;
            }
        });

        animatorSet.start();
    }

    public void setOnAnimateListener(OnAnimateListener onAnimateListener) {
        this.onAnimateListener = onAnimateListener;
    }

    public interface OnAnimateListener {
        void onAnimationEnd();

        void onAnimationStart();
    }

    public boolean isAnimating() {
        return isAnimating;
    }

    public void startAnimation() {
        if (!isAnimating) {
            performAnimation();
        }
    }

    private class MotionInfo {
        int fabRadius;
        int fabLeft;
        int fabTop;

        int cardTop;
        int cardLeft;
        int cardWidth;
        int cardHeight;

        int cy;
        int cx;

        int fabFinalCenterPositionX;
        int fabFinalCenterPositionY;
    }

}
