package com.example.community.controller;

import com.example.community.Provider.GithubProvider;
import com.example.community.dto.AccessTokenDTO;
import com.example.community.dto.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizCpontroller {
    @Autowired
    private GithubProvider githubProvider;
    @GetMapping("/callback")
public String callback(@RequestParam(name="code") String code,
                       @RequestParam(name="state") String state){
    AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
    accessTokenDTO.setClient_id("39aa3b52606aa47cddeb");
    accessTokenDTO.setClient_secret("a1e9431de891cee3c1918c62dbd4acfb04192d43");
    accessTokenDTO.setCode(code);
    accessTokenDTO.setState(state);
    accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
    String accessToken = githubProvider.getAccessToken(accessTokenDTO);
    GithubUser user = githubProvider.getUser(accessToken);
    System.out.println(user.getName());
    return "index";
}
}
