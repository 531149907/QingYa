package com.xuan.qingya.Modules.Startup.Login;

import com.xuan.qingya.Modules.Startup.Constant;
import com.xuan.qingya.Modules.Startup.ForgotPassword.ForgotPasswordFragment;
import com.xuan.qingya.Modules.Startup.ForgotPassword.ForgotPasswordPresenter;
import com.xuan.qingya.Modules.Startup.Register.RegisterFragment;
import com.xuan.qingya.Modules.Startup.Register.RegisterPresenter;
import com.xuan.qingya.Modules.Startup.StartupContract;
import com.xuan.qingya.Utils.LogUtil;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhouzhixuan on 2017/8/24.
 */

public class LoginPresenter implements StartupContract.LoginPresenter {
    private StartupContract.LoginView view;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";

    public LoginPresenter(StartupContract.LoginView view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void onRegister() {
        RegisterFragment fragment = new RegisterFragment();
        view.startFragment(fragment, new RegisterPresenter(fragment), null);
    }

    @Override
    public void onLogin(HashMap<Integer, String> list) {
        boolean checkResult = checkIfCorrect(Constant.INPUT_EMAIL, list.get(Constant.INPUT_EMAIL)) &&
                checkIfCorrect(Constant.INPUT_PASSWORD, list.get(Constant.INPUT_PASSWORD), list.get(Constant.INPUT_EMAIL));

        if (checkResult) {
            //Activity is not prepared, can't access at this moment
            //view.startActivity(HomeActivity.class,null,null);

            //Process along with login cache
            LogUtil.show("register result", "success!");
        } else {
            LogUtil.show("register result", "failed!");
        }
    }

    @Override
    public void onForgotPassword() {
        ForgotPasswordFragment forgotPasswordFragment = new ForgotPasswordFragment();
        view.startFragment(forgotPasswordFragment, new ForgotPasswordPresenter(forgotPasswordFragment), null);
    }

    @Override
    public boolean checkIfCorrect(int type, String... values) {
        if (values[0].equals("") || values[0] == null) {
            view.setErrorMessage(type, "不能为空");
            return false;
        } else {
            switch (type) {
                case Constant.INPUT_EMAIL:
                    Pattern pattern = Pattern.compile(EMAIL_PATTERN);
                    Matcher matcher = pattern.matcher(values[0]);
                    if (!matcher.matches()) {
                        view.setErrorMessage(type, "邮箱格式不正确");
                        return false;
                    } else {
                        if (!checkValidEmail(values[0])) {
                            view.setErrorMessage(type, "邮箱尚未注册");
                            return false;
                        }
                    }
                    break;
                case Constant.INPUT_PASSWORD:
                    if (!checkValidPassword(values[0], values[1])) {
                        view.setErrorMessage(Constant.INPUT_PASSWORD, "密码不正确");
                        return false;
                    }
                    break;
            }
        }
        view.setErrorMessage(type, null);
        return true;
    }

    private boolean checkValidEmail(String emailValue) {
        //Retrofit: get result of email check
        //Check if email exist
        return true;
    }

    private boolean checkValidPassword(String passwordValue, String emailValue) {
        //Retrofit: get result of password check
        //Check if password matches the email
        return true;
    }
}
