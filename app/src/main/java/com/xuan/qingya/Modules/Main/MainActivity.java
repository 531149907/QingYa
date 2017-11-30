package com.xuan.qingya.Modules.Main;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.NestedScrollView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xuan.qingya.Common.View.CustomBottomNavigationView;
import com.xuan.qingya.Common.View.NestedViewPager;
import com.xuan.qingya.Core.base.BaseActivity;
import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.Modules.Fab.FabActivity;
import com.xuan.qingya.Modules.Main.Discover.DiscoverFragment;
import com.xuan.qingya.Modules.Main.Home.HomeFragment;
import com.xuan.qingya.Modules.Main.Interview.InterviewFragment;
import com.xuan.qingya.Modules.Main.Profile.ProfileFragment;
import com.xuan.qingya.Modules.Search.SearchActivity;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.DensityUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.viewpager)
    NestedViewPager viewPager;
    @BindView(R.id.nestedscrollview)
    NestedScrollView scrollView;
    @BindView(R.id.bottomNavigationBar)
    CustomBottomNavigationView bottomNavigationView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.toolbarSearchButton)
    ImageButton searchButton;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    private Fragment[] fragments;
    private String[] toolbarTitles;
    private int currentFragmentID = 0;

    @SuppressLint("UseSparseArrays")
    private HashMap<Integer, Integer> scrollViewPosition = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isTransparentStatusBar();
        ButterKnife.bind(this);

        init();
        initAdapters();
        initListeners(fab, searchButton);
    }

    @Override
    protected void initListeners(View... views) {
        super.initListeners(views);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    private int beforeClickItem = 0;

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        beforeClickItem = currentFragmentID;
                        currentFragmentID = item.getOrder();

                        if (beforeClickItem == currentFragmentID) {
                            return true;
                        }

                        setToolbarTitle(toolbarTitles[currentFragmentID]);
                        setScrollViewPosition(beforeClickItem, currentFragmentID);

                        viewPager.resetHeight(currentFragmentID);
                        viewPager.setCurrentItem(currentFragmentID, false);

                        if (currentFragmentID != 0) {
                            fab.hide();
                        } else {
                            fab.show();
                        }

                        return true;
                    }
                }
        );
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY - oldScrollY > 0) {
                    bottomNavigationView.hideBarAnimation();
                    if (currentFragmentID == 0) {
                        fab.hide();
                    }
                } else {
                    bottomNavigationView.showBarAnimation();
                    if (currentFragmentID == 0) {
                        fab.show();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.fab:
                intent = new Intent(this, FabActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_alpha_out);
                break;
            case R.id.toolbarSearchButton:
                intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_alpha_out);
                break;
        }
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    public void init() {
        toolbarTitles = new String[]{"青芽", "采访", "发现", "个人"};

        fragments = new Fragment[4];
        fragments[0] = new HomeFragment();
        fragments[1] = new InterviewFragment();
        fragments[2] = new DiscoverFragment();
        fragments[3] = new ProfileFragment();

        for (int i = 0; i < fragments.length; i++) {
            scrollViewPosition.put(i, 0);
        }

        toolbarTitle.setText(toolbarTitles[0]);
        viewPager.setOffscreenPageLimit(fragments.length - 1);

        doEnterAnimation();
    }

    private void initAdapters() {
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        });
    }

    private void setToolbarTitle(String title) {
        toolbarTitle.setText(title);
    }

    public NestedViewPager getViewPager() {
        return this.viewPager;
    }

    public void setScrollViewPosition(final int currentFragmentID, final int newFragmentID) {
        scrollViewPosition.put(currentFragmentID, scrollView.getScrollY());
        final ViewTreeObserver viewTreeObserver = scrollView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                viewTreeObserver.removeOnGlobalLayoutListener(this);
                scrollView.scrollTo(0, scrollViewPosition.get(newFragmentID));
            }
        });
    }

    public AppBarLayout getAppBarLayout() {
        return appBarLayout;
    }

    private void doEnterAnimation() {
        final float screenHeight = DensityUtil.getScreenHeight();
        final Interpolator interpolator = AnimationUtils.loadInterpolator(getApplicationContext(), R.interpolator.msf_interpolator);
        appBarLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                appBarLayout.setVisibility(View.VISIBLE);
                ObjectAnimator animator = ObjectAnimator.ofFloat(appBarLayout, "Y", -80 * 3, 0);
                animator.setDuration(500);
                animator.setInterpolator(interpolator);

                float screenHeight = DensityUtil.getScreenHeight();
                bottomNavigationView.setVisibility(View.VISIBLE);
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(bottomNavigationView, "Y", screenHeight, screenHeight - 56 * 3);
                animator1.setDuration(500);
                animator1.setInterpolator(interpolator);

                animator.start();
                animator1.start();
            }
        }, 1200);

        toolbarTitle.postDelayed(new Runnable() {
            @Override
            public void run() {
                toolbarTitle.setVisibility(View.VISIBLE);
                ObjectAnimator animator = ObjectAnimator.ofFloat(toolbarTitle, "translationY", -80 * 3, 0);
                animator.setDuration(500);
                animator.setInterpolator(interpolator);
                animator.start();
            }
        }, 1260);

        searchButton.postDelayed(new Runnable() {
            @Override
            public void run() {
                searchButton.setVisibility(View.VISIBLE);
                ObjectAnimator animator = ObjectAnimator.ofFloat(searchButton, "translationY", -80 * 3, 0);
                animator.setDuration(500);
                animator.setInterpolator(interpolator);
                animator.start();
            }
        }, 1320);

        viewPager.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.setVisibility(View.VISIBLE);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(scrollView, "translationY", screenHeight, 0);
                animator2.setDuration(700);
                animator2.setInterpolator(new DecelerateInterpolator(3.f));

                animator2.start();
            }
        }, 1800);

    }
}
