package com.xuan.qingya.Core.Base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by zhouzhixuan on 2017/8/21.
 */

public class BaseFragment extends Fragment implements View.OnClickListener {

    @NonNull
    protected View mRootView = null;

    protected <T extends View> T $(int resId) {
        return mRootView.findViewById(resId);
    }

    protected void initListeners(@Nullable View... views) {
        if (views != null) {
            for (View view : views) {
                view.setOnClickListener(this);
            }
        }
    }

    public View getRootView() {
        return this.mRootView;
    }

    @Override
    public void onClick(View view) {

    }
}
