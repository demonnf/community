package com.example.community.controller;

import com.example.community.Provider.GithubProvider;
import com.example.community.dto.AccessTokenDTO;
import com.example.community.dto.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizCpontroller {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientid;
    @Value("${github.client.secret}")
    private String clientsecret;
    @Value("${github.redirect.uri}")
    private String clienturi;
    @GetMapping("/callback")
public String callback(@RequestParam(name="code") String code,
                       @RequestParam(name="state") String state){
    AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
    accessTokenDTO.setClient_id(clientid);
    accessTokenDTO.setClient_secret(clientsecret);
    accessTokenDTO.setCode(code);
    accessTokenDTO.setState(state);
    accessTokenDTO.setRedirect_uri(clienturi);
    String accessToken = githubProvider.getAccessToken(accessTokenDTO);
    GithubUser user = githubProvider.getUser(accessToken);
    System.out.println(user.getName());
    return "index";
}
}
