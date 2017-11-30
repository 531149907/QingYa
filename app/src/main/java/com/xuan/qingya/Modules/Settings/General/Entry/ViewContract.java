package com.xuan.qingya.Modules.Settings.General.Entry;

import com.xuan.qingya.Core.base.BaseView;

/**
 * Created by zhouzhixuan on 2017/10/18.
 */

public interface ViewContract extends BaseView {
    void changeNightModeSwitcherState();

    void setCacheSize(String cacheSize);
}
