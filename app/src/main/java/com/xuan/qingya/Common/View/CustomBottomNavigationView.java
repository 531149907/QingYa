package com.xuan.qingya.Common.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.AttributeSet;

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

    public CustomBottomNavigationView(Context context, AttributeSet attrs)
            throws NoSuchFieldException, IllegalAccessException {
        super(context, attrs);
        setItemIconTintList(colorStateList);
        setItemTextColor(colorStateList);
        disableShiftMode(this);
    }

    @SuppressLint("RestrictedApi")
    public void disableShiftMode(BottomNavigationView view)
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
}
