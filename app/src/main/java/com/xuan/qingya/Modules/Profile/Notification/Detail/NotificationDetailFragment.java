package com.xuan.qingya.Modules.Profile.Notification.Detail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Core.base.BaseFragment;
import com.xuan.qingya.Models.entity.Message;
import com.xuan.qingya.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationDetailFragment extends BaseFragment<ViewContract, NotificationDetailPresenter> implements ViewContract {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.deleteButton)
    Button deleteButton;

    private Message bean;

    public NotificationDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.fragment_notification_detail, container, false);
        ButterKnife.bind(this, mRootView);

        init();
        initListeners(deleteButton);

        return mRootView;
    }

    @Override
    public NotificationDetailPresenter initPresenter() {
        return new NotificationDetailPresenter();
    }

    private void init() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            bean = bundle.getParcelable(Constant.BEAN);
        }
        if (bean != null) {
            title.setText(bean.getTitle());
            time.setText(bean.getDate());
            content.setText(bean.getContent());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.deleteButton:
                presenter.onDelete(bean);
                break;
        }
    }
}
