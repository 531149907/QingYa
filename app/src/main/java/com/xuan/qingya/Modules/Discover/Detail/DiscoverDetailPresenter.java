package com.xuan.qingya.Modules.Discover.Detail;

import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Models.Entity.ArticleBean;
import com.xuan.qingya.R;

/**
 * Created by zhouzhixuan on 2017/8/30.
 */

public class DiscoverDetailPresenter implements DiscoverDetailContract.DiscoverDetailPresenter {

    private DiscoverDetailContract.DiscoverDetailView view;

    public DiscoverDetailPresenter(DiscoverDetailContract.DiscoverDetailView view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void initLayoutByType(int type) {
        ArticleBean bean = new ArticleBean();
        switch (type) {
            case Constant.CONTENT_DISCOVER_ARTICLE_READ:
                bean.setTitle("通过观察别人的眼神来判断其精神状态靠谱吗？");
                bean.setAuthor("文 / 别人的佩佩");
                bean.setContent(((DiscoverDetailActivity) view).getResources().getString(R.string.test_long_content));
                break;
            case Constant.CONTENT_DISCOVER_ARTICLE_IMAGE:
                bean.setCover_img(R.drawable.a9);
                bean.setAuthor("文 / 别人的佩佩");
                bean.setContent(((DiscoverDetailActivity) view).getResources().getString(R.string.test_long_content));
                break;
            case Constant.CONTENT_DISCOVER_ARTICLE_POEM:
                bean.setCover_img(R.drawable.a10);
                bean.setAuthor("文 / 别人的佩佩");
                bean.setContent(((DiscoverDetailActivity) view).getResources().getString(R.string.test_long_content));
                break;
            case Constant.CONTENT_DISCOVER_PHOTOGRAPHY:
                bean.setCover_img(R.drawable.a11);
                bean.setAuthor("文 / 别人的佩佩");
                break;
            case Constant.CONTENT_DISCOVER_MUSIC:
                bean.setTitle("通过观察别人的眼神来判断其精神状态靠谱吗？");
                bean.setAuthor("文 / 别人的佩佩");
                bean.setContent(((DiscoverDetailActivity) view).getResources().getString(R.string.test_long_content));
                break;
            case Constant.CONTENT_DISCOVER_MOVIE:
                bean.setTitle("通过观察别人的眼神来判断其精神状态靠谱吗？");
                bean.setAuthor("文 / 别人的佩佩");
                bean.setContent(((DiscoverDetailActivity) view).getResources().getString(R.string.test_long_content));
                break;
            case Constant.CONTENT_DISCOVER_QUESTION:
                bean.setTitle("通过观察别人的眼神来判断其精神状态靠谱吗？");
                bean.setAnswer_author("文 / 别人的佩佩");
                bean.setAnswer_content(((DiscoverDetailActivity) view).getResources().getString(R.string.test_long_content));
                bean.setAsk_author("文 / 别人的佩佩");
                bean.setAsk_content(((DiscoverDetailActivity) view).getResources().getString(R.string.test_long_content));
                break;
        }
        bean.setType(type);
        view.setContent(bean);
    }

    @Override
    public void onMusicPlay() {

    }

    @Override
    public void onVideoPlay() {

    }

    @Override
    public void onPhotoDownload() {

    }

    @Override
    public void onLoveClick() {

    }
}
