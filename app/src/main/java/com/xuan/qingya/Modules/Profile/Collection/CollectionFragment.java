package com.xuan.qingya.Modules.Profile.Collection;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuan.qingya.Core.Base.BaseFragment;
import com.xuan.qingya.Models.Entity.ArticleBean;
import com.xuan.qingya.Modules.Profile.ProfileActivity;
import com.xuan.qingya.Modules.Profile.ProfileContract;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.LogUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionFragment extends BaseFragment implements ProfileContract.CollectionView {
    private ProfileContract.CollectionPresenter presenter;

    private RecyclerView recyclerView;
    private CollectionRecyclerViewAdapter adapter;


    public CollectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_collection, container, false);

        init();

        return mRootView;
    }

    @Override
    public void setPresenter(ProfileContract.CollectionPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {
        ((ProfileActivity) getActivity()).setToolbarTitle("点赞与收藏");

        recyclerView = $(R.id.collection_recyclerview);
        adapter = new CollectionRecyclerViewAdapter(getActivity(), presenter.getData(), presenter);

        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.getItemAnimator().setChangeDuration(0);
        adapter.addOnClickListener(new CollectionRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, ArticleBean bean, int position) {
                LogUtil.show("onClick", bean.getType());
            }
        });
    }
}
