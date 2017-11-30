package com.xuan.qingya.Modules.Discover.Detail;

import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.Models.entity.Article;

/**
 * Created by zhouzhixuan on 2017/8/30.
 */

public class DiscoverDetailPresenter extends BasePresenter<ViewContract> {

    public void onLoveButtonClick(Article bean) {
        if (!bean.isLoved()) {
            bean.setLove(bean.getLove() + 1);

        } else {
            bean.setLove(bean.getLove() - 1);
        }
        bean.setLoved(!bean.isLoved());
    }
}
