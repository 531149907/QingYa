package com.xuan.qingya.Modules.Settings.General;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Core.Base.BaseActivity;
import com.xuan.qingya.Modules.Settings.General.About.AboutFragment;
import com.xuan.qingya.Modules.Settings.General.Agreement.AgreementFragment;
import com.xuan.qingya.Modules.Settings.General.Contact.ContactFragment;
import com.xuan.qingya.Modules.Settings.General.Entry.EntryFragment;
import com.xuan.qingya.Modules.Settings.General.Entry.EntryPresenter;
import com.xuan.qingya.Modules.Settings.General.Feedback.FeedbackFragment;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.FragmentManagement;

public class SettingsActivity extends BaseActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        isNotTransparentStatusBar();

        init();
    }

    public void init() {
        toolbar = $(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        int entryType = intent.getIntExtra(Constant.ENTRY_TYPE, 0);
        switch (entryType) {
            case Constant.FRAGMENT_SETTINGS_ABOUT:
                setToolbarTitle("关于");
                AboutFragment aboutFragment = new AboutFragment();
                FragmentManagement.addFragmentToActivity(getSupportFragmentManager(), aboutFragment, R.id.fragment_container);
                break;
            case Constant.FRAGMENT_SETTINGS_AGREEMENT:
                setToolbarTitle("用户协议");
                AgreementFragment agreementFragment = new AgreementFragment();
                FragmentManagement.addFragmentToActivity(getSupportFragmentManager(), agreementFragment, R.id.fragment_container);
                break;
            case Constant.FRAGMENT_SETTINGS_CONTACT:
                setToolbarTitle("联系我们");
                ContactFragment contactFragment = new ContactFragment();
                FragmentManagement.addFragmentToActivity(getSupportFragmentManager(), contactFragment, R.id.fragment_container);
                break;
            case Constant.FRAGMENT_SETTINGS_FEEDBACK:
                setToolbarTitle("意见与反馈");
                FeedbackFragment feedbackFragment = new FeedbackFragment();
                FragmentManagement.addFragmentToActivity(getSupportFragmentManager(), feedbackFragment, R.id.fragment_container);
                break;
            case Constant.FRAGEMNT_SETTINGS_MAIN_ENTRY:
                setToolbarTitle("设置");
                EntryFragment entryFragment = new EntryFragment();
                new EntryPresenter(entryFragment);
                FragmentManagement.addFragmentToActivity(getSupportFragmentManager(), entryFragment, R.id.fragment_container);
                break;
        }

    }

    private void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }
}
