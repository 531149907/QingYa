package com.xuan.qingya.Modules.Settings.Account.Signature;

/**
 * Created by zhouzhixuan on 2017/9/8.
 */

public class SignaturePresenter implements SignatureContract.SignaturePresenter {

    private SignatureContract.SignatureView view;

    public SignaturePresenter(SignatureContract.SignatureView view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void init() {

    }
}
