package com.xuan.qingya.Modules.Settings.Account.Signature;

import com.google.gson.Gson;
import com.xuan.qingya.Common.CacheKeys;
import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.Core.net.APIService;
import com.xuan.qingya.Models.entity.User;
import com.xuan.qingya.Modules.Startup.Constant;
import com.xuan.qingya.Utils.CacheUtil;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhouzhixuan on 2017/9/8.
 */

public class SignaturePresenter extends BasePresenter<ViewContract> {
    private static final int SIGNATURE_LENGTH = 16;

    public boolean onConfirm(String value) {
        boolean checkResult = checkIfCorrect(Constant.INPUT_EMAIL, value);

        if (checkResult) {
            User user = (User) CacheUtil.get(getContext()).getAsObject(CacheKeys.USER_ENTITY);
            APIService.getService().updateSignature(user.getId(), value).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    User newUser = null;
                    try {
                        newUser = new Gson().fromJson(response.body().string(), User.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    CacheUtil.get(getContext()).put(CacheKeys.USER_ENTITY, newUser);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }

        return checkResult;
    }

    public boolean checkIfCorrect(int type, String... values) {
        if (isActivityAlive()) {
            if (values[0].equals("") || values[0] == null) {
                getMvpView().setErrorMessage(type, "不能为空");
                return false;
            } else {
                switch (type) {
                    case Constant.INPUT_SIGNATURE:
                        if (values[0].length() > SIGNATURE_LENGTH) {
                            getMvpView().setErrorMessage(type, "不能超过16个字符");
                            return false;
                        }
                        break;
                }
            }
            getMvpView().setErrorMessage(type, null);
            return true;
        }
        return false;
    }

}
