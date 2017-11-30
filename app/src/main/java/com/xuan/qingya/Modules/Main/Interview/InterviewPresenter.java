package com.xuan.qingya.Modules.Main.Interview;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.Core.net.APIService;
import com.xuan.qingya.Models.entity.Interview;
import com.xuan.qingya.Modules.Main.MainActivity;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhouzhixuan on 2017/8/27.
 */

public class InterviewPresenter extends BasePresenter<ViewContract> {
    public void getListData() {
        APIService.getService().getInterviews().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                List<Interview> data = null;
                try {
                    data = new Gson().fromJson(response.body().string(), new TypeToken<List<Interview>>() {
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

    public void onLoveButtonClick(Interview bean) {
        /*todo: 取消收藏，更新后台数据库和本地数据库*/
        if (!bean.isLoved()) {
            bean.setLove(bean.getLove() + 1);

        } else {
            bean.setLove(bean.getLove() - 1);
        }
        bean.setLoved(!bean.isLoved());
    }

    public void retainNestedScrollViewPosition() {
        ((MainActivity) ((InterviewFragment) getMvpView()).getActivity()).setScrollViewPosition(1, 1);
    }
}
