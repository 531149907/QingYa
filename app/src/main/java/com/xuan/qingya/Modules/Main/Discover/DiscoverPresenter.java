package com.xuan.qingya.Modules.Main.Discover;

import android.os.Bundle;

import com.xuan.qingya.Models.Entity.BannerBean;
import com.xuan.qingya.Models.Entity.TopicBean;
import com.xuan.qingya.Modules.Main.Home.HomeFragment;
import com.xuan.qingya.Modules.Main.MainActivity;
import com.xuan.qingya.Modules.Main.MainContract;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouzhixuan on 2017/8/27.
 */

public class DiscoverPresenter implements MainContract.DiscoverPresenter {

    private MainContract.DiscoverView view;

    public DiscoverPresenter(MainContract.DiscoverView view){
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void onEntryItemClick(int entryType, Bundle bundle) {
        LogUtil.show("onEntryItemClick",entryType);
    }

    @Override
    public void onBannerItemClick(int id, Bundle bundle) {
        LogUtil.show("onBannerItemClick",id);
    }

    @Override
    public void onListItemClick(TopicBean bean, Bundle bundle) {
        LogUtil.show("onListItemClick",bean.getTitle());
    }

    @Override
    public int onLoveButtonClick(TopicBean bean, boolean setLove) {
        if(setLove){
            bean.setLove(bean.getLove()+1);

        }else{
            bean.setLove(bean.getLove()-1);
        }
        bean.setLoved(setLove);
        return bean.getLove();
    }

    @Override
    public void retainNestedScrollViewPosition() {
        ((MainActivity)((DiscoverFragment)view).getActivity()).setScrollViewPosition(2,2);
    }

    @Override
    public List<BannerBean> getBannerListData() {
        List<BannerBean> list = new ArrayList<>();

        BannerBean bean = new BannerBean();
        bean.setCover(R.drawable.a9);
        list.add(bean);

        bean = new BannerBean();
        bean.setCover(R.drawable.a6);
        list.add(bean);

        bean = new BannerBean();
        bean.setCover(R.drawable.a7);
        list.add(bean);

        bean = new BannerBean();
        bean.setCover(R.drawable.a10);
        list.add(bean);

        bean = new BannerBean();
        bean.setCover(R.drawable.a11);
        list.add(bean);

        return list;
    }

    @Override
    public List<TopicBean> getTopicListData() {
        List<TopicBean> list = new ArrayList<>();
        TopicBean bean;

        bean= new TopicBean();
        bean.setTitle("学霸看了涨姿势，文青看了涨逼格，死宅看了打开新世界的大门");
        bean.setAuthor("文 / 别人的佩佩");
        bean.setLove(127);
        bean.setCover(R.drawable.a15);
        list.add(bean);

        bean= new TopicBean();
        bean.setTitle("人的快乐，多半是自以为是的快乐。植物动物，如果快乐，真快乐");
        bean.setAuthor("文 / 别人的佩佩");
        bean.setLove(23);
        bean.setCover(R.drawable.a17);
        list.add(bean);

        bean= new TopicBean();
        bean.setTitle("六月中旬我开始找工作，因为我发现我他妈没钱了，据说我还欠了朋友王强一万块");
        bean.setAuthor("文 / 别人的佩佩");
        bean.setLove(98);
        bean.setCover(R.drawable.a24);
        list.add(bean);

        bean= new TopicBean();
        bean.setTitle("我跟着去他房间，透过他房间那扇不能打开的，模模糊糊的窗户，我什么都看不到");
        bean.setAuthor("文 / 别人的佩佩");
        bean.setLove(39);
        bean.setCover(R.drawable.a12);
        list.add(bean);

        bean= new TopicBean();
        bean.setTitle("想来我一直没有去死，大概是因为没有充足的理由吧");
        bean.setAuthor("文 / 别人的佩佩");
        bean.setLove(54);
        bean.setCover(R.drawable.a21);
        list.add(bean);

        return list;
    }
}
