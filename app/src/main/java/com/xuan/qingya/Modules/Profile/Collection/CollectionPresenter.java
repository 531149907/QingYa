package com.xuan.qingya.Modules.Profile.Collection;

import com.xuan.qingya.Models.Entity.ArticleBean;
import com.xuan.qingya.Modules.Profile.ProfileContract;

import java.util.List;

/**
 * Created by zhouzhixuan on 2017/9/5.
 */

public class CollectionPresenter implements ProfileContract.CollectionPresenter {
    private ProfileContract.CollectionView view;

    public CollectionPresenter(ProfileContract.CollectionView view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void onLoveCancel(ArticleBean bean) {
        boolean oldValue = bean.isLoved();
        bean.setLoved(!oldValue);
    }

    @Override
    public List<ArticleBean> getData() {
        return null;
    }

}
