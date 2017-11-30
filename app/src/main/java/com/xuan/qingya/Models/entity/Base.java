package com.xuan.qingya.Models.entity;

/**
 * Created by zhouzhixuan on 2017/11/29.
 */

public class Base {
    private int id = 0;
    private int type = 0;
    private int love = 0;
    private int subType;
    private boolean isLoved = false;

    public int getSubType() {
        return subType;
    }

    public void setSubType(int subType) {
        this.subType = subType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
