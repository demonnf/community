package com.example.community.Service;

import com.example.community.Mapper.CommentMapper;
import com.example.community.Model.Comment;
import com.example.community.dto.ResultDTO;
import com.example.community.exception.CustomErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Commentservice {
    @Autowired
    CommentMapper commentMapper;
    public ResultDTO insert(Comment comment) {
        if(comment.getParentId()==null||comment.getParentId()==0){
            return ResultDTO.Errorof(CustomErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        commentMapper.insert(comment);
        return ResultDTO.ofok();
    }
}
