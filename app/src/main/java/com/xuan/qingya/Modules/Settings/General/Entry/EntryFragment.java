package com.xuan.qingya.Modules.Settings.General.Entry;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Core.Base.BaseFragment;
import com.xuan.qingya.Modules.Settings.General.SettingsActivity;
import com.xuan.qingya.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EntryFragment extends BaseFragment implements EntryContract.EntryView {

    private EntryContract.EntryPresenter presenter;
    private CardView nightMode, cleanCache, cleanSearch, about, agreement;

    public EntryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_settings_entry, container, false);

        init();
        initListeners(nightMode, cleanCache, cleanSearch, about, agreement);

        return mRootView;
    }

    @Override
    public void setPresenter(EntryContract.EntryPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {
        nightMode = $(R.id.skin_mode_switch);
        cleanCache = $(R.id.clear_cache);
        cleanSearch = $(R.id.clear_search_history);
        about = $(R.id.about);
        agreement = $(R.id.agreement);

    }

    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), SettingsActivity.class);
        switch (view.getId()) {
            case R.id.skin_mode_switch:
                Switch night_switch = $(R.id.switcher);
                night_switch.setChecked(!night_switch.isChecked());
                presenter.onNightModeChange();
                return;
            case R.id.clear_cache:
                presenter.clearCache();
                return;
            case R.id.clear_search_history:
                presenter.clearSearchHistory();
                return;
            case R.id.about:
                intent.putExtra(Constant.ENTRY_TYPE, Constant.FRAGMENT_SETTINGS_ABOUT);
                break;
            case R.id.agreement:
                intent.putExtra(Constant.ENTRY_TYPE, Constant.FRAGMENT_SETTINGS_AGREEMENT);
                break;
        }
        startActivity(intent);
    }
}
