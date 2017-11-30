package com.xuan.qingya.Modules.Settings.General;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Core.base.BaseActivity;
import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.Modules.Settings.General.About.AboutFragment;
import com.xuan.qingya.Modules.Settings.General.Agreement.AgreementFragment;
import com.xuan.qingya.Modules.Settings.General.Contact.ContactFragment;
import com.xuan.qingya.Modules.Settings.General.Entry.EntryFragment;
import com.xuan.qingya.Modules.Settings.General.Feedback.FeedbackFragment;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.FragmentManagement;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        isNotTransparentStatusBar();
        ButterKnife.bind(this);

        init();
        initListeners();
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    public void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        int entryType = intent.getIntExtra(Constant.ENTRY_TYPE, 0);
        Fragment fragment = null;
        switch (entryType) {
            case Constant.FRAGMENT_SETTINGS_ABOUT:
                setToolbarTitle("关于");
                fragment = new AboutFragment();
                break;
            case Constant.FRAGMENT_SETTINGS_AGREEMENT:
                setToolbarTitle("用户协议");
                fragment = new AgreementFragment();
                break;
            case Constant.FRAGMENT_SETTINGS_CONTACT:
                setToolbarTitle("联系我们");
                fragment = new ContactFragment();
                break;
            case Constant.FRAGMENT_SETTINGS_FEEDBACK:
                setToolbarTitle("意见与反馈");
                fragment = new FeedbackFragment();
                break;
            case Constant.FRAGMENT_SETTINGS_MAIN_ENTRY:
                setToolbarTitle("设置");
                fragment = new EntryFragment();
                break;
        }
        FragmentManagement.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fragment_container);
    }

    @Override
    protected void initListeners(@Nullable View... views) {
        super.initListeners(views);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }
}
