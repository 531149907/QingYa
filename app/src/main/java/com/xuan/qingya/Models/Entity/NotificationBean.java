package com.xuan.qingya.Models.Entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhouzhixuan on 2017/9/4.
 */

public class NotificationBean extends BaseBean implements Parcelable {
    private int id = 0;
    private String title = null;
    private String date = null;
    private String content = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(date);
        parcel.writeString(content);
    }

    public static final Parcelable.Creator<NotificationBean> CREATOR = new Creator<NotificationBean>() {
        @Override
        public NotificationBean createFromParcel(Parcel parcel) {
            NotificationBean bean = new NotificationBean();
            bean.setId(parcel.readInt());
            bean.setTitle(parcel.readString());
            bean.setDate(parcel.readString());
            bean.setContent(parcel.readString());
            return bean;
        }

        @Override
        public NotificationBean[] newArray(int i) {
            return new NotificationBean[i];
        }
    };
}
