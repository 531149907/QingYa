package com.xuan.qingya.Modules.Profile.PlayHistory;

import com.xuan.qingya.Core.base.BaseView;
import com.xuan.qingya.Models.entity.Music;

import java.util.List;

/**
 * Created by zhouzhixuan on 2017/10/16.
 */

public interface ViewContract extends BaseView {
    void showList(List<Music> list);
}
