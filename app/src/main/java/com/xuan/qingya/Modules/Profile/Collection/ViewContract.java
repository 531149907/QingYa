package com.xuan.qingya.Modules.Profile.Collection;

import com.xuan.qingya.Core.base.BaseView;
import com.xuan.qingya.Models.entity.Article;

import java.util.List;

/**
 * Created by zhouzhixuan on 2017/10/16.
 */

public interface ViewContract extends BaseView {
    void showList(List<Article> list);
}
