package com.example.community.dto;

import com.example.community.Model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private long gmtcreate;
    private long gmtmodified;
    private Integer creator;
    private Integer commentcount;
    private Integer viewcount;
    private Integer likecount;
    private String tag;
    private User user;

}
