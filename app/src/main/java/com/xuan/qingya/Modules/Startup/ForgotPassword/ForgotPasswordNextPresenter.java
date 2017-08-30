package com.xuan.qingya.Modules.Startup.ForgotPassword;

import com.xuan.qingya.Modules.Startup.Constant;
import com.xuan.qingya.Modules.Startup.Login.LoginFragment;
import com.xuan.qingya.Modules.Startup.Login.LoginPresenter;
import com.xuan.qingya.Modules.Startup.StartupContract;

import java.util.HashMap;

public class ForgotPasswordNextPresenter implements StartupContract.ForgotPasswordNextPresenter {

    private StartupContract.ForgotPasswordNextView view;

    public ForgotPasswordNextPresenter(StartupContract.ForgotPasswordNextView view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void onFinish(HashMap<Integer, String> values) {
        boolean checkResult = checkIfCorrect(Constant.INPUT_VERIFICATION, values.get(Constant.INPUT_VERIFICATION)) &&
                checkIfCorrect(Constant.INPUT_PASSWORD_CONFIRMED, values.get(Constant.INPUT_PASSWORD_CONFIRMED), values.get(Constant.INPUT_PASSWORD));

        if (checkResult) {
            LoginFragment fragment = new LoginFragment();
            view.startFragment(fragment, new LoginPresenter(fragment), null);
        }
    }

    @Override
    public boolean checkIfCorrect(int type, String... values) {
        if (values[0].equals("") || values[0] == null) {
            view.setErrorMessage(type, "不能为空");
            return false;
        } else {
            switch (type) {
                case Constant.INPUT_PASSWORD:
                    break;
                case Constant.INPUT_PASSWORD_CONFIRMED:
                    if (!values[0].equals(values[1])) {
                        view.setErrorMessage(type, "两次密码输入不一致");
                        return false;
                    }
                    break;
                case Constant.INPUT_VERIFICATION:
                    if (!checkValidVerificationCode(values[0])) {
                        view.setErrorMessage(type, "验证码错误");
                        return false;
                    }
                    break;
            }
        }
        view.setErrorMessage(type, null);
        return true;
    }

    private boolean checkValidVerificationCode(String codeValue) {
        //Retrofit: check if the code is correct
        return true;
    }
}
