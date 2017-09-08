package com.xuan.qingya.Modules.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Core.Base.BaseActivity;
import com.xuan.qingya.Modules.Profile.Collection.CollectionFragment;
import com.xuan.qingya.Modules.Profile.Collection.CollectionPresenter;
import com.xuan.qingya.Modules.Profile.Notification.List.NotificationFragment;
import com.xuan.qingya.Modules.Profile.Notification.List.NotificationPresenter;
import com.xuan.qingya.Modules.Profile.PlayHistory.PlayHistoryFragment;
import com.xuan.qingya.Modules.Profile.PlayHistory.PlayHistoryPresenter;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.FragmentManagement;

public class ProfileActivity extends BaseActivity implements ProfileContract.ProfileView {

    private ProfileContract.ProfilePresenter presenter;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        init();
    }


    @Override
    public void setPresenter(ProfileContract.ProfilePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {
        toolbar = $(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        new ProfilePresenter(this);

        Intent intent = getIntent();
        switch (intent.getIntExtra("entryTypeCode", 0)) {
            case Constant.FRAGMENT_NOTIFICATION_LIST:
                NotificationFragment fragment0 = new NotificationFragment();
                FragmentManagement.addFragmentToActivity(getSupportFragmentManager(), fragment0, R.id.fragment_container);
                new NotificationPresenter(fragment0);
                break;
            case Constant.FRAGMENT_COLLECTION:
                CollectionFragment fragment1 = new CollectionFragment();
                FragmentManagement.addFragmentToActivity(getSupportFragmentManager(), fragment1, R.id.fragment_container);
                new CollectionPresenter(fragment1);
                break;
            case Constant.FRAGMENT_PLAYHISTORY:
                PlayHistoryFragment fragment2 = new PlayHistoryFragment();
                FragmentManagement.addFragmentToActivity(getSupportFragmentManager(), fragment2, R.id.fragment_container);
                new PlayHistoryPresenter(fragment2);
                break;
        }
    }

    @Override
    public void ShowDeleteToolbar() {

    }

    public void setToolbarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
