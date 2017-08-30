package com.xuan.qingya.Modules.Splash;

import android.os.Bundle;

import com.xuan.qingya.Core.Base.BaseActivity;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.FragmentManagement;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        FragmentManagement.addFragmentToActivity(getSupportFragmentManager(), new SplashFragment(), R.id.splash_container);
    }
}
