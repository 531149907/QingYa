package com.xuan.qingya.Modules.Startup.Register;

import com.xuan.qingya.Core.base.BaseView;

/**
 * Created by zhouzhixuan on 2017/10/14.
 */

public interface ViewContract extends BaseView {
    void setErrorMessage(int type, String errorMessage);
}
