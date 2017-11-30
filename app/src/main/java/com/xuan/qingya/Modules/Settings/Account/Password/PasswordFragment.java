package com.xuan.qingya.Modules.Settings.Account.Password;


import android.annotation.SuppressLint;
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

import com.xuan.qingya.Core.base.BaseFragment;
import com.xuan.qingya.Modules.Startup.Constant;
import com.xuan.qingya.R;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PasswordFragment extends BaseFragment<ViewContract, PasswordPresenter> implements ViewContract {
    @BindView(R.id.old_password_input)
    TextInputLayout oldPasswordInput;
    @BindView(R.id.new_password_input)
    TextInputLayout newPasswordInput;
    @BindView(R.id.confirm_password_input)
    TextInputLayout confirmPasswordInput;
    @BindView(R.id.confirmButton)
    Button confirmButton;

    public PasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.fragment_password, container, false);
        ButterKnife.bind(this, mRootView);

        init();
        initListeners(confirmButton);

        return mRootView;
    }

    @Override
    public PasswordPresenter initPresenter() {
        return new PasswordPresenter();
    }

    public void init() {

    }

    @Override
    protected void initListeners(@Nullable View... views) {
        super.initListeners(views);
        oldPasswordInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.checkIfCorrect(Constant.INPUT_PASSWORD_OLD, editable.toString());
            }
        });
        newPasswordInput.getEditText().addTextChangedListener(new TextWatcher() {
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
        confirmPasswordInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.checkIfCorrect(Constant.INPUT_PASSWORD_CONFIRMED, editable.toString());
            }
        });
    }

    @Override
    public void setErrorMessage(int type, String errorMessage) {
        switch (type) {
            case Constant.INPUT_PASSWORD_OLD:
                oldPasswordInput.setError(errorMessage);
                oldPasswordInput.setErrorEnabled(true);
                break;
            case Constant.INPUT_PASSWORD:
                newPasswordInput.setError(errorMessage);
                newPasswordInput.setErrorEnabled(true);
                break;
            case Constant.INPUT_PASSWORD_CONFIRMED:
                confirmPasswordInput.setError(errorMessage);
                confirmPasswordInput.setErrorEnabled(true);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirmButton:
                @SuppressLint("UseSparseArrays")
                HashMap<Integer, String> values = new HashMap<>();
                values.put(Constant.INPUT_PASSWORD_OLD, oldPasswordInput.getEditText().getText().toString());
                values.put(Constant.INPUT_PASSWORD, newPasswordInput.getEditText().getText().toString());
                values.put(Constant.INPUT_PASSWORD_CONFIRMED, confirmPasswordInput.getEditText().getText().toString());
                if (presenter.onChangeConfirm(values)) {

                }
                break;
        }
    }
}
