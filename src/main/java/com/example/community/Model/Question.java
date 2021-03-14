package com.example.community.Model;

import lombok.Data;

import java.io.Serializable;
@Data
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

}