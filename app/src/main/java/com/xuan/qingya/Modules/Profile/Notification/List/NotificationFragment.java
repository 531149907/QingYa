package com.xuan.qingya.Modules.Profile.Notification.List;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuan.qingya.Core.Base.BaseFragment;
import com.xuan.qingya.Models.Entity.NotificationBean;
import com.xuan.qingya.Modules.Profile.Notification.Detail.NotificationDetailFragment;
import com.xuan.qingya.Modules.Profile.Notification.Detail.NotificationDetailPresenter;
import com.xuan.qingya.Modules.Profile.ProfileActivity;
import com.xuan.qingya.Modules.Profile.ProfileContract;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.FragmentManagement;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends BaseFragment implements ProfileContract.NotificationView {

    private ProfileContract.NotificationPresenter presenter;
    private RecyclerView recyclerView;
    private NotificationRecyclerViewAdapter adapter;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_notification, container, false);

        init();

        return mRootView;
    }

    @Override
    public void setPresenter(ProfileContract.NotificationPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {
        ((ProfileActivity) getActivity()).setToolbarTitle("消息");

        recyclerView = $(R.id.notification_recyclerview);
        adapter = new NotificationRecyclerViewAdapter(getActivity(), presenter.getData());

        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.getItemAnimator().setChangeDuration(0);
        adapter.addOnClickListener(new NotificationRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, NotificationBean bean, int position) {
                NotificationDetailFragment fragment = new NotificationDetailFragment();
                FragmentManagement.switchFragment(getFragmentManager(), fragment, R.id.fragment_container, true);
                new NotificationDetailPresenter(fragment);
            }
        });
    }
}
