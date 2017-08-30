package com.xuan.qingya.Modules.Main.Interview;

import android.os.Bundle;

import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Models.Entity.InterviewBean;
import com.xuan.qingya.Modules.Interview.InterviewDetailActivity;
import com.xuan.qingya.Modules.Main.MainActivity;
import com.xuan.qingya.Modules.Main.MainContract;
import com.xuan.qingya.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouzhixuan on 2017/8/27.
 */

public class InterviewPresenter implements MainContract.InterviewPresenter {

    private MainContract.InterviewView view;

    public InterviewPresenter(MainContract.InterviewView view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void onListItemClick(InterviewBean interviewBean, Bundle bundle) {
        Bundle newBundle = new Bundle();
        newBundle.putParcelable("bean", interviewBean);
        view.startActivity(InterviewDetailActivity.class, null, newBundle, null);
    }

    @Override
    public List<InterviewBean> getListData() {
        List<InterviewBean> list = new ArrayList<>();
        InterviewBean bean = new InterviewBean();

        bean.setId(0);
        bean.setType(Constant.CONTENT_INTERVIEW_IMAGE);
        bean.setTitle("通过观察别人的眼神来判断其精神状态靠谱吗？");
        bean.setAuthor("文 / 别人的佩佩");
        bean.setCover(R.drawable.a2);
        bean.setContent("CONTENT FOR INTERVIEW ID: "+bean.getId()+" TYPE: "+ bean.getType());
        bean.setLoved(false);
        bean.setLove(657);
        list.add(bean);

        bean = new InterviewBean();
        bean.setId(1);
        bean.setType(Constant.CONTENT_INTERVIEW_IMAGE);
        bean.setTitle("能否通过阅读找到一个合适的灵魂伴侣？");
        bean.setAuthor("文 / 丹丹宁");
        bean.setCover(R.drawable.a7);
        bean.setContent("CONTENT FOR INTERVIEW ID: "+bean.getId()+" TYPE: "+ bean.getType());
        bean.setLove(24);
        list.add(bean);

        bean = new InterviewBean();
        bean.setId(2);
        bean.setType(Constant.CONTENT_INTERVIEW_VIDEO);
        bean.setTitle("走进思维深处的感应？");
        bean.setAuthor("文 / 金一");
        bean.setCover(R.drawable.a14);
        bean.setContent("CONTENT FOR INTERVIEW ID: "+bean.getId()+" TYPE: "+ bean.getType());
        bean.setLove(125);
        list.add(bean);

        bean = new InterviewBean();
        bean.setId(2);
        bean.setType(Constant.CONTENT_INTERVIEW_VIDEO);
        bean.setTitle("来自末代皇帝的清华事迹");
        bean.setAuthor("文 / 我爱哈士奇");
        bean.setCover(R.drawable.a19);
        bean.setContent("CONTENT FOR INTERVIEW ID: "+bean.getId()+" TYPE: "+ bean.getType());
        bean.setLove(39);
        list.add(bean);

        return list;
    }

    @Override
    public int onLoveButtonClick(InterviewBean bean, boolean setLove) {
        if(setLove){
            bean.setLove(bean.getLove()+1);

        }else{
            bean.setLove(bean.getLove()-1);
        }
        bean.setLoved(setLove);
        return bean.getLove();
    }

    @Override
    public void retainNestedScrollViewPosition() {
        ((MainActivity)((InterviewFragment)view).getActivity()).setScrollViewPosition(1,1);
    }
}
