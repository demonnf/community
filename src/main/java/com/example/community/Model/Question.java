package com.example.community.Model;

import java.io.Serializable;

public class Question implements Serializable {
    private Integer id;
    private String title;
    private String description;
    private long gmtcreate;
    private long gmtmodified;
    private Integer creator;
    private Integer comment_count;
    private Integer view_count;
    private Integer like_count;
    private String tag;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getGmtcreate() {
        return gmtcreate;
    }

    public void setGmtcreate(long gmtcreate) {
        this.gmtcreate = gmtcreate;
    }

    public long getGmtmodified() {
        return gmtmodified;
    }

    public void setGmtmodified(long gmtmodified) {
        this.gmtmodified = gmtmodified;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}