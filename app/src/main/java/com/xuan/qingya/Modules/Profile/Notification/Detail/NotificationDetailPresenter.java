package com.xuan.qingya.Modules.Profile.Notification.Detail;

import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.Models.entity.Message;

/**
 * Created by zhouzhixuan on 2017/9/4.
 */

public class NotificationDetailPresenter extends BasePresenter<ViewContract> {
    public void onDelete(Message bean) {
        if (bean != null) {
            // todo: 消息删除操作, 从本地数据库中删除
        }
    }
}
