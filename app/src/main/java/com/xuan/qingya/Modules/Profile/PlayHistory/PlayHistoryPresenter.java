package com.xuan.qingya.Modules.Profile.PlayHistory;

import com.xuan.qingya.Models.Entity.MusicBean;
import com.xuan.qingya.Modules.Profile.ProfileContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouzhixuan on 2017/9/6.
 */

public class PlayHistoryPresenter implements ProfileContract.PlayHistoryPresenter {

    private ProfileContract.PlayHistoryView view;

    public PlayHistoryPresenter(ProfileContract.PlayHistoryView view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void updatePlayerUI() {

    }

    @Override
    public void onPlayClick() {

    }

    @Override
    public void onPlayModeChange() {

    }

    @Override
    public void onItemClick() {

    }

    @Override
    public void onItemDelete() {

    }

    @Override
    public List<MusicBean> getData() {
        List<MusicBean> list = new ArrayList<>();
        MusicBean bean;
        bean = new MusicBean();
        bean.setAuthor("Aasa");
        bean.setTime("asass");
        bean.setTitle("%gfdscs");
        list.add(bean);
        bean = new MusicBean();
        bean.setAuthor("Aasa");
        bean.setTime("asass");
        bean.setTitle("%gfdscs");
        list.add(bean);
        bean = new MusicBean();
        bean.setAuthor("Aasa");
        bean.setTime("asass");
        bean.setTitle("%gfdscs");
        list.add(bean);
        return list;
    }

    private void updatePlayer() {

    }


}
