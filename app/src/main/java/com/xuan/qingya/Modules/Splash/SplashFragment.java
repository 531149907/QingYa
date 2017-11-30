package com.xuan.qingya.Modules.Splash;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuan.qingya.Common.CacheKeys;
import com.xuan.qingya.Common.CacheValues;
import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Core.base.BaseFragment;
import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.Modules.Main.MainActivity;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.CacheUtil;
import com.xuan.qingya.Utils.FragmentManagement;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SplashFragment extends BaseFragment {

    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.appName)
    TextView appName;
    @BindView(R.id.appDescription)
    TextView description;

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_splash, container, false);
        ButterKnife.bind(this, mRootView);

        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        CacheUtil cacheUtil = CacheUtil.get(getActivity());
        String isFirstOpen = cacheUtil.getAsString(CacheKeys.IS_FIRST_OPEN);
        if (isFirstOpen == null) {
            //安装后第一次打开
            cacheUtil.put(CacheKeys.IS_FIRST_OPEN, CacheValues.TRUE);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constant.ENTRY_TYPE, Constant.ACTIVITY_SPLASH);

                    OptionFragment optionFragment = new OptionFragment();

                    optionFragment.setSharedElementEnterTransition(TransitionInflater.from(getActivity())
                            .inflateTransition(R.transition.splash_sharedelement_transition)
                            .setInterpolator(new DecelerateInterpolator())
                            .setDuration(300));
                    optionFragment.setEnterTransition(new Fade());
                    optionFragment.setArguments(bundle);

                    HashMap<View, String> sharedElements = new HashMap<>();
                    sharedElements.put(logo, ViewCompat.getTransitionName(logo));
                    sharedElements.put(appName, ViewCompat.getTransitionName(appName));
                    sharedElements.put(description, ViewCompat.getTransitionName(description));

                    FragmentManagement.replaceFragmentWithSharedElement(getActivity().getSupportFragmentManager(), optionFragment, R.id.splash_container, sharedElements);
                }
            }, 3000);
        }
        if (isFirstOpen != null && isFirstOpen.equals(CacheValues.TRUE)) {
            //非第一次打开
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }, 3000);
        }
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }


}
