package com.xuan.qingya.Modules.Search;

import com.xuan.qingya.Core.base.BaseView;
import com.xuan.qingya.Models.entity.Base;
import com.xuan.qingya.Models.entity.SearchHistory;

import java.util.List;

/**
 * Created by zhouzhixuan on 2017/10/14.
 */

public interface ViewContract extends BaseView {
    void cleanSearchContent();

    void fillSearchContent(String content);

    void showHistoryList(List<SearchHistory> data);

    void showResultList(List<Base> data);

    void listSwitchAnimation();
}
