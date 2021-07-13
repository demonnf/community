package com.example.community.Advice;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import com.example.community.controller.QuestionController;
import com.example.community.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MycontrollerAdvice {
    @ExceptionHandler(Exception.class)
    public ModelAndView handleControllerException(HttpServletRequest request, Throwable e, Model model) {
       if (e instanceof CustomException){
           model.addAttribute("message", e.getMessage());
       }else {
           model.addAttribute("message", "Server fail");
       }

        return new ModelAndView("error");
    }
}

