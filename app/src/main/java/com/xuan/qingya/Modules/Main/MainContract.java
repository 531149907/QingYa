package com.xuan.qingya.Modules.Main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xuan.qingya.Core.Base.BasePresenter;
import com.xuan.qingya.Core.Base.BaseView;
import com.xuan.qingya.Models.Entity.ArticleBean;
import com.xuan.qingya.Models.Entity.BannerBean;
import com.xuan.qingya.Models.Entity.InterviewBean;
import com.xuan.qingya.Models.Entity.TopicBean;

import java.util.List;

/**
 * Created by zhouzhixuan on 2017/8/25.
 */

public interface MainContract {
    //Activity
    interface MainView extends BaseView<MainPresenter> {
        void showFabList();
    }

    interface MainPresenter extends BasePresenter {
        void onSearch();

        void onFabClick();

        //UserBean getUserData();
    }

    //Home fragment
    interface HomeView extends BaseView<HomePresenter> {
        void startActivity(Class<?> target, BasePresenter presenter, @Nullable Bundle bundle, @Nullable String extra);
    }

    interface HomePresenter extends BasePresenter {
        void onListItemClick(ArticleBean articleBean, Bundle bundle);

        List<ArticleBean> getListData();

        int onLoveButtonClick(ArticleBean bean, boolean setLove);

        void retainNestedScrollViewPosition();
    }

    //Interview fragment
    interface InterviewView extends BaseView<InterviewPresenter> {
        void startActivity(Class<?> target, BasePresenter presenter, @Nullable Bundle bundle, @Nullable String extra);
    }

    interface InterviewPresenter extends BasePresenter {
        void onListItemClick(InterviewBean interviewBean, Bundle bundle);

        List<InterviewBean> getListData();

        int onLoveButtonClick(InterviewBean bean, boolean setLove);

        void retainNestedScrollViewPosition();
    }

    //Discover fragment
    interface DiscoverView extends BaseView<DiscoverPresenter> {
        void startActivity(Class<?> target, BasePresenter presenter, @Nullable Bundle bundle, @Nullable String extra);
    }

    interface DiscoverPresenter extends BasePresenter {
        void onEntryItemClick(int entryType, Bundle bundle);

        void onBannerItemClick(int id, Bundle bundle);

        void onListItemClick(TopicBean bean, Bundle bundle);

        int onLoveButtonClick(TopicBean bean, boolean setLove);

        void retainNestedScrollViewPosition();

        List<BannerBean> getBannerListData();

        List<TopicBean> getTopicListData();
    }

    //Profile fragment
    interface ProfileView extends BaseView<ProfilePresenter> {
        void startActivity(Class<?> target, BasePresenter presenter, @Nullable Bundle bundle, @Nullable String extra);
    }

    interface ProfilePresenter extends BasePresenter {
        void onListItemClicked(int type, Bundle bundle);

        void onLogout(Bundle bundle);
    }

}
