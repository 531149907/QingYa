package com.xuan.qingya.Modules.Main.Profile;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.xuan.qingya.Core.Base.BaseFragment;
import com.xuan.qingya.Core.Base.BasePresenter;
import com.xuan.qingya.Modules.Main.MainActivity;
import com.xuan.qingya.Modules.Main.MainContract;
import com.xuan.qingya.Modules.Profile.ProfileActivity;
import com.xuan.qingya.Modules.Settings.Account.AccountSettingsActivity;
import com.xuan.qingya.Modules.Settings.General.SettingsActivity;
import com.xuan.qingya.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment implements MainContract.ProfileView {

    private CardView notificationItem, settingsItem, loveItem, playHistoryItem, logoutItem;
    private RelativeLayout accountSettingsItem;

    private MainContract.ProfilePresenter presenter;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_profile, container, false);

        init();
        initListeners(notificationItem, settingsItem, loveItem, playHistoryItem, logoutItem, accountSettingsItem);

        return mRootView;
    }

    @Override
    public void setPresenter(MainContract.ProfilePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {
        notificationItem = $(R.id.profile_notification_item);
        settingsItem = $(R.id.profile_settings_item);
        loveItem = $(R.id.profile_love_item);
        playHistoryItem = $(R.id.profile_play_history_item);
        logoutItem = $(R.id.profile_love_item);
        accountSettingsItem = ((MainActivity) getActivity()).findViewById(R.id.profile_section);

        ((MainActivity) getActivity()).getViewPager().setObjectForPosition(mRootView, 3);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.profile_section:
                presenter.onListItemClicked(Constant.PROFILE_ACCOUNT_SETTINGS, null);
                intent = new Intent(getActivity(), AccountSettingsActivity.class);
                intent.putExtra(com.xuan.qingya.Common.Constant.ENTRY_TYPE, com.xuan.qingya.Common.Constant.FRAGMENT_ACCOUNT_SETTINGS_MAIN_ENTRY);
                startActivity(intent);
                break;
            case R.id.profile_notification_item:
                presenter.onListItemClicked(Constant.PROFILE_NOTIFICATION, null);
                intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra("entryTypeCode", com.xuan.qingya.Common.Constant.FRAGMENT_NOTIFICATION_LIST);
                startActivity(intent);
                break;
            case R.id.profile_settings_item:
                presenter.onListItemClicked(Constant.PROFILE_SETTINGS, null);
                intent = new Intent(getActivity(), SettingsActivity.class);
                intent.putExtra(com.xuan.qingya.Common.Constant.ENTRY_TYPE, com.xuan.qingya.Common.Constant.FRAGEMNT_SETTINGS_MAIN_ENTRY);
                startActivity(intent);
                break;
            case R.id.profile_love_item:
                presenter.onListItemClicked(Constant.PROFILE_LOVE, null);
                intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra("entryTypeCode", com.xuan.qingya.Common.Constant.FRAGMENT_COLLECTION);
                startActivity(intent);
                break;
            case R.id.profile_play_history_item:
                presenter.onListItemClicked(Constant.PROFILE_PLAY_HISTORY, null);
                intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra("entryTypeCode", com.xuan.qingya.Common.Constant.FRAGMENT_PLAYHISTORY);
                startActivity(intent);
                break;
            case R.id.profile_logout_item:
                presenter.onLogout(null);
                break;
        }
    }

    @Override
    public void startActivity(Class<?> target, BasePresenter presenter, @Nullable Bundle bundle, @Nullable String extra) {

    }
}
