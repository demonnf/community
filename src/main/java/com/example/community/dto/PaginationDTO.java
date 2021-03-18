package com.example.community.dto;

import lombok.Data;

import java.util.List;
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showprevious;
    private boolean showfirstpage;
    private boolean shownextpage;
    private boolean showendpage;
    private Integer page;
    private List<Integer> pages;


    public void setpagination(Integer totalcount, Integer page, Integer size) {
    }
}
