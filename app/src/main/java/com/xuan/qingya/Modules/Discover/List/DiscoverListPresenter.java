package com.xuan.qingya.Modules.Discover.List;

import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Models.Entity.DiscoverListBean;
import com.xuan.qingya.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouzhixuan on 2017/8/30.
 */

public class DiscoverListPresenter implements DiscoverListContract.DiscoverListPresenter {
    private DiscoverListContract.DiscoverListView view;

    public DiscoverListPresenter(DiscoverListContract.DiscoverListView view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void onHistoryIconClick() {

    }

    @Override
    public void onFabClick() {

    }


    @Override
    public List<DiscoverListBean> getData(int dataType, int monthDate) {
        List<DiscoverListBean> beans = new ArrayList<>();
        DiscoverListBean bean;

        switch (dataType) {
            case Constant.SIMPLIFY_CONTENT_TYPE_ARTICLE:
                bean = new DiscoverListBean();
                bean.setId(0);
                bean.setType(Constant.CONTENT_DISCOVER_ARTICLE_IMAGE);
                bean.setCover_img(R.drawable.a24);
                bean.setTitle("陌生女人的来信1");
                bean.setAuthor("文章 / 大斯");
                bean.setType_main(dataType);
                beans.add(bean);

                bean = new DiscoverListBean();
                bean.setId(1);
                bean.setType(Constant.CONTENT_DISCOVER_ARTICLE_POEM);
                bean.setCover_img(R.drawable.a25);
                bean.setTitle("陌生女人的来信2");
                bean.setAuthor("文章 / 大斯");
                bean.setType_main(dataType);
                beans.add(bean);

                bean = new DiscoverListBean();
                bean.setId(2);
                bean.setType(Constant.CONTENT_DISCOVER_ARTICLE_READ);
                bean.setCover_img(R.drawable.a26);
                bean.setTitle("陌生女人的来信3");
                bean.setAuthor("文章 / 大斯");
                bean.setType_main(dataType);
                beans.add(bean);
                break;
            case Constant.SIMPLIFY_CONTENT_TYPE_PHOTOGRAPHY:
                bean = new DiscoverListBean();
                bean.setId(0);
                bean.setType(Constant.CONTENT_DISCOVER_PHOTOGRAPHY);
                bean.setPhoto_id(R.drawable.a19);
                bean.setLove(132);
                bean.setType_main(dataType);
                beans.add(bean);

                bean = new DiscoverListBean();
                bean.setId(1);
                bean.setType(Constant.CONTENT_DISCOVER_PHOTOGRAPHY);
                bean.setPhoto_id(R.drawable.a22);
                bean.setLove(132);
                bean.setType_main(dataType);
                beans.add(bean);

                bean = new DiscoverListBean();
                bean.setId(2);
                bean.setType(Constant.CONTENT_DISCOVER_PHOTOGRAPHY);
                bean.setPhoto_id(R.drawable.a23);
                bean.setLove(132);
                bean.setType_main(dataType);
                beans.add(bean);
                break;
            case Constant.SIMPLIFY_CONTENT_TYPE_MUSIC:
                bean = new DiscoverListBean();
                bean.setId(0);
                bean.setType(Constant.CONTENT_DISCOVER_MUSIC);
                bean.setCover_img(R.drawable.a24);
                bean.setTitle("陌生女人的来信");
                bean.setAuthor("文章 / 大斯");
                bean.setType_main(dataType);
                beans.add(bean);

                bean = new DiscoverListBean();
                bean.setId(1);
                bean.setType(Constant.CONTENT_DISCOVER_MUSIC);
                bean.setCover_img(R.drawable.a25);
                bean.setTitle("陌生女人的来信");
                bean.setAuthor("文章 / 大斯");
                bean.setType_main(dataType);
                beans.add(bean);

                bean = new DiscoverListBean();
                bean.setId(2);
                bean.setType(Constant.CONTENT_DISCOVER_MUSIC);
                bean.setCover_img(R.drawable.a26);
                bean.setTitle("陌生女人的来信");
                bean.setAuthor("文章 / 大斯");
                bean.setType_main(dataType);
                beans.add(bean);
                break;
            case Constant.SIMPLIFY_CONTENT_TYPE_MOVIE:
                bean = new DiscoverListBean();
                bean.setId(0);
                bean.setType(Constant.CONTENT_DISCOVER_MOVIE);
                bean.setCover_img(R.drawable.a24);
                bean.setTitle("陌生女人的来信");
                bean.setAuthor("文章 / 大斯");
                bean.setType_main(dataType);
                beans.add(bean);

                bean = new DiscoverListBean();
                bean.setId(1);
                bean.setType(Constant.CONTENT_DISCOVER_MOVIE);
                bean.setCover_img(R.drawable.a25);
                bean.setTitle("陌生女人的来信");
                bean.setType_main(dataType);
                bean.setAuthor("文章 / 大斯");
                beans.add(bean);

                bean = new DiscoverListBean();
                bean.setId(2);
                bean.setType(Constant.CONTENT_DISCOVER_MOVIE);
                bean.setCover_img(R.drawable.a26);
                bean.setTitle("陌生女人的来信");
                bean.setType_main(dataType);
                bean.setAuthor("文章 / 大斯");
                beans.add(bean);
                break;
            case Constant.SIMPLIFY_CONTENT_TYPE_QUESTION:
                bean = new DiscoverListBean();
                bean.setId(0);
                bean.setType(Constant.CONTENT_DISCOVER_QUESTION);
                bean.setCover_img(R.drawable.a24);
                bean.setTitle("陌生女人的来信");
                bean.setType_main(dataType);
                bean.setAsk_content("没看过春风十里，还没爱过痞子男没看过春风十里，还没爱过痞子男");
                beans.add(bean);

                bean = new DiscoverListBean();
                bean.setId(1);
                bean.setType(Constant.CONTENT_DISCOVER_QUESTION);
                bean.setCover_img(R.drawable.a25);
                bean.setTitle("陌生女人的来信");
                bean.setType_main(dataType);
                bean.setAsk_content("没看过春风十里，还没爱过痞子男没看过春风十里，还没爱过痞子男");
                beans.add(bean);

                bean = new DiscoverListBean();
                bean.setId(2);
                bean.setType(Constant.CONTENT_DISCOVER_QUESTION);
                bean.setCover_img(R.drawable.a26);
                bean.setTitle("陌生女人的来信");
                bean.setType_main(dataType);
                bean.setAsk_content("没看过春风十里，还没爱过痞子男没看过春风十里，还没爱过痞子男");
                beans.add(bean);
                break;
        }

        return beans;
    }

    @Override
    public int onLoveButtonClick(DiscoverListBean bean, boolean setLove) {
        if (setLove) {
            bean.setLove(bean.getLove() + 1);

        } else {
            bean.setLove(bean.getLove() - 1);
        }
        bean.setLoved(setLove);
        return bean.getLove();
    }

    @Override
    public void retainNestedScrollViewPosition() {

    }
}
