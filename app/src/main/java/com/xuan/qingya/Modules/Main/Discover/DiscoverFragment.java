package com.xuan.qingya.Modules.Main.Discover;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.xuan.qingya.Common.View.BannerViewPager;
import com.xuan.qingya.Core.Base.BaseFragment;
import com.xuan.qingya.Core.Base.BasePresenter;
import com.xuan.qingya.Models.Entity.BannerBean;
import com.xuan.qingya.Models.Entity.TopicBean;
import com.xuan.qingya.Modules.Discover.List.DiscoverListActivity;
import com.xuan.qingya.Modules.Main.MainActivity;
import com.xuan.qingya.Modules.Main.MainContract;
import com.xuan.qingya.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends BaseFragment implements MainContract.DiscoverView {

    private MainContract.DiscoverPresenter presenter;
    private BannerViewPager bannerViewPager;
    private RelativeLayout bookEntry, photoEntry, musicEntry, movieEntry, questionEntry;
    private RecyclerView recyclerView;
    private DiscoverRecyclerViewAdapter adapter;

    public DiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_discover, container, false);

        init();
        initListeners(bookEntry, photoEntry, musicEntry, movieEntry, questionEntry);

        return mRootView;
    }

    @Override
    protected void initListeners(@Nullable View... views) {
        super.initListeners(views);
        adapter.addOnClickListener(new DiscoverRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, TopicBean bean, int position) {
                presenter.onListItemClick(bean, null);
            }
        });
        bannerViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                presenter.retainNestedScrollViewPosition();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void setPresenter(MainContract.DiscoverPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {
        bannerViewPager = $(R.id.discover_banner_viewpager);
        bookEntry = $(R.id.discover_book);
        photoEntry = $(R.id.discover_photo);
        musicEntry = $(R.id.discover_music);
        movieEntry = $(R.id.discover_movie);
        questionEntry = $(R.id.discover_question);
        recyclerView = $(R.id.discover_topic_recyclerview);

        adapter = new DiscoverRecyclerViewAdapter(getActivity(), presenter.getTopicListData(), presenter);

        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.getItemAnimator().setChangeDuration(0);

        final List<BannerBean> list = presenter.getBannerListData();

        bannerViewPager.setAdapter(new PagerAdapter() {

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, final int position) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_item_banner, null);
                Glide.with(container).load(list.get(position).getCover()).into((ImageView) view.findViewById(R.id.item_cover));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.onBannerItemClick(position, null);
                    }
                });
                container.addView(view);
                return view;
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return object == view;
            }
        });

        ((MainActivity) getActivity()).getViewPager().setObjectForPosition(mRootView, 2);
    }

    @Override
    public void startActivity(Class<?> target, BasePresenter presenter, @Nullable Bundle bundle, @Nullable String extra) {

    }

    @Override
    public void onClick(View view) {
        int clickId = 0;
        switch (view.getId()) {
            case R.id.discover_book:
                clickId = 0;
                Intent intent = new Intent(getActivity(), DiscoverListActivity.class);
                startActivity(intent);
                break;
            case R.id.discover_photo:
                clickId = 1;
                break;
            case R.id.discover_music:
                clickId = 2;
                break;
            case R.id.discover_movie:
                clickId = 3;
                break;
            case R.id.discover_question:
                clickId = 4;
                break;
        }
        presenter.onEntryItemClick(clickId, null);
    }
}
