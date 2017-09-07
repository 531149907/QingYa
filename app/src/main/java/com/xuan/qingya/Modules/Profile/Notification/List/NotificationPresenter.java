package com.xuan.qingya.Modules.Profile.Notification.List;

import com.xuan.qingya.Models.Entity.NotificationBean;
import com.xuan.qingya.Modules.Profile.ProfileContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouzhixuan on 2017/9/4.
 */

public class NotificationPresenter implements ProfileContract.NotificationPresenter {

    private ProfileContract.NotificationView view;

    public NotificationPresenter(ProfileContract.NotificationView view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void init() {

    }

    @Override
    public List<NotificationBean> getData() {
        List<NotificationBean> list = new ArrayList<>();
        NotificationBean bean;

        bean = new NotificationBean();
        bean.setId(0);
        bean.setTitle("欢迎加入青芽");
        bean.setDate("12:30");
        bean.setContent("在这里，你可以浏览鸡汤文，高质量的精选摄影作...");
        list.add(bean);

        bean = new NotificationBean();
        bean.setId(1);
        bean.setTitle("新增了摄影来源图库");
        bean.setDate("昨天");
        bean.setContent("除了来自Unsplash的优质大图，我们还增加了来自...");
        list.add(bean);

        bean = new NotificationBean();
        bean.setId(2);
        bean.setTitle("消息推送已关闭");
        bean.setDate("一周前");
        bean.setContent("您已经将青芽的推送关闭，可以再设置中再次开启...");
        list.add(bean);

        return list;
    }
}
