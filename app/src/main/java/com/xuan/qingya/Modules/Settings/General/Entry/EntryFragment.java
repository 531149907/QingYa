package com.xuan.qingya.Modules.Settings.General.Entry;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
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
public class EntryFragment extends BaseFragment<ViewContract, EntryPresenter> implements ViewContract {
    @BindView(R.id.nightMode)
    CardView nightMode;
    @BindView(R.id.clearCache)
    CardView cleanCache;
    @BindView(R.id.clearSearchHistory)
    CardView cleanSearch;
    @BindView(R.id.about)
    CardView about;
    @BindView(R.id.agreement)
    CardView agreement;
    @BindView(R.id.cacheSize)
    TextView cacheSize;
    @BindView(R.id.switcher)
    Switch nightModeSwitcher;

    public EntryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.fragment_settings_entry, container, false);
        ButterKnife.bind(this, mRootView);

        init();
        initListeners(nightMode, cleanCache, cleanSearch, about, agreement);

        return mRootView;
    }

    public void init() {
        presenter.getCacheSize();
    }

    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), SettingsActivity.class);
        switch (view.getId()) {
            case R.id.nightMode:
                presenter.onNightModeChange(nightModeSwitcher.isChecked());
                return;
            case R.id.clearCache:
                presenter.clearCache();
                return;
            case R.id.clearSearchHistory:
                presenter.clearSearchHistory();
                return;
            case R.id.about:
                intent.putExtra(Constant.ENTRY_TYPE, Constant.FRAGMENT_SETTINGS_ABOUT);
                startActivity(intent);
                break;
            case R.id.agreement:
                intent.putExtra(Constant.ENTRY_TYPE, Constant.FRAGMENT_SETTINGS_AGREEMENT);
                startActivity(intent);
                break;
        }
    }

    @Override
    public EntryPresenter initPresenter() {
        return new EntryPresenter();
    }

    @Override
    public void changeNightModeSwitcherState() {
        nightModeSwitcher.setChecked(!nightModeSwitcher.isChecked());
    }

    @Override
    public void setCacheSize(String cacheSize) {
        this.cacheSize.setText(cacheSize);
    }
}
