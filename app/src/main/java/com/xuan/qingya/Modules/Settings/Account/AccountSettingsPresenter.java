package com.xuan.qingya.Modules.Settings.Account;

/**
 * Created by zhouzhixuan on 2017/9/8.
 */

public class AccountSettingsPresenter implements AccountSettingsContract.AccountSettingsPresenter {
    private AccountSettingsContract.AccountSettingsView view;

    public AccountSettingsPresenter(AccountSettingsContract.AccountSettingsView view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void init() {

    }
}
