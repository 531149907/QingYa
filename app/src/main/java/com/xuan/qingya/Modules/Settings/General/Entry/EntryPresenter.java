package com.xuan.qingya.Modules.Settings.General.Entry;

/**
 * Created by zhouzhixuan on 2017/9/9.
 */

public class EntryPresenter implements EntryContract.EntryPresenter {
    private EntryContract.EntryView view;

    public EntryPresenter(EntryContract.EntryView view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void onNightModeChange() {

    }

    @Override
    public void clearCache() {

    }

    @Override
    public void clearSearchHistory() {

    }
}
