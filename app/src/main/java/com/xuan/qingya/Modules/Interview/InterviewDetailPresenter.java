package com.xuan.qingya.Modules.Interview;

import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.Models.entity.Interview;

/**
 * Created by zhouzhixuan on 2017/8/29.
 */

public class InterviewDetailPresenter extends BasePresenter<ViewContract> {
    public void onLoveClick(Interview bean) {
        /*todo: 取消收藏，更新后台数据库和本地数据库*/
        if (!bean.isLoved()) {
            bean.setLove(bean.getLove() + 1);

        } else {
            bean.setLove(bean.getLove() - 1);
        }
        bean.setLoved(!bean.isLoved());
    }
}
