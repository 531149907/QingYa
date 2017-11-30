package com.xuan.qingya.Modules.Profile.Collection;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuan.qingya.Core.base.BaseFragment;
import com.xuan.qingya.Models.entity.Article;
import com.xuan.qingya.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionFragment extends BaseFragment<ViewContract, CollectionPresenter> implements ViewContract {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private CollectionRecyclerViewAdapter adapter;

    public CollectionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.fragment_collection, container, false);
        ButterKnife.bind(this, mRootView);

        init();
        initListeners();

        return mRootView;
    }

    @Override
    public CollectionPresenter initPresenter() {
        return new CollectionPresenter();
    }

    public void init() {
        adapter = new CollectionRecyclerViewAdapter(getActivity());

        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.getItemAnimator().setChangeDuration(0);

        presenter.getListData();
    }

    @Override
    public void showList(List<Article> list) {
        adapter.setData(list);
    }

    @Override
    protected void initListeners(@Nullable View... views) {
        adapter.addOnClickListener(new CollectionRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(Article bean, int position) {

            }

            @Override
            public void onLoveCancelClick(Article bean, int position) {

            }
        });
    }
}
