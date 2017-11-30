package com.xuan.qingya.Modules.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Core.base.BaseActivity;
import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.Models.entity.Base;
import com.xuan.qingya.Models.entity.Message;
import com.xuan.qingya.Modules.Profile.Collection.CollectionFragment;
import com.xuan.qingya.Modules.Profile.Notification.Detail.NotificationDetailFragment;
import com.xuan.qingya.Modules.Profile.Notification.List.NotificationFragment;
import com.xuan.qingya.Modules.Profile.PlayHistory.PlayHistoryFragment;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.FragmentManagement;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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
        Base bean = intent.getParcelableExtra(Constant.BEAN);
        Fragment fragment;
        switch (intent.getIntExtra(Constant.ENTRY_TYPE, 0)) {
            case Constant.FRAGMENT_NOTIFICATION_LIST:
                fragment = new NotificationFragment();
                FragmentManagement.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fragment_container);
                setToolbarTitle("消息");
                break;
            case Constant.FRAGMENT_NOTIFICATION_DETAIL:
                fragment = new NotificationDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constant.BEAN, (Message) bean);
                fragment.setArguments(bundle);
                FragmentManagement.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fragment_container);
                setToolbarTitle("消息详情");
                break;
            case Constant.FRAGMENT_COLLECTION:
                fragment = new CollectionFragment();
                FragmentManagement.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fragment_container);
                setToolbarTitle("点赞与收藏");
                break;
            case Constant.FRAGMENT_PLAYHISTORY:
                fragment = new PlayHistoryFragment();
                FragmentManagement.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fragment_container);
                setToolbarTitle("播放记录");
                break;
        }
    }

    public void setToolbarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
