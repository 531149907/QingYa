package com.xuan.qingya.Models.Entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhouzhixuan on 2017/8/27.
 */

public class InterviewBean implements Parcelable {
    public static final Parcelable.Creator<InterviewBean> CREATOR = new Creator<InterviewBean>() {
        @Override
        public InterviewBean createFromParcel(Parcel parcel) {
            InterviewBean bean = new InterviewBean();
            bean.setId(parcel.readInt());
            bean.setType(parcel.readInt());
            bean.setTitle(parcel.readString());
            bean.setAuthor(parcel.readString());
            bean.setDate(parcel.readString());
            bean.setCover(parcel.readInt());
            bean.setContent(parcel.readString());
            bean.setLove(parcel.readInt());
            bean.setLoved(parcel.readByte() == 1);
            return bean;
        }

        @Override
        public InterviewBean[] newArray(int i) {
            return new InterviewBean[i];
        }
    };
    private int id = 0;
    private int type = 0;
    private String title = null;
    private String author = null;
    private String date = null;
    private int cover = 0;
    private String content = null;
    private int love = 0;
    private boolean isLoved = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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

    public int getCover() {
        return cover;
    }

    public void setCover(int cover) {
        this.cover = cover;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public boolean isLoved() {
        return isLoved;
    }

    public void setLoved(boolean loved) {
        isLoved = loved;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(type);
        parcel.writeString(title);
        parcel.writeString(author);
        parcel.writeString(date);
        parcel.writeInt(cover);
        parcel.writeString(content);
        parcel.writeInt(love);
        parcel.writeByte((byte) (isLoved ? 1 : 0));
    }
}
