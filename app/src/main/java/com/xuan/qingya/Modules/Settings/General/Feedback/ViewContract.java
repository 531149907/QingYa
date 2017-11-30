package com.xuan.qingya.Modules.Settings.General.Feedback;

import com.xuan.qingya.Core.base.BaseView;

/**
 * Created by zhouzhixuan on 2017/10/18.
 */

public interface ViewContract extends BaseView {
    void setErrorMessage(int type, String errorMessage);

    void cleanInput();

    void showToast(String message);
}
