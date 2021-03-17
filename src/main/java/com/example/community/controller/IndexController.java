package com.example.community.controller;

import com.example.community.Mapper.QuestionMapper;
import com.example.community.Mapper.UserMapper;
import com.example.community.Model.Question;
import com.example.community.Model.User;
import com.example.community.Service.QuestionService;
import com.example.community.dto.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionService questionService;
    @GetMapping("/")
    public String index(HttpServletRequest httpServletRequest,
                        Model model){
        Cookie[] cookies = httpServletRequest.getCookies();
        if(cookies!=null&&cookies.length!=0){
        for (Cookie cookie : cookies) {
          if(cookie.getName().equals("token")){
              String token = cookie.getValue();
              User user = userMapper.findbytoken(token);
              if (user!=null){
               httpServletRequest.getSession().setAttribute("user", user);
              }
              break;
          }
        }}
        List<QuestionDTO> questionList=questionService.list();
        model.addAttribute("questions",questionList);
        return "index";
    }
}
