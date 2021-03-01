package com.example.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizCpontroller {
public String callback(@RequestParam(name="code") String code,
                       @RequestParam(name="state") String state){
    return "index";
}
}
