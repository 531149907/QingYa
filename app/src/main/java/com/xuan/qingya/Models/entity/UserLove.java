package com.xuan.qingya.Models.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhouzhixuan on 2017/11/29.
 */

public class UserLove implements Parcelable {
    private int id;
    private int userId;
    private int type;
    private int contentId;
    private String date;

    public UserLove(int userId, int type, int contentId, String date) {
        this.userId = userId;
        this.type = type;
        this.contentId = contentId;
        this.date = date;
    }

    protected UserLove(Parcel in) {
        id = in.readInt();
        userId = in.readInt();
        type = in.readInt();
        contentId = in.readInt();
        date = in.readString();
    }

    public static final Creator<UserLove> CREATOR = new Creator<UserLove>() {
        @Override
        public UserLove createFromParcel(Parcel in) {
            return new UserLove(in);
        }

        @Override
        public UserLove[] newArray(int size) {
            return new UserLove[size];
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(userId);
        parcel.writeInt(type);
        parcel.writeInt(contentId);
        parcel.writeString(date);
    }
}
