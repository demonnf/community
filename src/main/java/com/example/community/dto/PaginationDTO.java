package com.example.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showprevious;
    private boolean showfirstpage;
    private boolean shownextpage;
    private boolean showendpage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalpage;


    public void setpagination(Integer totalpage, Integer page, Integer size) {

        this.page=page;
        this.totalpage=totalpage;
        pages.add(page);

//        页面集合所需要展示的页面数
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalpage) {
                pages.add(page + i);
            }
        }
//        是否展示上一页
        if (page == 1) {
            showprevious = false;
        } else {
            showprevious = true;

        }
//        是否展示下一页
        if (page == totalpage) {
            showendpage = false;
        } else {
            showendpage = true;

        }
//        是否展示第一页
        if (pages.contains(1)) {
            showfirstpage = false;
        } else {
            showfirstpage = true;

        }
        if (pages.contains(totalpage)) {
            shownextpage = false;
        } else {
            shownextpage = true;

        }
    }

}
