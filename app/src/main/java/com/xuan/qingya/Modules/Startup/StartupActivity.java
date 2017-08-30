package com.xuan.qingya.Modules.Startup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xuan.qingya.Core.Base.BaseActivity;
import com.xuan.qingya.Modules.Startup.Login.LoginFragment;
import com.xuan.qingya.Modules.Startup.Login.LoginPresenter;
import com.xuan.qingya.Modules.Startup.Register.RegisterFragment;
import com.xuan.qingya.Modules.Startup.Register.RegisterPresenter;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.FragmentManagement;

public class StartupActivity extends BaseActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        isNotTransparentStatusBar();
        init();

        Intent intent = getIntent();
        int fragmentOperation = intent.getIntExtra("fragmentOperation", 0);

        if (fragmentOperation == 0) {
            RegisterFragment registerFragment = new RegisterFragment();
            FragmentManagement.switchFragment(getSupportFragmentManager(), registerFragment, R.id.startup_container, false);
            new RegisterPresenter(registerFragment);
        }

        if (fragmentOperation == 1) {
            LoginFragment loginFragment = new LoginFragment();
            FragmentManagement.switchFragment(getSupportFragmentManager(), loginFragment, R.id.startup_container, false);
            new LoginPresenter(loginFragment);
        }
    }

    private void init() {
        toolbar = $(R.id.startup_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }
}
