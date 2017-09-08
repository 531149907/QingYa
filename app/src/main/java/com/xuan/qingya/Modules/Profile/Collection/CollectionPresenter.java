package com.xuan.qingya.Modules.Profile.Collection;

import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Models.Entity.ArticleBean;
import com.xuan.qingya.Modules.Profile.ProfileContract;
import com.xuan.qingya.R;

import java.util.ArrayList;
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
        List<ArticleBean> list = new ArrayList<>();
        ArticleBean bean;

        bean = new ArticleBean();
        bean.setTitle("dsfsdfdsff");
        bean.setAuthor("ssfsddf");
        bean.setType(Constant.CONTENT_DISCOVER_ARTICLE_IMAGE);
        bean.setCover_img(R.drawable.a3);
        list.add(bean);

        bean = new ArticleBean();
        bean.setTitle("dsfsdfdsff");
        bean.setAuthor("ssfsddf");
        bean.setType(Constant.CONTENT_DISCOVER_ARTICLE_POEM);
        bean.setCover_img(R.drawable.a4);
        list.add(bean);

        bean = new ArticleBean();
        bean.setTitle("dsfsdfdsff");
        bean.setAuthor("ssfsddf");
        bean.setType(Constant.CONTENT_DISCOVER_ARTICLE_READ);
        bean.setCover_img(R.drawable.a5);
        list.add(bean);

        bean = new ArticleBean();
        bean.setTitle("dsfsdfdsff");
        bean.setAuthor("ssfsddf");
        bean.setType(Constant.CONTENT_DISCOVER_MOVIE);
        bean.setCover_img(R.drawable.a6);
        list.add(bean);

        bean = new ArticleBean();
        bean.setTitle("dsfsdfdsff");
        bean.setAuthor("ssfsddf");
        bean.setType(Constant.CONTENT_DISCOVER_MUSIC);
        bean.setCover_img(R.drawable.a7);
        list.add(bean);

        bean = new ArticleBean();
        bean.setTitle("dsfsdfdsff");
        bean.setAuthor("ssfsddf");
        bean.setType(Constant.CONTENT_DISCOVER_PHOTOGRAPHY);
        bean.setCover_img(R.drawable.a8);
        list.add(bean);

        bean = new ArticleBean();
        bean.setTitle("dsfsdfdsff");
        bean.setAuthor("ssfsddf");
        bean.setType(Constant.CONTENT_DISCOVER_QUESTION);
        bean.setCover_img(R.drawable.a9);
        list.add(bean);

        bean = new ArticleBean();
        bean.setTitle("dsfsdfdsff");
        bean.setAuthor("ssfsddf");
        bean.setType(Constant.CONTENT_INTERVIEW_IMAGE);
        bean.setCover_img(R.drawable.a10);
        list.add(bean);

        bean = new ArticleBean();
        bean.setTitle("dsfsdfdsff");
        bean.setAuthor("ssfsddf");
        bean.setType(Constant.CONTENT_INTERVIEW_VIDEO);
        bean.setCover_img(R.drawable.a11);
        list.add(bean);

        return list;
    }


}
