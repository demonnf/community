package com.example.community.dto;


import com.example.community.Model.User;
import lombok.Data;

@Data
public class ResoultEXDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private Integer commentCount;
    private String content;
    private User user;
}
