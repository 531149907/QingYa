package com.xuan.qingya.Models.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhouzhixuan on 2017/9/27.
 */

public class ItemViewInfo implements Parcelable {
    int height = 0;
    int width = 0;
    int X = 0;
    int Y = 0;
    float translationX = 0;
    float translationY = 0;
    String tag;

    public ItemViewInfo() {

    }

    protected ItemViewInfo(Parcel in) {
        height = in.readInt();
        width = in.readInt();
        X = in.readInt();
        Y = in.readInt();
        translationX = in.readFloat();
        translationY = in.readFloat();
        tag = in.readString();
    }

    public static final Creator<ItemViewInfo> CREATOR = new Creator<ItemViewInfo>() {
        @Override
        public ItemViewInfo createFromParcel(Parcel in) {
            return new ItemViewInfo(in);
        }

        @Override
        public ItemViewInfo[] newArray(int size) {
            return new ItemViewInfo[size];
        }
    };

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getY() {
        return Y;
    }

    public int getX() {
        return X;
    }

    public float getTranslationX() {
        return translationX;
    }

    public float getTranslationY() {
        return translationY;
    }

    public String getTag() {
        return tag;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setLocation(int[] location) {
        this.X = location[0];
        this.Y = location[1];
    }

    public void setTranslationXY(float translationX, float translationY) {
        this.translationX = translationX;
        this.translationY = translationY;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(height);
        parcel.writeInt(width);
        parcel.writeInt(X);
        parcel.writeInt(Y);
        parcel.writeFloat(translationX);
        parcel.writeFloat(translationY);
        parcel.writeString(tag);
    }
}
