package com.xuan.qingya.Modules.Settings.Account.Password;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuan.qingya.Core.Base.BaseFragment;
import com.xuan.qingya.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PasswordFragment extends BaseFragment implements PasswordContract.PasswordView {

    private PasswordContract.PasswordPresenter presenter;

    public PasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_password, container, false);

        init();

        return mRootView;
    }

    @Override
    public void setPresenter(PasswordContract.PasswordPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {

    }
}
