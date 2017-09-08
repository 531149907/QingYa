package com.xuan.qingya.Modules.Settings.Account.MainEntry;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuan.qingya.Core.Base.BaseFragment;
import com.xuan.qingya.Modules.Settings.Account.AccountSettingsActivity;
import com.xuan.qingya.Modules.Settings.Account.Avatar.AvatarFragment;
import com.xuan.qingya.Modules.Settings.Account.Avatar.AvatarPresenter;
import com.xuan.qingya.Modules.Settings.Account.Password.PasswordFragment;
import com.xuan.qingya.Modules.Settings.Account.Password.PasswordPresenter;
import com.xuan.qingya.Modules.Settings.Account.Signature.SignatureFragment;
import com.xuan.qingya.Modules.Settings.Account.Signature.SignaturePresenter;
import com.xuan.qingya.Modules.Settings.Account.Username.UsernameFragment;
import com.xuan.qingya.Modules.Settings.Account.Username.UsernamePresenter;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.FragmentManagement;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainEntryFragment extends BaseFragment implements MainEntryContract.MainEntryView {

    private MainEntryContract.MainEntryPresenter presenter;
    private CardView avatarEntry, usernameEntry, signatureEntry, passwordEntry;

    public MainEntryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_main_entry, container, false);

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
        avatarEntry = $(R.id.change_avatar);
        usernameEntry = $(R.id.change_username);
        signatureEntry = $(R.id.change_signature);
        passwordEntry = $(R.id.change_password);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_avatar:
                ((AccountSettingsActivity) getActivity()).setToolbarTitle("更新头像");
                AvatarFragment fragment0 = new AvatarFragment();
                FragmentManagement.switchFragment(getActivity().getSupportFragmentManager(), fragment0, R.id.fragment_container, true, true);
                new AvatarPresenter(fragment0);
                break;
            case R.id.change_password:
                ((AccountSettingsActivity) getActivity()).setToolbarTitle("更换密码");
                PasswordFragment fragment1 = new PasswordFragment();
                FragmentManagement.switchFragment(getActivity().getSupportFragmentManager(), fragment1, R.id.fragment_container, true, true);
                new PasswordPresenter(fragment1);
                break;
            case R.id.change_signature:
                ((AccountSettingsActivity) getActivity()).setToolbarTitle("个性签名");
                SignatureFragment fragment2 = new SignatureFragment();
                FragmentManagement.switchFragment(getActivity().getSupportFragmentManager(), fragment2, R.id.fragment_container, true, true);
                new SignaturePresenter(fragment2);
                break;
            case R.id.change_username:
                ((AccountSettingsActivity) getActivity()).setToolbarTitle("昵称");
                UsernameFragment fragment3 = new UsernameFragment();
                FragmentManagement.switchFragment(getActivity().getSupportFragmentManager(), fragment3, R.id.fragment_container, true, true);
                new UsernamePresenter(fragment3);
                break;
        }
    }
}
