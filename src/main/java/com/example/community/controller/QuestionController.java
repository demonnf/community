package com.example.community.controller;

import com.example.community.Enum.CommentTypeEnum;
import com.example.community.Service.Commentservice;
import com.example.community.Service.QuestionService;
import com.example.community.dto.QuestionDTO;
import com.example.community.dto.ResoultEXDTO;
import com.example.community.exception.CustomErrorCode;
import com.example.community.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private Commentservice commentservice;

    @GetMapping("/question/{id}")
    public String Question(@PathVariable(name = "id" ) String id,
                           Model model) {
        Long questionId = null;
        try {
            questionId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new CustomException(CustomErrorCode.INVALID_INPUT);
        }
        QuestionDTO questionDTO = questionService.findbyid(questionId);
        questionService.incview(questionId);
       List<ResoultEXDTO> resultList= commentservice.SelectComment(questionId, CommentTypeEnum.QUESTION);
       model.addAttribute("resultlist",resultList);
        model.addAttribute("questionDTO",questionDTO);
        return "Question";
    }
}
