package com.xuan.qingya.Models.entity;

/**
 * Created by zhouzhixuan on 2017/9/12.
 */

public class SearchHistory extends Base {
    private int id = 0;
    private boolean isHistoryItem = true;
    private String content;

    public SearchHistory(String content) {
        this.content = content;
    }

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
