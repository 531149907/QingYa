package com.xuan.qingya.Modules.Settings.Account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Core.Base.BaseActivity;
import com.xuan.qingya.Modules.Settings.Account.Avatar.AvatarFragment;
import com.xuan.qingya.Modules.Settings.Account.Avatar.AvatarPresenter;
import com.xuan.qingya.Modules.Settings.Account.MainEntry.MainEntryFragment;
import com.xuan.qingya.Modules.Settings.Account.MainEntry.MainEntryPresenter;
import com.xuan.qingya.Modules.Settings.Account.Password.PasswordFragment;
import com.xuan.qingya.Modules.Settings.Account.Password.PasswordPresenter;
import com.xuan.qingya.Modules.Settings.Account.Signature.SignatureFragment;
import com.xuan.qingya.Modules.Settings.Account.Signature.SignaturePresenter;
import com.xuan.qingya.Modules.Settings.Account.Username.UsernameFragment;
import com.xuan.qingya.Modules.Settings.Account.Username.UsernamePresenter;
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
                setToolbarTitle("更新头像");
                AvatarFragment fragment0 = new AvatarFragment();
                FragmentManagement.addFragmentToActivity(getSupportFragmentManager(), fragment0, R.id.fragment_container);
                new AvatarPresenter(fragment0);
                break;
            case Constant.FRAGMENT_ACCOUNT_SETTINGS_PASSWORD:
                setToolbarTitle("更换密码");
                PasswordFragment fragment1 = new PasswordFragment();
                FragmentManagement.addFragmentToActivity(getSupportFragmentManager(), fragment1, R.id.fragment_container);
                new PasswordPresenter(fragment1);
                break;
            case Constant.FRAGMENT_ACCOUNT_SETTINGS_SIGNATURE:
                setToolbarTitle("个性签名");
                SignatureFragment fragment2 = new SignatureFragment();
                FragmentManagement.addFragmentToActivity(getSupportFragmentManager(), fragment2, R.id.fragment_container);
                new SignaturePresenter(fragment2);
                break;
            case Constant.FRAGMENT_ACCOUNT_SETTINGS_USERNAME:
                setToolbarTitle("昵称");
                UsernameFragment fragment3 = new UsernameFragment();
                FragmentManagement.addFragmentToActivity(getSupportFragmentManager(), fragment3, R.id.fragment_container);
                new UsernamePresenter(fragment3);
                break;
        }
    }

    public void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }
}
