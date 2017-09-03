package com.xuan.qingya.Models.Entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhouzhixuan on 2017/8/30.
 */

public class DiscoverListBean implements Parcelable {
    //Use to get detailed bean
    private int id = 0;

    //Content type
    private int type = 0;

    //Simplify content type, use to get list
    private int type_main = 0;

    //Used in: "阅读"、"音乐"、"影视"
    private String author = null;
    private int cover_img = 0;

    //Used in: "摄影"
    private int photo_id = 0;
    private int love = 0;
    private boolean isLoved = false;

    //Used in: "问答"(in short version)
    private String ask_content = null;

    //Common except "摄影"
    private String title = null;

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

    public int getType_main() {
        return type_main;
    }

    public void setType_main(int type_main) {
        this.type_main = type_main;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCover_img() {
        return cover_img;
    }

    public void setCover_img(int cover_img) {
        this.cover_img = cover_img;
    }

    public int getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(int photo_id) {
        this.photo_id = photo_id;
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

    public String getAsk_content() {
        return ask_content;
    }

    public void setAsk_content(String answer_content) {
        this.ask_content = answer_content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(type);
        parcel.writeInt(type_main);
        parcel.writeString(author);
        parcel.writeInt(cover_img);
        parcel.writeInt(photo_id);
        parcel.writeInt(love);
        parcel.writeByte((byte) (isLoved ? 1 : 0));
        parcel.writeString(ask_content);
        parcel.writeString(title);
    }

    public static final Parcelable.Creator<DiscoverListBean> CREATOR = new Creator<DiscoverListBean>() {
        @Override
        public DiscoverListBean createFromParcel(Parcel parcel) {
            DiscoverListBean bean = new DiscoverListBean();
            bean.setId(parcel.readInt());
            bean.setType(parcel.readInt());
            bean.setType_main(parcel.readInt());
            bean.setAuthor(parcel.readString());
            bean.setCover_img(parcel.readInt());
            bean.setPhoto_id(parcel.readInt());
            bean.setLove(parcel.readInt());
            bean.setLoved(parcel.readByte() == 1);
            bean.setAsk_content(parcel.readString());
            bean.setTitle(parcel.readString());
            return bean;
        }

        @Override
        public DiscoverListBean[] newArray(int i) {
            return new DiscoverListBean[i];
        }
    };
}
