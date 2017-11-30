package com.xuan.qingya.Models.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhouzhixuan on 2017/11/29.
 */

public class Banner implements Parcelable {
    private int id;
    private String src;
    private String addDate;
    private String linkTo;
    private String comment;

    public Banner(int id, String src, String addDate, String linkTo, String comment) {
        this.id = id;
        this.src = src;
        this.addDate = addDate;
        this.linkTo = linkTo;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getLinkTo() {
        return linkTo;
    }

    public void setLinkTo(String linkTo) {
        this.linkTo = linkTo;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public static Creator<Banner> getCREATOR() {
        return CREATOR;
    }

    protected Banner(Parcel in) {
        id = in.readInt();
        src = in.readString();
        addDate = in.readString();
        linkTo = in.readString();
        comment = in.readString();
    }

    public static final Creator<Banner> CREATOR = new Creator<Banner>() {
        @Override
        public Banner createFromParcel(Parcel in) {
            return new Banner(in);
        }

        @Override
        public Banner[] newArray(int size) {
            return new Banner[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(src);
        parcel.writeString(addDate);
        parcel.writeString(linkTo);
        parcel.writeString(comment);
    }
}
