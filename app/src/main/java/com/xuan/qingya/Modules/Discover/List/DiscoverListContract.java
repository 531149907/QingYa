package com.xuan.qingya.Modules.Discover.List;

import com.xuan.qingya.Core.Base.BasePresenter;
import com.xuan.qingya.Core.Base.BaseView;
import com.xuan.qingya.Models.Entity.DiscoverListBean;

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

        /*
        * @param dataType:  simplify content type ONLY
        * @param monthDate: integer, from 1 to 12
        * */
        List<DiscoverListBean> getData(int dataType, int monthDate);

        int onLoveButtonClick(DiscoverListBean bean, boolean setLove);

        void retainNestedScrollViewPosition();
    }
}
