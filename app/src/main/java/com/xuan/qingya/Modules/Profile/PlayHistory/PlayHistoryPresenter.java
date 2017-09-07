package com.xuan.qingya.Modules.Profile.PlayHistory;

import com.xuan.qingya.Modules.Profile.ProfileContract;

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

    private void updatePlayer() {

    }
}
