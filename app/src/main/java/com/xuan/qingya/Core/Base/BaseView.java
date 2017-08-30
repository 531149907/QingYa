package com.xuan.qingya.Core.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by zhouzhixuan on 2017/8/17.
 */

public interface BaseView<T> {
    void setPresenter(T presenter);
    void init();
}
