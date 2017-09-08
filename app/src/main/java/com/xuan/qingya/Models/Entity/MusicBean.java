package com.xuan.qingya.Models.Entity;

/**
 * Created by zhouzhixuan on 2017/9/7.
 */

public class MusicBean {
    private int id = 0;
    private String author = null;
    private String title = null;
    private String time = null;
    private int status = 0;

    //status = 0 playing, 1 paused


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
