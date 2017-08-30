package com.xuan.qingya.Modules.Main.Profile;

import android.os.Bundle;

import com.xuan.qingya.Modules.Main.MainContract;

/**
 * Created by zhouzhixuan on 2017/8/28.
 */

public class ProfilePresenter implements MainContract.ProfilePresenter {

    private MainContract.ProfileView view;

    public ProfilePresenter(MainContract.ProfileView view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void onListItemClicked(int type, Bundle bundle) {

    }

    @Override
    public void onLogout(Bundle bundle) {

    }
}
