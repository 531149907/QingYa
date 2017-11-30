package com.xuan.qingya.Modules.Settings.General.About;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Core.base.BaseFragment;
import com.xuan.qingya.Modules.Settings.General.SettingsActivity;
import com.xuan.qingya.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends BaseFragment<ViewContract, AboutPresenter> implements ViewContract {
    @BindView(R.id.update)
    CardView updateEntry;
    @BindView(R.id.feedback)
    CardView feedbackEntry;
    @BindView(R.id.contact)
    CardView contactEntry;
    @BindView(R.id.buildVersion)
    TextView buildVersion;

    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this, mRootView);

        init();
        initListeners(updateEntry, feedbackEntry, contactEntry);

        return mRootView;
    }

    public void init() {
        presenter.checkUpdateRequest();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), SettingsActivity.class);
        switch (view.getId()) {
            case R.id.update:
                return;
            case R.id.feedback:
                intent.putExtra(Constant.ENTRY_TYPE, Constant.FRAGMENT_SETTINGS_FEEDBACK);
                break;
            case R.id.contact:
                intent.putExtra(Constant.ENTRY_TYPE, Constant.FRAGMENT_SETTINGS_CONTACT);
                break;
        }
        startActivity(intent);
    }

    @Override
    public AboutPresenter initPresenter() {
        return new AboutPresenter();
    }

    @Override
    public void setCheckState(String content) {
        buildVersion.setText(content);
    }
}

