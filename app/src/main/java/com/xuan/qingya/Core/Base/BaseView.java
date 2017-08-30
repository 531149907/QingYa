package com.xuan.qingya.Core.Base;

/**
 * Created by zhouzhixuan on 2017/8/17.
 */

public interface BaseView<T> {
    void setPresenter(T presenter);

    void init();
}
