package com.example.community.controller;

import com.example.community.Mapper.QuestionMapper;
import com.example.community.Mapper.UserMapper;
import com.example.community.Model.Question;
import com.example.community.Model.User;
import com.example.community.Service.QuestionService;
import com.example.community.dto.PaginationDTO;
import com.example.community.dto.QuestionDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    QuestionService questionService;
    @GetMapping("/")
    public String index(HttpServletRequest httpServletRequest,
                        Model model,
                        @RequestParam (value = "page",defaultValue = "1")Integer page ,
                        @RequestParam(value = "size",defaultValue = "5") Integer size){
        PaginationDTO pagination=questionService.list(page,size);
        model.addAttribute("pagination",pagination);
        return "index";
    }
}
