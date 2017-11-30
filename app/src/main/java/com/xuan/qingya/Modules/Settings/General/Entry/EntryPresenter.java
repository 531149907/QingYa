package com.xuan.qingya.Modules.Settings.General.Entry;

import com.xuan.qingya.Core.base.BasePresenter;

/**
 * Created by zhouzhixuan on 2017/9/9.
 */

public class EntryPresenter extends BasePresenter<ViewContract> {
    public void getCacheSize() {
        if (isActivityAlive()) {
            //todo: 获取本地cache大小
            getMvpView().setCacheSize("N/A");
        }
    }

    public void onNightModeChange(boolean isChecked) {
        if (isChecked) {
            //todo: 启动夜间模式
        } else {
            //todo: 启动日间模式
        }

        getMvpView().changeNightModeSwitcherState();
    }

    public void clearCache() {
        //todo: 清空缓存
        getMvpView().setCacheSize("");
    }

    public void clearSearchHistory() {
        //todo: 清空搜索记录
    }
}
