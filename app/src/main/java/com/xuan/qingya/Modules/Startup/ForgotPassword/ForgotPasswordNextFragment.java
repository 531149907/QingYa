package com.xuan.qingya.Modules.Startup.ForgotPassword;


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

import com.xuan.qingya.Core.Base.BaseFragment;
import com.xuan.qingya.Core.Base.BasePresenter;
import com.xuan.qingya.Modules.Startup.Constant;
import com.xuan.qingya.Modules.Startup.StartupActivity;
import com.xuan.qingya.Modules.Startup.StartupContract;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.FragmentManagement;

import java.util.HashMap;

public class ForgotPasswordNextFragment extends BaseFragment implements StartupContract.ForgotPasswordNextView {
    private TextInputLayout input_verification;
    private TextInputLayout input_password;
    private TextInputLayout input_confirmpassword;
    private Button finish_btn;

    private StartupContract.ForgotPasswordNextPresenter presenter;

    public ForgotPasswordNextFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_forgot_password_next, container, false);

        init();

        return mRootView;
    }

    @Override
    public void setPresenter(StartupContract.ForgotPasswordNextPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {
        ((StartupActivity) getActivity()).setToolbarTitle("忘记密码");

        input_verification = $(R.id.forgot_password_next_verification_input);
        input_password = $(R.id.forgot_password_next_password_input);
        input_confirmpassword = $(R.id.forgot_password_next_confirm_password_input);
        finish_btn = $(R.id.forgot_password_next_finish_btn);

        initListeners(finish_btn);

        input_verification.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.checkIfCorrect(Constant.INPUT_VERIFICATION, editable.toString());
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
                presenter.checkIfCorrect(Constant.INPUT_PASSWORD, editable.toString());
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forgot_password_next_finish_btn:
                HashMap<Integer, String> values = new HashMap<>();
                values.put(Constant.INPUT_VERIFICATION, input_verification.getEditText().getText().toString());
                values.put(Constant.INPUT_PASSWORD, input_password.getEditText().getText().toString());
                values.put(Constant.INPUT_PASSWORD_CONFIRMED, input_confirmpassword.getEditText().getText().toString());
                presenter.onFinish(values);
                break;
        }
    }

    @Override
    public void setErrorMessage(int type, String errorMessage) {
        switch (type) {
            case Constant.INPUT_VERIFICATION:
                input_verification.setError(errorMessage);
                input_verification.setErrorEnabled(true);
                break;
            case Constant.INPUT_PASSWORD:
                input_password.setError(errorMessage);
                input_password.setErrorEnabled(true);
                break;
            case Constant.INPUT_PASSWORD_CONFIRMED:
                input_confirmpassword.setError(errorMessage);
                input_confirmpassword.setErrorEnabled(true);
                break;
        }
    }

    @Override
    public void startFragment(Fragment fragment, BasePresenter presenter, @Nullable String extra) {
        FragmentManagement.switchFragment(getActivity().getSupportFragmentManager(), fragment, R.id.startup_container, true);
    }
}
