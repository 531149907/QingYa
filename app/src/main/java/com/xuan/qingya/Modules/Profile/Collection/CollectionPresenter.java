package com.xuan.qingya.Modules.Profile.Collection;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xuan.qingya.Common.CacheKeys;
import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.Core.net.APIService;
import com.xuan.qingya.Models.entity.Article;
import com.xuan.qingya.Models.entity.User;
import com.xuan.qingya.Models.entity.UserLove;
import com.xuan.qingya.Utils.CacheUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhouzhixuan on 2017/9/5.
 */

public class CollectionPresenter extends BasePresenter<ViewContract> {
    public void onLoveCancelClick(Article bean) {
        /*todo: 取消收藏，更新后台数据库*/
        bean.setLoved(!bean.isLoved());
    }

    public void getListData() {
        User user = (User) CacheUtil.get(getContext()).getAsObject(CacheKeys.USER_ENTITY);
        APIService.getService().getCollections(user.getId()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (isActivityAlive()) {
                    List<UserLove> list = null;
                    try {
                        list = new Gson().fromJson(response.body().string(), new TypeToken<List<UserLove>>() {
                        }.getType());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    final List<Article> data = new ArrayList<>();
                    if (list != null) {
                        for (UserLove userLove : list) {
                            APIService.getService().getArticle(userLove.getContentId()).enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    try {
                                        data.add(new Gson().fromJson(response.body().string(), Article.class));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {

                                }
                            });
                        }
                    }
                    getMvpView().showList(data);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


}
