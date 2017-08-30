package com.xuan.qingya.Models.Entity;

/**
 * Created by zhouzhixuan on 2017/8/27.
 */

public class BannerBean {
    private int id;
    //private String cover;
    private int cover;
    private String add_date;
    private String link_to;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCover() {
        return cover;
    }

    public void setCover(int cover) {
        this.cover = cover;
    }

    public String getAdd_date() {
        return add_date;
    }

    public void setAdd_date(String add_date) {
        this.add_date = add_date;
    }

    public String getLink_to() {
        return link_to;
    }

    public void setLink_to(String link_to) {
        this.link_to = link_to;
    }
}
