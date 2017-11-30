package com.xuan.qingya.Core.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.xuan.qingya.Common.Constant.BASE_URL;

/**
 * Created by zhouzhixuan on 2017/8/19.
 */

public class RetrofitGenerator {

    private RetrofitGenerator() {
    }

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <T> T createService(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
