package com.xuan.qingya.Modules.Discover.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.Core.net.APIService;
import com.xuan.qingya.Models.entity.Article;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhouzhixuan on 2017/8/30.
 */

public class DiscoverListPresenter extends BasePresenter<ViewContract> {
    public void showListContent(int dataType, int monthDate) {
        int type = 1;
        switch (dataType) {
            case Constant.CONTENT_SUB_TYPE_ARTICLE_IMAGE:
            case Constant.CONTENT_SUB_TYPE_ARTICLE_POEM:
            case Constant.CONTENT_SUB_TYPE_ARTICLE_READ:
                type = 1;
                break;
            case Constant.CONTENT_SUB_TYPE_MOVIE:
                type = 2;
                break;
            case Constant.CONTENT_SUB_TYPE_MUSIC:
                type = 3;
                break;
            case Constant.CONTENT_SUB_TYPE_PHOTOGRAPHY:
                type = 4;
                break;
            case Constant.CONTENT_SUB_TYPE_QUESTION:
                type = 5;
                break;
        }

        Map<String, Object> params = new HashMap<>();
        params.put("type", type);
        APIService.getService().getArticles(params).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                List<Article> data = null;
                try {
                    data = new Gson().fromJson(response.body().string(), new TypeToken<List<Article>>() {
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

    public void onLoveButtonClick(Article bean) {
        if (!bean.isLoved()) {
            bean.setLove(bean.getLove() + 1);

        } else {
            bean.setLove(bean.getLove() - 1);
        }
        bean.setLoved(!bean.isLoved());
    }

}
