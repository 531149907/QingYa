package com.xuan.qingya.Modules.Startup.ForgotPassword;

import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.Modules.Startup.Constant;

import java.util.HashMap;

public class ForgotPasswordNextPresenter extends BasePresenter<ViewContract> {

    public boolean onFinish(HashMap<Integer, String> values) {
        boolean checkResult = checkIfCorrect(Constant.INPUT_VERIFICATION, values.get(Constant.INPUT_VERIFICATION)) &&
                checkIfCorrect(Constant.INPUT_PASSWORD_CONFIRMED, values.get(Constant.INPUT_PASSWORD_CONFIRMED), values.get(Constant.INPUT_PASSWORD));

        if (checkResult) {
            //todo: 更新用户密码
        }

        return checkResult;
    }

    public boolean checkIfCorrect(int type, String... values) {
        if (!isActivityAlive()) {
            return false;
        }
        if (values[0].equals("") || values[0] == null) {
            getMvpView().setErrorMessage(type, "不能为空");
            return false;
        } else {
            switch (type) {
                case Constant.INPUT_PASSWORD:
                    break;
                case Constant.INPUT_PASSWORD_CONFIRMED:
                    if (!values[0].equals(values[1])) {
                        getMvpView().setErrorMessage(type, "两次密码输入不一致");
                        return false;
                    }
                    break;
                case Constant.INPUT_VERIFICATION:
                    if (!checkValidVerificationCode(values[0])) {
                        getMvpView().setErrorMessage(type, "验证码错误");
                        return false;
                    }
                    break;
            }
        }
        getMvpView().setErrorMessage(type, null);
        return true;
    }

    private boolean checkValidVerificationCode(String codeValue) {
        //todo: 检查验证码是否正确
        return true;
    }
}
