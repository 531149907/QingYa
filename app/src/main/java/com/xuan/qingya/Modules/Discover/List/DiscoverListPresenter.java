package com.xuan.qingya.Modules.Discover.List;

import java.util.List;

/**
 * Created by zhouzhixuan on 2017/8/30.
 */

public class DiscoverListPresenter implements DiscoverListContract.DiscoverListPresenter {
    private DiscoverListContract.DiscoverListView view;

    public DiscoverListPresenter(DiscoverListContract.DiscoverListView view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void onHistoryIconClick() {

    }

    @Override
    public void onFabClick() {

    }

    @Override
    public <T> List<T> getData(int dataType) {
        return null;
    }
}
