package com.android.lostpersonproject.bean;

import java.io.Serializable;

public class SearchBean implements Serializable {

    private String id;
    private String name;
    private String like;
    private String url;

    public SearchBean() {
    }

    public SearchBean(String id, String name, String like, String url) {
        this.id = id;
        this.name = name;
        this.like = like;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "SearchBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", like='" + like + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
