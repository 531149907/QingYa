package com.xuan.qingya.Modules.Splash;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.xuan.qingya.Core.Base.BaseFragment;
import com.xuan.qingya.Modules.Startup.StartupActivity;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.DensityUtil;

public class OptionFragment extends BaseFragment {
    private ImageButton close_btn;
    private Button skip_btn;
    private ImageView divider;
    private Button reg_btn, login_btn;

    private int[] YCollection = new int[3];

    private ViewTreeObserver viewTreeObserver;

    public OptionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_option, container, false);

        initView();

        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRootView.getViewTreeObserver().
                addOnDrawListener(new ViewTreeObserver.OnDrawListener() {
            @Override
            public void onDraw() {
                if (divider.getY() != 0.0f) {
                    YCollection[0] = (int) divider.getY();
                }
                if (reg_btn.getY() != 0.0f) {
                    YCollection[1] = (int) reg_btn.getY();
                }
                if (login_btn.getY() != 0.0f) {
                    YCollection[2] = (int) login_btn.getY();
                }

                for (int e : YCollection) {
                    if (e == 0) {
                        return;
                    }
                }

                mRootView.getViewTreeObserver().removeOnDrawListener(this);
                AnimationStart();
            }
        });

    }

    protected void initView() {
        close_btn = $(R.id.close_btn);
        skip_btn = $(R.id.skip_btn);
        divider = $(R.id.divider);
        reg_btn = $(R.id.reg_btn);
        login_btn = $(R.id.login_btn);

        reg_btn.setAlpha(0.0f);
        login_btn.setAlpha(0.0f);
        divider.setAlpha(0.0f);

        initListeners(close_btn, skip_btn, reg_btn, login_btn);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.close_btn:
                getActivity().onBackPressed();
                break;
            case R.id.reg_btn:
                Intent intent = new Intent(getActivity(),StartupActivity.class);
                intent.putExtra("fragmentOperation",0);
                startActivity(intent);
                break;
            case R.id.login_btn:
                Intent intent2 = new Intent(getActivity(),StartupActivity.class);
                intent2.putExtra("fragmentOperation",1);
                startActivity(intent2);
                break;
        }
    }

    private void AnimationStart() {
        int moveRange = DensityUtil.dip2px(230);

        Path path = new Path();
        path.moveTo(0, YCollection[0] + moveRange);
        path.lineTo(0, YCollection[0]);

        ObjectAnimator animator = ObjectAnimator.ofFloat(divider, "x", "y", path);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setDuration(340);
        animator.start();

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(divider, "alpha", 0.0f, 1.0f);
        animator2.setInterpolator(new DecelerateInterpolator());
        animator2.setDuration(340);
        animator2.start();

        path.reset();
        path.moveTo(getResources().getDimensionPixelSize(R.dimen.default_margin), YCollection[1] + moveRange);
        path.lineTo(getResources().getDimensionPixelSize(R.dimen.default_margin), YCollection[1]);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(reg_btn, "x", "y", path);
        animator3.setInterpolator(new DecelerateInterpolator());
        animator3.setDuration(360);
        animator3.start();

        ObjectAnimator animator4 = ObjectAnimator.ofFloat(reg_btn, "alpha", 0.0f, 1.0f);
        animator4.setInterpolator(new DecelerateInterpolator());
        animator4.setDuration(360);
        animator4.start();

        path.reset();
        path.moveTo(getResources().getDimensionPixelSize(R.dimen.default_margin), YCollection[2] + moveRange);
        path.lineTo(getResources().getDimensionPixelSize(R.dimen.default_margin), YCollection[2]);
        ObjectAnimator animator5 = ObjectAnimator.ofFloat(login_btn, "x", "y", path);
        animator5.setInterpolator(new DecelerateInterpolator());
        animator5.setDuration(380);
        animator5.start();

        ObjectAnimator animator6 = ObjectAnimator.ofFloat(login_btn, "alpha", 0.0f, 1.0f);
        animator6.setInterpolator(new DecelerateInterpolator());
        animator6.setDuration(380);
        animator6.start();
    }
}
