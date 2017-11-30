package com.xuan.qingya.Modules.Settings.General.Feedback;

import com.xuan.qingya.Common.CacheKeys;
import com.xuan.qingya.Core.base.BasePresenter;
import com.xuan.qingya.Core.net.APIService;
import com.xuan.qingya.Models.entity.Feedback;
import com.xuan.qingya.Models.entity.User;
import com.xuan.qingya.Modules.Startup.Constant;
import com.xuan.qingya.Utils.CacheUtil;
import com.xuan.qingya.Utils.DateUtil;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhouzhixuan on 2017/10/18.
 */

public class FeedbackPresenter extends BasePresenter<ViewContract> {
    private static final int MAX_FEEDBACK_LENGTH = 200;

    public boolean sendFeedback(String content) {
        boolean checkResult = checkIfCorrect(Constant.INPUT_FEEDBACK, content);

        if (checkResult) {
            User user = (User) CacheUtil.get(getContext()).getAsObject(CacheKeys.USER_ENTITY);
            Feedback feedback = new Feedback(user.getId(), DateUtil.getDate(), content);
            APIService.getService().sentFeedback(feedback).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (isActivityAlive()) {
                        getMvpView().cleanInput();
                        getMvpView().showToast("发送成功,感谢你的反馈");
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }

        return checkResult;
    }

    public boolean checkIfCorrect(int type, String... values) {
        if (isActivityAlive()) {
            if (values[0].equals("") || values[0] == null) {
                getMvpView().setErrorMessage(type, "不能为空");
                return false;
            } else {
                switch (type) {
                    case Constant.INPUT_FEEDBACK:
                        if (values[0].length() > MAX_FEEDBACK_LENGTH) {
                            getMvpView().setErrorMessage(type, "最多200字");
                            return false;
                        }
                        break;
                }
            }
            getMvpView().setErrorMessage(type, null);
            return true;
        }
        return false;
    }
}
