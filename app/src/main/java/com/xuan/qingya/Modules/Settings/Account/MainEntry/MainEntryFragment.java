package com.xuan.qingya.Modules.Settings.Account.MainEntry;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Core.base.BaseFragment;
import com.xuan.qingya.Modules.Settings.Account.AccountSettingsActivity;
import com.xuan.qingya.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainEntryFragment extends BaseFragment<ViewContract, MainEntryPresenter> implements ViewContract {
    @BindView(R.id.avatar)
    ImageView avatarImage;
    @BindView(R.id.changeAvatar)
    CardView avatarEntry;
    @BindView(R.id.changeUsername)
    CardView usernameEntry;
    @BindView(R.id.changeSignature)
    CardView signatureEntry;
    @BindView(R.id.changePassword)
    CardView passwordEntry;

    public MainEntryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.fragment_account_settings_entry, container, false);
        ButterKnife.bind(this, mRootView);

        init();
        initListeners(avatarEntry, usernameEntry, signatureEntry, passwordEntry);

        return mRootView;
    }

    public void init() {
        presenter.getAvatarImage();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), AccountSettingsActivity.class);
        switch (view.getId()) {
            case R.id.changeAvatar:
                intent.putExtra(Constant.ENTRY_TYPE, Constant.FRAGMENT_ACCOUNT_SETTINGS_AVATAR);
                break;
            case R.id.changePassword:
                intent.putExtra(Constant.ENTRY_TYPE, Constant.FRAGMENT_ACCOUNT_SETTINGS_PASSWORD);
                break;
            case R.id.changeSignature:
                intent.putExtra(Constant.ENTRY_TYPE, Constant.FRAGMENT_ACCOUNT_SETTINGS_SIGNATURE);
                break;
            case R.id.changeUsername:
                intent.putExtra(Constant.ENTRY_TYPE, Constant.FRAGMENT_ACCOUNT_SETTINGS_USERNAME);
                break;
        }
        startActivity(intent);
    }

    @Override
    public MainEntryPresenter initPresenter() {
        return new MainEntryPresenter();
    }

    @Override
    public ImageView getAvatarContainer() {
        return avatarImage;
    }
}
