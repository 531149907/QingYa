package com.xuan.qingya;

import android.app.Application;

import com.xuan.qingya.Utils.DensityUtil;

/**
 * Created by zhouzhixuan on 2017/8/24.
 */

public class QingYaApplication extends Application {

    private static QingYaApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        new DensityUtil(application.getApplicationContext());
    }

    public static QingYaApplication getApplication() {
        return application;
    }
}
