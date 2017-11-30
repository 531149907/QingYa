package com.xuan.qingya.Modules.Main.Profile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Core.base.BaseFragment;
import com.xuan.qingya.Modules.Main.MainActivity;
import com.xuan.qingya.Modules.Profile.ProfileActivity;
import com.xuan.qingya.Modules.Settings.Account.AccountSettingsActivity;
import com.xuan.qingya.Modules.Settings.General.SettingsActivity;
import com.xuan.qingya.Modules.Splash.SplashActivity;
import com.xuan.qingya.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment<ViewContract, ProfilePresenter> implements ViewContract {
    @BindView(R.id.notificationItem)
    CardView notificationItem;
    @BindView(R.id.settingsItem)
    CardView settingsItem;
    @BindView(R.id.loveItem)
    CardView loveItem;
    @BindView(R.id.playHistoryItem)
    CardView playHistoryItem;
    @BindView(R.id.logoutItem)
    CardView logoutItem;
    @BindView(R.id.profileSection)
    CardView accountSettingsItem;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, mRootView);

        init();
        initListeners(notificationItem, settingsItem, loveItem, playHistoryItem, logoutItem, accountSettingsItem);

        return mRootView;
    }

    public void init() {
        RequestOptions options = RequestOptions.circleCropTransform();
        Glide.with(this).load(R.drawable.a23).apply(options).into((ImageView) accountSettingsItem.findViewById(R.id.avatar));
        ((MainActivity) getActivity()).getViewPager().setObjectForPosition(mRootView, 3);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.profileSection:
                intent = new Intent(getActivity(), AccountSettingsActivity.class);
                intent.putExtra(Constant.ENTRY_TYPE, Constant.FRAGMENT_ACCOUNT_SETTINGS_MAIN_ENTRY);
                startActivity(intent);
                break;
            case R.id.notificationItem:
                intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra(Constant.ENTRY_TYPE, Constant.FRAGMENT_NOTIFICATION_LIST);
                startActivity(intent);
                break;
            case R.id.settingsItem:
                intent = new Intent(getActivity(), SettingsActivity.class);
                intent.putExtra(Constant.ENTRY_TYPE, Constant.FRAGMENT_SETTINGS_MAIN_ENTRY);
                startActivity(intent);
                break;
            case R.id.loveItem:
                intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra(Constant.ENTRY_TYPE, Constant.FRAGMENT_COLLECTION);
                startActivity(intent);
                break;
            case R.id.playHistoryItem:
                intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra(Constant.ENTRY_TYPE, Constant.FRAGMENT_PLAYHISTORY);
                startActivity(intent);
                break;
            case R.id.logoutItem:
                presenter.onLogout();
                intent = new Intent(getActivity(), SplashActivity.class);
                intent.putExtra(Constant.ENTRY_TYPE, Constant.FRAGMENT_OPTION);
                startActivity(intent);
                getActivity().finish();
                break;
        }
    }

    @Override
    public ProfilePresenter initPresenter() {
        return new ProfilePresenter();
    }

}
