package com.xuan.qingya.Modules.Profile.PlayHistory;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.RelativeLayout;

import com.xuan.qingya.Core.base.BaseFragment;
import com.xuan.qingya.Models.entity.Music;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.DensityUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayHistoryFragment extends BaseFragment<ViewContract, PlayHistoryPresenter> implements ViewContract {
    @BindView(R.id.playerFrame)
    RelativeLayout playerFrame;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private PlayHistoryRecyclerViewAdapter adapter;

    public PlayHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.fragment_play_history, container, false);
        ButterKnife.bind(this, mRootView);

        init();
        initListeners();

        return mRootView;
    }

    @Override
    public PlayHistoryPresenter initPresenter() {
        return new PlayHistoryPresenter();
    }

    public void init() {
        adapter = new PlayHistoryRecyclerViewAdapter(getActivity());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.getItemAnimator().setChangeDuration(0);

        playerFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                showPlayerWithAnimation();
            }
        }, 150);
    }

    private void showPlayerWithAnimation() {
        final int startY = DensityUtil.dip2px(-64);

        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                playerFrame.setTranslationY(startY * (interpolatedTime));
            }
        };
        animation.setInterpolator(new DecelerateInterpolator());
        animation.setDuration(300);
        playerFrame.startAnimation(animation);
    }

    @Override
    public void showList(List<Music> list) {
        adapter.setData(list);
    }

    @Override
    protected void initListeners(@Nullable View... views) {
        adapter.addOnClickListener(new PlayHistoryRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(Music bean, int position) {

            }
        });
    }

}
