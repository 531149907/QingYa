package com.xuan.qingya.Modules.Settings.Account.Username;

/**
 * Created by zhouzhixuan on 2017/9/8.
 */

public class UsernamePresenter implements UsernameContract.UsernamePresenter {

    private UsernameContract.UsernameView view;

    public UsernamePresenter(UsernameContract.UsernameView view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void init() {

    }
}
