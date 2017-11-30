package com.xuan.qingya.Modules.Main.Discover;

import com.xuan.qingya.Core.base.BaseView;
import com.xuan.qingya.Models.entity.Banner;

import java.util.List;

/**
 * Created by zhouzhixuan on 2017/10/14.
 */

public interface ViewContract extends BaseView {
    void showList(List<Banner> list);
}
