package com.xuan.qingya.Modules.Splash;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuan.qingya.Core.Base.BaseFragment;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.FragmentManagement;

import java.util.HashMap;


public class SplashFragment extends BaseFragment {

    private ImageView logo;
    private TextView app_name, app_description;

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_splash, container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logo = $(R.id.logo);
        app_name = $(R.id.app_name);
        app_description = $(R.id.app_description);
    }

    @Override
    public void onStart() {
        super.onStart();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                OptionFragment optionFragment = new OptionFragment();

                optionFragment.setSharedElementEnterTransition(TransitionInflater.from(getActivity())
                        .inflateTransition(R.transition.splash_sharedelement_transition)
                        .setInterpolator(new DecelerateInterpolator())
                        .setDuration(300));
                optionFragment.setEnterTransition(new Fade());

                HashMap<View, String> sharedElements = new HashMap<>();
                sharedElements.put(logo, ViewCompat.getTransitionName(logo));
                sharedElements.put(app_name, ViewCompat.getTransitionName(app_name));
                sharedElements.put(app_description, ViewCompat.getTransitionName(app_description));

                FragmentManagement.replaceFragmentWithSharedElement(getActivity().getSupportFragmentManager(), optionFragment, R.id.splash_container, sharedElements);

            }
        }, 3000);
    }
}
