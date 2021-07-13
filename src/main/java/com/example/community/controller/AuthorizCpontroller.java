package com.example.community.controller;

import com.example.community.Mapper.UserMapper;
import com.example.community.Model.User;
import com.example.community.Provider.GithubProvider;
import com.example.community.Service.Userservice;
import com.example.community.dto.AccessTokenDTO;
import com.example.community.dto.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

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
    @Autowired
     private Userservice userservice;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse httpServletResponse) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientid);
        accessTokenDTO.setClient_secret(clientsecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(clienturi);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null) {
//      如果成功设置session cookie
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setAvatarUrl(githubUser.getAvatar_url());
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            userservice.createorupdate(user);
            Cookie cookie=new Cookie("token", token);
            httpServletResponse.addCookie(cookie);
            return "redirect:/";
        } else {
//      登陆失败重新登陆
            return "redirect:/";
        }

    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse){
        httpServletRequest.getSession().removeAttribute("user");
        Cookie cookie=new Cookie("token", null);
        cookie.setMaxAge(0);
        httpServletResponse.addCookie(cookie);
        return "redirect:/";
    }

}
