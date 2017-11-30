package com.xuan.qingya.Modules.Search;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.TextView;

import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Common.RecyclerConfig.ItemOffsetDecoration;
import com.xuan.qingya.Core.base.BaseActivity;
import com.xuan.qingya.Models.entity.Article;
import com.xuan.qingya.Models.entity.Base;
import com.xuan.qingya.Models.entity.Interview;
import com.xuan.qingya.Models.entity.SearchHistory;
import com.xuan.qingya.Modules.Discover.Detail.DiscoverDetailActivity;
import com.xuan.qingya.Modules.Interview.InterviewDetailActivity;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.DensityUtil;
import com.xuan.qingya.Utils.KeyboardUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchActivity extends BaseActivity<ViewContract, SearchPresenter> implements ViewContract {
    @BindView(R.id.searchInput)
    EditText searchInput;
    @BindView(R.id.clearInputButton)
    ImageButton clearButton;
    @BindView(R.id.historyRecyclerView)
    RecyclerView historyRecyclerView;
    @BindView(R.id.resultRecyclerView)
    RecyclerView resultRecyclerView;
    @BindView(R.id.history_list_cardview)
    CardView historyList;
    @BindView(R.id.result_list_cardview)
    LinearLayout resultList;
    @BindView(R.id.toolbarCloseButton)
    ImageButton backButton;

    private InputMethodManager inputManager;
    private SearchHistoryAdapter historyAdapter;
    private SearchResultAdapter resultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        isNotTransparentStatusBar();
        ButterKnife.bind(this);

        init();
        initListeners(clearButton, backButton);
    }

    public void init() {
        searchInput.requestFocus();
        searchInput.postDelayed(new Runnable() {
            @Override
            public void run() {
                inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(searchInput, InputMethodManager.HIDE_IMPLICIT_ONLY);
            }
        }, 500);

        clearButton.post(new Runnable() {
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

        historyAdapter = new SearchHistoryAdapter(this);
        historyRecyclerView.setAdapter(historyAdapter);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        historyRecyclerView.getItemAnimator().setChangeDuration(0);

        resultAdapter = new SearchResultAdapter(this, presenter);
        resultRecyclerView.setAdapter(resultAdapter);
        resultRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        resultRecyclerView.addItemDecoration(new ItemOffsetDecoration(16));
        resultRecyclerView.getItemAnimator().setChangeDuration(0);

        presenter.getHistoryDataList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clearInputButton:
                cleanSearchContent();
                break;
            case R.id.toolbarCloseButton:
                KeyboardUtil.hideKeyboard(inputManager, getCurrentFocus().getWindowToken());
                onBackPressed();
                break;
        }
    }

    @Override
    public SearchPresenter initPresenter() {
        return new SearchPresenter();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_alpha_out);
    }

    @Override
    public void listSwitchAnimation() {
        KeyboardUtil.hideKeyboard(inputManager, getCurrentFocus().getWindowToken());

        Runnable animationRunnable = new Runnable() {
            @Override
            public void run() {
                final Interpolator interpolator = AnimationUtils.loadInterpolator(getContext(), R.interpolator.msf_interpolator);

                final ViewTreeObserver viewTreeObserver = historyList.getViewTreeObserver();
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        viewTreeObserver.removeOnGlobalLayoutListener(this);
                        final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) historyList.getLayoutParams();
                        final int originalHeight = historyRecyclerView.getChildCount() * DensityUtil.dip2px(64);
                        //LogUtil.show("origanialHeight", originalHeight);
                        Animation animation = new Animation() {
                            @Override
                            protected void applyTransformation(float interpolatedTime, Transformation t) {
                                params.height = originalHeight - (int) (originalHeight * interpolatedTime);
                                historyList.setLayoutParams(params);
                            }
                        };
                        animation.setInterpolator(interpolator);
                        animation.setDuration(500);
                        historyList.startAnimation(animation);
                    }
                });

                resultList.setAlpha(0.0f);
                resultList.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        ObjectAnimator animator2 = ObjectAnimator.ofFloat(resultList, "translationY", 50 * 3, 0);
                        animator2.setDuration(600);
                        animator2.setInterpolator(interpolator);
                        animator2.start();

                        ObjectAnimator animator = ObjectAnimator.ofFloat(resultList, "alpha", 0, 1f);
                        animator.setDuration(600);
                        animator.start();
                    }

                }, 500);
            }
        };

        new Handler().postDelayed(animationRunnable, 500);
    }

    @Override
    public void cleanSearchContent() {
        searchInput.getEditableText().clear();
    }

    @Override
    public void fillSearchContent(String content) {
        searchInput.setText(content);
    }

    @Override
    public void showHistoryList(List<SearchHistory> data) {
        historyAdapter.setData(data);
    }

    @Override
    public void showResultList(List<Base> data) {
        resultAdapter.setData(data);
    }

    @Override
    protected void initListeners(@Nullable View... views) {
        super.initListeners(views);
        historyAdapter.addOnClickListener(new SearchHistoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SearchHistory bean) {
                presenter.getResultDataList(bean.getContent());
            }

            @Override
            public void onFillContentClick(String content) {
                fillSearchContent(content);
            }
        });

        resultAdapter.addOnClickListener(new SearchResultAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Base bean) {
                Intent intent = new Intent();
                if (bean.getSubType() == Constant.CONTENT_SUB_TYPE_INTERVIEW) {
                    intent.setClass(getContext(), InterviewDetailActivity.class);
                    intent.putExtra("bean", (Interview) bean);
                } else {
                    intent.setClass(getContext(), DiscoverDetailActivity.class);
                    intent.putExtra("bean", (Article) bean);
                }
                startActivity(intent);
            }

            @Override
            public void onLoveClick(Base bean) {
                presenter.onLoveButtonClick(bean);
            }
        });

        searchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    presenter.getResultDataList(searchInput.getEditableText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    private Context getContext() {
        return this;
    }
}
