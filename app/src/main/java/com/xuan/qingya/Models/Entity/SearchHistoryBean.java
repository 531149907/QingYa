package com.xuan.qingya.Models.Entity;

/**
 * Created by zhouzhixuan on 2017/9/12.
 */

public class SearchHistoryBean extends BaseBean {
    private int id = 0;
    private boolean isHistoryItem = true;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isHistoryItem() {
        return isHistoryItem;
    }

    public void setHistoryItem(boolean historyItem) {
        isHistoryItem = historyItem;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
