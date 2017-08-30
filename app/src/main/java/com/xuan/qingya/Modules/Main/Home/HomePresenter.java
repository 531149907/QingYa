package com.xuan.qingya.Modules.Main.Home;

import android.os.Bundle;

import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Models.Entity.ArticleBean;
import com.xuan.qingya.Modules.Main.MainActivity;
import com.xuan.qingya.Modules.Main.MainContract;
import com.xuan.qingya.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouzhixuan on 2017/8/26.
 */

public class HomePresenter implements MainContract.HomePresenter {

    private MainContract.HomeView view;

    public HomePresenter(MainContract.HomeView view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void onListItemClick(ArticleBean articleBean, Bundle bundle) {

    }

    @Override
    public List<ArticleBean> getListData() {
        List<ArticleBean> list = new ArrayList<>();

        ArticleBean bean1 = new ArticleBean();
        bean1.setId(1);
        bean1.setType(Constant.CONTENT_DISCOVER_ARTICLE_READ);
        bean1.setTitle("向着二十亿光年的孤独，我情不自禁打了个喷嚏");
        bean1.setAuthor("文 / 别人的佩佩");
        bean1.setCover_img(R.drawable.a1);
        bean1.setContent("于是我们看完了一部又一部烂剧，就这样过完了前半生。在拥挤的人群中，我不想失去她。");
        bean1.setLove(126);

        ArticleBean bean2 = new ArticleBean();
        bean2.setId(2);
        bean2.setType(Constant.CONTENT_DISCOVER_ARTICLE_IMAGE);
        bean2.setCover_img(R.drawable.a2);
        bean2.setAuthor("文 / 别人的佩佩");
        bean2.setContent("生命里无疑还有许多夏天，但肯定没有一个夏天，会如今夏。");
        bean2.setLove(34);

        ArticleBean bean3 = new ArticleBean();
        bean3.setId(3);
        bean3.setType(Constant.CONTENT_DISCOVER_ARTICLE_POEM);
        bean3.setAuthor("文 / 别人的佩佩");
        bean3.setContent("撕去日历，\n" + "为的是不让时间停止，\n" + "即使她人生里的可能都已失去了，\n" + "但倘若时间不存在，\n" + "那么她的爱情尸骨无存。");
        bean3.setLove(27);

        ArticleBean bean4 = new ArticleBean();
        bean4.setId(4);
        bean4.setType(Constant.CONTENT_DISCOVER_QUESTION);
        bean4.setTitle("为什么人的一生如此短暂？");
        bean4.setAnswer_author("文 / 别人的佩佩");
        bean4.setContent("于是我们看完了一部又一部烂剧，就这样过完了前半生。在拥挤的人群中，我不想失去她。");
        bean4.setLove(71);

        ArticleBean bean5 = new ArticleBean();
        bean5.setId(5);
        bean5.setType(Constant.CONTENT_DISCOVER_MUSIC);
        bean5.setTitle("我的少年时代时过去了，但我一点也不怀念它");
        bean5.setAuthor("文 / 别人的佩佩");
        bean5.setCover_img(R.drawable.a18);
        bean5.setContent("生命里无疑还有许多夏天，但肯定没有一个夏天，会如今夏。");
        bean5.setLove(196);

        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        list.add(bean4);
        list.add(bean5);

        return list;
    }

    @Override
    public int onLoveButtonClick(ArticleBean bean, boolean setLove) {
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
        ((MainActivity) ((HomeFragment) view).getActivity()).setScrollViewPosition(0, 0);
    }
}
