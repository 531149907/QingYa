package com.xuan.qingya.Modules.Main.Discover;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuan.qingya.Common.View.BannerItemView;
import com.xuan.qingya.Common.View.BannerView;
import com.xuan.qingya.Core.base.BaseFragment;
import com.xuan.qingya.Models.entity.Banner;
import com.xuan.qingya.Modules.Discover.List.DiscoverListActivity;
import com.xuan.qingya.Modules.Main.MainActivity;
import com.xuan.qingya.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends BaseFragment<ViewContract, DiscoverPresenter> implements ViewContract {
    @BindView(R.id.banner)
    BannerView bannerView;
    @BindView(R.id.articleEntry)
    CardView bookEntry;
    @BindView(R.id.photoEntry)
    CardView photoEntry;
    @BindView(R.id.musicEntry)
    CardView musicEntry;
    @BindView(R.id.movieEntry)
    CardView movieEntry;
    @BindView(R.id.questionEntry)
    CardView questionEntry;

    public DiscoverFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.fragment_discover, container, false);
        ButterKnife.bind(this, mRootView);

        init();
        initListeners(bookEntry, photoEntry, musicEntry, movieEntry, questionEntry);

        return mRootView;
    }

    public void init() {
        for (int i = 0; i < 5; i++) {
            bannerView.addImage(new BannerItemView(getContext(), R.drawable.a22));
        }
        ((MainActivity) getActivity()).getViewPager().setObjectForPosition(mRootView, 2);
    }

    @Override
    public void onClick(View view) {
        int clickId = 0;
        Intent intent = new Intent(getActivity(), DiscoverListActivity.class);
        switch (view.getId()) {
            case R.id.articleEntry:
                clickId = 0;
                intent.putExtra("discoverID", clickId);
                break;
            case R.id.photoEntry:
                clickId = 1;
                intent.putExtra("discoverID", clickId);
                break;
            case R.id.musicEntry:
                clickId = 2;
                intent.putExtra("discoverID", clickId);
                break;
            case R.id.movieEntry:
                clickId = 3;
                intent.putExtra("discoverID", clickId);
                break;
            case R.id.questionEntry:
                clickId = 4;
                intent.putExtra("discoverID", clickId);
                break;
        }
        startActivity(intent);
    }

    @Override
    public DiscoverPresenter initPresenter() {
        return new DiscoverPresenter();
    }

    @Override
    public void showList(List<Banner> list) {

    }
}
