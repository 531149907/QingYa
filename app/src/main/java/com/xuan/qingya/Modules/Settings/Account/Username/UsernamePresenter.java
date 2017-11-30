package com.xuan.qingya.Modules.Settings.Account.Username;

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

public class UsernamePresenter extends BasePresenter<ViewContract> {
    public boolean onConfirm(String value) {
        boolean checkResult = checkIfCorrect(Constant.INPUT_EMAIL, value);

        if (checkResult) {
            User user = (User) CacheUtil.get(getContext()).getAsObject(CacheKeys.USER_ENTITY);
            APIService.getService().updateUsername(user.getId(), value).enqueue(new Callback<ResponseBody>() {
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
                    case Constant.INPUT_USERNAME:
                        if (!checkValidUsername(values[0])) {
                            getMvpView().setErrorMessage(type, "用户名已存在");
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

    private boolean checkValidUsername(String newUserName) {
        APIService.getService().verifyUsername(newUserName).cancel();
        boolean verifyResult = false;
        try {
            verifyResult = APIService.getService().verifyUsername(newUserName).execute().body().string().equals(String.valueOf(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return verifyResult;
    }
}
