package com.xuan.qingya.Modules.Profile;

/**
 * Created by zhouzhixuan on 2017/9/4.
 */

public class ProfilePresenter implements ProfileContract.ProfilePresenter {

    private ProfileContract.ProfileView view;

    public ProfilePresenter(ProfileContract.ProfileView view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void onDeleteByDeleteToolbar() {

    }
}
