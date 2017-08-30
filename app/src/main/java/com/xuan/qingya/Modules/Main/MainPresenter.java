package com.xuan.qingya.Modules.Main;

/**
 * Created by zhouzhixuan on 2017/8/26.
 */

public class MainPresenter implements MainContract.MainPresenter {

    private MainContract.MainView view;

    MainPresenter(MainContract.MainView view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void onSearch() {

    }

    @Override
    public void onFabClick() {

    }
}
