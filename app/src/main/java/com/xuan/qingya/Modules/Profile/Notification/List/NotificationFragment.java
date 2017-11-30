package com.xuan.qingya.Modules.Profile.Notification.List;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Core.base.BaseFragment;
import com.xuan.qingya.Models.entity.Message;
import com.xuan.qingya.Modules.Profile.ProfileActivity;
import com.xuan.qingya.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends BaseFragment<ViewContract, NotificationPresenter> implements ViewContract {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private NotificationRecyclerViewAdapter adapter;

    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.fragment_notification, container, false);
        ButterKnife.bind(this, mRootView);

        init();
        initListeners();

        return mRootView;
    }

    @Override
    public NotificationPresenter initPresenter() {
        return new NotificationPresenter();
    }

    public void init() {
        adapter = new NotificationRecyclerViewAdapter(getActivity());

        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.getItemAnimator().setChangeDuration(0);

        presenter.getData();
    }

    @Override
    public void showList(List<Message> list) {
        adapter.setData(list);
    }

    @Override
    protected void initListeners(@Nullable View... views) {
        adapter.addOnClickListener(new NotificationRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(Message bean, int position) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra(Constant.ENTRY_TYPE, Constant.FRAGMENT_NOTIFICATION_DETAIL);
                intent.putExtra(Constant.BEAN, bean);
                startActivity(intent);
            }
        });
    }
}
