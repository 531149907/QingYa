package com.xuan.qingya.Models.Entity;

/**
 * Created by zhouzhixuan on 2017/8/30.
 */

public class DiscoverListBean {
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
}
