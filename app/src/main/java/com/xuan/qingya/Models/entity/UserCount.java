package com.xuan.qingya.Models.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhouzhixuan on 2017/11/29.
 */

public class UserCount implements Parcelable {
    private int id;
    private int userId;
    private int articleId;
    private int interviewId;
    private int readTime;

    public UserCount(int userId, int articleId, int interviewId, int readTime) {
        this.userId = userId;
        this.articleId = articleId;
        this.interviewId = interviewId;
        this.readTime = readTime;
    }

    protected UserCount(Parcel in) {
        id = in.readInt();
        userId = in.readInt();
        articleId = in.readInt();
        interviewId = in.readInt();
        readTime = in.readInt();
    }

    public static final Creator<UserCount> CREATOR = new Creator<UserCount>() {
        @Override
        public UserCount createFromParcel(Parcel in) {
            return new UserCount(in);
        }

        @Override
        public UserCount[] newArray(int size) {
            return new UserCount[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
    }

    public int getReadTime() {
        return readTime;
    }

    public void setReadTime(int readTime) {
        this.readTime = readTime;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(userId);
        parcel.writeInt(getArticleId());
        parcel.writeInt(interviewId);
        parcel.writeInt(readTime);
    }
}
