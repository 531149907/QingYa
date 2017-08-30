package com.xuan.qingya.Modules.Interview;

/**
 * Created by zhouzhixuan on 2017/8/29.
 */

public class InterviewDetailPresenter implements InterviewDetailContract.InterviewDetailPresenter {

    private InterviewDetailContract.InterviewDetailView view;

    public InterviewDetailPresenter(InterviewDetailContract.InterviewDetailView view) {
        this.view = view;
    }

    @Override
    public void init() {

    }
}
