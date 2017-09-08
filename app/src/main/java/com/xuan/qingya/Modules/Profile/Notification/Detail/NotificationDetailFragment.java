package com.xuan.qingya.Modules.Profile.Notification.Detail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuan.qingya.Core.Base.BaseFragment;
import com.xuan.qingya.Modules.Profile.ProfileActivity;
import com.xuan.qingya.Modules.Profile.ProfileContract;
import com.xuan.qingya.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationDetailFragment extends BaseFragment implements ProfileContract.NotificationDetailView {

    private ProfileContract.NotificationDetailPresenter presenter;

    public NotificationDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_notification_detail, container, false);

        init();

        return mRootView;
    }

    @Override
    public void setPresenter(ProfileContract.NotificationDetailPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {
        ((ProfileActivity) getActivity()).setToolbarTitle("详情");
    }
}
