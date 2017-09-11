package com.xuan.qingya.Modules.Search;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.xuan.qingya.Core.Base.BaseActivity;
import com.xuan.qingya.R;


public class SearchActivity extends BaseActivity {

    private EditText searchInput;
    private ImageButton clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        isNotTransparentStatusBar();

        init();
        initListeners(clearButton);
    }

    private void init() {
        searchInput = $(R.id.search_input);
        clearButton = $(R.id.clearInput);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchInput.requestFocus();
        searchInput.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(searchInput, InputMethodManager.SHOW_IMPLICIT);
            }
        }, 300);
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clearInput:
                searchInput.getEditableText().clear();
                break;
        }
    }


}
