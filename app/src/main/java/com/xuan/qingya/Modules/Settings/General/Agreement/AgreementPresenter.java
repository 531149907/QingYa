package com.xuan.qingya.Modules.Settings.General.Agreement;

import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.Core.net.APIService;

import java.io.IOException;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhouzhixuan on 2017/10/18.
 */

public class AgreementPresenter extends BasePresenter<ViewContract> {
    public void getContent() {
        APIService.getService().getAgreement().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String content = null;
                try {
                    content = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (isActivityAlive()) {
                    getMvpView().setContent(content);
                    getMvpView().setDate(new Date().toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
