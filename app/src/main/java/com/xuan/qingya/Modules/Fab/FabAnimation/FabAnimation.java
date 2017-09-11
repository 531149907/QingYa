package com.xuan.qingya.Modules.Fab.FabAnimation;

import android.animation.Animator;
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
    private FloatingActionButton fab;
    private CardView card;

    private final float SCALE_FACT = 3.0f;

    private int fabRadius;
    private int fabLeft;
    private int fabTop;

    private int cardTop;
    private int cardLeft;
    private int cardWidth;
    private int cardHeight;

    private OnAnimateListener onAnimateListener;


    public FabAnimation(FloatingActionButton fab, CardView cardView) {
        this.fab = fab;
        this.card = cardView;
        getParams();
    }

    public void setOnAnimateListener(OnAnimateListener onAnimateListener) {
        this.onAnimateListener = onAnimateListener;
    }

    public void start() {
        animationStart();
    }

    private void getParams() {

        if (card.getVisibility() != View.INVISIBLE) {
            card.setVisibility(View.INVISIBLE);
        }

        fab.post(new Runnable() {
            @Override
            public void run() {
                fabRadius = fab.getHeight() / 2;
                fabTop = fab.getTop();
                fabLeft = fab.getLeft();
            }
        });
        card.post(new Runnable() {
            @Override
            public void run() {
                cardHeight = card.getHeight();
                cardWidth = card.getWidth();
                cardTop = card.getTop();
                cardLeft = card.getLeft();
            }
        });
    }

    private void animationStart() {

        int endY = (int) (fabTop - cardTop - (cardHeight - SCALE_FACT * 2 * fabRadius - 70));
        int endX = fabLeft - cardLeft - cardWidth / 2 + fabRadius;

        Path fabPath = new Path();
        fabPath.moveTo(0, 0);
        fabPath.quadTo(-endX, 0, -endX, -endY);

        ObjectAnimator fabAnimator = ObjectAnimator.ofFloat(fab, "translationX", "translationY", fabPath);
        fabAnimator.setDuration(400);
        fabAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        fabAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                if (onAnimateListener != null) {
                    onAnimateListener.onAnimationStart();
                }
            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        ObjectAnimator animator = ObjectAnimator.ofInt(fab, "imageAlpha", 100, 0);
        animator.setDuration(250);

        ObjectAnimator fabScale1 = ObjectAnimator.ofFloat(fab, "scaleX", 1, SCALE_FACT);
        ObjectAnimator fabScale2 = ObjectAnimator.ofFloat(fab, "scaleY", 1, SCALE_FACT);
        fabScale1.setDuration(300);
        fabScale2.setDuration(300);

        fabScale1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                fab.setPivotX(fab.getWidth() / 2);
                fab.setPivotY(fab.getWidth() / 2);
            }
        });
        fabScale1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                card.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int cy = cardTop + (int) fab.getY() + fab.getHeight() / 2;

                        Animator animator1 = ViewAnimationUtils.createCircularReveal(card, cardWidth / 2, cy, fabRadius * SCALE_FACT, 800);
                        animator1.setInterpolator(new DecelerateInterpolator());
                        animator1.setDuration(300);
                        animator1.start();
                        animator1.addListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {
                                if (onAnimateListener != null) {
                                    onAnimateListener.onAnimationEnd();
                                }
                            }

                            @Override
                            public void onAnimationCancel(Animator animator) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animator) {

                            }
                        });
                        card.setVisibility(View.VISIBLE);
                    }
                }, 280);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                fab.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        fabAnimator.start();
        animator.start();
        fabScale1.start();
        fabScale2.start();

    }


    public interface OnAnimateListener {
        void onAnimationEnd();

        void onAnimationStart();
    }

}
