package com.xuan.qingya.Modules.Startup.Register;

import com.xuan.qingya.Modules.Startup.Constant;
import com.xuan.qingya.Modules.Startup.Login.LoginFragment;
import com.xuan.qingya.Modules.Startup.Login.LoginPresenter;
import com.xuan.qingya.Modules.Startup.StartupContract;
import com.xuan.qingya.Utils.LogUtil;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhouzhixuan on 2017/8/23.
 */

public class RegisterPresenter implements StartupContract.RegisterPresenter {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private StartupContract.RegisterView view;

    public RegisterPresenter(StartupContract.RegisterView view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void onRegister(HashMap<Integer, String> list) {

        boolean checkResult = checkIfCorrect(Constant.INPUT_EMAIL, list.get(Constant.INPUT_EMAIL)) &&
                checkIfCorrect(Constant.INPUT_USERNAME, list.get(Constant.INPUT_USERNAME)) &&
                checkIfCorrect(Constant.INPUT_PASSWORD, list.get(Constant.INPUT_PASSWORD)) &&
                checkIfCorrect(Constant.INPUT_PASSWORD_CONFIRMED, list.get(Constant.INPUT_PASSWORD), list.get(Constant.INPUT_PASSWORD_CONFIRMED)) &&
                checkIfCorrect(Constant.INPUT_SIGNATURE, list.get(Constant.INPUT_SIGNATURE));

        if (checkResult) {
            //Activity is not prepared, can't access at this moment
            //view.startActivity(HomeActivity.class,null,null);

            //Process along with login cache
            LogUtil.show("register result", "success!");
        } else {
            LogUtil.show("register", "fail!");
        }
    }

    @Override
    public void onLogin() {
        LoginFragment fragment = new LoginFragment();
        view.startFragment(fragment,new LoginPresenter(fragment),null);
    }

    @Override
    public void startAgreement() {
        //Activity is not prepared, can't access at this moment
        //view.startActivity(Settings.class,null,null);
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
                            view.setErrorMessage(type, "该邮箱已被注册");
                            return false;
                        }
                    }
                    break;
                case Constant.INPUT_USERNAME:
                    if (values[0].length() > 10) {
                        return false;
                    } else {
                        if (!checkValidUsername(values[0])) {
                            view.setErrorMessage(type, "用户昵称已存在");
                            return false;
                        }
                    }
                    break;
                case Constant.INPUT_PASSWORD:
                    break;
                case Constant.INPUT_PASSWORD_CONFIRMED:
                    if (!values[0].equals(values[1])) {
                        view.setErrorMessage(type, "两次密码输入不一致");
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
        view.setErrorMessage(type, null);
        return true;
    }

    private boolean checkValidEmail(String emailValue) {
        //Retrofit: get result of email check
        return true;
    }

    private boolean checkValidUsername(String usernameValue) {
        //Retrofit: get result of username check
        return true;
    }
}
