package com.example.community.controller;

import com.example.community.Mapper.QuestionMapper;
import com.example.community.Mapper.UserMapper;
import com.example.community.Model.Question;
import com.example.community.Model.User;
import com.example.community.Service.QuestionService;
import com.example.community.dto.QuestionDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String editor(@PathVariable(name = "id") Long id,
                         Model model) {
        QuestionDTO question = questionService.findbyid(id);
       model.addAttribute("title", question.getTitle());
       model.addAttribute("tag", question.getTag());
       model.addAttribute("description", question.getDescription());
       model.addAttribute("id", question.getId());
        return "publish";

    }

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "id",required = false) Long id,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest httpServletRequest,
            Model model
    ) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        if (title == null) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }

        User user = (User) httpServletRequest.getSession().getAttribute("user");


        if (user == null) {
            model.addAttribute("error", "用户未登陆");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setTag(tag);
        question.setDescription(description);
        question.setCreator(user.getId());
        question.setId(id);
        questionService.createorupdate(question);


        return "redirect:/";
    }
}
