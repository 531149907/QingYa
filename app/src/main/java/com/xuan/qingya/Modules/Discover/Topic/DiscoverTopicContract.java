package com.xuan.qingya.Modules.Discover.Topic;

import com.xuan.qingya.Core.Base.BasePresenter;
import com.xuan.qingya.Core.Base.BaseView;
import com.xuan.qingya.Models.Entity.ArticleBean;

import java.util.List;

/**
 * Created by zhouzhixuan on 2017/9/15.
 */

public interface DiscoverTopicContract {
    interface DiscoverTopicView extends BaseView<DiscoverTopicPresenter> {

    }

    interface DiscoverTopicPresenter extends BasePresenter<DiscoverTopicView> {
        void onLoveButtonClick(ArticleBean bean, boolean setLove);

        List<ArticleBean> getData();
    }
}
