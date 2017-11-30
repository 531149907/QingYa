package com.xuan.qingya.Modules.Main.Home;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.Core.net.APIService;
import com.xuan.qingya.Models.entity.Article;
import com.xuan.qingya.Modules.Main.MainActivity;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.xuan.qingya.Common.Constant.CONTENT_SUB_TYPE_MOVIE;
import static com.xuan.qingya.Common.Constant.CONTENT_SUB_TYPE_PHOTOGRAPHY;

/**
 * Created by zhouzhixuan on 2017/8/26.
 */

public class HomePresenter extends BasePresenter<ViewContract> {
    public void getListData() {
        APIService.getService().getAllArticles().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                List<Article> data = null;
                try {
                    data = new Gson().fromJson(response.body().string(), new TypeToken<List<Article>>() {
                    }.getType());
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i).getSubType() == CONTENT_SUB_TYPE_MOVIE
                                || data.get(i).getSubType() == CONTENT_SUB_TYPE_PHOTOGRAPHY) {
                            data.remove(i);
                        }
                    }
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
        /*todo: 取消收藏，更新后台数据库和本地数据库*/
        if (!bean.isLoved()) {
            bean.setLove(bean.getLove() + 1);

        } else {
            bean.setLove(bean.getLove() - 1);
        }
        bean.setLoved(!bean.isLoved());
    }

    public void retainNestedScrollViewPosition() {
        if (!isActivityAlive()) {
            return;
        }
        ((MainActivity) ((HomeFragment) getMvpView()).getActivity()).setScrollViewPosition(0, 0);
    }
}
