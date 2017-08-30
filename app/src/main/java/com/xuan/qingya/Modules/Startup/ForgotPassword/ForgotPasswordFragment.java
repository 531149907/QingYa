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

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgotPasswordFragment extends BaseFragment implements StartupContract.ForgotPasswordView{
    private TextInputLayout input_email;
    private Button next_btn;

    private StartupContract.ForgotPasswordPresenter presenter;

    public ForgotPasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        init();

        return mRootView;
    }

    @Override
    public void setPresenter(StartupContract.ForgotPasswordPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {

        ((StartupActivity)getActivity()).setToolbarTitle("忘记密码");

        input_email = $(R.id.forgot_password_email_input);
        next_btn = $(R.id.forgot_password_next_btn);

        initListeners(next_btn);

        input_email.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.checkIfCorrect(Constant.INPUT_EMAIL,editable.toString());
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.forgot_password_next_btn:
                presenter.onNext(input_email.getEditText().getText().toString());
        }
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
        }
    }
}
