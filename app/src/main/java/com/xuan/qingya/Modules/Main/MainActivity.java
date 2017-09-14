package com.xuan.qingya.Modules.Main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xuan.qingya.Common.View.CustomBottomNavigationView;
import com.xuan.qingya.Common.View.NestedViewPager;
import com.xuan.qingya.Core.Base.BaseActivity;
import com.xuan.qingya.Modules.Fab.FabActivity;
import com.xuan.qingya.Modules.Main.Discover.DiscoverFragment;
import com.xuan.qingya.Modules.Main.Discover.DiscoverPresenter;
import com.xuan.qingya.Modules.Main.Home.HomeFragment;
import com.xuan.qingya.Modules.Main.Home.HomePresenter;
import com.xuan.qingya.Modules.Main.Interview.InterviewFragment;
import com.xuan.qingya.Modules.Main.Interview.InterviewPresenter;
import com.xuan.qingya.Modules.Main.Profile.ProfileFragment;
import com.xuan.qingya.Modules.Main.Profile.ProfilePresenter;
import com.xuan.qingya.Modules.Search.SearchActivity;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.DensityUtil;

import java.util.HashMap;

public class MainActivity extends BaseActivity implements MainContract.MainView {

    private MainContract.MainPresenter presenter;

    private AppBarLayout appBarLayout;
    private NestedViewPager viewPager;
    private NestedScrollView scrollView;
    private CustomBottomNavigationView bottomNavigationView;
    private Fragment[] fragments;
    private FloatingActionButton fab;

    private String[] toolbarTitles;
    private int currentFragmentID = 0;

    @SuppressLint("UseSparseArrays")
    private HashMap<Integer, Integer> scrollViewPosition = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isNotTransparentStatusBar();

        init();
        initAdapters();
        initListeners(fab);

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

                        if (currentFragmentID == 3) {
                            fab.hide();
                            final int newHeight = DensityUtil.dip2px(88);
                            final int originalHeight = DensityUtil.dip2px(56);
                            final CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();

                            Interpolator interpolator = AnimationUtils.loadInterpolator(getApplicationContext(), R.interpolator.msf_interpolator);
                            Animation animation = new Animation() {
                                @Override
                                protected void applyTransformation(float interpolatedTime, Transformation t) {
                                    params.height = originalHeight + (int) (newHeight * interpolatedTime);
                                    appBarLayout.setLayoutParams(params);
                                }
                            };
                            animation.setInterpolator(interpolator);
                            animation.setDuration(300);
                            appBarLayout.startAnimation(animation);
                        }
                        if (beforeClickItem == 3 && currentFragmentID != 3) {
                            fab.show();
                            final int newHeight = DensityUtil.dip2px(88);
                            final int originalHeight = DensityUtil.dip2px(56 + 88);
                            final CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();

                            Interpolator interpolator = AnimationUtils.loadInterpolator(getApplicationContext(), R.interpolator.msf_interpolator);
                            Animation animation = new Animation() {
                                @Override
                                protected void applyTransformation(float interpolatedTime, Transformation t) {
                                    params.height = originalHeight - (int) (newHeight * interpolatedTime);
                                    appBarLayout.setLayoutParams(params);
                                }
                            };
                            animation.setInterpolator(interpolator);
                            animation.setDuration(300);
                            appBarLayout.startAnimation(animation);
                        }

                        return true;
                    }
                }
        );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_fab:
                Intent intent = new Intent(this, FabActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_alpha_out);
                break;
        }
    }

    @Override
    public void setPresenter(MainContract.MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {
        new MainPresenter(this);
        toolbarTitles = new String[]{"青芽", "采访", "发现", "个人"};

        fragments = new Fragment[4];
        fragments[0] = new HomeFragment();
        fragments[1] = new InterviewFragment();
        fragments[2] = new DiscoverFragment();
        fragments[3] = new ProfileFragment();
        new HomePresenter((MainContract.HomeView) fragments[0]);
        new InterviewPresenter((MainContract.InterviewView) fragments[1]);
        new DiscoverPresenter((MainContract.DiscoverView) fragments[2]);
        new ProfilePresenter((MainContract.ProfileView) fragments[3]);

        for (int i = 0; i < fragments.length; i++) {
            scrollViewPosition.put(i, 0);
        }

        scrollView = $(R.id.nestedscrollview);
        bottomNavigationView = $(R.id.bottom_navigation_bar);
        viewPager = $(R.id.main_viewpager);
        appBarLayout = $(R.id.main_appbar_layout);
        fab = $(R.id.main_fab);

        setSupportActionBar((Toolbar) $(R.id.main_toolbar));
        setToolbarTitle(toolbarTitles[0]);

        viewPager.setOffscreenPageLimit(fragments.length - 1);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
        params.height = DensityUtil.dip2px(56);
        appBarLayout.setLayoutParams(params);

        RequestOptions options = RequestOptions.circleCropTransform();
        Glide.with(getApplicationContext()).load(R.drawable.a23).apply(options).into((ImageView) findViewById(R.id.profile_avatar));
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

    private void setToolbarTitle(String toolbarTitle) {
        getSupportActionBar().setTitle(toolbarTitle);
    }

    public NestedViewPager getViewPager() {
        return this.viewPager;
    }

    public void setScrollViewPosition(final int currentFragmentID, final int newFragmentID) {
        scrollViewPosition.put(currentFragmentID, scrollView.getScrollY());
        final ViewTreeObserver viewTreeObserver = scrollView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                scrollView.scrollTo(0, scrollViewPosition.get(newFragmentID));
                viewTreeObserver.removeOnGlobalLayoutListener(this);
            }
        });
    }

    @Override
    public void showFabList() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_alpha_out);
        }
        return true;
    }
}
