package com.xuan.qingya.Modules.Discover.List;

import com.xuan.qingya.Core.Base.BasePresenter;
import com.xuan.qingya.Core.Base.BaseView;

import java.util.List;

/**
 * Created by zhouzhixuan on 2017/8/30.
 */

public interface DiscoverListContract {
    interface DiscoverListView extends BaseView<DiscoverListPresenter> {
        void AppbarExpandedAnimation();

        void ShowHistoryFragment();

        void ShowMusicPlayerFragment();
    }

    interface DiscoverListPresenter extends BasePresenter {
        void onHistoryIconClick();

        void onFabClick();

        <T> List<T> getData(int dataType);
    }
}
