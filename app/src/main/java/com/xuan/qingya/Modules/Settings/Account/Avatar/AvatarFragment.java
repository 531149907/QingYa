package com.xuan.qingya.Modules.Settings.Account.Avatar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuan.qingya.Core.base.BaseFragment;
import com.xuan.qingya.R;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AvatarFragment extends BaseFragment<ViewContract, AvatarPresenter> implements ViewContract {

    public AvatarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.fragment_avatar, container, false);
        ButterKnife.bind(this, mRootView);

        init();
        initListeners();

        return mRootView;
    }

    @Override
    public AvatarPresenter initPresenter() {
        return null;
    }

    public void init() {

    }
}
