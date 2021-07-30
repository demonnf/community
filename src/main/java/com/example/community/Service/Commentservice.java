package com.example.community.Service;
import com.example.community.Enum.CommentTypeEnum;
import com.example.community.Mapper.CommentMapper;
import com.example.community.Mapper.QuestionEXTMapper;
import com.example.community.Mapper.QuestionMapper;
import com.example.community.Mapper.UserMapper;
import com.example.community.Model.*;
import com.example.community.dto.ResoultEXDTO;
import com.example.community.exception.CustomErrorCode;
import com.example.community.exception.CustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class Commentservice {
    @Autowired
    private  CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionEXTMapper questionEXTMapper;
    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void insert(Comment comment) {
        if(comment.getParentId()==null||comment.getParentId()==0){
           throw new CustomException(CustomErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType()==null||!CommentTypeEnum.isexit(comment.getType())){
            throw new CustomException(CustomErrorCode.TYPE_PARAM_WRONG);
        }
        if(comment.getType()==CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment comment1 = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(comment1==null){
                throw new CustomException(CustomErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        }else{
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if(question==null){
                throw  new CustomException(CustomErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionEXTMapper.incCommentcount(question);


        }

}

    public List<ResoultEXDTO> SelectComment(Long id, CommentTypeEnum type) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);

        if (comments.size() == 0) {
            return new ArrayList<>();
        }
        // 获取去重的评论人
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList();
        userIds.addAll(commentators);


        // 获取评论人并转换为 Map
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));


        // 转换 comment 为 commentDTO
        List<ResoultEXDTO>ResoultEXDTOS = comments.stream().map(comment -> {
            ResoultEXDTO ResoultEXDTO = new ResoultEXDTO();
            BeanUtils.copyProperties(comment, ResoultEXDTO);
            ResoultEXDTO.setUser(userMap.get(comment.getCommentator()));
            return ResoultEXDTO;
        }).collect(Collectors.toList());

        return ResoultEXDTOS;
    }
}
