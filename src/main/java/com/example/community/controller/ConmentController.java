package com.example.community.controller;
import com.example.community.Model.Comment;
import com.example.community.Model.User;
import com.example.community.Service.Commentservice;
import com.example.community.dto.CommentDTO;
import com.example.community.dto.ResultDTO;
import com.example.community.exception.CustomErrorCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;



@Controller
public class ConmentController {
    @Autowired
    private Commentservice commentservice;


    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
   public Object post(@RequestBody CommentDTO commentDTO,
                      HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
          return ResultDTO.Errorof(CustomErrorCode.NO_LOGIN);
        }
        if(commentDTO==null|| StringUtils.isBlank(commentDTO.getContent())){
            return  ResultDTO.Errorof(CustomErrorCode.CONTENT_IS_EMPTY);
        }
        Comment comment=new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentservice.insert(comment);
       return ResultDTO.ofok();
   }
}
