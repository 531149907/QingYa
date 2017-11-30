package com.xuan.qingya.Common.View;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.AttributeSet;
import android.view.animation.DecelerateInterpolator;

import com.xuan.qingya.R;

import java.lang.reflect.Field;

/**
 * Created by zhouzhixuan on 2017/8/25.
 */

public class CustomBottomNavigationView extends BottomNavigationView {

    private int[][] states = new int[][]{new int[]{-android.R.attr.state_checked},
            new int[]{android.R.attr.state_checked}};
    private int[] colors = new int[]{getResources().getColor(R.color.grey),
            getResources().getColor(R.color.colorAccent)};
    private ColorStateList colorStateList = new ColorStateList(states, colors);

    private boolean isShown = true;
    private boolean isAnimating = false;

    private ObjectAnimator hideAnimation, showAnimation;

    private final float barHeight = getResources().getDimensionPixelSize(R.dimen.bottom_navigation_height);

    @Override
    public boolean isShown() {
        return isShown;
    }

    public CustomBottomNavigationView(Context context, AttributeSet attrs)
            throws NoSuchFieldException, IllegalAccessException {
        super(context, attrs);
        setItemIconTintList(colorStateList);
        setItemTextColor(colorStateList);
        disableShiftMode(this);
        setupAnimation();
    }

    @SuppressLint("RestrictedApi")
    private void disableShiftMode(BottomNavigationView view)
            throws IllegalAccessException, NoSuchFieldException {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);

        Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
        shiftingMode.setAccessible(true);
        shiftingMode.setBoolean(menuView, false);
        shiftingMode.setAccessible(false);

        for (int i = 0; i < menuView.getChildCount(); i++) {
            BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
            item.setShiftingMode(false);
            item.setChecked(item.getItemData().isChecked());
        }

    }

    private void setupAnimation() {
        showAnimation = ObjectAnimator.ofFloat(this, "translationY", barHeight, 0);
        showAnimation.setDuration(350);
        showAnimation.setInterpolator(new DecelerateInterpolator());
        showAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimating = false;
                isShown = true;
            }
        });

        hideAnimation = ObjectAnimator.ofFloat(this, "translationY", 0, barHeight);
        hideAnimation.setDuration(350);
        hideAnimation.setInterpolator(new DecelerateInterpolator());
        hideAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimating = false;
                isShown = false;
            }
        });
    }

    public void showBarAnimation() {
        if (!isShown && !isAnimating) {
            showAnimation.start();
        }
    }

    public void hideBarAnimation() {
        if (isShown && !isAnimating) {
            hideAnimation.start();
        }

    }
}
