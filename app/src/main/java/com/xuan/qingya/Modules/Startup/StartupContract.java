package com.xuan.qingya.Modules.Startup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.xuan.qingya.Core.Base.BasePresenter;
import com.xuan.qingya.Core.Base.BaseView;

import java.util.HashMap;

/**
 * Created by zhouzhixuan on 2017/8/23.
 */

public interface StartupContract {

    //Register
    interface RegisterView extends BaseView<RegisterPresenter> {
        void setErrorMessage(int type, String errorMessage);

        void startActivity(Class<?> target, BasePresenter presenter, @Nullable Bundle bundle, @Nullable String extra);

        void startFragment(Fragment fragment, BasePresenter presenter, @Nullable String extra);
    }

    interface RegisterPresenter extends BasePresenter {
        void onRegister(HashMap<Integer, String> list);

        void onLogin();

        void startAgreement();

        boolean checkIfCorrect(int type, String... values);
    }

    //Login
    interface LoginView extends BaseView<LoginPresenter> {
        void setErrorMessage(int type, String errorMessage);

        void startActivity(Class<?> target, BasePresenter presenter, @Nullable Bundle bundle, @Nullable String extra);

        void startFragment(Fragment fragment, BasePresenter presenter, @Nullable String extra);
    }

    interface LoginPresenter extends BasePresenter {
        void onRegister();

        void onLogin(HashMap<Integer, String> list);

        void onForgotPassword();

        boolean checkIfCorrect(int type, String... values);
    }

    //Forgot password
    interface ForgotPasswordView extends BaseView<ForgotPasswordPresenter> {
        void startFragment(Fragment fragment, BasePresenter presenter, @Nullable String extra);

        void setErrorMessage(int type, String errorMessage);
    }

    interface ForgotPasswordNextView extends BaseView<ForgotPasswordNextPresenter> {
        void setErrorMessage(int type, String errorMessage);

        void startFragment(Fragment fragment, BasePresenter presenter, @Nullable String extra);
    }

    interface ForgotPasswordPresenter extends BasePresenter {
        void onNext(String value);

        boolean checkIfCorrect(int type, String... values);
    }

    interface ForgotPasswordNextPresenter extends BasePresenter {
        void onFinish(HashMap<Integer, String> values);

        boolean checkIfCorrect(int type, String... values);
    }
}
