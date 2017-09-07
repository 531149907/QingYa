package com.xuan.qingya.Common;

/**
 * Created by zhouzhixuan on 2017/8/21.
 */

public final class Constant {

    //Network config
    public static final String BASE_URL = "http://192.168.0.136:8080/";

    //Content type
    public static final int CONTENT_UNDEFINED = -1;
    public static final int CONTENT_INTERVIEW_IMAGE = 0;
    public static final int CONTENT_INTERVIEW_VIDEO = 1;
    public static final int CONTENT_DISCOVER_TOPIC = 2;
    public static final int CONTENT_DISCOVER_ARTICLE_READ = 3;
    public static final int CONTENT_DISCOVER_ARTICLE_IMAGE = 4;
    public static final int CONTENT_DISCOVER_ARTICLE_POEM = 5;
    public static final int CONTENT_DISCOVER_PHOTOGRAPHY = 6;
    public static final int CONTENT_DISCOVER_MUSIC = 7;
    public static final int CONTENT_DISCOVER_QUESTION = 8;
    public static final int CONTENT_DISCOVER_MOVIE = 9;

    //Simplify content type
    public static final int SIMPLIFY_CONTENT_TYPE_ARTICLE = 0;
    public static final int SIMPLIFY_CONTENT_TYPE_PHOTOGRAPHY = 1;
    public static final int SIMPLIFY_CONTENT_TYPE_MUSIC = 2;
    public static final int SIMPLIFY_CONTENT_TYPE_MOVIE = 3;
    public static final int SIMPLIFY_CONTENT_TYPE_QUESTION = 4;

    //Entry type code
    public static final int FRAGMENT_NOTIFICATION_LIST = 0;
    public static final int FRAGMENT_NOTIFICATION_DETAIL = 1;
    public static final int FRAGMENT_COLLECTION = 2;
    public static final int FRAGMENT_PLAYHISTORY = 3;
}
