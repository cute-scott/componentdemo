package com.qinyaoz.componentdemo;

/**
 * Created by qinyaoz on 2019/5/5
 * Describe:
 **/
public class ItemData {
    private String routerUrl;
    private String title;

    public ItemData(String url, String t) {
        this.routerUrl = url;
        this.title = t;
    }

    public String getRouterUrl() {
        return routerUrl;
    }

    public void setRouterUrl(String routerUrl) {
        this.routerUrl = routerUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
