package com.xuan.qingya.Modules.Startup.Login;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.xuan.qingya.Core.Base.BaseFragment;
import com.xuan.qingya.Core.Base.BasePresenter;
import com.xuan.qingya.Modules.Startup.Constant;
import com.xuan.qingya.Modules.Startup.StartupActivity;
import com.xuan.qingya.Modules.Startup.StartupContract;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.FragmentManagement;

import java.util.HashMap;


public class LoginFragment extends BaseFragment implements StartupContract.LoginView {
    private TextInputLayout input_email;
    private TextInputLayout input_password;
    private Button reg_btn;
    private Button login_btn;
    private TextView forgot_password_btn;

    private StartupContract.LoginPresenter presenter;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_login, container, false);

        init();

        return mRootView;
    }

    @Override
    public void init() {

        ((StartupActivity) getActivity()).setToolbarTitle("登录");

        input_email = $(R.id.login_email_input);
        input_password = $(R.id.login_password_input);
        reg_btn = $(R.id.login_reg_btn);
        login_btn = $(R.id.login_login_btn);
        forgot_password_btn = $(R.id.login_forgot_password);

        initListeners(reg_btn, login_btn, forgot_password_btn);

        input_email.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.checkIfCorrect(Constant.INPUT_EMAIL, editable.toString());
            }
        });

        input_password.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.checkIfCorrect(Constant.INPUT_PASSWORD, editable.toString(),
                        input_email.getEditText().getText().toString());
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_reg_btn:
                presenter.onRegister();
                break;
            case R.id.login_login_btn:
                @SuppressLint("UseSparseArrays")
                HashMap<Integer, String> values = new HashMap<>();
                values.put(Constant.INPUT_EMAIL, input_email.getEditText().getText().toString());
                values.put(Constant.INPUT_PASSWORD, input_password.getEditText().getText().toString());
                presenter.onLogin(values);
                break;
            case R.id.login_forgot_password:
                presenter.onForgotPassword();
                break;
        }
    }

    @Override
    public void setPresenter(StartupContract.LoginPresenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void setErrorMessage(int type, String errorMessage) {
        switch (type) {
            case Constant.INPUT_EMAIL:
                input_email.setError(errorMessage);
                input_email.setErrorEnabled(true);
                break;
            case Constant.INPUT_PASSWORD:
                input_password.setError(errorMessage);
                input_password.setErrorEnabled(true);
                break;
        }
    }

    @Override
    public void startActivity(Class<?> target, BasePresenter presenter, @Nullable Bundle bundle, @Nullable String extra) {
        Intent intent = new Intent(getActivity(), target.getClass());
        startActivity(intent);
    }

    @Override
    public void startFragment(Fragment fragment, BasePresenter presenter, @Nullable String extra) {
        FragmentManagement.switchFragment(getActivity().getSupportFragmentManager(), fragment, R.id.startup_container, true);
    }
}
