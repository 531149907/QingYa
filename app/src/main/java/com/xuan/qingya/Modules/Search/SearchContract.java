package com.xuan.qingya.Modules.Search;

import android.view.View;

import com.xuan.qingya.Core.Base.BasePresenter;
import com.xuan.qingya.Core.Base.BaseView;
import com.xuan.qingya.Models.Entity.BaseBean;
import com.xuan.qingya.Models.Entity.SearchHistoryBean;

import java.util.List;

/**
 * Created by zhouzhixuan on 2017/9/12.
 */

public interface SearchContract {
    interface SearchView extends BaseView<SearchPresenter> {
        void replaceSearchContent(String searchContent);

        void switchToResultList();

        void switchToSearchList();
    }

    interface SearchPresenter extends BasePresenter<SearchView> {
        void fillEditTextWithHistoryContent(String content);

        void onSearchHistoryItemClick(View view, SearchHistoryBean bean, String content);

        void onSearchResultItemClick();

        List<SearchHistoryBean> getHistoryDataList();

        List<BaseBean> getResultDataList(String keyword);

        void onLoveButtonClick(BaseBean bean, boolean love);
    }
}
