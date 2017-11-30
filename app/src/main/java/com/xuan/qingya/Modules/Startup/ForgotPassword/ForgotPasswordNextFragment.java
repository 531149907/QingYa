package com.xuan.qingya.Modules.Startup.ForgotPassword;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xuan.qingya.Core.base.BaseFragment;
import com.xuan.qingya.Modules.Startup.Constant;
import com.xuan.qingya.Modules.Startup.Login.LoginFragment;
import com.xuan.qingya.Modules.Startup.StartupActivity;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.FragmentManagement;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgotPasswordNextFragment extends BaseFragment<ViewContract, ForgotPasswordNextPresenter> implements ViewContract {
    @BindView(R.id.verification_input)
    TextInputLayout input_verification;
    @BindView(R.id.password_input)
    TextInputLayout input_password;
    @BindView(R.id.confirm_password_input)
    TextInputLayout input_confirm_password;
    @BindView(R.id.finishButton)
    Button finishButton;

    public ForgotPasswordNextFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.fragment_forgot_password_next, container, false);
        ButterKnife.bind(this, mRootView);

        init();
        initListeners(finishButton);

        return mRootView;
    }

    public void init() {
        ((StartupActivity) getActivity()).setToolbarTitle("忘记密码");

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

        input_confirm_password.getEditText().addTextChangedListener(new TextWatcher() {
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
            case R.id.finishButton:
                @SuppressLint("UseSparseArrays")
                HashMap<Integer, String> values = new HashMap<>();
                values.put(Constant.INPUT_VERIFICATION, input_verification.getEditText().getText().toString());
                values.put(Constant.INPUT_PASSWORD, input_password.getEditText().getText().toString());
                values.put(Constant.INPUT_PASSWORD_CONFIRMED, input_confirm_password.getEditText().getText().toString());
                if (presenter.onFinish(values)) {
                    FragmentManagement.replaceFragmentWithSharedElement(getActivity().getSupportFragmentManager(), new LoginFragment(), R.id.startup_container, null);
                }
                getActivity().finish();
                break;
        }
    }

    @Override
    public ForgotPasswordNextPresenter initPresenter() {
        return new ForgotPasswordNextPresenter();
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
                input_confirm_password.setError(errorMessage);
                input_confirm_password.setErrorEnabled(true);
                break;
        }
    }
}
