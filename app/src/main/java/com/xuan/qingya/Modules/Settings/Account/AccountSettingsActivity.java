package com.xuan.qingya.Modules.Settings.Account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Core.base.BaseActivity;
import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.Modules.Settings.Account.Avatar.AvatarFragment;
import com.xuan.qingya.Modules.Settings.Account.MainEntry.MainEntryFragment;
import com.xuan.qingya.Modules.Settings.Account.Password.PasswordFragment;
import com.xuan.qingya.Modules.Settings.Account.Signature.SignatureFragment;
import com.xuan.qingya.Modules.Settings.Account.Username.UsernameFragment;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.FragmentManagement;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountSettingsActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        isNotTransparentStatusBar();
        ButterKnife.bind(this);

        init();
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    public void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        int entry = intent.getIntExtra(Constant.ENTRY_TYPE, 0);
        switch (entry) {
            case Constant.FRAGMENT_ACCOUNT_SETTINGS_MAIN_ENTRY:
                setToolbarTitle("个性化");
                MainEntryFragment fragment = new MainEntryFragment();
                FragmentManagement.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fragment_container);
                break;
            case Constant.FRAGMENT_ACCOUNT_SETTINGS_AVATAR:
                setToolbarTitle("更新头像");
                AvatarFragment fragment0 = new AvatarFragment();
                FragmentManagement.addFragmentToActivity(getSupportFragmentManager(), fragment0, R.id.fragment_container);
                break;
            case Constant.FRAGMENT_ACCOUNT_SETTINGS_PASSWORD:
                setToolbarTitle("更换密码");
                PasswordFragment fragment1 = new PasswordFragment();
                FragmentManagement.addFragmentToActivity(getSupportFragmentManager(), fragment1, R.id.fragment_container);
                break;
            case Constant.FRAGMENT_ACCOUNT_SETTINGS_SIGNATURE:
                setToolbarTitle("个性签名");
                SignatureFragment fragment2 = new SignatureFragment();
                FragmentManagement.addFragmentToActivity(getSupportFragmentManager(), fragment2, R.id.fragment_container);
                break;
            case Constant.FRAGMENT_ACCOUNT_SETTINGS_USERNAME:
                setToolbarTitle("昵称");
                UsernameFragment fragment3 = new UsernameFragment();
                FragmentManagement.addFragmentToActivity(getSupportFragmentManager(), fragment3, R.id.fragment_container);
                break;
        }
    }

    public void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }
}
