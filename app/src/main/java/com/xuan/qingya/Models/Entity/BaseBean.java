package com.xuan.qingya.Models.Entity;

/**
 * Created by zhouzhixuan on 2017/9/13.
 */

public class BaseBean {
    private int type = 0;
    private int love = 0;
    private boolean isLoved = false;

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
