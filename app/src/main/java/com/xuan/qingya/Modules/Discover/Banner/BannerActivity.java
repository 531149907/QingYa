package com.xuan.qingya.Modules.Discover.Banner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xuan.qingya.Core.base.BaseActivity;
import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BannerActivity extends BaseActivity {
    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        isNotTransparentStatusBar();
        ButterKnife.bind(this);

        init();
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    private void init() {
        Intent intent = getIntent();
        int selectedBannerID = intent.getIntExtra("currentBannerID", 0);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("http://www.baidu.com/");

        toolbar.setTitle("WebView");
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        onBackPressed();
        return false;
    }
}



