package com.xuan.qingya.Models.Entity;

public class ArticleBean {
    private int id = 0;
    private int type = 0;
    private String title = null;
    private String date = null;
    private int love = 0;
    //private String cover_img = null;
    private int cover_img = 0;
    private String author = null;
    private String content = null;
    private int photo_id = 0;
    private String ask_author = null;
    private String ask_content = null;
    private String answer_author = null;
    private String answer_content = null;
    private boolean isLoved = false;

    public int getCover_img() {
        return cover_img;
    }

    public void setCover_img(int cover_img) {
        this.cover_img = cover_img;
    }

    public boolean isLoved() {
        return isLoved;
    }

    public void setLoved(boolean loved) {
        isLoved = loved;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }

//    public String getCover_img() {
//        return cover_img;
//    }
//
//    public void setCover_img(String cover_img) {
//        this.cover_img = cover_img;
//    }

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

    public int getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(int photo_id) {
        this.photo_id = photo_id;
    }

    public String getAsk_author() {
        return ask_author;
    }

    public void setAsk_author(String ask_author) {
        this.ask_author = ask_author;
    }

    public String getAsk_content() {
        return ask_content;
    }

    public void setAsk_content(String ask_content) {
        this.ask_content = ask_content;
    }

    public String getAnswer_author() {
        return answer_author;
    }

    public void setAnswer_author(String answer_author) {
        this.answer_author = answer_author;
    }

    public String getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }
}
