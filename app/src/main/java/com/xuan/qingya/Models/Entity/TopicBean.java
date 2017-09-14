package com.xuan.qingya.Models.Entity;

import com.xuan.qingya.Common.Constant;

/**
 * Created by zhouzhixuan on 2017/8/27.
 */

public class TopicBean extends BaseBean {
    private int id = Constant.CONTENT_UNDEFINED;
    private int type = Constant.CONTENT_DISCOVER_TOPIC;
    private String title = null;
    //private String cover;
    private int cover = Constant.CONTENT_UNDEFINED;
    private String author = null;
    private String date = null;
    private int love = Constant.CONTENT_UNDEFINED;
    private String content = null;
    private int article1 = Constant.CONTENT_UNDEFINED;
    private int article2 = Constant.CONTENT_UNDEFINED;
    private int article3 = Constant.CONTENT_UNDEFINED;
    private int article4 = Constant.CONTENT_UNDEFINED;
    private int article5 = Constant.CONTENT_UNDEFINED;
    private int article6 = Constant.CONTENT_UNDEFINED;
    private int article7 = Constant.CONTENT_UNDEFINED;
    private int article8 = Constant.CONTENT_UNDEFINED;
    private int article9 = Constant.CONTENT_UNDEFINED;
    private int article10 = Constant.CONTENT_UNDEFINED;
    private boolean isLoved = false;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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

    public int getCover() {
        return cover;
    }

    public void setCover(int cover) {
        this.cover = cover;
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

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getArticle1() {
        return article1;
    }

    public void setArticle1(int article1) {
        this.article1 = article1;
    }

    public int getArticle2() {
        return article2;
    }

    public void setArticle2(int article2) {
        this.article2 = article2;
    }

    public int getArticle3() {
        return article3;
    }

    public void setArticle3(int article3) {
        this.article3 = article3;
    }

    public int getArticle4() {
        return article4;
    }

    public void setArticle4(int article4) {
        this.article4 = article4;
    }

    public int getArticle5() {
        return article5;
    }

    public void setArticle5(int article5) {
        this.article5 = article5;
    }

    public int getArticle6() {
        return article6;
    }

    public void setArticle6(int article6) {
        this.article6 = article6;
    }

    public int getArticle7() {
        return article7;
    }

    public void setArticle7(int article7) {
        this.article7 = article7;
    }

    public int getArticle8() {
        return article8;
    }

    public void setArticle8(int article8) {
        this.article8 = article8;
    }

    public int getArticle9() {
        return article9;
    }

    public void setArticle9(int article9) {
        this.article9 = article9;
    }

    public int getArticle10() {
        return article10;
    }

    public void setArticle10(int article10) {
        this.article10 = article10;
    }

    public boolean isLoved() {
        return isLoved;
    }

    public void setLoved(boolean loved) {
        isLoved = loved;
    }
}
