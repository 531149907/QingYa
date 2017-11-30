package com.xuan.qingya.Modules.Main.Profile;

import com.xuan.qingya.Common.CacheKeys;
import com.xuan.qingya.Common.CacheValues;
import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.Utils.CacheUtil;

/**
 * Created by zhouzhixuan on 2017/8/28.
 */

public class ProfilePresenter extends BasePresenter<ViewContract> {
    public void onLogout() {
        CacheUtil cacheUtil = CacheUtil.get(getContext());
        cacheUtil.put(CacheKeys.USER_STATUS, CacheValues.USER_UNKNOWN);
        cacheUtil.remove(CacheKeys.IS_FIRST_OPEN);
    }
}
