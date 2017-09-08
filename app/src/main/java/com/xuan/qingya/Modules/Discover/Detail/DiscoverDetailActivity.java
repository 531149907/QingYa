package com.xuan.qingya.Modules.Discover.Detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Common.Listeners.AppBarStateChangeListener;
import com.xuan.qingya.Core.Base.BaseActivity;
import com.xuan.qingya.Models.Entity.ArticleBean;
import com.xuan.qingya.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscoverDetailActivity extends BaseActivity implements DiscoverDetailContract.DiscoverDetailView {

    private DiscoverDetailContract.DiscoverDetailPresenter presenter;

    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private NestedScrollView articleScrollView;
    private NestedScrollView askScrollView;
    private NestedScrollView photoScrollView;
    private NestedScrollView musicScrollView;
    private NestedScrollView movieScrollView;

    private boolean isFullScreenType = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preInit();
        init();
        initListeners();
    }

    private void preInit() {
        Intent intent = getIntent();
        int beanType = intent.getIntExtra("beanType", 0);
        switch (beanType) {
            case Constant.CONTENT_DISCOVER_ARTICLE_READ:
            case Constant.CONTENT_DISCOVER_QUESTION:
                isFullScreenType = false;
        }
        if (isFullScreenType) {
            isTransparentStatusBar();
            setContentView(R.layout.activity_discover_detail_full);
        } else {
            isNotTransparentStatusBar();
            setContentView(R.layout.activity_discover_detail_normal);
        }
    }

    @Override
    public void setPresenter(DiscoverDetailContract.DiscoverDetailPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {
        appBarLayout = $(R.id.appbar);
        toolbar = $(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        if (isFullScreenType) {
            toolbar.getNavigationIcon().setTint(getResources().getColor(R.color.colorPrimary));
        }


        new DiscoverDetailPresenter(this);
        Intent intent = getIntent();
        int beanType = intent.getIntExtra("beanType", 0);
        presenter.initLayoutByType(beanType);

    }

    @Override
    protected void initListeners(@Nullable View... views) {
        super.initListeners(views);
        toolbar.setNavigationOnClickListener(this);
        if (isFullScreenType) {
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
    }

    @Override
    public void setContent(ArticleBean bean) {
        int type = bean.getType();
        ImageView cover = $(R.id.cover);

        switch (type) {
            case Constant.CONTENT_DISCOVER_ARTICLE_IMAGE:
                articleScrollView = $(R.id.article_scrollview);
                articleScrollView.setVisibility(View.VISIBLE);
                Glide.with(this).load(bean.getCover_img()).into(cover);
                TextView article_image_author = $(R.id.article_author);
                TextView article_image_content = $(R.id.article_content);
                article_image_author.setText(bean.getAuthor());
                article_image_content.setText(bean.getContent());
                break;
            case Constant.CONTENT_DISCOVER_ARTICLE_READ:
                articleScrollView = $(R.id.article_scrollview);
                articleScrollView.setVisibility(View.VISIBLE);
                TextView article_read_title = $(R.id.article_title);
                TextView article_read_author = $(R.id.article_author);
                TextView article_read_content = $(R.id.article_content);
                article_read_title.setText(bean.getTitle());
                article_read_author.setText(bean.getAuthor());
                article_read_content.setText(bean.getContent());
                break;
            case Constant.CONTENT_DISCOVER_ARTICLE_POEM:
                articleScrollView = $(R.id.article_scrollview);
                articleScrollView.setVisibility(View.VISIBLE);
                Glide.with(this).load(bean.getCover_img()).into(cover);
                TextView article_poem_author = $(R.id.article_author);
                TextView article_poem_content = $(R.id.article_content);
                article_poem_author.setText(bean.getAuthor());
                article_poem_content.setText(bean.getContent());
                break;
            case Constant.CONTENT_DISCOVER_PHOTOGRAPHY:
                Glide.with(this).load(bean.getCover_img()).into(cover);
                photoScrollView = $(R.id.photo_scrollview);
                photoScrollView.setVisibility(View.VISIBLE);
                TextView photo_author = $(R.id.photo_author);
                photo_author.setText(bean.getAuthor());

                List<Map<String, Object>> data_list = new ArrayList<>();
                Map<String, Object> map1 = new HashMap<>();
                map1.put("title", "相机厂商");
                map1.put("figure", "Nikon");
                data_list.add(map1);
                Map<String, Object> map2 = new HashMap<>();
                map2.put("title", "型号");
                map2.put("figure", "Nikon D3300");
                data_list.add(map2);
                Map<String, Object> map3 = new HashMap<>();
                map3.put("title", "快门速度");
                map3.put("figure", "1/4000s");
                data_list.add(map3);
                Map<String, Object> map4 = new HashMap<>();
                map4.put("title", "光圈");
                map4.put("figure", "ƒ/1.6");
                data_list.add(map4);
                Map<String, Object> map5 = new HashMap<>();
                map5.put("title", "焦距");
                map5.put("figure", "35mm");
                data_list.add(map5);
                Map<String, Object> map6 = new HashMap<>();
                map6.put("title", "ISO");
                map6.put("figure", "100");
                data_list.add(map6);
                Map<String, Object> map7 = new HashMap<>();
                map7.put("title", "发布于");
                map7.put("figure", "2017/1/17");
                data_list.add(map7);
                Map<String, Object> map8 = new HashMap<>();
                map8.put("title", "图片尺寸");
                map8.put("figure", "3902 × 5464");
                data_list.add(map8);
                String[] from = {"title", "figure"};
                int[] to = {R.id.photo_detail_text, R.id.photo_detail_figures};
                SimpleAdapter sim_adapter = new SimpleAdapter(this, data_list, R.layout.layout_item_photo_detail_grid, from, to);
                GridView gridView = $(R.id.photo_grid_view);
                gridView.setAdapter(sim_adapter);
                break;
            case Constant.CONTENT_DISCOVER_MUSIC:
                musicScrollView = $(R.id.music_scrollview);
                musicScrollView.setVisibility(View.VISIBLE);
                Glide.with(this).load(bean.getCover_img()).into(cover);
                TextView music_title = $(R.id.music_title);
                TextView music_author = $(R.id.music_author);
                TextView music_content = $(R.id.music_content);
                music_title.setText(bean.getTitle());
                music_author.setText(bean.getAuthor());
                music_content.setText(bean.getContent());
                break;
            case Constant.CONTENT_DISCOVER_MOVIE:
                movieScrollView = $(R.id.movie_scrollview);
                movieScrollView.setVisibility(View.VISIBLE);
                Glide.with(this).load(bean.getCover_img()).into(cover);
                TextView movie_title = $(R.id.movie_title);
                TextView movie_author = $(R.id.movie_author);
                TextView movie_content = $(R.id.movie_content);
                movie_title.setText(bean.getTitle());
                movie_author.setText(bean.getAuthor());
                movie_content.setText(bean.getContent());
                break;
            case Constant.CONTENT_DISCOVER_QUESTION:
                askScrollView = $(R.id.ask_scrollview);
                askScrollView.setVisibility(View.VISIBLE);
                TextView ask_title = $(R.id.ask_title);
                TextView ask_ask_author = $(R.id.ask_ask_author);
                TextView ask_ask_content = $(R.id.ask_ask_content);
                TextView ask_answer_author = $(R.id.ask_answer_author);
                TextView ask_answer_content = $(R.id.ask_answer_content);
                ask_title.setText(bean.getTitle());
                ask_ask_author.setText(bean.getAnswer_author());
                ask_ask_content.setText(bean.getAsk_content());
                ask_answer_author.setText(bean.getAnswer_author());
                ask_answer_content.setText(bean.getAnswer_content());
                break;
        }
    }
}
