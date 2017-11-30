package com.xuan.qingya.Modules.Discover.List;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Core.base.BaseFragment;
import com.xuan.qingya.Models.entity.Article;
import com.xuan.qingya.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverListFragment extends BaseFragment<ViewContract, DiscoverListPresenter> implements ViewContract {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private DiscoverListRecyclerViewAdapter adapter;
    private int dataType;

    public DiscoverListFragment() {
        // Required empty public constructor
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        dataType = args.getInt(Constant.ENTRY_TYPE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.fragment_discover_list, container, false);
        ButterKnife.bind(this, mRootView);

        init();
        initListeners();
        return mRootView;
    }

    private void init() {
        adapter = new DiscoverListRecyclerViewAdapter(getActivity(), recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.getItemAnimator().setChangeDuration(0);

        presenter.showListContent(dataType, 0);
    }

    @Override
    public DiscoverListPresenter initPresenter() {
        return new DiscoverListPresenter();
    }

    @Override
    protected void initListeners(@Nullable View... views) {
        adapter.addOnClickListener(new DiscoverListRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(Article bean, Intent intent, int position) {
                if (intent != null) {
                    startActivity(intent);
                }
            }

            @Override
            public void onLoveButtonClick(Article bean, int position) {
                presenter.onLoveButtonClick(bean);
            }
        });
    }

    @Override
    public void showList(List<Article> data) {
        adapter.setData(data);
    }
}
