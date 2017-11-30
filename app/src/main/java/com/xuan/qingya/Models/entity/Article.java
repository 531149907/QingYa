package com.xuan.qingya.Models.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhouzhixuan on 2017/11/29.
 */

public class Article extends Base implements Parcelable {
    private String title;
    private String date;
    private String coverImg;
    private String author;
    private String content;
    private String askAuthor;
    private String answerAuthor;
    private String askContent;
    private String answerContent;
    private String keywords;

    public Article(int id, int type, int subType, String title, String date, String coverImg, String author, String content, String askAuthor, String answerAuthor, String askContent, String answerContent, String keywords) {
        setId(id);
        setType(type);
        setSubType(subType);
        this.title = title;
        this.date = date;
        this.coverImg = coverImg;
        this.author = author;
        this.content = content;
        this.askAuthor = askAuthor;
        this.answerAuthor = answerAuthor;
        this.askContent = askContent;
        this.answerContent = answerContent;
        this.keywords = keywords;
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

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

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAskAuthor() {
        return askAuthor;
    }

    public void setAskAuthor(String askAuthor) {
        this.askAuthor = askAuthor;
    }

    public String getAnswerAuthor() {
        return answerAuthor;
    }

    public void setAnswerAuthor(String answerAuthor) {
        this.answerAuthor = answerAuthor;
    }

    public String getAskContent() {
        return askContent;
    }

    public void setAskContent(String askContent) {
        this.askContent = askContent;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    protected Article(Parcel in) {
        setSubType(in.readInt());
        title = in.readString();
        date = in.readString();
        coverImg = in.readString();
        author = in.readString();
        content = in.readString();
        askAuthor = in.readString();
        answerAuthor = in.readString();
        askContent = in.readString();
        answerContent = in.readString();
        keywords = in.readString();
        setId(in.readInt());
        setType(in.readInt());
        setLove(in.readInt());
        setLoved(in.readByte() == 1);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getSubType());
        parcel.writeString(title);
        parcel.writeString(date);
        parcel.writeString(coverImg);
        parcel.writeString(author);
        parcel.writeString(content);
        parcel.writeString(askAuthor);
        parcel.writeString(answerAuthor);
        parcel.writeString(askContent);
        parcel.writeString(answerContent);
        parcel.writeString(keywords);
        parcel.writeInt(getId());
        parcel.writeInt(getType());
        parcel.writeInt(getLove());
        parcel.writeByte((byte) (isLoved() ? 1 : 0));
    }
}
