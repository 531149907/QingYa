package com.xuan.qingya.Models.api;

import com.xuan.qingya.Models.entity.Feedback;
import com.xuan.qingya.Models.entity.User;
import com.xuan.qingya.Models.entity.UserLove;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by zhouzhixuan on 2017/11/29.
 */

public interface SpringAPIs {
    @GET("api/info/agreement")
    Call<ResponseBody> getAgreement();

    @GET("api/article/{id}")
    Call<ResponseBody> getArticle(@Path("id") int id);

    @GET("api/article/")
    Call<ResponseBody> getArticles(@QueryMap Map<String, Object> params);

    @GET("api/article/all")
    Call<ResponseBody> getAllArticles();

    @GET("api/banner")
    Call<ResponseBody> getBanners();

    @POST("api/feedback")
    Call<ResponseBody> sentFeedback(@Body Feedback feedback);

    @GET("api/interview")
    Call<ResponseBody> getInterviews(@Query("keyword") String keyword);

    @GET("api/interview")
    Call<ResponseBody> getInterviews();

    @GET("api/interview/{id}")
    Call<ResponseBody> getInterview(@Path("id") int id);

    @GET("api/message/{id}")
    Call<ResponseBody> getNotification(@Path("id") int id);

    @GET("api/message/")
    Call<ResponseBody> getNotifications();

    @POST("api/user/verify/email")
    Call<ResponseBody> verifyEmail(@Query("email") String email);

    @POST("api/user/verify/username")
    Call<ResponseBody> verifyUsername(@Query("username") String username);

    @POST("api/user/verify/combination")
    Call<ResponseBody> verifyCombination(@Query("email") String email, @Query("password") String password);

    @POST("api/user/login")
    Call<ResponseBody> login(@Query("user") User user);

    @POST("api/user/register")
    Call<ResponseBody> register(@Query("user") User user);

    @POST("api/user/update/password")
    Call<ResponseBody> updatePassword(@Query("id") int id, @Query("password") String password);

    @POST("api/user/update/signature")
    Call<ResponseBody> updateSignature(@Query("id") int id, @Query("signature") String signature);

    @POST("api/user/update/username")
    Call<ResponseBody> updateUsername(@Query("id") int id, @Query("username") String username);

    @DELETE("api/love/{id}")
    void deleteCollectionItem(@Path("id") int id);

    @GET("api/love")
    Call<ResponseBody> getCollections(@Query("userId") int userId);

    @POST("api/love")
    Call<ResponseBody> addNewCollectionItem(@Body UserLove userLove);
}
