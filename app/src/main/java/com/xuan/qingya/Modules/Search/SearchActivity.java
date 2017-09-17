package com.xuan.qingya.Modules.Search;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.xuan.qingya.Common.RecyclerConfig.ItemOffsetDecoration;
import com.xuan.qingya.Core.Base.BaseActivity;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.DensityUtil;
import com.xuan.qingya.Utils.KeyboardUtil;
import com.xuan.qingya.Utils.LogUtil;


public class SearchActivity extends BaseActivity implements SearchContract.SearchView {

    private EditText searchInput;
    private ImageButton clearButton;
    InputMethodManager inputManager;
    SearchContract.SearchPresenter presenter;
    RecyclerView historyRecyclerView;
    RecyclerView resultRecyclerView;
    CardView historyCardview;
    LinearLayout resultCardview;
    Space space;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        isNotTransparentStatusBar();

        presenter = new SearchPresenter(this);

        init();
        initListeners(clearButton);
    }

    @Override
    public void setPresenter(SearchContract.SearchPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {
        historyCardview = $(R.id.history_list_cardview);
        resultCardview = $(R.id.result_list_cardview);
        space = $(R.id.space);
        historyRecyclerView = $(R.id.recyclerView);
        searchInput = $(R.id.search_input);
        clearButton = $(R.id.clearInput);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchInput.requestFocus();
        searchInput.postDelayed(new Runnable() {
            @Override
            public void run() {
                inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(searchInput, InputMethodManager.HIDE_IMPLICIT_ONLY);
            }
        }, 500);
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        searchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    switchToResultList();

                    return true;
                }
                return false;
            }
        });

        clearButton.post(new Runnable() {
            @Override
            public void run() {
                clearButton.setPivotX(clearButton.getHeight() / 2);
                clearButton.setPivotY(clearButton.getHeight() / 2);

                ObjectAnimator animator2 = ObjectAnimator.ofFloat(clearButton, "scaleX", 0, 1);
                ObjectAnimator animator3 = ObjectAnimator.ofFloat(clearButton, "scaleY", 0, 1);
                ObjectAnimator animator = ObjectAnimator.ofFloat(clearButton, "rotation", 0, 180);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(animator, animator2, animator3);
                animatorSet.setDuration(300);
                animatorSet.setInterpolator(new DecelerateInterpolator());
                animatorSet.start();
            }
        });

        historyRecyclerView.setAdapter(new SearchHistoryAdapter(this, presenter.getHistoryDataList(), presenter));
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        resultRecyclerView = $(R.id.resultRecyclerView);
        resultRecyclerView.setAdapter(new SearchResultAdapter(this, presenter.getResultDataList("null"), presenter));
        resultRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        resultRecyclerView.addItemDecoration(new ItemOffsetDecoration(16));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clearInput:
                searchInput.getEditableText().clear();
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_alpha_out);
    }

    @Override
    public void replaceSearchContent(String searchContent) {
        searchInput.setText(searchContent);
    }

    @Override
    public void switchToResultList() {
        KeyboardUtil.hideKeyboard(inputManager, getCurrentFocus().getWindowToken());
        new Handler().postDelayed(new Runnable() {
            public void run() {
                final Interpolator interpolator = AnimationUtils.loadInterpolator(getApplicationContext(), R.interpolator.msf_interpolator);

                final ViewTreeObserver viewTreeObserver = historyCardview.getViewTreeObserver();
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) historyCardview.getLayoutParams();
                        final int originalHeight = historyRecyclerView.getChildCount() * DensityUtil.dip2px(64);
                        LogUtil.show("origanialHeight", originalHeight);
                        Animation animation = new Animation() {
                            @Override
                            protected void applyTransformation(float interpolatedTime, Transformation t) {
                                params.height = originalHeight - (int) (originalHeight * interpolatedTime);
                                historyCardview.setLayoutParams(params);
                            }
                        };
                        animation.setInterpolator(interpolator);
                        animation.setDuration(500);
                        historyCardview.startAnimation(animation);
                        viewTreeObserver.removeOnGlobalLayoutListener(this);
                    }
                });

                resultCardview.setAlpha(0.0f);
                resultCardview.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        ObjectAnimator animator2 = ObjectAnimator.ofFloat(resultCardview, "translationY", 50 * 3, 0);
                        animator2.setDuration(600);
                        animator2.setInterpolator(interpolator);
                        animator2.start();

                        ObjectAnimator animator = ObjectAnimator.ofFloat(resultCardview, "alpha", 0, 1f);
                        animator.setDuration(600);
                        animator.start();
                    }

                }, 500);
            }

        }, 500);

    }

    @Override
    public void switchToSearchList() {

    }

}
