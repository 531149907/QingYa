package com.xuan.qingya.Modules.Startup.Login;

import com.xuan.qingya.Common.CacheKeys;
import com.xuan.qingya.Common.CacheValues;
import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.Core.net.APIService;
import com.xuan.qingya.Modules.Startup.Constant;
import com.xuan.qingya.Utils.CacheUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhouzhixuan on 2017/8/24.
 */

public class LoginPresenter extends BasePresenter<ViewContract> {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private boolean verifyResult;

    public boolean onLogin(HashMap<Integer, String> list) {
        boolean checkResult = checkIfCorrect(Constant.INPUT_EMAIL, list.get(Constant.INPUT_EMAIL)) &&
                checkIfCorrect(Constant.INPUT_PASSWORD, list.get(Constant.INPUT_PASSWORD), list.get(Constant.INPUT_EMAIL));

        if (checkResult) {
            //todo: 进行用户登录操作
            CacheUtil cacheUtil = CacheUtil.get(getContext());
            cacheUtil.put(CacheKeys.USER_STATUS, CacheValues.USER_LOGGED_IN);
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
                    case Constant.INPUT_EMAIL:
                        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
                        Matcher matcher = pattern.matcher(values[0]);
                        if (!matcher.matches()) {
                            getMvpView().setErrorMessage(type, "邮箱格式不正确");
                            return false;
                        } else {
                            if (!checkValidEmail(values[0])) {
                                getMvpView().setErrorMessage(type, "邮箱尚未注册");
                                return false;
                            }
                        }
                        break;
                    case Constant.INPUT_PASSWORD:
                        if (!checkValidPassword(values[0], values[1])) {
                            getMvpView().setErrorMessage(Constant.INPUT_PASSWORD, "密码不正确");
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

    private boolean checkValidPassword(String passwordValue, String emailValue) {
        verifyResult = false;
        APIService.getService().verifyCombination(emailValue, passwordValue).cancel();
        try {
            verifyResult = APIService.getService().verifyCombination(emailValue, passwordValue).execute().body().string().equals(String.valueOf(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return verifyResult;
    }
}
