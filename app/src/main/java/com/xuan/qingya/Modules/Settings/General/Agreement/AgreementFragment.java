package com.xuan.qingya.Modules.Settings.General.Agreement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuan.qingya.Core.base.BaseFragment;
import com.xuan.qingya.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgreementFragment extends BaseFragment<ViewContract, AgreementPresenter> implements ViewContract {
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.content)
    TextView content;

    public AgreementFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.fragment_agreement, container, false);
        ButterKnife.bind(this, mRootView);

        init();

        return mRootView;
    }

    private void init() {
        presenter.getContent();
    }

    @Override
    public AgreementPresenter initPresenter() {
        return new AgreementPresenter();
    }

    @Override
    public void setDate(String date) {
        this.date.setText(date);
    }

    @Override
    public void setContent(String content) {
        this.content.setText(content);
    }
}
