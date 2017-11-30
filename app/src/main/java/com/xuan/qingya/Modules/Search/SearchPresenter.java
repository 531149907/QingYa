package com.xuan.qingya.Modules.Search;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xuan.qingya.Common.CacheKeys;
import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.Core.net.APIService;
import com.xuan.qingya.Models.entity.Article;
import com.xuan.qingya.Models.entity.Base;
import com.xuan.qingya.Models.entity.SearchHistory;
import com.xuan.qingya.Models.entity.User;
import com.xuan.qingya.Models.entity.UserLove;
import com.xuan.qingya.Utils.CacheUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.xuan.qingya.Common.Constant.CONTENT_SUB_TYPE_MOVIE;
import static com.xuan.qingya.Common.Constant.CONTENT_SUB_TYPE_PHOTOGRAPHY;

/**
 * Created by zhouzhixuan on 2017/9/13.
 */

public class SearchPresenter extends BasePresenter<ViewContract> {
    public void getHistoryDataList() {
        /*todo: 从本地搜索记录的数据库中获取数据*/
        List<SearchHistory> list = new ArrayList<>();

        SearchHistory bean = new SearchHistory("history 1");
        bean.setType(Constant.SEARCH_HISTORY);
        list.add(bean);

        bean = new SearchHistory("history 2");
        bean.setType(Constant.SEARCH_HISTORY);
        list.add(bean);

        bean = new SearchHistory("history 3");
        bean.setType(Constant.SEARCH_HISTORY);
        list.add(bean);

        getMvpView().showHistoryList(list);
    }

    public void getResultDataList(final String keyword) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword);

        APIService.getService().getArticles(params).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                if (response.body() != null) {
                    List<Article> list = null;
                    try {
                        list = new Gson().fromJson(response.body().string(), new TypeToken<List<Article>>() {
                        }.getType());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    List<Base> data = new ArrayList<>();
                    if (list != null && isActivityAlive()) {
                        data.addAll(list);
                        for (int i = 0; i < data.size(); i++) {
                            if (data.get(i).getSubType() == CONTENT_SUB_TYPE_MOVIE
                                    || data.get(i).getSubType() == CONTENT_SUB_TYPE_PHOTOGRAPHY) {
                                data.remove(i);
                            }
                        }
                        getMvpView().showResultList(data);
                        getMvpView().listSwitchAnimation();
                    }
                } else {
                    /*todo:显示无搜索结果*/
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void onLoveButtonClick(Base bean) {
        /*todo: 更新点赞*/
        if (!bean.isLoved()) {
            bean.setLove(bean.getLove() + 1);

        } else {
            bean.setLove(bean.getLove() - 1);
        }
        bean.setLoved(!bean.isLoved());
        User user = (User) CacheUtil.get(getContext()).getAsObject(CacheKeys.USER_ENTITY);
        UserLove userLove = new UserLove(user.getId(), bean.getSubType(), bean.getId(), new Date().toString());
        APIService.getService().addNewCollectionItem(userLove).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    protected void detach() {
        super.detach();

    }
}
