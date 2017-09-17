package com.xuan.qingya.Modules.Discover.Topic;

import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Models.Entity.ArticleBean;
import com.xuan.qingya.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouzhixuan on 2017/9/15.
 */

public class DiscoverTopicPresenter implements DiscoverTopicContract.DiscoverTopicPresenter {

    private DiscoverTopicContract.DiscoverTopicView view;

    public DiscoverTopicPresenter(DiscoverTopicContract.DiscoverTopicView view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void onLoveButtonClick(ArticleBean bean, boolean setLove) {
        if (setLove) {
            bean.setLove(bean.getLove() + 1);

        } else {
            bean.setLove(bean.getLove() - 1);
        }
        bean.setLoved(setLove);
    }

    @Override
    public List<ArticleBean> getData() {
        List<ArticleBean> list = new ArrayList<>();

        ArticleBean bean1 = new ArticleBean();
        bean1.setId(1);
        bean1.setType(Constant.CONTENT_DISCOVER_ARTICLE_READ);
        bean1.setTitle("向着二十亿光年的孤独，我情不自禁打了个喷嚏");
        bean1.setAuthor("文 / 别人的佩佩");
        bean1.setCover_img(R.drawable.a1);
        bean1.setContent("于是我们看完了一部又一部烂剧，就这样过完了前半生。在拥挤的人群中，我不想失去她。");
        bean1.setLove(126);
        list.add(bean1);

        bean1 = new ArticleBean();
        bean1.setId(1);
        bean1.setType(Constant.CONTENT_DISCOVER_ARTICLE_READ);
        bean1.setTitle("向着二十亿光年的孤独，我情不自禁打了个喷嚏");
        bean1.setAuthor("文 / 别人的佩佩");
        bean1.setCover_img(R.drawable.a1);
        bean1.setContent("于是我们看完了一部又一部烂剧，就这样过完了前半生。在拥挤的人群中，我不想失去她。");
        bean1.setLove(126);
        list.add(bean1);

        bean1 = new ArticleBean();
        bean1.setId(1);
        bean1.setType(Constant.CONTENT_DISCOVER_ARTICLE_READ);
        bean1.setTitle("向着二十亿光年的孤独，我情不自禁打了个喷嚏");
        bean1.setAuthor("文 / 别人的佩佩");
        bean1.setCover_img(R.drawable.a1);
        bean1.setContent("于是我们看完了一部又一部烂剧，就这样过完了前半生。在拥挤的人群中，我不想失去她。");
        bean1.setLove(126);
        list.add(bean1);

        bean1 = new ArticleBean();
        bean1.setId(1);
        bean1.setType(Constant.CONTENT_DISCOVER_ARTICLE_READ);
        bean1.setTitle("向着二十亿光年的孤独，我情不自禁打了个喷嚏");
        bean1.setAuthor("文 / 别人的佩佩");
        bean1.setCover_img(R.drawable.a1);
        bean1.setContent("于是我们看完了一部又一部烂剧，就这样过完了前半生。在拥挤的人群中，我不想失去她。");
        bean1.setLove(126);
        list.add(bean1);

        return list;
    }
}
