package com.xuan.qingya.Modules.Settings.Account.Password;

import com.google.gson.Gson;
import com.xuan.qingya.Common.CacheKeys;
import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.Core.net.APIService;
import com.xuan.qingya.Models.entity.User;
import com.xuan.qingya.Modules.Startup.Constant;
import com.xuan.qingya.Utils.CacheUtil;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhouzhixuan on 2017/9/8.
 */

public class PasswordPresenter extends BasePresenter<ViewContract> {
    public boolean onChangeConfirm(HashMap<Integer, String> list) {
        boolean checkResult = checkIfCorrect(Constant.INPUT_PASSWORD_OLD, list.get(Constant.INPUT_PASSWORD_OLD)) &&
                checkIfCorrect(Constant.INPUT_PASSWORD, list.get(Constant.INPUT_PASSWORD), list.get(Constant.INPUT_PASSWORD_CONFIRMED));

        if (checkResult) {
            User user = (User) CacheUtil.get(getContext()).getAsObject(CacheKeys.USER_ENTITY);
            APIService.getService().updateUsername(user.getId(), list.get(Constant.INPUT_PASSWORD)).enqueue(new Callback<ResponseBody>() {
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
                    case Constant.INPUT_PASSWORD_OLD:
                        if (!checkIfOldPasswordCorrect(values[0])) {
                            getMvpView().setErrorMessage(type, "邮箱格式不正确");
                            return false;
                        }
                        break;
                    case Constant.INPUT_PASSWORD:
                        break;
                    case Constant.INPUT_PASSWORD_CONFIRMED:
                        if (!values[0].equals(values[1])) {
                            getMvpView().setErrorMessage(type, "两次密码输入不一致");
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

    private boolean checkIfOldPasswordCorrect(String oldPassword) {
        User user = (User) CacheUtil.get(getContext()).getAsObject(CacheKeys.USER_ENTITY);
        return user.getPassword().equals(oldPassword);
    }
}
