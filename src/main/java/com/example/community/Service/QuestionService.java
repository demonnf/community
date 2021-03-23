package com.example.community.Service;

import com.example.community.Mapper.QuestionMapper;
import com.example.community.Mapper.UserMapper;
import com.example.community.Model.Question;
import com.example.community.Model.User;
import com.example.community.dto.PaginationDTO;
import com.example.community.dto.QuestionDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalcount= questionMapper.count();
        paginationDTO.setpagination(totalcount,page,size);
//        锁定页面
        if(page<1){
            page=1;
        }
        if(page>paginationDTO.getTotalpage()){
            page=paginationDTO.getTotalpage();
        }
        Integer offset = size * (page - 1);
        List<Question> list = questionMapper.findlist(offset, size);

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : list) {
            QuestionDTO questionDTO = new QuestionDTO();
            User user = userMapper.findbyid(question.getCreator());
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;

    }
}
