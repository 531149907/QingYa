package com.xuan.qingya.Core.net;

import com.xuan.qingya.Models.api.SpringAPIs;

/**
 * Created by zhouzhixuan on 2017/11/29.
 */

public class APIService {
    private static SpringAPIs service = RetrofitGenerator.createService(SpringAPIs.class);

    private APIService() {

    }

    public static SpringAPIs getService() {
        return service;
    }
}
