package com.xuan.qingya.Modules.Discover.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Core.base.BaseActivity;
import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscoverListActivity extends BaseActivity {
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private String[] titles;
    private List<Fragment> fragments;
    private int entryType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_list);
        isTransparentStatusBar();
        ButterKnife.bind(this);

        init();
        initListeners();
    }

    public void init() {
        Intent intent = getIntent();
        entryType = intent.getIntExtra(Constant.ENTRY_TYPE, entryType);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fragments = new ArrayList<>();
        titles = new String[]{"阅读", "摄影", "音乐", "影视", "问答"};

        for (int i = 0; i < 5; i++) {
            DiscoverListFragment fragment = new DiscoverListFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(Constant.ENTRY_TYPE, i);
            fragment.setArguments(bundle);
            fragments.add(fragment);
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]), i == entryType);
        }

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        };

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void initListeners(@Nullable View... views) {
        super.initListeners(views);
        toolbar.setNavigationOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    public AppBarLayout getAppBarLayout() {
        return appBarLayout;
    }
}