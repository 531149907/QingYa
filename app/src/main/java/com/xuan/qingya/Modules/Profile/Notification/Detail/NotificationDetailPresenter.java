package com.xuan.qingya.Modules.Profile.Notification.Detail;

import com.xuan.qingya.Modules.Profile.ProfileContract;

/**
 * Created by zhouzhixuan on 2017/9/4.
 */

public class NotificationDetailPresenter implements ProfileContract.NotificationDetailPresenter {

    private ProfileContract.NotificationDetailView view;

    public NotificationDetailPresenter(ProfileContract.NotificationDetailView view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void init() {

    }
}
