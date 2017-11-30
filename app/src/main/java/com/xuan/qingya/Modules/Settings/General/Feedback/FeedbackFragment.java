package com.xuan.qingya.Modules.Settings.General.Feedback;


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
import android.widget.Toast;

import com.xuan.qingya.Core.base.BaseFragment;
import com.xuan.qingya.Modules.Startup.Constant;
import com.xuan.qingya.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedbackFragment extends BaseFragment<ViewContract, FeedbackPresenter> implements ViewContract {
    @BindView(R.id.feedbackInput)
    TextInputLayout feedbackInput;
    @BindView(R.id.confirmButton)
    Button confirmButton;

    public FeedbackFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.fragment_feedback, container, false);
        ButterKnife.bind(this, mRootView);

        init();
        initListeners(confirmButton);

        return mRootView;
    }

    private void init() {

    }

    @Override
    protected void initListeners(@Nullable View... views) {
        super.initListeners(views);
        feedbackInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.checkIfCorrect(Constant.INPUT_FEEDBACK, editable.toString());
            }
        });
    }

    @Override
    public FeedbackPresenter initPresenter() {
        return new FeedbackPresenter();
    }

    @Override
    public void setErrorMessage(int type, String errorMessage) {
        switch (type) {
            case Constant.INPUT_FEEDBACK:
                feedbackInput.setError(errorMessage);
                feedbackInput.setErrorEnabled(true);
                break;
        }
    }

    @Override
    public void cleanInput() {
        feedbackInput.getEditText().setText("");
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirmButton:
                presenter.sendFeedback(feedbackInput.getEditText().getText().toString());
        }
    }
}
