package com.xuan.qingya.Modules.Startup.ForgotPassword;


import android.content.Intent;
import android.os.Bundle;
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
import com.xuan.qingya.Modules.Startup.StartupActivity;
import com.xuan.qingya.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgotPasswordFragment extends BaseFragment<ViewContract, ForgotPasswordPresenter> implements ViewContract {
    @BindView(R.id.email_input)
    TextInputLayout input_email;
    @BindView(R.id.nextButton)
    Button nextButton;

    public ForgotPasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.fragment_forgot_password, container, false);
        ButterKnife.bind(this, mRootView);

        init();
        initListeners(nextButton);

        return mRootView;
    }

    public void init() {
        ((StartupActivity) getActivity()).setToolbarTitle("忘记密码");

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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextButton:
                if (presenter.onNext(input_email.getEditText().getText().toString())) {
                    Intent intent = new Intent(getActivity(), StartupActivity.class);
                    intent.putExtra(com.xuan.qingya.Common.Constant.ENTRY_TYPE, com.xuan.qingya.Common.Constant.FRAGMENT_FORGOT_PASSWORD_NEXT);
                    startActivity(intent);
                    getActivity().finish();
                }
                break;
        }
    }

    @Override
    public ForgotPasswordPresenter initPresenter() {
        return new ForgotPasswordPresenter();
    }

    @Override
    public void setErrorMessage(int type, String errorMessage) {
        switch (type) {
            case Constant.INPUT_EMAIL:
                input_email.setError(errorMessage);
                input_email.setErrorEnabled(true);
                break;
        }
    }
}
