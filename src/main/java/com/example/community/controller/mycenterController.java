package com.example.community.controller;

import com.example.community.Mapper.UserMapper;
import com.example.community.Model.User;
import com.example.community.Service.QuestionService;
import com.example.community.dto.PaginationDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class mycenterController {
    @Autowired
    public UserMapper userMapper;
    @Autowired
    public QuestionService questionService;

    @GetMapping("/publish/{action}")
    public String PersonalMessage(
            @PathVariable(name = "action") String action,
            Model model,
            HttpServletRequest httpServletRequest,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size) {
        Cookie[] cookies = httpServletRequest.getCookies();
        User user = null;
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findbytoken(token);
                    if (user != null) {
                        httpServletRequest.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if (user == null) {
            return "redirect:/";
        }
        if ("question".equals(action)) {
            model.addAttribute("section", "question");
            model.addAttribute("sectionName", "我的问题");
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "我的回复");
        }
        PaginationDTO paginationDTO = questionService.questionlist(page, size, user.getId());
        model.addAttribute("pagination", paginationDTO);
        return "personalMessage";
    }
}
