package com.example.community.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private  long parentId;
    private Integer type;
    private  String content;
}
