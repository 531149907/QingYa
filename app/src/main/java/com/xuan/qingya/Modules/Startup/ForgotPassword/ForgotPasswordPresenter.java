package com.xuan.qingya.Modules.Startup.ForgotPassword;

import com.xuan.qingya.Modules.Startup.Constant;
import com.xuan.qingya.Modules.Startup.StartupContract;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhouzhixuan on 2017/8/24.
 */

public class ForgotPasswordPresenter implements StartupContract.ForgotPasswordPresenter {

    private StartupContract.ForgotPasswordView view;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";


    public ForgotPasswordPresenter(StartupContract.ForgotPasswordView view){
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void onNext(String value) {
        boolean checkResult = checkIfCorrect(Constant.INPUT_EMAIL,value);

        if(checkResult){
            ForgotPasswordNextFragment fragment = new ForgotPasswordNextFragment();
            view.startFragment(fragment,new ForgotPasswordNextPresenter(fragment),null);
        }

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
}
