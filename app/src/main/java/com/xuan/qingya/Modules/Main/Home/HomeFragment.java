package com.xuan.qingya.Modules.Main.Home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuan.qingya.Common.RecyclerConfig.ItemOffsetDecoration;
import com.xuan.qingya.Core.base.BaseFragment;
import com.xuan.qingya.Models.entity.Article;
import com.xuan.qingya.Modules.Main.MainActivity;
import com.xuan.qingya.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends BaseFragment<ViewContract, HomePresenter> implements ViewContract {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private HomeRecyclerViewAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, mRootView);

        init();
        initListeners();

        return mRootView;
    }

    public void init() {
        adapter = new HomeRecyclerViewAdapter(getContext(), presenter, recyclerView);

        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new ItemOffsetDecoration(16));
        recyclerView.getItemAnimator().setChangeDuration(0);
        ((MainActivity) getActivity()).getViewPager().setObjectForPosition(mRootView, 0);
        presenter.getListData();
    }

    @Override
    protected void initListeners(@Nullable View... views) {
        adapter.addOnClickListener(new HomeRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, Article bean, int position) {

            }
        });
    }

    @Override
    public HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    public void showList(List<Article> list) {
        adapter.setData(list);
    }
}
