package com.xuan.qingya.Modules.Settings.General.About;

import com.xuan.qingya.Core.base.BasePresenter;

/**
 * Created by zhouzhixuan on 2017/10/18.
 */

public class AboutPresenter extends BasePresenter<ViewContract> {

    private String newVersionURL;

    public void checkUpdateRequest() {
        boolean hasNewVersion = checkVersion();
        if (hasNewVersion) {
            if (isActivityAlive()) {
                getMvpView().setCheckState("有新版本");
                /*todo: 弹窗提示新版本更新信息*/
            }
        } else {

        }
    }

    private boolean checkVersion() {
       /*todo: 检测新版本*/
        newVersionURL = null;
        return false;
    }
}
