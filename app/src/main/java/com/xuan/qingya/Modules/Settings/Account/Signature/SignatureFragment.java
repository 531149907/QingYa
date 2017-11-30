package com.xuan.qingya.Modules.Settings.Account.Signature;


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
public class SignatureFragment extends BaseFragment<ViewContract, SignaturePresenter> implements ViewContract {

    @BindView(R.id.signature_input)
    TextInputLayout signatureInput;
    @BindView(R.id.confirmButton)
    Button confirmButton;

    public SignatureFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.fragment_signature, container, false);
        ButterKnife.bind(this, mRootView);

        init();
        initListeners(confirmButton);

        return mRootView;
    }

    @Override
    public SignaturePresenter initPresenter() {
        return new SignaturePresenter();
    }

    public void init() {

    }

    @Override
    protected void initListeners(@Nullable View... views) {
        super.initListeners(views);
        signatureInput.getEditText().addTextChangedListener(new TextWatcher() {
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
    public void setErrorMessage(int type, String errorMessage) {
        switch (type) {
            case Constant.INPUT_SIGNATURE:
                signatureInput.setError(errorMessage);
                signatureInput.setErrorEnabled(true);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirmButton:
                if (presenter.onConfirm(signatureInput.getEditText().toString())) {

                }
                break;
        }
    }
}
