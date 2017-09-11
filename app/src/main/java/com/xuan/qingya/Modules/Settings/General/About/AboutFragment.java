package com.xuan.qingya.Modules.Settings.General.About;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Core.Base.BaseFragment;
import com.xuan.qingya.Modules.Settings.General.SettingsActivity;
import com.xuan.qingya.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends BaseFragment {

    private CardView updateEntry, feedbackEntry, contactEntry;

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_about, container, false);

        init();
        initListeners(updateEntry, feedbackEntry, contactEntry);

        return mRootView;
    }

    public void init() {
        updateEntry = $(R.id.update);
        feedbackEntry = $(R.id.feedback);
        contactEntry = $(R.id.contact);
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
}

