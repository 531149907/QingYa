package com.xuan.qingya.Modules.Settings.Account.Password;

/**
 * Created by zhouzhixuan on 2017/9/8.
 */

public class PasswordPresenter implements PasswordContract.PasswordPresenter {

    private PasswordContract.PasswordView view;

    public PasswordPresenter(PasswordContract.PasswordView view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void init() {

    }
}
