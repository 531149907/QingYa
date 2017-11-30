package com.xuan.qingya.Modules.Startup.Login;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.xuan.qingya.Core.base.BaseFragment;
import com.xuan.qingya.Modules.Main.MainActivity;
import com.xuan.qingya.Modules.Startup.Constant;
import com.xuan.qingya.Modules.Startup.StartupActivity;
import com.xuan.qingya.R;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginFragment extends BaseFragment<ViewContract, LoginPresenter> implements ViewContract {

    @BindView(R.id.email_input)
    TextInputLayout input_email;
    @BindView(R.id.password_input)
    TextInputLayout input_password;
    @BindView(R.id.regButton)
    Button regButton;
    @BindView(R.id.loginButton)
    Button loginButton;
    @BindView(R.id.forgotPasswordButton)
    TextView forgotPasswordButton;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, mRootView);

        init();
        initListeners(regButton, loginButton, forgotPasswordButton);

        return mRootView;
    }

    public void init() {
        ((StartupActivity) getActivity()).setToolbarTitle("登录");

        input_email.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //presenter.checkIfCorrect(Constant.INPUT_EMAIL, editable.toString());
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
                //presenter.checkIfCorrect(Constant.INPUT_PASSWORD, editable.toString(),input_email.getEditText().getText().toString());
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.regButton:
                intent = new Intent(getActivity(), StartupActivity.class);
                intent.putExtra(com.xuan.qingya.Common.Constant.ENTRY_TYPE, com.xuan.qingya.Common.Constant.FRAGMENT_REGISTER);
                startActivity(intent);
                break;
            case R.id.loginButton:
                @SuppressLint("UseSparseArrays")
                HashMap<Integer, String> values = new HashMap<>();
                values.put(Constant.INPUT_EMAIL, input_email.getEditText().getText().toString());
                values.put(Constant.INPUT_PASSWORD, input_password.getEditText().getText().toString());
                if (presenter.onLogin(values)) {
                    intent = new Intent();
                    intent.setClass(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
                break;
            case R.id.forgotPasswordButton:
                intent = new Intent(getActivity(), StartupActivity.class);
                intent.putExtra(com.xuan.qingya.Common.Constant.ENTRY_TYPE, com.xuan.qingya.Common.Constant.FRAGMENT_FORGOT_PASSWORD);
                startActivity(intent);
                break;
        }
    }

    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter();
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
}
