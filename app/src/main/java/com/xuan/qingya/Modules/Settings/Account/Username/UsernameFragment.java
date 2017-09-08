package com.xuan.qingya.Modules.Settings.Account.Username;


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
public class UsernameFragment extends BaseFragment implements UsernameContract.UsernameView {

    private UsernameContract.UsernamePresenter presenter;

    public UsernameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_username, container, false);

        init();

        return mRootView;
    }

    @Override
    public void setPresenter(UsernameContract.UsernamePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {

    }
}
