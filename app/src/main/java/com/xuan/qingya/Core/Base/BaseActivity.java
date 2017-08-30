package com.xuan.qingya.Core.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xuan.qingya.R;

/**
 * Created by zhouzhixuan on 2017/8/21.
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView()
                .setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

    }

    protected void isNotTransparentStatusBar() {
        getWindow().setStatusBarColor(getResources().getColor(R.color.statusBar));
    }

    protected void isTransparentStatusBar() {
        getWindow().setStatusBarColor(getResources().getColor(R.color.statusBarTransparent));
    }

    protected <T extends View> T $(int resId) {
        return super.findViewById(resId);
    }

    protected void initListeners(@Nullable View... views) {
        if (views != null) {
            for (View view : views) {
                view.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View view) {

    }
}
