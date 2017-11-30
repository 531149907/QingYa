package com.xuan.qingya.Modules.Startup.Register;


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
import com.xuan.qingya.Modules.Settings.General.SettingsActivity;
import com.xuan.qingya.Modules.Startup.Constant;
import com.xuan.qingya.Modules.Startup.StartupActivity;
import com.xuan.qingya.R;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterFragment extends BaseFragment<ViewContract, RegisterPresenter> implements ViewContract {

    @BindView(R.id.email_input)
    TextInputLayout input_email;
    @BindView(R.id.username_input)
    TextInputLayout input_username;
    @BindView(R.id.password_input)
    TextInputLayout input_password;
    @BindView(R.id.confirm_password_input)
    TextInputLayout input_confirm_password;
    @BindView(R.id.signature_input)
    TextInputLayout input_signature;
    @BindView(R.id.regButton)
    Button regButton;
    @BindView(R.id.loginButton)
    Button loginButton;
    @BindView(R.id.agreementButton)
    TextView agreementButton;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, mRootView);

        init();
        initListeners(regButton, loginButton, agreementButton);

        return mRootView;
    }

    public void init() {
        ((StartupActivity) getActivity()).setToolbarTitle("注册");

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

        input_username.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //presenter.checkIfCorrect(Constant.INPUT_USERNAME, editable.toString());
            }
        });

        input_confirm_password.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //presenter.checkIfCorrect(Constant.INPUT_PASSWORD_CONFIRMED, editable.toString(), input_password.getEditText().getText().toString());
            }
        });

        input_signature.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //presenter.checkIfCorrect(Constant.INPUT_SIGNATURE, editable.toString());
            }
        });
    }

    public void setErrorMessage(int type, String errorMessage) {
        switch (type) {
            case Constant.INPUT_EMAIL:
                input_email.setError(errorMessage);
                input_email.setErrorEnabled(true);
                break;
            case Constant.INPUT_USERNAME:
                input_username.setError(errorMessage);
                input_username.setErrorEnabled(true);
                break;
            case Constant.INPUT_PASSWORD:
                input_password.setError(errorMessage);
                input_password.setErrorEnabled(true);
                break;
            case Constant.INPUT_PASSWORD_CONFIRMED:
                input_confirm_password.setError(errorMessage);
                input_confirm_password.setErrorEnabled(true);
                break;
            case Constant.INPUT_SIGNATURE:
                input_signature.setError(errorMessage);
                input_signature.setErrorEnabled(true);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.regButton:
                @SuppressLint("UseSparseArrays")
                HashMap<Integer, String> values = new HashMap<>();
                values.put(Constant.INPUT_EMAIL, input_email.getEditText().getText().toString());
                values.put(Constant.INPUT_USERNAME, input_username.getEditText().getText().toString());
                values.put(Constant.INPUT_PASSWORD, input_password.getEditText().getText().toString());
                values.put(Constant.INPUT_PASSWORD_CONFIRMED, input_confirm_password.getEditText().getText().toString());
                values.put(Constant.INPUT_SIGNATURE, input_signature.getEditText().getText().toString());
                if (presenter.onRegister(values)) {
                    intent = new Intent();
                    intent.setClass(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
                break;
            case R.id.agreementButton:
                intent = new Intent();
                intent.setClass(getActivity(), SettingsActivity.class);
                intent.putExtra(com.xuan.qingya.Common.Constant.ENTRY_TYPE, com.xuan.qingya.Common.Constant.FRAGMENT_SETTINGS_AGREEMENT);
                startActivity(intent);
                break;
            case R.id.loginButton:
                intent = new Intent(getActivity(), StartupActivity.class);
                intent.putExtra(com.xuan.qingya.Common.Constant.ENTRY_TYPE, com.xuan.qingya.Common.Constant.FRAGMENT_LOGIN);
                startActivity(intent);
                break;
        }
    }

    @Override
    public RegisterPresenter initPresenter() {
        return new RegisterPresenter();
    }

}
