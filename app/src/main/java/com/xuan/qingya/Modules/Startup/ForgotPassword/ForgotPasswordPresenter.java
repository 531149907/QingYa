package com.xuan.qingya.Modules.Startup.ForgotPassword;

import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.Modules.Startup.Constant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhouzhixuan on 2017/8/24.
 */

public class ForgotPasswordPresenter extends BasePresenter<ViewContract> {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";

    public boolean onNext(String value) {
        boolean checkResult = checkIfCorrect(Constant.INPUT_EMAIL, value);

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
                }
            }
            getMvpView().setErrorMessage(type, null);
            return true;
        }
        return false;
    }

    private boolean checkValidEmail(String emailValue) {
        //todo: 检查email是否存在
        return true;
    }
}
