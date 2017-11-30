package com.xuan.qingya.Modules.Startup.Register;

import com.google.gson.Gson;
import com.xuan.qingya.Common.CacheKeys;
import com.xuan.qingya.Common.CacheValues;
import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.Core.net.APIService;
import com.xuan.qingya.Models.entity.User;
import com.xuan.qingya.Modules.Startup.Constant;
import com.xuan.qingya.Utils.CacheUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhouzhixuan on 2017/8/23.
 */

public class RegisterPresenter extends BasePresenter<ViewContract> {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private boolean verifyResult;

    public boolean onRegister(HashMap<Integer, String> list) {
        boolean isCorrect = checkIfCorrect(Constant.INPUT_EMAIL, list.get(Constant.INPUT_EMAIL)) &&
                checkIfCorrect(Constant.INPUT_USERNAME, list.get(Constant.INPUT_USERNAME)) &&
                checkIfCorrect(Constant.INPUT_PASSWORD, list.get(Constant.INPUT_PASSWORD)) &&
                checkIfCorrect(Constant.INPUT_PASSWORD_CONFIRMED, list.get(Constant.INPUT_PASSWORD), list.get(Constant.INPUT_PASSWORD_CONFIRMED)) &&
                checkIfCorrect(Constant.INPUT_SIGNATURE, list.get(Constant.INPUT_SIGNATURE));

        if (isCorrect) {
            final User user = new User(list.get(Constant.INPUT_EMAIL),
                    list.get(Constant.INPUT_USERNAME),
                    list.get(Constant.INPUT_PASSWORD),
                    list.get(Constant.INPUT_SIGNATURE));
            APIService.getService().register(user).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    User newUser = null;
                    try {
                        newUser = new Gson().fromJson(response.body().string(), User.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    user.setId(newUser.getId());
                    CacheUtil.get(getContext()).put(CacheKeys.USER_ENTITY, newUser);
                    CacheUtil.get(getContext()).put(CacheKeys.USER_STATUS, CacheValues.USER_LOGGED_IN);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }

        return isCorrect;
    }

    public boolean checkIfCorrect(int type, String... values) {
        if (isActivityAlive()) {
            if (values[0].equals("") || values[0] == null) {
                getMvpView().setErrorMessage(type, "不能为空");
                return false;
            } else {
                switch (type) {
                    case Constant.INPUT_EMAIL:
                        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
                        Matcher matcher = pattern.matcher(values[0]);
                        if (!matcher.matches()) {
                            getMvpView().setErrorMessage(type, "邮箱格式不正确");
                            return false;
                        } else {
                            if (!checkValidEmail(values[0])) {
                                getMvpView().setErrorMessage(type, "该邮箱已被注册");
                                return false;
                            }
                        }
                        break;
                    case Constant.INPUT_USERNAME:
                        if (values[0].length() > 10) {
                            return false;
                        } else {
                            if (!checkValidUsername(values[0])) {
                                getMvpView().setErrorMessage(type, "用户昵称已存在");
                                return false;
                            }
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
                    case Constant.INPUT_SIGNATURE:
                        if (values[0].length() > 16) {
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

    private boolean checkValidEmail(String emailValue) {
        verifyResult = false;
        APIService.getService().verifyEmail(emailValue).cancel();
        try {
            verifyResult = APIService.getService().verifyEmail(emailValue).execute().body().string().equals(String.valueOf(1));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return verifyResult;
    }

    private boolean checkValidUsername(String usernameValue) {
        verifyResult = false;
        APIService.getService().verifyUsername(usernameValue).cancel();
        try {
            verifyResult = APIService.getService().verifyUsername(usernameValue).execute().body().string().equals(String.valueOf(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return verifyResult;
    }
}
