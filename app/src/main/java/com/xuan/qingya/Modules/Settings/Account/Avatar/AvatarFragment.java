package com.xuan.qingya.Modules.Settings.Account.Avatar;


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
public class AvatarFragment extends BaseFragment implements AvatarContract.AvatarView {

    private AvatarContract.AvatarPresenter presenter;

    public AvatarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_avatar, container, false);

        init();

        return mRootView;
    }

    @Override
    public void setPresenter(AvatarContract.AvatarPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {

    }
}
