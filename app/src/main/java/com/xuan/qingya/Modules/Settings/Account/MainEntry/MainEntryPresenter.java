package com.xuan.qingya.Modules.Settings.Account.MainEntry;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.R;

/**
 * Created by zhouzhixuan on 2017/9/8.
 */

public class MainEntryPresenter extends BasePresenter<ViewContract> {
    void getAvatarImage() {
        RequestOptions options = RequestOptions.circleCropTransform();
        if (isActivityAlive()) {
            Glide.with(getContext()).load(R.drawable.a25).apply(options).into(getMvpView().getAvatarContainer());
        }
    }
}
