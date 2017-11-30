package com.xuan.qingya.Modules.Main.Interview;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuan.qingya.Common.RecyclerConfig.ItemOffsetDecoration;
import com.xuan.qingya.Core.base.BaseFragment;
import com.xuan.qingya.Models.entity.Interview;
import com.xuan.qingya.Modules.Main.MainActivity;
import com.xuan.qingya.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class InterviewFragment extends BaseFragment<ViewContract, InterviewPresenter> implements ViewContract {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private InterviewRecyclerViewAdapter adapter;

    public InterviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.fragment_interview, container, false);
        ButterKnife.bind(this, mRootView);

        init();
        initListeners();

        return mRootView;
    }

    @Override
    protected void initListeners(@Nullable View... views) {
        adapter.addOnClickListener(new InterviewRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, Interview bean, int position) {

            }
        });
    }

    @Override
    public InterviewPresenter initPresenter() {
        return new InterviewPresenter();
    }


    public void init() {
        adapter = new InterviewRecyclerViewAdapter(getContext(), recyclerView, presenter);

        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new ItemOffsetDecoration(16));
        recyclerView.getItemAnimator().setChangeDuration(0);
        ((MainActivity) getActivity()).getViewPager().setObjectForPosition(mRootView, 1);
        presenter.getListData();
    }

    @Override
    public void showList(List<Interview> list) {
        adapter.setData(list);
    }
}
