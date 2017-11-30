package com.xuan.qingya.Modules.Profile.Notification.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.Core.net.APIService;
import com.xuan.qingya.Models.entity.Message;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhouzhixuan on 2017/9/4.
 */

public class NotificationPresenter extends BasePresenter<ViewContract> {

    public void getData() {
        /*todo: 从后台获取消息列表，并存储在本地数据库中*/
        APIService.getService().getNotifications().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                List<Message> data = null;
                try {
                    data = new Gson().fromJson(response.body().string(), new TypeToken<List<Message>>() {
                    }.getType());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (isActivityAlive()) {
                    getMvpView().showList(data);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
