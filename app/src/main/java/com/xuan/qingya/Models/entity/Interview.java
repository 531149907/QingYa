package com.xuan.qingya.Models.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhouzhixuan on 2017/11/29.
 */

public class Interview extends Base implements Parcelable {
    private String title;
    private String author;
    private String date;
    private String content;
    private String coverContent;

    public Interview(int id, String title, String author, String date, String content, String coverContent, int subType, int love) {
        setId(id);
        this.title = title;
        this.author = author;
        this.date = date;
        this.content = content;
        this.coverContent = coverContent;
        setSubType(subType);
        setLove(love);
    }

    protected Interview(Parcel in) {
        title = in.readString();
        author = in.readString();
        date = in.readString();
        content = in.readString();
        coverContent = in.readString();
        setId(in.readInt());
        setType(in.readInt());
        setLove(in.readInt());
        setLoved(in.readByte() == 1);
        setSubType(in.readInt());
    }

    public static final Creator<Interview> CREATOR = new Creator<Interview>() {
        @Override
        public Interview createFromParcel(Parcel in) {
            return new Interview(in);
        }

        @Override
        public Interview[] newArray(int size) {
            return new Interview[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getCoverContent() {
        return coverContent;
    }

    public void setCoverContent(String coverContent) {
        this.coverContent = coverContent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(author);
        parcel.writeString(date);
        parcel.writeString(content);
        parcel.writeString(coverContent);
        parcel.writeInt(getId());
        parcel.writeInt(getType());
        parcel.writeInt(getLove());
        parcel.writeByte((byte) (isLoved() ? 1 : 0));
        parcel.writeInt(getSubType());
    }
}
