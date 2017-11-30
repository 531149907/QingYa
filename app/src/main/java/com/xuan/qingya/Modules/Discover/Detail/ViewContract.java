package com.xuan.qingya.Modules.Discover.Detail;

import com.xuan.qingya.Core.base.BaseView;
import com.xuan.qingya.Models.entity.Article;

/**
 * Created by zhouzhixuan on 2017/10/15.
 */

public interface ViewContract extends BaseView {
    void setContent(Article bean);
}
