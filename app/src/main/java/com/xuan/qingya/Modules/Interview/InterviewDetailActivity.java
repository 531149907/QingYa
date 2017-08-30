package com.xuan.qingya.Modules.Interview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.xuan.qingya.Common.Listeners.AppBarStateChangeListener;
import com.xuan.qingya.Core.Base.BaseActivity;
import com.xuan.qingya.Models.Entity.InterviewBean;
import com.xuan.qingya.R;

public class InterviewDetailActivity extends BaseActivity implements InterviewDetailContract.InterviewDetailView {

    private InterviewDetailContract.InterviewDetailPresenter presenter;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview_detail);

        isTransparentStatusBar();
        init();
        initListeners();
    }

    @Override
    public void setPresenter(InterviewDetailContract.InterviewDetailPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bean_bundle");
        InterviewBean bean = bundle.getParcelable("bean");

        TextView title = $(R.id.interview_detail_title);
        TextView author = $(R.id.interview_detail_author);
        TextView content = $(R.id.interview_detail_content);

        title.setText(bean.getTitle());
        author.setText(bean.getAuthor());
        content.setText(bean.getContent());

        toolbar = $(R.id.interview_detail_toolbar);
        appBarLayout = $(R.id.interview_detail_appbar_layout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar.getNavigationIcon().setTint(getResources().getColor(R.color.colorPrimary));

        ImageView cover = $(R.id.interview_detail_cover);
        Glide.with(this).load(R.drawable.a20)
                .transition(new DrawableTransitionOptions()
                        .crossFade(200)).into(cover);
    }

    @Override
    protected void initListeners(@Nullable View... views) {
        toolbar.setNavigationOnClickListener(this);
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.COLLAPSED) {
                    toolbar.getNavigationIcon().setTint(getResources().getColor(R.color.defaultIconTint));
                } else {
                    toolbar.getNavigationIcon().setTint(getResources().getColor(R.color.colorPrimary));
                }

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.interview_detail_toolbar:
                onBackPressed();
        }
    }
}
