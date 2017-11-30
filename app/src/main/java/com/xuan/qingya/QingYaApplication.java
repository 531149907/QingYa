package com.xuan.qingya;

import android.app.Application;

import com.xuan.qingya.Common.CacheKeys;
import com.xuan.qingya.Models.entity.User;
import com.xuan.qingya.Utils.CacheUtil;
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
        User user = new User("531149907@qq.com", "zzx", "123456", "simple life");
        user.setId(1);
        CacheUtil.get(this).put(CacheKeys.USER_ENTITY, user);
    }

    public static QingYaApplication getApplication() {
        return application;
    }
}
