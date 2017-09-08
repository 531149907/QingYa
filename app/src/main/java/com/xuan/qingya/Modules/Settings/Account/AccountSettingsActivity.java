package com.xuan.qingya.Modules.Settings.Account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Core.Base.BaseActivity;
import com.xuan.qingya.Modules.Settings.Account.MainEntry.MainEntryFragment;
import com.xuan.qingya.Modules.Settings.Account.MainEntry.MainEntryPresenter;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.FragmentManagement;

public class AccountSettingsActivity extends BaseActivity implements AccountSettingsContract.AccountSettingsView {

    private AccountSettingsContract.AccountSettingsPresenter presenter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        isNotTransparentStatusBar();

        init();
    }

    @Override
    public void setPresenter(AccountSettingsContract.AccountSettingsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {
        toolbar = $(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        int entry = intent.getIntExtra(Constant.ENTRY_TYPE, 0);
        switch (entry) {
            case Constant.FRAGMENT_ACCOUNT_SETTINGS_MAIN_ENTRY:
                setToolbarTitle("个性化");
                MainEntryFragment fragment = new MainEntryFragment();
                new MainEntryPresenter(fragment);
                FragmentManagement.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fragment_container);
                break;
            case Constant.FRAGMENT_ACCOUNT_SETTINGS_AVATAR:
                break;
            case Constant.FRAGMENT_ACCOUNT_SETTINGS_USERNAME:
                break;
            case Constant.FRAGMENT_ACCOUNT_SETTINGS_SIGNATURE:
                break;
            case Constant.FRAGMENT_ACCOUNT_SETTINGS_PASSWORD:
                break;
        }
    }

    public void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }
}
