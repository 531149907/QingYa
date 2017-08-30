package com.xuan.qingya.Modules.Main.Home;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xuan.qingya.Core.Base.BaseFragment;
import com.xuan.qingya.Core.Base.BasePresenter;
import com.xuan.qingya.Models.Entity.ArticleBean;
import com.xuan.qingya.Modules.Main.MainActivity;
import com.xuan.qingya.Modules.Main.MainContract;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.DensityUtil;
import com.xuan.qingya.Utils.LogUtil;

import java.util.List;


public class HomeFragment extends BaseFragment implements MainContract.HomeView{

    private MainContract.HomePresenter presenter;
    Parcelable state;
    private List<ArticleBean> data;
    private RecyclerView recyclerView;
    private HomeRecyclerViewAdapter adapter;
    private static final String BUNDLE_RECYCLER_LAYOUT = "classname.recycler.layout";
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home, container, false);

        init();
        initListeners();

        return mRootView;
    }

    @Override
    public void setPresenter(MainContract.HomePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {
        data = presenter.getListData();
        adapter = new HomeRecyclerViewAdapter(getActivity(),data,presenter);
        recyclerView = $(R.id.home_recyclerview);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.getItemAnimator().setChangeDuration(0);
        ((MainActivity)getActivity()).getViewPager().setObjectForPosition(mRootView,0);

    }

    @Override
    protected void initListeners(@Nullable View... views) {
        adapter.addOnClickListener(new HomeRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, ArticleBean bean, int position) {
                presenter.onListItemClick(bean,null);
                LogUtil.show("itemClicked","in "+position);
            }
        });
    }

    @Override
    public void startActivity(Class<?> target, BasePresenter presenter, @Nullable Bundle bundle, @Nullable String extra) {

    }
}
