package com.xuan.qingya.Modules.Profile;

import com.xuan.qingya.Core.Base.BasePresenter;
import com.xuan.qingya.Core.Base.BaseView;
import com.xuan.qingya.Models.Entity.ArticleBean;
import com.xuan.qingya.Models.Entity.MusicBean;
import com.xuan.qingya.Models.Entity.NotificationBean;

import java.util.List;

/**
 * Created by zhouzhixuan on 2017/9/3.
 */

public interface ProfileContract {
    //Profile Main Activity
    interface ProfileView extends BaseView<ProfilePresenter> {
        void ShowDeleteToolbar();
    }

    interface ProfilePresenter extends BasePresenter {
        void onDeleteByDeleteToolbar();
    }

    //Play history
    interface PlayHistoryView extends BaseView<PlayHistoryPresenter> {
        void setPlayerTitle(String title);

        void changePlayerStatusButton();
    }

    interface PlayHistoryPresenter extends BasePresenter {
        void updatePlayerUI();

        void onPlayClick();

        void onPlayModeChange();

        void onItemClick();

        void onItemDelete();

        List<MusicBean> getData();
    }

    //Notification
    interface NotificationView extends BaseView<NotificationPresenter> {

    }

    interface NotificationPresenter extends BasePresenter {
        List<NotificationBean> getData();
    }

    //Notification detail
    interface NotificationDetailView extends BaseView<NotificationDetailPresenter> {

    }

    interface NotificationDetailPresenter extends BasePresenter {

    }

    //Collection
    interface CollectionView extends BaseView<CollectionPresenter> {

    }

    interface CollectionPresenter extends BasePresenter {
        void onLoveCancel(ArticleBean bean);

        List<ArticleBean> getData();
    }
}
