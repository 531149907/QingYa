package com.xuan.qingya.Modules.Profile.PlayHistory;


import android.os.Bundle;
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

import com.xuan.qingya.Core.Base.BaseFragment;
import com.xuan.qingya.Models.Entity.MusicBean;
import com.xuan.qingya.Modules.Profile.ProfileActivity;
import com.xuan.qingya.Modules.Profile.ProfileContract;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.DensityUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayHistoryFragment extends BaseFragment implements ProfileContract.PlayHistoryView {

    private ProfileContract.PlayHistoryPresenter presenter;
    private RelativeLayout playerFrame;
    private RecyclerView recyclerView;
    private PlayHistoryRecyclerViewAdapter adapter;

    public PlayHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_play_history, container, false);

        init();

        return mRootView;
    }

    @Override
    public void setPresenter(ProfileContract.PlayHistoryPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {
        ((ProfileActivity) getActivity()).setToolbarTitle("消息");

        recyclerView = $(R.id.recyclerView);
        adapter = new PlayHistoryRecyclerViewAdapter(getActivity(), presenter.getData());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.getItemAnimator().setChangeDuration(0);
        adapter.addOnClickListener(new PlayHistoryRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, MusicBean bean, int position) {
            }
        });

        playerFrame = $(R.id.playerFrame);
        playerFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                showPlayerWithAnimation();
            }
        }, 150);
    }

    @Override
    public void setPlayerTitle(String title) {

    }

    @Override
    public void changePlayerStatusButton() {

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

}
