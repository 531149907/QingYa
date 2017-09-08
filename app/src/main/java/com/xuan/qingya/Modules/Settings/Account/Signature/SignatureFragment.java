package com.xuan.qingya.Modules.Settings.Account.Signature;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuan.qingya.Core.Base.BaseFragment;
import com.xuan.qingya.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignatureFragment extends BaseFragment implements SignatureContract.SignatureView {

    private SignatureContract.SignaturePresenter presenter;

    public SignatureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_signature, container, false);

        init();

        return mRootView;
    }

    @Override
    public void setPresenter(SignatureContract.SignaturePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {

    }
}
