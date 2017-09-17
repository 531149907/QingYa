package com.xuan.qingya.Modules.Settings.Account.MainEntry;


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
import com.xuan.qingya.Core.Base.BaseFragment;
import com.xuan.qingya.Modules.Settings.Account.AccountSettingsActivity;
import com.xuan.qingya.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainEntryFragment extends BaseFragment implements MainEntryContract.MainEntryView {

    private ImageView avatarImage;
    private MainEntryContract.MainEntryPresenter presenter;
    private CardView avatarEntry, usernameEntry, signatureEntry, passwordEntry;

    public MainEntryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_account_settings_entry, container, false);

        init();
        initListeners(avatarEntry, usernameEntry, signatureEntry, passwordEntry);

        return mRootView;
    }

    @Override
    public void setPresenter(MainEntryContract.MainEntryPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {
        avatarImage = $(R.id.avatar_image);
        avatarEntry = $(R.id.change_avatar);
        usernameEntry = $(R.id.change_username);
        signatureEntry = $(R.id.change_signature);
        passwordEntry = $(R.id.change_password);

        RequestOptions options = RequestOptions.circleCropTransform();
        Glide.with(this).load(R.drawable.a25).apply(options).into(avatarImage);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), AccountSettingsActivity.class);
        switch (view.getId()) {
            case R.id.change_avatar:
                intent.putExtra(Constant.ENTRY_TYPE, Constant.FRAGMENT_ACCOUNT_SETTINGS_AVATAR);
                break;
            case R.id.change_password:
                intent.putExtra(Constant.ENTRY_TYPE, Constant.FRAGMENT_ACCOUNT_SETTINGS_PASSWORD);
                break;
            case R.id.change_signature:
                intent.putExtra(Constant.ENTRY_TYPE, Constant.FRAGMENT_ACCOUNT_SETTINGS_SIGNATURE);
                break;
            case R.id.change_username:
                intent.putExtra(Constant.ENTRY_TYPE, Constant.FRAGMENT_ACCOUNT_SETTINGS_USERNAME);
                break;
        }
        startActivity(intent);
    }
}
