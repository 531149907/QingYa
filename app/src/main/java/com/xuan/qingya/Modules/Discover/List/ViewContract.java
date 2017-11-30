package com.xuan.qingya.Modules.Discover.List;

import com.xuan.qingya.Core.base.BaseView;
import com.xuan.qingya.Models.entity.Article;

import java.util.List;

/**
 * Created by zhouzhixuan on 2017/10/15.
 */

public interface ViewContract extends BaseView {
    void showList(List<Article> data);
}
