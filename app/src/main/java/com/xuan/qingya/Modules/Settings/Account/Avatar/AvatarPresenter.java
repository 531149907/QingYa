package com.xuan.qingya.Modules.Settings.Account.Avatar;

/**
 * Created by zhouzhixuan on 2017/9/8.
 */

public class AvatarPresenter implements AvatarContract.AvatarPresenter {

    private AvatarContract.AvatarView view;

    public AvatarPresenter(AvatarContract.AvatarView view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void init() {

    }
}
