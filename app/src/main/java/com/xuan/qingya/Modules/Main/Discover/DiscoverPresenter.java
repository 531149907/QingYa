package com.xuan.qingya.Modules.Main.Discover;

import android.os.Bundle;

import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.Modules.Main.MainActivity;
import com.xuan.qingya.Utils.LogUtil;

/**
 * Created by zhouzhixuan on 2017/8/27.
 */

public class DiscoverPresenter extends BasePresenter<ViewContract> {
    public void onBannerItemClick(int id, Bundle bundle) {
        LogUtil.show("onBannerItemClick", id);
    }

    public void retainNestedScrollViewPosition() {
        ((MainActivity) ((DiscoverFragment) getMvpView()).getActivity()).setScrollViewPosition(2, 2);
    }

    public void getBannerListData() {
        /*todo: 获取Banner数据*/

    }
}
