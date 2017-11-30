package com.xuan.qingya.Modules.Settings.Account.Username;


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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsernameFragment extends BaseFragment<ViewContract, UsernamePresenter> implements ViewContract {
    @BindView(R.id.username_input)
    TextInputLayout usernameInput;
    @BindView(R.id.confirmButton)
    Button confirmButton;

    public UsernameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_username, container, false);
        ButterKnife.bind(this, mRootView);

        init();
        initListeners(confirmButton);

        return mRootView;
    }

    @Override
    public UsernamePresenter initPresenter() {
        return new UsernamePresenter();
    }

    public void init() {

    }

    @Override
    protected void initListeners(@Nullable View... views) {
        super.initListeners(views);
        usernameInput.getEditText().addTextChangedListener(new TextWatcher() {
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
    }

    @Override
    public void setErrorMessage(int type, String errorMessage) {
        switch (type) {
            case Constant.INPUT_USERNAME:
                usernameInput.setError(errorMessage);
                usernameInput.setErrorEnabled(true);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirmButton:
                if (presenter.onConfirm(usernameInput.getEditText().toString())) {

                }
                break;
        }
    }
}
