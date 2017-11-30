package com.xuan.qingya.Modules.Splash;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Core.base.BaseFragment;
import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.Modules.Main.MainActivity;
import com.xuan.qingya.Modules.Startup.StartupActivity;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.DensityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OptionFragment extends BaseFragment {
    @BindView(R.id.closeButton)
    ImageButton closeButton;
    @BindView(R.id.skipButton)
    Button skipButton;
    @BindView(R.id.divider)
    ImageView divider;
    @BindView(R.id.regButton)
    Button regButton;
    @BindView(R.id.loginButton)
    Button loginButton;

    private Runnable animationRunnable;

    public OptionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_option, container, false);
        ButterKnife.bind(this, mRootView);

        init();
        initListeners(closeButton, skipButton, regButton, loginButton);
        AnimationStart();

        return mRootView;
    }

    private void init() {
        Bundle bundle = getArguments();
        int entryType = bundle.getInt(Constant.ENTRY_TYPE);
        if (entryType == Constant.ACTIVITY_SPLASH) {
            closeButton.setVisibility(View.GONE);
        } else {
            skipButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.closeButton:
                getActivity().onBackPressed();
                break;
            case R.id.skipButton:
                intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
            case R.id.regButton:
                intent = new Intent(getActivity(), StartupActivity.class);
                intent.putExtra(Constant.ENTRY_TYPE, Constant.FRAGMENT_REGISTER);
                startActivity(intent);
                break;
            case R.id.loginButton:
                intent = new Intent(getActivity(), StartupActivity.class);
                intent.putExtra(Constant.ENTRY_TYPE, Constant.FRAGMENT_LOGIN);
                startActivity(intent);
                break;
        }
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    private void AnimationStart() {
        int moveRange = DensityUtil.dip2px(230);

        ObjectAnimator dividerTranslationYAnimator = ObjectAnimator.ofFloat(divider, "translationY", moveRange, 0);
        ObjectAnimator dividerAlphaAnimator = ObjectAnimator.ofFloat(divider, "alpha", 0, 1);
        ObjectAnimator regButtonTranslationYAnimator = ObjectAnimator.ofFloat(regButton, "translationY", moveRange, 0);
        ObjectAnimator regButtonAlphaAnimator = ObjectAnimator.ofFloat(regButton, "alpha", 0, 1);
        ObjectAnimator loginButtonTranslationYAnimator = ObjectAnimator.ofFloat(loginButton, "translationY", moveRange, 0);
        ObjectAnimator loginButtonAlphaAnimator = ObjectAnimator.ofFloat(loginButton, "alpha", 0, 1);

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(dividerAlphaAnimator, dividerTranslationYAnimator, regButtonAlphaAnimator, regButtonTranslationYAnimator, loginButtonAlphaAnimator, loginButtonTranslationYAnimator);
        animatorSet.setDuration(350);
        animatorSet.setInterpolator(new DecelerateInterpolator());

        animationRunnable = new Runnable() {
            @Override
            public void run() {
                animatorSet.start();
            }
        };

        divider.post(animationRunnable);
    }

    @Override
    public void onPause() {
        super.onPause();
        divider.removeCallbacks(animationRunnable);
    }
}
