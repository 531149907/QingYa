package com.xuan.qingya.Modules.Settings.General.Entry;

import com.xuan.qingya.Core.Base.BasePresenter;
import com.xuan.qingya.Core.Base.BaseView;

/**
 * Created by zhouzhixuan on 2017/9/9.
 */

public class EntryContract {
    interface EntryView extends BaseView<EntryPresenter> {

    }

    interface EntryPresenter extends BasePresenter {
        void onNightModeChange();

        void clearCache();

        void clearSearchHistory();
    }
}
