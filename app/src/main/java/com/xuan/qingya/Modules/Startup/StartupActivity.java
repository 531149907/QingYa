package com.xuan.qingya.Modules.Startup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Core.base.BaseActivity;
import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.Modules.Startup.ForgotPassword.ForgotPasswordFragment;
import com.xuan.qingya.Modules.Startup.ForgotPassword.ForgotPasswordNextFragment;
import com.xuan.qingya.Modules.Startup.Login.LoginFragment;
import com.xuan.qingya.Modules.Startup.Register.RegisterFragment;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.FragmentManagement;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartupActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        isNotTransparentStatusBar();
        ButterKnife.bind(this);

        init();
        initListeners();
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Fragment fragment = null;
        int entryType = intent.getIntExtra(Constant.ENTRY_TYPE, Constant.FRAGMENT_REGISTER);
        switch (entryType) {
            case Constant.FRAGMENT_LOGIN:
                fragment = new LoginFragment();
                break;
            case Constant.FRAGMENT_REGISTER:
                fragment = new RegisterFragment();
                break;
            case Constant.FRAGMENT_FORGOT_PASSWORD:
                fragment = new ForgotPasswordFragment();
                break;
            case Constant.FRAGMENT_FORGOT_PASSWORD_NEXT:
                fragment = new ForgotPasswordNextFragment();
                break;
        }
        FragmentManagement.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.startup_container);
    }

    @Override
    protected void initListeners(@Nullable View... views) {
        super.initListeners(views);
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
