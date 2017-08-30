package com.xuan.qingya.Modules.Main.Interview;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuan.qingya.Core.Base.BaseFragment;
import com.xuan.qingya.Core.Base.BasePresenter;
import com.xuan.qingya.Models.Entity.InterviewBean;
import com.xuan.qingya.Modules.Interview.InterviewDetailActivity;
import com.xuan.qingya.Modules.Main.MainActivity;
import com.xuan.qingya.Modules.Main.MainContract;
import com.xuan.qingya.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InterviewFragment extends BaseFragment implements MainContract.InterviewView {

    private MainContract.InterviewPresenter presenter;

    private List<InterviewBean> data;
    private RecyclerView recyclerView;
    private InterviewRecyclerViewAdapter adapter;

    public InterviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_interview, container, false);

        init();
        initListeners();

        return mRootView;
    }

    @Override
    protected void initListeners(@Nullable View... views) {
        adapter.addOnClickListener(new InterviewRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, InterviewBean bean, int position) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("bean", bean);
                startActivity(InterviewDetailActivity.class, null, bundle, null);
            }
        });
    }

    @Override
    public void setPresenter(MainContract.InterviewPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {
        data = presenter.getListData();
        adapter = new InterviewRecyclerViewAdapter(getActivity(),data,presenter);

        recyclerView = $(R.id.interview_recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.getItemAnimator().setChangeDuration(0);
        recyclerView.setHasFixedSize(false);
        ((MainActivity)getActivity()).getViewPager().setObjectForPosition(mRootView,1);
    }

    @Override
    public void startActivity(Class<?> target, BasePresenter presenter, @Nullable Bundle bundle, @Nullable String extra) {
        Intent intent = new Intent(getActivity(), target);
        intent.putExtra("bean_bundle", bundle);
        startActivity(intent);
    }
}
