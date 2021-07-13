package com.example.community.controller;

import com.example.community.Mapper.CommentMapper;
import com.example.community.Model.Comment;
import com.example.community.Model.User;
import com.example.community.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
public class ConmentController {
    @Autowired
     private CommentMapper commentMapper;
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
   public Object post(@RequestBody CommentDTO commentDTO,
                      HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){

        }
        Comment comment=new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(1);
        comment.setLikeCount(0L);
        commentMapper.insert(comment);
        Map objectMap=new HashMap();
        objectMap.put("message", "success");
        return objectMap;
   }
}
