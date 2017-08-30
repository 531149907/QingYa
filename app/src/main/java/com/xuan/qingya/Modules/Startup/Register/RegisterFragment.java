package com.xuan.qingya.Modules.Startup.Register;


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

public class RegisterFragment extends BaseFragment implements StartupContract.RegisterView {
    private TextInputLayout input_email;
    private TextInputLayout input_username;
    private TextInputLayout input_password;
    private TextInputLayout input_confirmpassword;
    private TextInputLayout input_signature;

    private Button reg_btn;
    private Button login_btn;
    private TextView agreement_btn;

    StartupContract.RegisterPresenter presenter;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_register, container, false);

        init();

        return mRootView;
    }

    @Override
    public void setPresenter(StartupContract.RegisterPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {

        ((StartupActivity)getActivity()).setToolbarTitle("注册");

        input_email = $(R.id.register_email_input);
        input_username = $(R.id.register_username_input);
        input_password = $(R.id.register_password_input);
        input_confirmpassword = $(R.id.register_confirm_password_input);
        input_signature = $(R.id.register_signature_input);

        reg_btn = $(R.id.register_reg_btn);
        login_btn = $(R.id.register_login_btn);
        agreement_btn = $(R.id.register_agreement);

        initListeners(reg_btn,login_btn,agreement_btn);

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

        input_username.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.checkIfCorrect(Constant.INPUT_USERNAME, editable.toString());
            }
        });

        input_confirmpassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.checkIfCorrect(Constant.INPUT_PASSWORD_CONFIRMED, editable.toString(), input_password.getEditText().getText().toString());
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
                presenter.checkIfCorrect(Constant.INPUT_SIGNATURE, editable.toString());
            }
        });
    }

    @Override
    public void startActivity(Class<?> target, BasePresenter presenter, @Nullable Bundle bundle, @Nullable String extra) {
        Intent intent = new Intent(getActivity(), target.getClass());
        startActivity(intent);
    }

    @Override
    public void startFragment(Fragment fragment, BasePresenter presenter, @Nullable String extra) {
        FragmentManagement.switchFragment(getActivity().getSupportFragmentManager(),fragment,R.id.startup_container,true);
    }

    @Override
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
                input_confirmpassword.setError(errorMessage);
                input_confirmpassword.setErrorEnabled(true);
                break;
            case Constant.INPUT_SIGNATURE:
                input_signature.setError(errorMessage);
                input_signature.setErrorEnabled(true);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_reg_btn:
                @SuppressLint("UseSparseArrays")
                HashMap<Integer, String> values = new HashMap<>();
                values.put(Constant.INPUT_EMAIL, input_email.getEditText().getText().toString());
                values.put(Constant.INPUT_USERNAME, input_username.getEditText().getText().toString());
                values.put(Constant.INPUT_PASSWORD, input_password.getEditText().getText().toString());
                values.put(Constant.INPUT_PASSWORD_CONFIRMED, input_confirmpassword.getEditText().getText().toString());
                values.put(Constant.INPUT_SIGNATURE, input_signature.getEditText().getText().toString());
                presenter.onRegister(values);
                break;
            case R.id.register_agreement:
                presenter.startAgreement();
                break;
            case R.id.register_login_btn:
                presenter.onLogin();
                break;
        }
    }
}
