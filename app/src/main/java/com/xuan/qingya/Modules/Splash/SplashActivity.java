package com.xuan.qingya.Modules.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Core.base.BaseActivity;
import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.FragmentManagement;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Fragment fragment;
        Intent intent = getIntent();
        int entryType = intent.getIntExtra(Constant.ENTRY_TYPE, Constant.FRAGMENT_SPLASH);
        if (entryType == Constant.FRAGMENT_SPLASH) {
            fragment = new SplashFragment();
        } else {
            fragment = new OptionFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(Constant.ENTRY_TYPE, entryType);
            fragment.setArguments(bundle);
        }

        FragmentManagement.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.splash_container);
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
