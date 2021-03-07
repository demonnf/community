package com.example.community.controller;

import com.example.community.Mapper.UserMapper;
import com.example.community.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;
    @GetMapping("/")
    public String index(HttpServletRequest httpServletRequest){
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
          if(cookie.getName().equals("token")){
              String token = cookie.getValue();
              User user = userMapper.findbytoken(token);
              if (user!=null){
               httpServletRequest.getSession().setAttribute("user", user);
              }
              break;
          }
        }

        return "index";
    }
}
