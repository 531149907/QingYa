package com.xuan.qingya.Modules.Settings.Account.MainEntry;

/**
 * Created by zhouzhixuan on 2017/9/8.
 */

public class MainEntryPresenter implements MainEntryContract.MainEntryPresenter {

    private MainEntryContract.MainEntryView view;

    public MainEntryPresenter(MainEntryContract.MainEntryView view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void init() {

    }
}
