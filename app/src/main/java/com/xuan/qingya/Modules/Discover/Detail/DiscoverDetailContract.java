package com.xuan.qingya.Modules.Discover.Detail;

import com.xuan.qingya.Core.Base.BasePresenter;
import com.xuan.qingya.Core.Base.BaseView;
import com.xuan.qingya.Models.Entity.ArticleBean;

/**
 * Created by zhouzhixuan on 2017/8/30.
 */

public interface DiscoverDetailContract {
    interface DiscoverDetailView extends BaseView<DiscoverDetailPresenter> {
        void setContent(ArticleBean bean);
    }

    interface DiscoverDetailPresenter extends BasePresenter {
        void initLayoutByType(int type);

        void onMusicPlay();

        void onVideoPlay();

        void onPhotoDownload();

        void onLoveClick();
    }
}
